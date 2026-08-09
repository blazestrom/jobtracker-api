package com.piyush.jobtracker.service;
import java.util.*;
import com.piyush.jobtracker.dto.UserResponseDTO;
import com.piyush.jobtracker.entity.User;
import com.piyush.jobtracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.piyush.jobtracker.exception.UserNotFoundException;
import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository= userRepository;
    }
//    public User saveUser(User user){
//        return userRepository.save(user);
//    }
    // This is for mapping user -> dto so that password will not be visible
    public List<UserResponseDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserResponseDTO> response = new ArrayList<>();

        for (User user : users) {

            UserResponseDTO dto = new UserResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCollege(),
                    user.getYearOfPassing()
            );

            response.add(dto);
        }

        return response;
    }
    /*return users.stream() # this is how stream work for dto
            .map(user -> new UserResponseDTO(
            user.getId(),
                user.getName(),
                        user.getEmail(),
                        user.getCollege(),
                        user.getYearOfPassing()))
                        .toList();*/

    public  UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
        UserResponseDTO dto = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCollege(),
                user.getYearOfPassing()
        );
        return dto;
    }
    public UserResponseDTO saveUser(User user) {

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCollege(),
                savedUser.getYearOfPassing()
        );
    }


}
