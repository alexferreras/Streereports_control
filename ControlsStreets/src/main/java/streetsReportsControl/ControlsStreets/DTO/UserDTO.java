package streetsReportsControl.ControlsStreets.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String profile;
    private boolean active;
    private String email;
    private String password;
}
