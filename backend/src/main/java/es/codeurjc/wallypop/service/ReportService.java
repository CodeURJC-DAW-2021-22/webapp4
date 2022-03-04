package es.codeurjc.wallypop.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.Report;
import es.codeurjc.wallypop.repository.CategoryRepository;
import es.codeurjc.wallypop.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;
	
	public Optional<Report> findById(long id) {
		return reportRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return reportRepository.existsById(id);
	}

	public List<Report> findAll() {
		return reportRepository.findAll();
	}

	public void save(Report report) {
		reportRepository.save(report);
	}

	public void delete(long id) {
		reportRepository.deleteById(id);
	}
	
	public List<Report> findAllReportsByArticle(long article_id) {
		Optional<List<Report>> lReports = reportRepository.findAllByArticleId(article_id); 
		if (lReports.isPresent()) {
			return lReports.get();
		}
		return new LinkedList<>();
	}
}