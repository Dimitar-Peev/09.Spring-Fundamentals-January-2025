package com.example.likebook.service.impl;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodName;
import com.example.likebook.model.entity.Post;
import com.example.likebook.model.entity.User;
import com.example.likebook.model.service.PostServiceModel;
import com.example.likebook.model.view.HomeViewModel;
import com.example.likebook.model.view.PostViewModel;
import com.example.likebook.repository.PostRepository;
import com.example.likebook.service.MoodService;
import com.example.likebook.service.PostService;
import com.example.likebook.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final MoodService moodService;
    private final UserService userService;
    private final HttpSession httpSession;

    @Override
    public boolean add(PostServiceModel postServiceModel) {
        Mood mood = this.moodService.findByName(MoodName.valueOf(postServiceModel.getMood()));

        if (mood != null) {
            Post post = this.modelMapper.map(postServiceModel, Post.class);
            post.setMood(mood);
            post.setUser(userService.findUserById(postServiceModel.getUserId()));
            this.postRepository.save(post);
            log.info("Successfully added post.");
            return true;
        } else {
            log.info("Failed to add post.");
            return false;
        }
    }

    @Override
    public List<PostViewModel> getPostsByUser(String userId) {
        return postRepository
                .findAllByUserIdOrderByIdDesc(userId)
                .stream()
                .map(post -> {
                    PostServiceModel serviceModel = modelMapper.map(post, PostServiceModel.class);
                    PostViewModel viewModel = modelMapper.map(serviceModel, PostViewModel.class);
                    viewModel.setLikes(post.getUserLikes().size());
                    viewModel.setUserUsername(post.getUser().getUsername());
                    viewModel.setMood(post.getMood().getName().name());
                    return viewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PostViewModel> getPostsByOtherUsers(String userId) {
        return postRepository
                .findAllByUserIdNotOrderByIdDesc(userId)
                .stream()
                .map(post -> {
                    PostServiceModel serviceModel = modelMapper.map(post, PostServiceModel.class);
                    PostViewModel viewModel = modelMapper.map(serviceModel, PostViewModel.class);
                    viewModel.setLikes(post.getUserLikes().size());
                    viewModel.setUserUsername(post.getUser().getUsername());
                    viewModel.setMood(post.getMood().getName().name());
                    return viewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void likePost(String postId, String userId) {

        Post post = postRepository.findById(postId).orElse(null);

        User user = userService.findUserById(userId);

        if (post != null) {
            if (post.getUserLikes().contains(user)) {
                log.info("User already liked this post");
                return;
            }

            post.getUserLikes().add(user);
            postRepository.save(post);
        }
    }

    @Override
    public void removePost(String postId) {
        postRepository.findById(postId).ifPresent(postRepository::delete);
    }

    @Override
    public HomeViewModel getHomeViewModel() {
        String userId = httpSession.getAttribute("user_id").toString();

        HomeViewModel homeViewModel = new HomeViewModel();

        List<PostViewModel> userPosts = getPostsByUser(userId);
        List<PostViewModel> otherPosts = getPostsByOtherUsers(userId);

        homeViewModel.setUserPosts(userPosts);
        homeViewModel.setOtherPosts(otherPosts);

        return homeViewModel;
    }

}
