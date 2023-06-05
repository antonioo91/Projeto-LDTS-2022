package org.example.controller.menu;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controllers.menu.MenuController;
import org.example.controller.Controllers.menu.PauseMenuController;
import org.example.gui.GUI;
import org.example.model.game.arena.Arena;
import org.example.model.menu.Menu;
import org.example.states.GameOverScreen;
import org.example.states.GameState;
import org.example.states.MenuState;
import org.example.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PauseMenuControllerTest {
    private Menu menu;
    private PauseMenuController controller;
    private Game game;
    private Music music;

    @BeforeEach
    void setup() throws IOException {
        menu = new Menu(Arrays.asList("Resume ", "Restart", "Quit"));
        controller = new PauseMenuController(menu);
        game = Mockito.mock(Game.class);

        music = Mockito.spy(new Music("Level.wav"));
        Mockito.doNothing().when(music).start();
        Mockito.doNothing().when(music).stop();
        Mockito.when(game.getState()).thenReturn(new GameState(new Arena(1,1), music));
    }

    @Test
    void up() throws IOException {
        controller.step(game, GUI.ACTION.UP, 100);
        assertTrue(menu.isSelected(2));
        controller.step(game, GUI.ACTION.UP, 100);
        assertTrue(menu.isSelected(1));
    }

    @Test
    void down() throws IOException {
        controller.step(game, GUI.ACTION.DOWN, 100);
        assertTrue(menu.isSelected(1));

        //looping around the menu
        controller.step(game, GUI.ACTION.DOWN, 100);
        assertTrue(menu.isSelected(2));
    }

    @Test
    void selectResume() throws IOException {
        controller.step(game, GUI.ACTION.SELECT, 100);

        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any());
        Mockito.verify(game, Mockito.times(1)).setPreviousState(Mockito.any());
        Mockito.verify(music, Mockito.times(1)).start();
    }

    @Test
    void selectQuit() throws IOException {
        controller.step(game, GUI.ACTION.DOWN, 100); //select restart
        controller.step(game, GUI.ACTION.DOWN, 100); // select quit
        controller.step(game, GUI.ACTION.SELECT, 100);

        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(MenuState.class));

        Mockito.verify(music, Mockito.times(1)).start();
    }

    @Test
    void selectRestart() throws IOException {
        controller.step(game, GUI.ACTION.DOWN, 100); //select restart
        controller.step(game, GUI.ACTION.SELECT, 100);

        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(GameState.class));
        Mockito.verify(music, Mockito.times(1)).start();
    }

    @Test
    void quit() throws IOException {
        controller.step(game, GUI.ACTION.QUIT, 10);

        Mockito.verify(music, Mockito.times(1)).stop();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any());
        Mockito.verify(game, Mockito.times(1)).setPreviousState(Mockito.any());

        Mockito.verify(music, Mockito.times(1)).start();
    }
}
