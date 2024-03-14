package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idgame;

    private String gamename;
    private double price;

    public Games(int idgame, String gamename, double price) {
        this.idgame = idgame;
        this.gamename = gamename;
        this.price = price;
    }

    public Games(int idgame,String gamename, double price, Categories category) {
        this.idgame = idgame;
        this.gamename = gamename;
        this.price = price;
        this.category = category;
    }

    public int getIdgame() {
        return idgame;
    }

    public void setIdgame(int idgame) {
        this.idgame = idgame;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @ManyToOne
    @JoinColumn(name = "lib")
    private Library lib;
    @OneToMany(mappedBy = "game")
    private List<Reviews> reviewsList;
    @OneToMany(mappedBy = "game")
    private List<Skins> skinsList;
    @OneToMany(mappedBy = "game")
    private List<Sales> sales;

}
