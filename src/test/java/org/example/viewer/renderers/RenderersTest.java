package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.*;
import org.example.model.game.entities.powerups.PowerUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RenderersTest {
    private GUI gui;
    private BombermanRenderer bombermanRenderer;
    private BombRenderer bombRenderer;
    private BreakableWallRenderer breakableWallRenderer;
    private UnbreakableWallRenderer unbreakableWallRenderer;
    private ExplosionRenderer explosionRenderer;
    private ExitRenderer exitRenderer;
    private MonsterRenderer monsterRenderer;
    private PowerUpRenderer powerUpRenderer;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        bombermanRenderer = new BombermanRenderer();
        bombRenderer = new BombRenderer();
        breakableWallRenderer = new BreakableWallRenderer();
        unbreakableWallRenderer = new UnbreakableWallRenderer();
        explosionRenderer = new ExplosionRenderer();
        exitRenderer = new ExitRenderer();
        monsterRenderer = new MonsterRenderer();
        powerUpRenderer = new PowerUpRenderer();
    }

    @Test
    void bombermanRenderer() {
        //act
        bombermanRenderer.draw(Mockito.mock(Bomberman.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawBomberman(Mockito.any(), Mockito.any(Character.class), Mockito.any());
    }

    @Test
    void bombRenderer() {
        //act
        bombRenderer.draw(Mockito.mock(Bomb.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawBomb(Mockito.any());
    }

    @Test
    void breakableWallRenderer() {
        //act
        breakableWallRenderer.draw(Mockito.mock(BreakableWall.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawBreakableWall(Mockito.any(),Mockito.any(Character.class),Mockito.any(),Mockito.any());
    }

    @Test
    void unbreakableWallRenderer() {
        //act
        unbreakableWallRenderer.draw(Mockito.mock(UnbreakableWall.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawUnbreakableWall(Mockito.any());
    }

    @Test
    void explosionRenderer() {
        //act
        explosionRenderer.draw(Mockito.mock(Explosion.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawExplosion(Mockito.any(),Mockito.any(Character.class),Mockito.any());
    }

    @Test
    void exitRenderer() {
        //act
        exitRenderer.draw(Mockito.mock(Exit.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawExit(Mockito.any(),Mockito.any());
    }

    @Test
    void monsterRenderer() {
        //act
        monsterRenderer.draw(Mockito.mock(Monster.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawMonster(Mockito.any(), Mockito.any(Character.class), Mockito.any());
    }

    @Test
    void powerUpRenderer() {
        //act
        powerUpRenderer.draw(Mockito.mock(PowerUp.class), gui);
        //assert
        Mockito.verify(gui, Mockito.times(1)).drawPowerUp(Mockito.any(), Mockito.any(Character.class),Mockito.any());
    }
}
