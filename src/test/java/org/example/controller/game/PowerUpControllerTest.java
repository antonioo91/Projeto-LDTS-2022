package org.example.controller.game;

import org.example.controller.Controllers.game.PowerUpController;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.powerups.HP;
import org.example.model.game.entities.powerups.PowerUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PowerUpControllerTest {
    private PowerUpController powerUpController;
    private Arena arena;
    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10, 10);
        powerUpController = new PowerUpController(arena);
    }

    @Test
    void BombermanCatchesPowerUP() throws IOException {
        //arrange
        HP powerUp = new HP(new Position(1,1), 1);
        powerUp.setTimer(1);
        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setLives(1);
        arena.setBomberman(bomberman);
        arena.addPowerUp(powerUp);

        //assert
        assertEquals(arena.getPowerUps().size(), 1);
        assertEquals(powerUp.getAnimation(), '♥');

        //act
        powerUpController.step(null, null, 1100);

        //assert
        assertTrue(arena.getBomberman().getLives()>1);
        assertEquals(arena.getPowerUps().size(), 0);
        assertEquals(powerUp.getAnimation(), 'Ṉ');
        assertEquals(powerUp.getTimer(), 0);
        assertEquals(powerUp.getLastStepTime(), 1100);
        //Mockito.verify(Mockito.spy(powerUp)).removeEffect(Mockito.any(Bomberman.class));
    }

    @Test
    void testAnimation() throws IOException {
        //arrange
        HP powerUp = new HP(new Position(1,1), 1);
        powerUp.setTimer(1);
        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setLives(1);
        arena.setBomberman(bomberman);
        arena.addPowerUp(powerUp);

        //assert
        assertEquals(powerUp.getAnimation(), '♥');
        assertEquals(powerUp.getLastAnimation(), 0);

        //act
        powerUpController.step(null, null, 400);

        //assert
        assertEquals(powerUp.getAnimation(), 'Ṉ');
        assertEquals(powerUp.getLastAnimation(), 400);
    }

    @Test
    void testDuration() throws IOException {
        //arrange
        HP powerUp = new HP(new Position(1,1), 1);
        powerUp.setTimer(1);
        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setLives(1);
        arena.setBomberman(bomberman);
        arena.addPowerUp(powerUp);

        //assert
        assertEquals(powerUp.getTimer(), 1);

        //act
        powerUpController.step(null, null, 1100);

        //assert
        assertEquals(powerUp.getTimer(), 0);

    }
}
