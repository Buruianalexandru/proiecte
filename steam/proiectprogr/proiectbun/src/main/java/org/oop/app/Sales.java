package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idsale;

    private int numberofsales;
    @ManyToOne
    @JoinColumn(name = "game")
    private Games game;

}
