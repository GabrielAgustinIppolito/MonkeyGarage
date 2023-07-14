package it.discordcgm.monkeygarage.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "avatars", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    // Definisce il tipo come "blob" sul db
    @Lob
    private byte[] data;

    private String fileType;

    // Nel crearlo mettere a true questo e a false quelli vecchi
    private boolean selected;

    public Avatar(User owner, String name, byte[] data, String fileType) {
        this.owner = owner;
        this.name = name;
        this.data = data;
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id == avatar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
