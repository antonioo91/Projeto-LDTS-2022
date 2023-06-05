package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.powerups.Invincibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvincibilityTest {

    private Invincibility invincibility;
    private Bomberman bomberman;

    @BeforeEach
    void setup(){
        invincibility = new Invincibility(new Position(0,0), 1);
        bomberman = new Bomberman(new Position(0,0));
    }

    @Test
    void testDoEffect(){
        assertFalse(bomberman.isInvincible());
        invincibility.doEffect(bomberman);
        assertTrue(bomberman.isInvincible());
        invincibility.removeEffect(bomberman);
        assertFalse(bomberman.isInvincible());
    }

    @Test
    void testAnimation(){
        assertEquals('ṗ', invincibility.getAnimation());
        assertEquals("#F9F295", invincibility.getColor());
        invincibility.nextAnimation();
        assertEquals('ṙ', invincibility.getAnimation());
        assertEquals("#E0AA3E", invincibility.getColor());
        invincibility.nextAnimation();
        assertEquals('ṗ', invincibility.getAnimation());
        assertEquals("#F9F295", invincibility.getColor());
    }
}
