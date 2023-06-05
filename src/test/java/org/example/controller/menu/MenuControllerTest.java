package org.example.controller.menu;

import com.sun.source.tree.ModuleTree;
import org.example.Game;
import org.example.Music;
import org.example.controller.Controllers.menu.MenuController;
import org.example.gui.GUI;
import org.example.model.game.arena.Arena;
import org.example.model.menu.Menu;
import org.example.states.GameState;
import org.example.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuControllerTest {

    private MenuController menuController;
    private Menu menu;
    private Game game;


    @BeforeEach
    void setup() throws IOException {
        menu = new Menu(0);
        menuController = new MenuController(menu);
        game = Mockito.mock(Game.class);

    }

    @Test
    void up() throws IOException {
        menuController.step(game, GUI.ACTION.UP, 10);
        assertTrue(menu.isSelected(1));
        menuController.step(game, GUI.ACTION.UP, 10);
        assertTrue(menu.isSelected(0));
    }

    @Test
    void down() throws IOException {
        menuController.step(game, GUI.ACTION.DOWN, 10);
        assertTrue(menu.isSelected(1));

        //looping around the menu
        menuController.step(game, GUI.ACTION.DOWN, 10);
        assertTrue(menu.isSelected(0));
    }

    @Test
    void selectStart() throws IOException {
        //arrange
        Music music = Mockito.spy(new Music("Level.wav"));
        Mockito.doNothing().when(music).start();
        Mockito.doNothing().when(music).stop();
        Mockito.when(game.getState()).thenReturn(new GameState(Mockito.mock(Arena.class), music));

        //act
        menuController.step(game, GUI.ACTION.SELECT, 10);

        //assert
        assertEquals(menu.getSelectedEntry(), "Start");
        Mockito.verify(music, Mockito.times(1)).start();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any());
        Mockito.verify(music, Mockito.times(1)).stop();
    }

    @Test
    void selectExit() throws IOException {
        //arrange
        Mockito.when(game.getState()).thenReturn(new GameState(Mockito.mock(Arena.class), Mockito.mock(Music.class)));

        //act
        menuController.step(game, GUI.ACTION.DOWN, 10); //select exit
        menuController.step(game, GUI.ACTION.SELECT, 10);

        //assert
        assertEquals(menu.getSelectedEntry(), "Exit ");
        Mockito.verify(game, Mockito.times(1)).getState();
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

}
