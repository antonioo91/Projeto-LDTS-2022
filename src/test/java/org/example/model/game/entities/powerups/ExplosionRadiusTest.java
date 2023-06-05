package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.powerups.ExplosionRadius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplosionRadiusTest {

    private ExplosionRadius explosionRadius;
    private Bomberman bomberman;

    @BeforeEach
    void setup(){
        explosionRadius = new ExplosionRadius(new Position(0,0), 1);
        bomberman = new Bomberman(new Position(0,0));
    }

    @Test
    void testDoEffect(){
        assertEquals(1, bomberman.getExplosionRadius());
        explosionRadius.doEffect(bomberman);
        assertEquals(2, bomberman.getExplosionRadius());
    }

    @Test
    void testAnimation(){
        assertEquals('▪', explosionRadius.getAnimation());
        assertEquals("#F9F295", explosionRadius.getColor());
        explosionRadius.nextAnimation();
        assertEquals('●', explosionRadius.getAnimation());
        assertEquals("#E0AA3E", explosionRadius.getColor());
        explosionRadius.nextAnimation();
        assertEquals('▪', explosionRadius.getAnimation());
        assertEquals("#F9F295", explosionRadius.getColor());
    }
}
