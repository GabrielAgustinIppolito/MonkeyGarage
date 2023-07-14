package it.discordcgm.monkeygarage.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean abilitato;

    @CreationTimestamp
    //in automatico si definisce un unica volta quando viene caricata per la prima volta sul db l'entità
    @Column(updatable = false)  // mi assicuro che non venga mai modificata
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="proprietario")
    private Set<Veicolo> veicolos;

    @ManyToMany // Giusto a scopo di esercitazione
    @JoinTable(name = "user_ruolo",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ruolo_id", referencedColumnName = "id")}
    )
    private Set<Ruolo> ruoli;

    @OneToMany(mappedBy = "owner")
    private List<Avatar> avatars;

    @OneToMany(mappedBy = "client")
    private List<Prenotazione> prenotaziones;

    public User(String nome, String email, String password, boolean abilitato) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.abilitato = abilitato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
