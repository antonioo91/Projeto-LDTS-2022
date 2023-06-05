package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.powerups.HP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HPTest {

    private HP hp;
    private Bomberman bomberman;

    @BeforeEach
    void setup(){
        hp = new HP(new Position(0,0), 1);
        bomberman = new Bomberman(new Position(0,0));
    }

    @Test
    void testDoEffect(){
        //arrange
        int maxLives=5;

        //assert
        assertEquals(3, bomberman.getLives());

        //arrange
        int previousLives = bomberman.getLives();

        //act
        hp.doEffect(bomberman);

        //assert
        assertTrue(bomberman.getLives()>previousLives);
        assertTrue(bomberman.getLives()<=maxLives);
    }

    @Test
    void testAnimation(){
        assertEquals('♥', hp.getAnimation());
        assertEquals("#F9F295", hp.getColor());
        hp.nextAnimation();
        assertEquals('Ṉ', hp.getAnimation());
        assertEquals("#E0AA3E", hp.getColor());
        hp.nextAnimation();
        assertEquals('♥', hp.getAnimation());
        assertEquals("#F9F295", hp.getColor());
    }
}
