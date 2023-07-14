package it.discordcgm.monkeygarage.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private User client;
    @ManyToOne
    @JoinColumn(name = "costo_veicolo", nullable = false)
    private CostoTipoVeicolo costoTipoVeicolo;
    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private LocalDateTime end;
    private double costoTotale;
    private boolean pagato = true;

    public Prenotazione(User client, CostoTipoVeicolo costoTipoVeicolo, LocalDateTime start, LocalDateTime end, boolean pagato) {
        this.client = client;
        this.costoTipoVeicolo = costoTipoVeicolo;
        this.start = start;
        this.end = end;
        this.pagato = pagato;
        this.costoTotale = calcolaCosto();
    }

    public double calcolaCosto(){
        int ore = end.getHour() - start.getHour();
        double costo;
        if(ore < 24){
            costo = ore * costoTipoVeicolo.getCostoPerOra();
        } else {
            int giorni = ore / 24;
            ore = ore % 24;
            costo = (giorni * costoTipoVeicolo.getCostoPerGiorni()) +
                    (ore * costoTipoVeicolo.getCostoPerOra());
        }
        return costo;
    }

    public void setCostoTotale(double costoTotale) {
        this.costoTotale = calcolaCosto();
    }
}
