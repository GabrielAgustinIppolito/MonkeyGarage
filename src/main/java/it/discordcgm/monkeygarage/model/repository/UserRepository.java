package it.discordcgm.monkeygarage.model.repository;

import it.discordcgm.monkeygarage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);


}
