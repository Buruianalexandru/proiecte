package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idreview;

    private String review;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;
    @ManyToOne
    @JoinColumn(name = "profile")
    private Player_profile profile;
}
