package com.tnt.food_delivery.controller;

import com.tnt.food_delivery.common.JwtUtils;
import com.tnt.food_delivery.model.User;
import com.tnt.food_delivery.model.request.AuthenticationRequestEntity;
import com.tnt.food_delivery.model.response.AuthenticationResponseEntity;
import com.tnt.food_delivery.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userRepository.findById(id).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không tìm thấy thông tin người dùng");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestEntity authentication) {
        try {
            User user = userRepository.login(authentication.getUsername(), authentication.getPassword());
            AuthenticationResponseEntity response = new AuthenticationResponseEntity(JwtUtils.renderAccessToken(user), user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tài khoản hoặc mật khẩu chưa chính xác");
        }

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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestHeader(name = "Authorization") String token,
                                        @PathVariable String id,
                                        @RequestBody User user) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            if (userID.equals(id) || role.equals("ADMIN")) {
                user.setId(id);
                return ResponseEntity.ok(userRepository.save(user));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền thay đổi thông tin người dùng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestHeader(name = "Authorization") String token, @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            if (userID.equals(id) || role.equals("ADMIN")) {
                userRepository.deleteById(id);
                return ResponseEntity.ok("ok");
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền xóa người dùng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
