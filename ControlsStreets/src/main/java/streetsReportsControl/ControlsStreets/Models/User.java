package streetsReportsControl.ControlsStreets.Models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String profile;
    @Column(unique=true)
    private String email;
    private String password;
    @OneToMany()
    private List<Report> reports;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
