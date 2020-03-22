package streetsReportsControl.ControlsStreets.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AuthUser {
    @Id
    private Long id;
    private String Email;
    private String Password;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
}
