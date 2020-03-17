package streetsReportsControl.ControlsStreets.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streetsReportsControl.ControlsStreets.DTO.ReportDTO;
import streetsReportsControl.ControlsStreets.Models.Report;
import streetsReportsControl.ControlsStreets.Models.User;
import streetsReportsControl.ControlsStreets.Services.ReportService;
import streetsReportsControl.ControlsStreets.Services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/report"})
public class ReportController {

    private ReportService _reportService;
    private UserService _userService;

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
    public ResponseEntity<Report> create(@RequestBody ReportDTO reportdto){
        Report report = new Report();
        report.setImgUrl(reportdto.getImgUrl());
        report.setDescription(reportdto.getDescription());
        report.setLatLng(reportdto.getLatLng());
        report.setTitle(reportdto.getTitle());
        User user =_userService.findOne(reportdto.getUser_id());
        report.setUser(user);

        return ResponseEntity.ok().body( _reportService.save(report));

    }
}
