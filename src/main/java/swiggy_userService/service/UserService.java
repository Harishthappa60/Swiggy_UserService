package swiggy_userService.service;

import swiggy_userService.model.SwiggyUser;
import swiggy_userService.userDTO.LoginRequestDTO;
import swiggy_userService.userDTO.UserRequestDTO;

import java.util.List;

public interface UserService {
    UserRequestDTO register(UserRequestDTO userRequest);
    UserRequestDTO login(LoginRequestDTO loginRequest);
    List<UserRequestDTO>getAllUsers();
    UserRequestDTO getUserById(Long id);
    UserRequestDTO updateUser(Long id,UserRequestDTO userRequest);
    String deleteUser(Long id);


}
