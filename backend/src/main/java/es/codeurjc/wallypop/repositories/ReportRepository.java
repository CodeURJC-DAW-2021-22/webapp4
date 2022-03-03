package es.codeurjc.wallypop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.wallypop.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
