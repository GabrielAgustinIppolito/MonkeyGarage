package it.discordcgm.monkeygarage.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String targa;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVeicolo tipo;

    private String marca;

    private String modello;

    @ManyToOne
    @JoinColumn(name="proprietario", nullable = false)
    private User proprietario;

    public Veicolo(String targa, String marca, TipoVeicolo tipo, String modello) {
        this.targa = targa;
        this.marca = marca;
        this.tipo = tipo;
        this.modello = modello;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veicolo veicolo = (Veicolo) o;
        return id == veicolo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
