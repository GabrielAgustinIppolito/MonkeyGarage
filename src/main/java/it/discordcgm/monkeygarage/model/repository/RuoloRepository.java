package it.discordcgm.monkeygarage.model.repository;

import it.discordcgm.monkeygarage.model.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByNome(String role);
}
