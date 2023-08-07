package com.db.api.service.user;

import com.db.api.dto.LoginDTO;
import com.db.api.dto.UserDTO;

public interface UserService {
//    void saveUser(UserDTO userDto);
//
//    User findUserByEmail(String email);
//
//    List<UserDTO> findAllUsers();
    String addUser(UserDTO userDTO);
    LoginMessage loginEmployee(LoginDTO loginDTO);
}
