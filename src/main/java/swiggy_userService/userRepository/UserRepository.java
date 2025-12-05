package swiggy_userService.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swiggy_userService.model.SwiggyUser;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SwiggyUser,Long> {
   boolean existsByEmail(String email);
   Optional<SwiggyUser> findByEmail(String email);
}
