package com.bonappetit.service;

import com.bonappetit.model.binding.UserLoginBindingModel;
import com.bonappetit.model.binding.UserRegisterBindingModel;
import jakarta.validation.Valid;

public interface UserService {

    boolean register(@Valid UserRegisterBindingModel userRegisterBindingModel);

    boolean login(@Valid UserLoginBindingModel data);
}
