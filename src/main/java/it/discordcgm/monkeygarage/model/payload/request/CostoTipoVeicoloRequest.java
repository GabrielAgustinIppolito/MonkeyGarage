package it.discordcgm.monkeygarage.model.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CostoTipoVeicoloRequest {

    @Min(1)
    private double costoPerOra;
    @Min(15)
    private double costoPerGiorni;
    @NotBlank @Size(min = 3)
    private String tipoVeicolo;

    public CostoTipoVeicoloRequest(double costoPerOra, double costoPerGiorni, String tipoVeicolo) {
        this.costoPerOra = costoPerOra;
        this.costoPerGiorni = costoPerGiorni;
        this.tipoVeicolo = tipoVeicolo.toUpperCase().trim();
    }
}
