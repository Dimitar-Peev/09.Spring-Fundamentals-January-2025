package coffeeshop.service.impl;

import coffeeshop.model.entity.User;
import coffeeshop.model.service.UserServiceModel;
import coffeeshop.model.view.UserViewModel;
import coffeeshop.repository.UserRepository;
import coffeeshop.service.UserService;
import coffeeshop.util.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        this.currentUser.login(userServiceModel);
    }

    @Override
    public void logout() {
        this.currentUser.logout();
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserViewModel> findAllUsersAndCountOfOrdersOrderByCountDesc() {
        return this.userRepository.findAllByOrdersAndOrderByCountDesc()
                .stream()
                .map(user -> {
                    UserViewModel userViewModel = new UserViewModel();
                    userViewModel.setUsername(user.getUsername());
                    userViewModel.setCountOfOrders(user.getOrders().size());
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }
}
