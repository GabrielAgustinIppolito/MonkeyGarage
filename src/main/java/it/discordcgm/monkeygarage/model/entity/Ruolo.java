package it.discordcgm.monkeygarage.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 25, nullable = false, unique = true)
    private String nome;

    public Ruolo(long id, String nome) {
        this.id = id;
        this.nome = nome.toUpperCase().trim();
    }


    public Ruolo(String nome) {
        this.nome = nome;
    }
}
