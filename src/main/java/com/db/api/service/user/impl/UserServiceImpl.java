package com.db.api.service.user.impl;

import com.db.api.dto.LoginDTO;
import com.db.api.dto.UserDTO;
import com.db.api.model.User;
import com.db.api.repository.UserRepository;
import com.db.api.service.user.LoginMessage;
import com.db.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
//
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(user);

         return user.getName();
    }

    UserDTO employeeDTO;
    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        User employee1 = userRepository.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }
//
//    public UserServiceImpl(UserRepository userRepository,
//                           RoleRepository roleRepository,
//                           PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void saveUser(UserDTO userDto) {
//        User user = new User();
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        // encrypt the password using spring security
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
//        userRepository.save(user);
//    }
//
//    private Role checkRoleExist(){
//        Role role = new Role();
//        role.setName("ROLE_ADMIN");
//        return roleRepository.save(role);
//    }
//
//    @Override
//    public User findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    @Override
//    public List<UserDTO> findAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map((user) -> mapToUserDto(user))
//                .collect(Collectors.toList());
//    }
//
//    private UserDTO mapToUserDto(User user){
//        UserDTO userDto = new UserDTO();
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        return userDto;
//    }
//
////    private Boolean loginUser(UserLoginInfo userLoginInfo) {
////        User user = findUserByEmail(userLoginInfo.getEmail());
////        if(Objects.isNull(user)) {
////            throw new RuntimeException("User not found for this mail ID");
////        }
////        String password = passwordEncoder.encode(userLoginInfo.getPassword());
////        if(!password.equals(user.getPassword())) {
////            throw new RuntimeException("")
////        }
////    }

}
