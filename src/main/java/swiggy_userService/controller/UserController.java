package swiggy_userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swiggy_userService.service.UserService;
import swiggy_userService.userDTO.LoginRequestDTO;
import swiggy_userService.userDTO.UserRequestDTO;
import swiggy_userService.userDTO.UserResponseDTO;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO>login(@RequestBody LoginRequestDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @GetMapping("/getById")
    public ResponseEntity<UserResponseDTO>getById(@RequestParam Long id){
        return ResponseEntity.ok(service.getUserById(id));
    }
    @PatchMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam Long id, @RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(service.updateUser(id,dto));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
