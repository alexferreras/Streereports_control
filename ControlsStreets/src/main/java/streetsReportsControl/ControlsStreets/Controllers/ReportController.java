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
@RestController
@RequestMapping({"/reports"})
public class ReportController {

    private ReportService _reportService;

    private UserService _userService;

    @Autowired
    public ReportController(ReportService reportService,UserService userService ){

        this._reportService=reportService;
        this._userService=userService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> FindAll(){
        return ResponseEntity.ok(_reportService.findAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Report> FindById(@PathVariable long id){
        return _reportService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity Create(@RequestBody ReportDTO reportdto){
        Report report = new Report();
        report.setImgUrl(reportdto.getImgUrl());
        report.setDescription(reportdto.getDescription());
        report.setLatLng(reportdto.getLatLng());
        report.setTitle(reportdto.getTitle());
        report.setActive(true);
        User user =_userService.findOne(reportdto.getUser_id());
        if (user ==null){
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
        report.setUser(user);
        return ResponseEntity.ok().body(_reportService.save(report));
    }

    @PutMapping
    public ResponseEntity<Report> Update(@RequestBody ReportDTO newReport) {
        return _reportService.findById(newReport.getId()).map(oldReport -> {
            oldReport.setActive(newReport.isActive());
            oldReport.setImgUrl(newReport.getImgUrl());
            oldReport.setDescription(newReport.getDescription());
            oldReport.setLatLng(newReport.getLatLng());
            oldReport.setTitle(newReport.getTitle());
            return ResponseEntity.ok().body(oldReport);
        }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> Delete(@PathVariable Long id) {

        return _reportService.findById(id).map(report -> {
            report.setActive(false);
            return ResponseEntity.ok().body("Reporte Eliminado correctamente");
        }).orElse(ResponseEntity.notFound().build());

    }
}
