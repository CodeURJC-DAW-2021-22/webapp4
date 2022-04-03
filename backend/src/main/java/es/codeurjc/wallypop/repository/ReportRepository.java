package es.codeurjc.wallypop.repository;

import es.codeurjc.wallypop.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // Optional<List<Report>> findAllByArticleId(long article_id);
}
