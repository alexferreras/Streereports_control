package streetsReportsControl.ControlsStreets.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import streetsReportsControl.ControlsStreets.DTO.AuthDTO;
import streetsReportsControl.ControlsStreets.DTO.ReportDTO;
import streetsReportsControl.ControlsStreets.Models.User;
import streetsReportsControl.ControlsStreets.Services.UserService;

@RestController
@RequestMapping({"/auth"})
public class AuthController {

    private UserService _userService;
    @Autowired
    public AuthController(UserService userService) {
        this._userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> logIn(@RequestBody AuthDTO loginRequest){
        return _userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .map(record -> {
                    record.setPassword(null);
                    return ResponseEntity.ok().body(record);})
                .orElse(ResponseEntity.notFound().build());
    }
}
