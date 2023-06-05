package org.example.controller.game;

import com.sun.source.tree.ModuleTree;
import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.controller.Controllers.game.*;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomberman;
import org.example.model.menu.Menu;
import org.example.states.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaControllerTest {
    //TODO check if this a good way to test the ArenaController
    private ArenaController arenaController;
    private Bomberman bomberman;
    private Arena arena;
    private Game game;
    private PowerUpController powerUpController;
    private BombermanController bombermanController;
    private MonsterController monsterController;
    private BombController bombController;
    private ExplosionController explosionController;
    private ExitController exitController;

    private Music music;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10,10);
        bomberman = new Bomberman(new Position(5,5));
        bomberman.setLives(1);
        arena.setBomberman(bomberman);
        arena.setScore(10);

        game = Mockito.mock(Game.class);


        arenaController = new ArenaController(arena);
        powerUpController = Mockito.mock(PowerUpController.class);
        bombermanController = Mockito.mock(BombermanController.class);
        monsterController = Mockito.mock(MonsterController.class);
        bombController = Mockito.mock(BombController.class);
        explosionController = Mockito.mock(ExplosionController.class);
        exitController = Mockito.mock(ExitController.class);

        music = Mockito.spy(new Music("Level.wav"));
        Mockito.doNothing().when(music).start();
        Mockito.doNothing().when(music).stop();

    }

    @Test
    void ifBombermanDied() throws IOException {
        //arrange

        ArenaController arenaControllerSpy = Mockito.spy(arenaController);
        Mockito.when(game.readHighscore()).thenReturn(10);
        Mockito.when(game.getState()).thenReturn(new GameState(Mockito.mock(Arena.class), music));

        //act
        bomberman.decreaseLife();
        arenaControllerSpy.step(game, GUI.ACTION.NONE, 10);

        //assert
        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(GameOverScreen.class));
        Mockito.verify(music, Mockito.times(1)).start();
    }

    @Test
    void ifPlayerQuit() throws IOException {
        //arrange
        bomberman.setLives(1);
        Mockito.when(game.getState()).thenReturn(new GameState(Mockito.mock(Arena.class), music));

        //act
        arenaController.step(game, GUI.ACTION.QUIT, 10);

        //assert
        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(3)).getState();
        Mockito.verify(game, Mockito.times(1)).setPreviousState(Mockito.any(GameState.class));
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(PauseMenu.class));
        Mockito.verify(music, Mockito.times(1)).start();


    }

    @Test
    void normalStep() throws IOException {
        //arrange
        arenaController.setPowerUpController(powerUpController);
        arenaController.setBombermanController(bombermanController);
        arenaController.setMonsterController(monsterController);
        arenaController.setBombController(bombController);
        arenaController.setExplosionController(explosionController);
        arenaController.setExitController(exitController);


        //act
        arenaController.step(game, GUI.ACTION.NONE, 10);

        //assert
        Mockito.verify(powerUpController, Mockito.times(1)).step(game, GUI.ACTION.NONE, 10);
        Mockito.verify(bombermanController, Mockito.times(1)).step(game, GUI.ACTION.NONE, 10);
        Mockito.verify(monsterController, Mockito.times(1)).step(game, GUI.ACTION.NONE, 10);
        Mockito.verify(bombController, Mockito.times(1)).step(game, GUI.ACTION.NONE, 10);
        Mockito.verify(explosionController, Mockito.times(1)).step(game, GUI.ACTION.NONE, 10);
        Mockito.verify(exitController, Mockito.times(1)).step(game, GUI.ACTION.NONE, 10);

    }

    @Test
    void HighScore() throws IOException {
        //arrange
        ArenaController arenaControllerSpy = Mockito.spy(arenaController);
        bomberman.setLives(1);
        Mockito.doNothing().when(arenaControllerSpy).writeHighScore(Mockito.any(Integer.class));
        Mockito.when(game.readHighscore()).thenReturn(10);
        Mockito.when(game.getState()).thenReturn(new GameState(Mockito.mock(Arena.class), Mockito.mock(Music.class)));


        //act
        bomberman.decreaseLife();
        arena.setScore(12);
        arenaControllerSpy.step(game, GUI.ACTION.QUIT, 10);

        //assert
        Mockito.verify(game, Mockito.times(2)).getState();
        Mockito.verify(arenaControllerSpy, Mockito.times(1)).writeHighScore(12);
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(GameOverScreen.class));

    }


}
