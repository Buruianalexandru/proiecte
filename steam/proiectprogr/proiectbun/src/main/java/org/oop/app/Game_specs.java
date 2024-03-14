package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game_specs extends Games{
    private int space_required;
    private int releasedate;

    public Game_specs(int idgame, @NonNull String gamename, double price, int space_required, int releasedate) {
        super(idgame, gamename, price);
        this.space_required = space_required;
        this.releasedate=releasedate;
    }

}
