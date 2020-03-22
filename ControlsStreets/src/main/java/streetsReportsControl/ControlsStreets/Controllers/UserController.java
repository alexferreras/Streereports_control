package streetsReportsControl.ControlsStreets.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streetsReportsControl.ControlsStreets.DTO.UserDTO;
import streetsReportsControl.ControlsStreets.Models.User;
import streetsReportsControl.ControlsStreets.Services.UserService;

import java.util.List;

@RestController
@RequestMapping({"/users"})
public class UserController {

    private UserService _userService;

    @Autowired
    public UserController(UserService userService) {
        this._userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> FindAll() {
        return ResponseEntity.ok(_userService.findAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> FindById(@PathVariable long id) {
        return _userService.findById(id)
                .map(record -> {
                    record.setPassword(null);
                    return ResponseEntity.ok().body(record);})
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity Create(@RequestBody UserDTO userdto) {
        User user = new User();
        if(_userService.existsByEmail(userdto.getEmail())){
            return ResponseEntity.badRequest().body("El correo ya esta en uso");
        }

        user.setProfile(userdto.getProfile());
        user.setLastName(userdto.getLastName());
        user.setName(userdto.getLastName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAddress(userdto.getAddress());
        user.setActive(true);
        return ResponseEntity.ok().body(_userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> Update(@RequestBody UserDTO newUser) {
        return _userService.findById(newUser.getId()).map(oldUser -> {
            oldUser.setActive(newUser.isActive());
            oldUser.setProfile(newUser.getProfile());
            oldUser.setLastName(newUser.getLastName());
            oldUser.setName(newUser.getLastName());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setAddress(newUser.getAddress());
            return ResponseEntity.ok().body(oldUser);
        }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> Delete(@PathVariable Long id) {

        return _userService.findById(id).map(user -> {
            user.setActive(false);
            return ResponseEntity.ok().body("Usuario Eliminado correctamente");
        }).orElse(ResponseEntity.notFound().build());

    }
}
