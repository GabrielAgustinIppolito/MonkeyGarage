package it.discordcgm.monkeygarage.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CostoTipoVeicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double costoPerOra;
    private double costoPerGiorni;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TipoVeicolo tipoVeicolo;

    @OneToMany(mappedBy = "costoTipoVeicolo")
    private List<Prenotazione> prenotaziones;

    public CostoTipoVeicolo(double costoPerOra, double costoPerGiorni, TipoVeicolo tipoVeicolo) {
        this.costoPerOra = costoPerOra;
        this.costoPerGiorni = costoPerGiorni;
        this.tipoVeicolo = tipoVeicolo;
    }
}
