package org.example.model.game.entities;

import org.example.model.Position;
import org.example.model.game.entities.Explosion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplosionTest {
    private Explosion explosion;

    @BeforeEach
    void setup(){
        explosion = new Explosion(Mockito.mock(Position.class));
    }

    @Test
    void testAnimation(){
        assertEquals('ḻ', explosion.getAnimation());
        explosion.nextAnimation();
        assertEquals('Ḽ', explosion.getAnimation());
    }
}
