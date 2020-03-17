package streetsReportsControl.ControlsStreets.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportDTO {

    private String title;
    private String imgUrl;
    private String description;
    private String latLng;
    private long user_id;
    private boolean active;

}
