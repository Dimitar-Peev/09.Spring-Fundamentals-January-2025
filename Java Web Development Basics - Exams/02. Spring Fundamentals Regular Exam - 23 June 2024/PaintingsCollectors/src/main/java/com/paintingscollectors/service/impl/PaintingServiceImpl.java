package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.service.PaintingServiceModel;
import com.paintingscollectors.model.view.HomeViewModel;
import com.paintingscollectors.model.view.PaintingViewModel;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.StyleService;
import com.paintingscollectors.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public boolean add(PaintingServiceModel paintingServiceModel) {

        Style style = this.styleService.findByName(paintingServiceModel.getStyle());
        String username = httpSession.getAttribute("username").toString();
        User user = userService.findUserByUsername(username);

        if (style != null && user != null) {
            Painting painting = this.modelMapper.map(paintingServiceModel, Painting.class);
            painting.setOwner(user);
            painting.setStyle(style);
            this.paintingRepository.save(painting);
            log.info("Successfully added painting.");
            return true;
        } else {
            log.info("Failed to add painting.");
            return false;
        }

    }

    @Override
    public List<PaintingServiceModel> getMyPaintings(String username) {

        User owner = userService.findUserByUsername(username);

        return paintingRepository.findAllByOwner(owner).stream()
                .map(painting -> {
                    PaintingServiceModel paintingServiceModel = this.modelMapper.map(painting, PaintingServiceModel.class);
                    paintingServiceModel.setOwner(painting.getOwner());
                    paintingServiceModel.setStyle(painting.getStyle().getName());
                    return paintingServiceModel;
                })
                .toList();
    }

    @Override
    @Transactional
    public void remove(String paintingId, String username) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new RuntimeException("Painting not found"));

        if (!painting.getOwner().getUsername().equals(username)) {
            throw new RuntimeException("User is not the owner of this painting");
        }

        if (painting.isFavorite()) {
            throw new RuntimeException("Cannot delete painting that is in someone's favorites");
        }

        List<User> usersWhoRated = userRepository.findAllByRatedPaintingsContaining(painting);

        for (User user : usersWhoRated) {
            user.getRatedPaintings().remove(painting);
            userRepository.save(user);
        }

        painting.setOwner(null);
        painting.setStyle(null);

        paintingRepository.save(painting);

        paintingRepository.delete(painting);
        log.info("Successfully removed painting from my collection.");
    }

    @Override
    public List<PaintingServiceModel> getOtherPaintings(String username) {

        User currentUser = userService.findUserByUsername(username);

        return paintingRepository.findAllByOwnerNot(currentUser)
                .stream()
                .map(painting -> {
                    PaintingServiceModel paintingServiceModel = this.modelMapper.map(painting, PaintingServiceModel.class);
                    paintingServiceModel.setStyle(painting.getStyle().getName());
                    paintingServiceModel.setOwner(painting.getOwner());
                    return paintingServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addToFavorites(String paintingId, String username) {

        User user = userService.findUserByUsername(username);

        Painting painting = paintingRepository.findById(paintingId).orElse(null);

        if (painting != null && !user.getFavoritePaintings().contains(painting)) {
            user.getFavoritePaintings().add(painting);
            painting.setFavorite(true);
            userRepository.save(user);
            paintingRepository.save(painting);
            log.info("Successfully add painting to my favorite collection.");
        }
    }

    @Override
    public void votePainting(String paintingId, String username) {

        User user = userService.findUserByUsername(username);

        Painting painting = paintingRepository.findPaintingById(paintingId);

        if (!user.getRatedPaintings().contains(painting) && !painting.getOwner().equals(user)) {
            painting.setVotes(painting.getVotes() + 1);
            user.getRatedPaintings().add(painting);
            paintingRepository.save(painting);
            userRepository.save(user);
        }
    }

    @Override
    public List<PaintingServiceModel> getUserFavoritePaintings(String username) {

        String userId = httpSession.getAttribute("user_id").toString();

        return paintingRepository.findFavoritePaintings(userId)
                .stream()
                .map(painting -> {
                    PaintingServiceModel paintingServiceModel = this.modelMapper.map(painting, PaintingServiceModel.class);
                    paintingServiceModel.setStyle(painting.getStyle().getName());
                    paintingServiceModel.setOwner(painting.getOwner());
                    return paintingServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeFromFavorites(String paintingId, String username) {
        User user = userService.findUserByUsername(username);
        Painting painting = paintingRepository.findPaintingById(paintingId);

        Set<Painting> favoritePaintings = user.getFavoritePaintings();
        if (favoritePaintings.contains(painting)) {
            user.getFavoritePaintings().remove(painting);
            if (user.getFavoritePaintings().isEmpty()) {
                painting.setFavorite(false);
            }
        }

        userRepository.save(user);
        paintingRepository.save(painting);
        log.info("Successfully removed painting from my favorites collection.");
    }

    @Override
    public List<PaintingServiceModel> getMostRatedPaintings() {

        return paintingRepository.findTop2ByOrderByVotesDescNameAsc()
                .stream()
                .map(painting -> {
                    PaintingServiceModel paintingServiceModel = this.modelMapper.map(painting, PaintingServiceModel.class);
                    paintingServiceModel.setStyle(painting.getStyle().getName());
                    paintingServiceModel.setOwner(painting.getOwner());
                    return paintingServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HomeViewModel getHomeViewModel() {
        HomeViewModel homeViewModel = new HomeViewModel();

        String username = httpSession.getAttribute("username").toString();

        List<PaintingViewModel> myPaintings = getMyPaintings(username).stream()
                .map(this::getPaintingViewModel)
                .toList();

        List<PaintingViewModel> otherPaintings = getOtherPaintings(username)
                .stream()
                .map(this::getPaintingViewModel)
                .toList();

        List<PaintingViewModel> userFavoritePaintings = getUserFavoritePaintings(username)
                .stream()
                .map(this::getPaintingViewModel)
                .toList();

        List<PaintingViewModel> mostRatedPaintings = getMostRatedPaintings()
                .stream()
                .map(this::getPaintingViewModel)
                .toList();

        homeViewModel.setMyPaintings(myPaintings);
        homeViewModel.setOtherPaintings(otherPaintings);
        homeViewModel.setUserFavoritePaintings(userFavoritePaintings);
        homeViewModel.setMostRatedPaintings(mostRatedPaintings);

        return homeViewModel;
    }

    private PaintingViewModel getPaintingViewModel(PaintingServiceModel paintingServiceModel) {
        PaintingViewModel paint = modelMapper.map(paintingServiceModel, PaintingViewModel.class);
        paint.setOwner(paintingServiceModel.getOwner());
        paint.setStyle(paintingServiceModel.getStyle().name());
        return paint;
    }
}
