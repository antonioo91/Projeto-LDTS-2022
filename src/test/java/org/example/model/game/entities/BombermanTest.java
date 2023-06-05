package org.example.model.game.entities;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class BombermanTest {
    private Bomberman bomberman;

    @BeforeEach
    void setup(){
        bomberman = new Bomberman(Mockito.mock(Position.class));
        bomberman.setColor("#FFFFFF");
    }

    @Test
    void testHPDecrease(){
        assertEquals(3, bomberman.getLives());
        bomberman.decreaseLife();
        assertEquals(2, bomberman.getLives());
        bomberman.decreaseLife();
        bomberman.decreaseLife();
        assertEquals(0, bomberman.getLives());
        bomberman.decreaseLife();
        assertEquals(0, bomberman.getLives());
    }

    @Test
    void testHPIncrease(){
        assertEquals(3, bomberman.getLives());
        bomberman.increaseLife();
        assertEquals(4, bomberman.getLives());
        bomberman.increaseLife();
        assertEquals(5, bomberman.getLives());
        bomberman.increaseLife();
        assertEquals(5, bomberman.getLives());


        bomberman.increaseLife();
        bomberman.increaseLife();
        bomberman.increaseLife();

        assertEquals(5, bomberman.getLives());
    }

    @Test
    void testAnimation(){
        assertEquals('ṗ', bomberman.getAnimation());
        assertEquals("#FFFFFF", bomberman.getColor());
        bomberman.nextAnimation();
        assertEquals('ṙ', bomberman.getAnimation());
        bomberman.setInvincible(true);
        assertEquals('ṙ', bomberman.getAnimation());
        assertEquals("#FFFFFF", bomberman.getColor());
        bomberman.nextAnimation();
        assertEquals('ṗ', bomberman.getAnimation());
        assertEquals("#F9F295", bomberman.getColor());
    }
}
