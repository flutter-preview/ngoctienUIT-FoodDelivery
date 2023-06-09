package com.tnt.food_delivery.controller;

import com.tnt.food_delivery.common.JwtUtils;
import com.tnt.food_delivery.model.User;
import com.tnt.food_delivery.model.request.AuthenticationRequestEntity;
import com.tnt.food_delivery.model.response.AuthenticationResponseEntity;
import com.tnt.food_delivery.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestEntity authentication) {
        User user = userRepository.login(authentication.getUsername(), authentication.getPassword());
        AuthenticationResponseEntity response = new AuthenticationResponseEntity(JwtUtils.renderAccessToken(user), user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User myUser = userRepository.save(user);
            return ResponseEntity.ok(myUser);
        } catch (DuplicateKeyException e) {
            int length = e.toString().split(":").length;
            String error = (e.toString().split(":")[length - 3]).split(" ")[1] + " is duplicated";
            return ResponseEntity.status(400).body(error);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e);
        }
    }
}
