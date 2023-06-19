package com.tnt.food_delivery.controller;

import com.tnt.food_delivery.common.JwtUtils;
import com.tnt.food_delivery.model.Register;
import com.tnt.food_delivery.model.User;
import com.tnt.food_delivery.model.request.AuthenticationRequestEntity;
import com.tnt.food_delivery.model.response.AuthenticationResponseEntity;
import com.tnt.food_delivery.repository.RegisterRepository;
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
    @Autowired
    RegisterRepository registerRepository;

    //AUTHENTICATION

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userRepository.findById(id).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không tìm thấy thông tin người dùng");
        }
    }

    @GetMapping("/restaurant")
    public ResponseEntity<?> searchRestaurant(@RequestParam String name) {
        try {
            return ResponseEntity.ok(userRepository.findRestaurantByName(name));
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

    @DeleteMapping("/{id}/block")
    public ResponseEntity<?> blocUser(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
//            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            if (role.equals("ADMIN")) {
                User user = userRepository.findById(id).get();
                user.setStatus(User.UserStatus.BLOCKED);
                return ResponseEntity.ok(userRepository.save(user));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền khóa tài khoản người dùng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            if (userID.equals(id) || role.equals("ADMIN")) {
                User user = userRepository.findById(id).get();
                user.setStatus(User.UserStatus.DELETED);
                return ResponseEntity.ok(userRepository.save(user));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền xóa người dùng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/verify")
    public ResponseEntity<?> verifyUser() {
        return ResponseEntity.ok(null);
    }

    // REGISTER

    @GetMapping("/register")
    public ResponseEntity<?> getRegister(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam String type,
            @RequestParam String status) {
        if (token != null) {
            try {
                String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
//                String userID = content.split("~")[0];
                String role = content.split("~")[1];
                if (role.equals("ADMIN")) {
                    return ResponseEntity.ok(registerRepository.findItem(type, status));
                }
                return ResponseEntity.badRequest().body("Bạn không có quyền truy cập chức năng này");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Bạn không có quyền truy cập chức năng này");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody Register register) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            if (userID.equals(register.getUserID()) && role.equals("USER") || role.equals("ADMIN")) {
                return ResponseEntity.ok(registerRepository.save(register));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền truy cập chức năng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/{id}") //id register
    public ResponseEntity<?> acceptRegister(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
//            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            Register register = registerRepository.findById(id).get();
            if (role.equals("ADMIN")) {
                User user = userRepository.findById(register.getUserID()).get();
                if (register.getType() == Register.RegisterType.RESTAURANT) {
                    user.setUserRole(User.UserRole.RESTAURANT);
                } else {
                    user.setUserRole(User.UserRole.DELIVER);
                }
                register.setStatus(Register.RegisterStatus.ACCEPT);
                register.setTimeUpdate(Register.getCurrentTime());
                registerRepository.save(register);
                return ResponseEntity.ok(userRepository.save(user));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền truy cập chức năng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/register/{id}") //id register
    public ResponseEntity<?> cancelRegister(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            Register register = registerRepository.findById(id).get();
            if (userID.equals(register.getUserID()) && role.equals("USER") || role.equals("ADMIN")) {
                register.setStatus(Register.RegisterStatus.CANCEL);
                register.setTimeUpdate(Register.getCurrentTime());
                return ResponseEntity.ok(registerRepository.save(register));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền truy cập chức năng này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
