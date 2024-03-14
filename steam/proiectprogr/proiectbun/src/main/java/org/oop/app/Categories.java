package org.oop.app;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import lombok.*;

import java.util.List;

@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idcategory;
    private String categoryname;

    public Categories(int idcategory, String categoryname) {
        this.idcategory = idcategory;
        this.categoryname = categoryname;
    }

    @OneToMany(mappedBy = "category")
    private List<Games> gamesList;

}
