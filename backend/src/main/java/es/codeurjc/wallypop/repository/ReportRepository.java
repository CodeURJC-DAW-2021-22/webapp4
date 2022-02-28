package es.codeurjc.wallypop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
