package com.fico.kriti.explorer.repositories;

import com.fico.kriti.explorer.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by KritiJain on 8/16/2017.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUserId(@PathVariable("userId") long userId);
    List<User> findAll();
}
