package it.discordcgm.monkeygarage.model.repository;

import it.discordcgm.monkeygarage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);


    Optional<User> findByEmail(String email);


    Optional <User> findByUsernameOrEmail(String username, String email);
}
