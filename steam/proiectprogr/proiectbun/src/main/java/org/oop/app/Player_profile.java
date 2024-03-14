package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity


@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Player_profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idplayer;


    private String username;

    public Player_profile(int idplayer, String username) {
        this.idplayer = idplayer;
        this.username = username;
    }

    public int getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(int idplayer) {
        this.idplayer = idplayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToOne(mappedBy = "players")
    private Library lib;
    @OneToMany(mappedBy = "profile")
    private List<Reviews> reviewsList ;
}