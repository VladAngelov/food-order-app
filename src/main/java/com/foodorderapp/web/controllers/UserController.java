package com.foodorderapp.web.controllers;

import com.foodorderapp.config.CurrentUser;
import com.foodorderapp.constants.Links;
import com.foodorderapp.models.binding.user.LocalUserBindingModel;
import com.foodorderapp.models.view.UserViewModel;
import com.foodorderapp.services.interfaces.UserService;
import com.foodorderapp.util.GeneralUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(Links.API)
//public class UserController {
//

//
//    @PostMapping(path = Links.USER_REGISTER)
//    public ResponseEntity<UserViewModel> Register(
//            @RequestBody UserAddBindingModel userAddBindingModel) {
//        try {
//            UserViewModel user = this.modelMapper
//                    .map(
//                        this.userService
//                                .register(this.userService
//                                        .register(this.modelMapper
//                                                .map(userAddBindingModel,
//                                                        UserServiceModel.class))),
//                            UserViewModel.class);
//
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @GetMapping("/user/{email}")
//    public ResponseEntity<UserViewModel> GetUser(@PathVariable String email) {
//        try {
//            UserViewModel user = this.modelMapper
//                    .map(
//                            this.userService.getUser(email),
//                            UserViewModel.class
//                    );
//
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    }
//}



@RestController
@RequestMapping(Links.API) // @RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(
            UserService userService,
            ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(path = Links.USER_CURRENT)
//    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUserBindingModel user) {
        var userViewModel = this.modelMapper.map(this.userService.findUserByEmail(user.getEmail()), UserViewModel.class);
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @GetMapping(path = Links.HOME)
//    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping(path = Links.USER)
//    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping(path = Links.WORKER)
//    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

}