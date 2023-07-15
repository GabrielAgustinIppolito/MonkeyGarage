package it.discordcgm.monkeygarage.model.service;

import it.discordcgm.monkeygarage.model.entity.Ruolo;
import it.discordcgm.monkeygarage.model.repository.RuoloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuoloService {

    private final RuoloRepository repo;

    public Optional<Ruolo> findByAuthorityName(String role) {
        return repo.findByNome(role);
    }
}
