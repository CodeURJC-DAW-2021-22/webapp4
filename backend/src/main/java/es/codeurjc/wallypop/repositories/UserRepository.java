package es.codeurjc.wallypop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
