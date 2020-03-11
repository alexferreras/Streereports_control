package streetsReportsControl.ControlsStreets.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streetsReportsControl.ControlsStreets.Models.Report;
import streetsReportsControl.ControlsStreets.Models.User;
import streetsReportsControl.ControlsStreets.Services.ReportService;
import streetsReportsControl.ControlsStreets.Services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/report"})
public class ReportController {
    @Autowired
    private ReportService _reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this._reportService=reportService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> findAll(){
        return ResponseEntity.ok(_reportService.findAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Report> findById(@PathVariable long id){
        return _reportService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Report create(@RequestBody Report report){
        return _reportService.save(report);
    }
}
