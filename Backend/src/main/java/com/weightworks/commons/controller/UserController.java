package com.weightworks.commons.controller;

import com.weightworks.commons.dao.UserDao;
import com.weightworks.commons.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.weightworks.commons.values.UrlString.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping
    public ResponseEntity<User> create (@RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById (@PathVariable Integer id) {
        return userDao.findById(id).map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.unprocessableEntity().build()
        );
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAll () {
        return ResponseEntity.ok(userDao.findAll());
    }
}
