package org.example.controller.game;

import org.example.controller.Controllers.game.ExplosionController;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomb;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.Explosion;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.powerups.HP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplosionControllerTest {
    private ExplosionController explosionController;
    private Arena arena;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10, 10);

        explosionController = new ExplosionController(arena);
    }

    @Test
    void AnotherBombInExplosion() throws IOException {
        //arrange
        Explosion explosion = new Explosion(new Position(1,1));
        arena.addExplosion(explosion);

        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setInvincible(true);
        arena.setBomberman(bomberman);

        Bomb bomb = new Bomb(new Position(1,1));
        bomb.setTimer(2);
        arena.addBomb(bomb);

        //act
        explosionController.step(null, null, 1100);

        //assert
        assertEquals(arena.getBombs().get(0).getTimer(), 1);
    }

    @Test
    void BombermanInExplosion() throws IOException {
        //arrange
        Explosion explosion = new Explosion(new Position(1,1));
        arena.addExplosion(explosion);

        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setInvincible(false);
        bomberman.setLives(2);
        arena.setBomberman(bomberman);

        //act
        explosionController.step(null, null, 1100);

        //assert
        assertEquals(arena.getBomberman().getLives(), 1);
        assertEquals(arena.getPowerUps().size(), 1);
        assertEquals(arena.getBomberman().getPosition().getX(), 1);
        assertEquals(arena.getBomberman().getPosition().getY(), 2);

    }

    @Test
    void PowerUpInExplosion() throws IOException {
        //arrange
        Explosion explosion = new Explosion(new Position(1,1));
        arena.addExplosion(explosion);

        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setInvincible(true);
        bomberman.setLives(2);
        arena.setBomberman(bomberman);

        HP powerUp = new HP(new Position(1,1), 2);
        arena.addPowerUp(powerUp);


        //assert
        assertEquals(arena.getPowerUps().size(), 1);

        //act
        explosionController.step(null, null, 1100);

        //assert
        assertEquals(arena.getPowerUps().size(), 0);
    }

    @Test
    void MonsterInExplosion() throws IOException {
        //arrange
        Explosion explosion = new Explosion(new Position(1,1));
        arena.addExplosion(explosion);

        Bomberman bomberman = new Bomberman(new Position(1,1));
        bomberman.setInvincible(true);
        bomberman.setLives(2);
        arena.setBomberman(bomberman);

        arena.addMonster(new Position(1,1));
        arena.setScore(0);

        Monster monster = arena.getMonsters().get(0);

        //assert
        assertEquals(arena.getMonsters().size(), 1);

        //act
        explosionController.step(null, null, 1100);

        //assert
        assertEquals(arena.getMonsters().size(), 0);


        assertEquals(arena.getScore(), 100*(int)pow(2, monster.getLevel()));

    }

    @Test
    void testAnimation() throws IOException {
        //arrange
        Explosion explosion = new Explosion(new Position(5,5));
        arena.addExplosion(explosion);
        Bomberman bomberman = new Bomberman(new Position(1,1));

        arena.setBomberman(bomberman);

        //assert
        assertEquals(explosion.getTimer(), 16);
        assertEquals(explosion.getLastStepTime(), 0);
        assertEquals(explosion.getColor(), "#e25822");

        //act
        explosionController.step(null, null, 200);

        //assert
        assertEquals(explosion.getTimer(), 14);
        assertEquals(explosion.getLastStepTime(), 200);
        assertEquals(explosion.getColor(), "#e25822");

        //act
        explosionController.step(null, null, 400);
        explosionController.step(null, null, 600);

        //assert
        assertEquals(explosion.getTimer(), 10);
        assertEquals(explosion.getLastStepTime(), 600);
        assertEquals(explosion.getColor(), "#f1bc31");

        //act
        explosionController.step(null, null, 800);

        //assert
        assertEquals(explosion.getTimer(), 8);
        assertEquals(explosion.getLastStepTime(), 800);
        assertEquals(explosion.getColor(), "#f1bc31");

    }


}
