package it.discordcgm.monkeygarage.model.repository;

import it.discordcgm.monkeygarage.model.entity.CostoTipoVeicolo;
import it.discordcgm.monkeygarage.model.entity.TipoVeicolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostoTipoVeicoloRepository extends JpaRepository<CostoTipoVeicolo, Long> {
    Optional<CostoTipoVeicolo> findByTipoVeicolo(TipoVeicolo tipoVeicolo);
}
