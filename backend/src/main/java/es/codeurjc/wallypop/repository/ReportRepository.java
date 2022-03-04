package es.codeurjc.wallypop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	// Optional<List<Report>> findAllByArticleId(long article_id);
}
