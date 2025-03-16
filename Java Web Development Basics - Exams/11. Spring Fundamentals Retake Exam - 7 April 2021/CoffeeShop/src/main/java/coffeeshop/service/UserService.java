package coffeeshop.service;

import coffeeshop.model.entity.User;
import coffeeshop.model.service.UserServiceModel;
import coffeeshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    User findById(String id);

    List<UserViewModel> findAllUsersAndCountOfOrdersOrderByCountDesc();
}

