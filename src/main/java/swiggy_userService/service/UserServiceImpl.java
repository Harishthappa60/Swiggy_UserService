package swiggy_userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiggy_userService.model.SwiggyUser;
import swiggy_userService.userDTO.LoginRequestDTO;
import swiggy_userService.userDTO.UserRequestDTO;
import swiggy_userService.userDTO.UserResponseDTO;
import swiggy_userService.userRepository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    //ConvertToDTO to Entity

    private UserResponseDTO convertToResponse(SwiggyUser user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDTO register(UserRequestDTO req) {
        repository.findByEmail(req.getEmail()).ifPresent(U->{
            throw new RuntimeException("Email already Exists");
        });
        SwiggyUser user=SwiggyUser.builder()
                .name(req.getName())
                .phoneNumber(req.getPhoneNumber())
                .email(req.getEmail())
                .password(req.getPassword())
                .build();

        return convertToResponse(repository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO req) {
        SwiggyUser user=repository.findByEmail(req.getEmail()).orElseThrow(()->new RuntimeException("User oot found"));
        if (!user.getPassword().equals(req.getPassword())){
            throw new RuntimeException("Incorrect Password");
        }
        return convertToResponse(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream().map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        SwiggyUser user= repository.findById(id).orElseThrow(()->new RuntimeException("User not  found"));
        return convertToResponse(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO req) {
       SwiggyUser user=repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
       if (req.getName()!=null)
           user.setName(req.getName());
       if (req.getPhoneNumber()!=null)
           user.setPhoneNumber(req.getPhoneNumber());
       if (req.getEmail()!=null)
           user.setEmail(req.getEmail());
       if (req.getPassword()!=null)
           user.setPassword(req.getPassword());
        return convertToResponse(repository.save(user));
    }

    @Override
    public String deleteUser(Long id) {
       repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return "User successfully deleted";
    }
}
