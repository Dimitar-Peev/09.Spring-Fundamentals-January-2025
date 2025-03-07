package com.example.likebook.service;

import com.example.likebook.model.service.PostServiceModel;
import com.example.likebook.model.view.HomeViewModel;
import com.example.likebook.model.view.PostViewModel;

import java.util.List;

public interface PostService {

    boolean add(PostServiceModel postServiceModel);

    List<PostViewModel> getPostsByUser(String userId);

    List<PostViewModel> getPostsByOtherUsers(String userId);

    void likePost(String postId, String userId);

    void removePost(String postId);

    HomeViewModel getHomeViewModel();
}
