package com.db.api.service.user;

import com.db.api.dto.UserDTO;
import com.db.api.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();
}
