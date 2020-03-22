package streetsReportsControl.ControlsStreets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import streetsReportsControl.ControlsStreets.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
}
