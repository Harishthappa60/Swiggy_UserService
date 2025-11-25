package swiggy_userService.userDTO;

import lombok.*;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phone;

}