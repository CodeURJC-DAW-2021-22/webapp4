package es.codeurjc.wallypop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	public void deleteById(long id) {
		reportRepository.deleteById(id);
	}

	public boolean exist(long id) {
		return reportRepository.existsById(id);
	}

	public List<Report> findAll() {
		return reportRepository.findAll();
	}

	public Optional<Report> findById(long id) {
		return reportRepository.findById(id);
	}

	public void flush() {
		reportRepository.flush();
	}

	public void save(Report report) {
		reportRepository.save(report);
	}
	
    public ResponseEntity<Report> updateReport(@PathVariable long id, @RequestBody Report updatedReport){
    	Report rep = findById(id).get();
    	if (updatedReport.getARTICLE() == null) {
        updatedReport.setARTICLE(rep.getARTICLE());
        }
        if (updatedReport.getEMAIL() == null) {
        	updatedReport.setEMAIL(rep.getEMAIL());
        }
        if (updatedReport.getDESCRIPTION() == null) {
        	updatedReport.setDESCRIPTION(rep.getDESCRIPTION());
        }
        if (updatedReport.getPROOF() == null) {
        	updatedReport.setPROOF(rep.getPROOF());
        }
        updatedReport.setID_REPORT(id);
        save(updatedReport);
        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
}

	/*
	 * public List<Report> findAllReportsByArticle(long article_id) {
	 * Optional<List<Report>> lReports =
	 * reportRepository.findAllByArticleId(article_id); if (lReports.isPresent()) {
	 * return lReports.get(); } return new LinkedList<>(); }
	 */
}
