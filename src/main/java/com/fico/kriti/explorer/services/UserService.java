package com.fico.kriti.explorer.services;

import com.fico.kriti.explorer.model.User;

import java.util.List;

/**
 * Created by KritiJain on 8/20/2017.
 */
public interface UserService {
    User create(User obj);

    List<User> displayAll();

    User displayById(long userId);
}
