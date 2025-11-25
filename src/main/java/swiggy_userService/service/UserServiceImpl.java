package swiggy_userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiggy_userService.model.SwiggyUser;
import swiggy_userService.userDTO.LoginRequestDTO;
import swiggy_userService.userDTO.UserRequestDTO;
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

    //convert entity to dto
    private UserRequestDTO convertToDTO(SwiggyUser user){
        UserRequestDTO dto=new UserRequestDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
    //Converto DTO to Entity

    private SwiggyUser convertToEntity(UserRequestDTO dto){
        SwiggyUser user=new SwiggyUser();
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserRequestDTO register(UserRequestDTO req) {
        repository.findByEmail(req.getEmail()).ifPresent(U->{
            throw new RuntimeException("Email already Exists");
        });
        SwiggyUser user=new SwiggyUser();
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        return convertToDTO(repository.save(user));
    }

    @Override
    public UserRequestDTO login(LoginRequestDTO req) {
        SwiggyUser user=repository.findByEmail(req.getEmail()).orElseThrow(()->new RuntimeException("User oot found"));
        if (!user.getPassword().equals(req.getPassword())){
            throw new RuntimeException("Incorrect Password");
        }
        return convertToDTO(user);
    }

    @Override
    public List<UserRequestDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserRequestDTO getUserById(Long id) {
        SwiggyUser user= repository.findById(id).orElseThrow(()->new RuntimeException("User not  found"));
        return convertToDTO(user);
    }

    @Override
    public UserRequestDTO updateUser(Long id, UserRequestDTO req) {
       SwiggyUser user=repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
       if (req.getName()!=null)
           user.setName(req.getName());
       if (req.getPhone()!=null)
           user.setPhone(req.getPhone());
       if (req.getEmail()!=null)
           user.setEmail(req.getEmail());
       if (req.getPassword()!=null)
           user.setPassword(req.getPassword());
        return convertToDTO(repository.save(user));
    }

    @Override
    public String deleteUser(Long id) {
       repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return "User successfully deleted";
    }
}
