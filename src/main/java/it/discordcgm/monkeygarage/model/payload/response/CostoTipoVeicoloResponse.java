package it.discordcgm.monkeygarage.model.payload.response;

import it.discordcgm.monkeygarage.model.entity.TipoVeicolo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter @NoArgsConstructor
public class CostoTipoVeicoloResponse {

    private double costoPerOra;
    private double costoPerGiorni;
    private TipoVeicolo tipoVeicolo;

    public CostoTipoVeicoloResponse(double costoPerOra, double costoPerGiorni, TipoVeicolo tipoVeicolo) {
        this.costoPerOra = costoPerOra;
        this.costoPerGiorni = costoPerGiorni;
        this.tipoVeicolo = tipoVeicolo;
    }
}
