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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String profileUrl;
    @OneToMany()

    private List<Report> reports;
    @CreationTimestamp
    @Column( columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
