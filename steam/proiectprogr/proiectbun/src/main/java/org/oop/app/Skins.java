package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idskin;
    private String skinname;

    private String skintype;

    private double skinprice;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;

    public Skins(int idskin, String skinname,  String skintype,  double skinprice, Games game) {
        this.idskin = idskin;
        this.skinname = skinname;
        this.skintype = skintype;
        this.skinprice = skinprice;
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name = "market")
    private Market market;
}
