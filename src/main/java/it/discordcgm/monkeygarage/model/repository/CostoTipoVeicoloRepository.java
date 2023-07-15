package it.discordcgm.monkeygarage.model.repository;

import it.discordcgm.monkeygarage.model.entity.CostoTipoVeicolo;
import it.discordcgm.monkeygarage.model.entity.TipoVeicolo;
import it.discordcgm.monkeygarage.model.payload.response.CostoTipoVeicoloResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CostoTipoVeicoloRepository extends JpaRepository<CostoTipoVeicolo, Long> {
    Optional<CostoTipoVeicolo> findByTipoVeicolo(TipoVeicolo tipoVeicolo);


    @Query (value = "SELECT new it.discordcgm.monkeygarage.model.payload.response.CostoTipoVeicoloResponse(" +
            "ctp.costoPerOra, " +
            "ctp.costoPerGiorni, " +
            "ctp.tipoVeicolo " +
            ") FROM CostoTipoVeicolo ctp " +
            "WHERE ctp.tipoVeicolo = :tipoVeicolo")
    Optional<CostoTipoVeicoloResponse> getCostoByTipoVeicolo(@Param("tipoVeicolo") TipoVeicolo tipoVeicolo);

    @Query (value = "SELECT new it.discordcgm.monkeygarage.model.payload.response.CostoTipoVeicoloResponse(" +
            "ctp.costoPerOra, " +
            "ctp.costoPerGiorni, " +
            "ctp.tipoVeicolo " +
            ") FROM CostoTipoVeicolo ctp ")
    List<CostoTipoVeicoloResponse> getAll();
}
