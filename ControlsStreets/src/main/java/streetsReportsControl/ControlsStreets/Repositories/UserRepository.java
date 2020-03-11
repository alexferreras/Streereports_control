package streetsReportsControl.ControlsStreets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import streetsReportsControl.ControlsStreets.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
