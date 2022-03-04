package es.codeurjc.wallypop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNAME(String EMAIL);
}
