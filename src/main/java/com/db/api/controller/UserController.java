package com.db.api.controller;

import com.db.api.dto.LoginDTO;
import com.db.api.dto.UserDTO;
import com.db.api.repository.UserRepository;
import com.db.api.service.user.LoginMessage;
import com.db.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginMessage loginResponse = userService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

//    public UserController(UserRepository userRepository, UserService userService) {
//        this.userRepository = userRepository;
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable Long id) {
//        return userRepository.findById(id).orElseThrow(RuntimeException::new);
//    }
//
//    @PostMapping
//    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
//        User savedUser = userRepository.save(user);
//        return ResponseEntity.created(new URI("/userss/" + savedUser.getId())).body(savedUser);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
//        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
//        currentUser.setName(user.getName());
//        currentUser.setEmail(user.getEmail());
//        currentUser = userRepository.save(user);
//
//        return ResponseEntity.ok(currentUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteUser(@PathVariable Long id) {
//        userRepository.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping(path = "/register/save")
//    public Boolean registration(@RequestBody UserDTO userDto){
//        System.out.println(userDto);
//        User existingUser = userService.findUserByEmail(userDto.getEmail());
//
//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            return false;
//        }
//
//        userService.saveUser(userDto);
//        return true;
}
