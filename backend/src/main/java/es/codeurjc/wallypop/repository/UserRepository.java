package es.codeurjc.wallypop.repository;

import es.codeurjc.wallypop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNAME(String EMAIL);

    Optional<User> findByTOKEN(String TOKEN);
}
