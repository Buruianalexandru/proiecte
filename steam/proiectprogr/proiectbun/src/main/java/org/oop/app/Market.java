package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    private String skinname;

    private Double skinprice;
    @OneToMany(mappedBy = "market")
    private List<Skins> skinsList;

    public Market(int id,String skinname, @NonNull Double skinprice) {
        this.id = id;
        this.skinname = skinname;
        this.skinprice = skinprice;
    }
}
