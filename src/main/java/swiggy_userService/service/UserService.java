package swiggy_userService.service;

import swiggy_userService.userDTO.LoginRequestDTO;
import swiggy_userService.userDTO.UserRequestDTO;
import swiggy_userService.userDTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO register(UserRequestDTO userRequest);
    UserResponseDTO login(LoginRequestDTO loginRequest);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequest);
    String deleteUser(Long id);


}
