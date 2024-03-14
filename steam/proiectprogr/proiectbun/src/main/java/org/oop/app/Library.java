package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data

@NoArgsConstructor

public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idlib;

    @OneToOne
    @JoinColumn(name = "players")
    private Player_profile players;
    @OneToMany(mappedBy = "lib", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Games> gamesList;

    public Library(int idlib, Player_profile players) {
        this.idlib = idlib;
        this.players = players;
    }

    public Library(int idlib, Player_profile players, List<Games> gamesList) {
        this.idlib = idlib;
        this.players = players;
        this.gamesList = gamesList;
    }
}
