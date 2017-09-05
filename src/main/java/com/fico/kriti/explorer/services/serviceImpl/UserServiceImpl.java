package com.fico.kriti.explorer.services.serviceImpl;

import com.fico.kriti.explorer.model.User;
import com.fico.kriti.explorer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by KritiJain on 8/19/2017.
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User obj){
        userRepository.save(obj);
        return obj;
    }

    public List<User> findAllUsers(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User findUserById(long userId){
        return userRepository.findByUserId(userId);
    }
}
