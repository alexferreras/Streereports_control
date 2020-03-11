package streetsReportsControl.ControlsStreets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import streetsReportsControl.ControlsStreets.Models.Report;

public interface ReportRepository extends JpaRepository<Report, Long> { }
