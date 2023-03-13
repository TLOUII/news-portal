package com.meiliev.rest.controller;

import com.meiliev.service.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-portal/users")
public class UserController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final com.meiliev.service.UserService userService;

    public UserController(com.meiliev.service.UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllArticles() {
        return ResponseEntity.ok(userService.getAll().orElseThrow(() -> new RuntimeException("there are no one user yet exist")));
    }

    @RequestMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        if (id > 0 && id instanceof Long) {
            try {
                return ResponseEntity.ok(userService.getById(id).orElseThrow(() -> new Throwable("not found current user from this id")));
            } catch (Throwable e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "Error in method - getUserById");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody User user) {
        if (user != null) {
            try {
                userService.createUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Throwable e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            logger.log(Level.SEVERE, "error in method Create");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> userPasswordUpdate(@RequestBody User user, @PathVariable("id") Long id) {
        if (id > 0) {
            try {
                userService.changeUser(id, user);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Throwable e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "error in method userPasswordUpdate");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (id > 0 && id instanceof Long) {
            try {
                userService.remove(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Throwable e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "error in method delete");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }
}
