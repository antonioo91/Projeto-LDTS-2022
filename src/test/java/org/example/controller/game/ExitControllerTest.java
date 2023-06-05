package org.example.controller.game;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controllers.game.ExitController;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.Exit;
import org.example.model.game.entities.Monster;
import org.example.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExitControllerTest {

    private ExitController exitController;
    private Bomberman bomberman;
    private Exit exit;
    private Arena arena;
    private Game game;
    private Music music;
    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10, 10);
        exit = new Exit(new Position(3,1));
        bomberman = new Bomberman(new Position(5,5));
        arena.setExit(exit);
        arena.setBomberman(bomberman);

        exitController = new ExitController(arena);

        game = Mockito.mock(Game.class);

        music = Mockito.spy(new Music("Level.wav"));
        Mockito.doNothing().when(music).start();
        Mockito.doNothing().when(music).stop();
    }

    @Test
    void ExitClosed() throws IOException {

        //arrange
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(5,1));
        monsters.add(monster);
        arena.setMonsters(monsters);

        //act
        exitController.step(game, GUI.ACTION.NONE, 10);

        //assert
        assertFalse(arena.getExit().isOpen());
        assertEquals(arena.getExit().getColor(), "#000000");


    }

    @Test
    void ExitOpen() throws IOException {

        //arrange
        List<Monster> monsters = new ArrayList<>();
        arena.setMonsters(monsters);

        //act
        exitController.step(game, GUI.ACTION.NONE, 10);

        //assert
        assertTrue(arena.getExit().isOpen());
        assertEquals(arena.getExit().getColor(), "#FFFFFF");

    }

    @Test
    void NewState() throws IOException {

        //arrange
        Mockito.when(game.getState()).thenReturn(Mockito.mock(GameState.class));
        Mockito.when(game.getState().getMusic()).thenReturn(music);
        List<Monster> monsters = new ArrayList<>();
        arena.setMonsters(monsters);
        bomberman.setPosition(exit.getPosition());

        //act
        exitController.step(game, GUI.ACTION.NONE, 10);

        //assert
        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(3)).getState();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(GameState.class));
        Mockito.verify(music, Mockito.times(1)).start();
    }


}
