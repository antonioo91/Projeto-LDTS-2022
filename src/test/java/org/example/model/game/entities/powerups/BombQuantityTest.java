package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomb;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.powerups.BombQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BombQuantityTest {
    private BombQuantity bombQuantity;
    private Bomberman bomberman;

    @BeforeEach
    void setup(){
        bombQuantity = new BombQuantity(new Position(0, 0), 1);
        bomberman = new Bomberman(new Position(0, 0));
    }

    @Test
    void testDoEffect(){

        //act
        bombQuantity.doEffect(bomberman);

        //assert
        assertEquals(2, bomberman.getBombQuantity());
    }

    @Test
    void testAnimation(){
        assertEquals('Ḿ', bombQuantity.getAnimation());
        assertEquals("#F9F295", bombQuantity.getColor());
        bombQuantity.nextAnimation();
        assertEquals("#E0AA3E", bombQuantity.getColor());
        bombQuantity.nextAnimation();
        assertEquals("#F9F295", bombQuantity.getColor());
    }

}
