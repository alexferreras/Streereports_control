package streetsReportsControl.ControlsStreets.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streetsReportsControl.ControlsStreets.Models.Report;
import streetsReportsControl.ControlsStreets.Repositories.ReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private ReportRepository _reportRepository;
    @Autowired
    public void ReportService(ReportRepository reportRepository){
        this._reportRepository=reportRepository;

    }
    public List<Report> findAll() {
        return _reportRepository.findAll().stream().filter(report -> report.isActive()).collect(Collectors.toList());
    }

    public Optional<Report> findById(Long id) {
        return _reportRepository.findById(id);
    }

    public Report save(Report report)
    {
        return _reportRepository.save(report);
    }

    public void deleteById(Long id) {
        _reportRepository.deleteById(id);
    }
}
