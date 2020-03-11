package streetsReportsControl.ControlsStreets.Models;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imgUrl;
    private String description;
    private String latLng;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
