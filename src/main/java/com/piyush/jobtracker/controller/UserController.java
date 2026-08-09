package com.piyush.jobtracker.controller;
import java.util.*;
import com.piyush.jobtracker.dto.UserResponseDTO;
import com.piyush.jobtracker.entity.User;
import com.piyush.jobtracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    // Depandecny injection construction wala without autowired
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }


//    @PostMapping
//    public User registerUser(@Valid @RequestBody User user ){
//      return   userService.saveUser(user);
//    }
    @GetMapping
    public List<UserResponseDTO>getAllUsers(){
            return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
       return userService.getUserById(id);
    }



    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody User user) {

        UserResponseDTO response = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


}
