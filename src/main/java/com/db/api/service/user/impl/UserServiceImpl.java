package com.db.api.service.user.impl;

import com.db.api.dto.UserDTO;
import com.db.api.model.Role;
import com.db.api.model.User;
import com.db.api.repository.RoleRepository;
import com.db.api.repository.UserRepository;
import com.db.api.service.user.UserLoginInfo;
import com.db.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

//    private Boolean loginUser(UserLoginInfo userLoginInfo) {
//        User user = findUserByEmail(userLoginInfo.getEmail());
//        if(Objects.isNull(user)) {
//            throw new RuntimeException("User not found for this mail ID");
//        }
//        String password = passwordEncoder.encode(userLoginInfo.getPassword());
//        if(!password.equals(user.getPassword())) {
//            throw new RuntimeException("")
//        }
//    }
}
