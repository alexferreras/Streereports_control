package streetsReportsControl.ControlsStreets.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streetsReportsControl.ControlsStreets.Models.User;
import streetsReportsControl.ControlsStreets.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository _userRepository;
    @Autowired
    public void UserService(UserRepository userRepository){
        this._userRepository=userRepository;

    }

    public List<User> findAll() {
        return _userRepository.findAll().stream().filter(user -> user.isActive()).collect(Collectors.toList());
    }
    public User findOne(Long id) {
        return _userRepository.findById(id).orElse(new User());
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return _userRepository.findAllByEmailAndPassword(email, password);
    }

    public Optional<User> findById(Long id) {
        return _userRepository.findById(id);
    }

    public User save(User user) {
        return _userRepository.save(user);
    }

    public void deleteById(Long id) {
        _userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return _userRepository.existsByEmail(email);
    }
}
