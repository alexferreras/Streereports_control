package streetsReportsControl.ControlsStreets.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streetsReportsControl.ControlsStreets.Models.User;
import streetsReportsControl.ControlsStreets.Services.UserService;

import java.util.List;

@RestController
@RequestMapping({"/user"})
public class UserController {

    private UserService _userService;
    @Autowired
    public UserController(UserService userService){
        this._userService=userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(_userService.findAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> findById(@PathVariable long id){
        return _userService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public User create(@RequestBody User user){
        return _userService.save(user);
    }

}
