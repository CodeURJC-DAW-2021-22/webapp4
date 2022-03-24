package es.codeurjc.wallypop.controller.api.admin;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports/admin")
public class AdminReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("")
    List<Report> all() {
        return reportService.findAll();
    }

    @GetMapping("/{idReport}")
    public ResponseEntity<Report> getReport(@PathVariable long idReport) {

        Optional<Report> or = reportService.findById(idReport);
        if (or.isPresent()) {
            Report report = or.get();
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idReport}")
    public ResponseEntity<Report> updateReport(@PathVariable long idReport, @RequestBody Report updatedReport) throws SQLException {
        if (reportService.exist(idReport)) {
            reportService.updateReport(idReport, updatedReport);
            return new ResponseEntity<>(reportService.findById(idReport).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idReport}")
    public ResponseEntity<Report> deleteReport(@PathVariable long idReport) {

        try {
            reportService.deleteById(idReport);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
