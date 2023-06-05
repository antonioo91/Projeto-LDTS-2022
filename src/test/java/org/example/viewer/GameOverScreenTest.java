package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameOverScreenTest {
    private GameOverScreenViewer gameOverScreenViewer;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setup() throws IOException {
        List<String> GameOverScreenEntries = Arrays.asList("Game Over", "Score: 10");
        menu = new Menu(GameOverScreenEntries);
        gameOverScreenViewer = new GameOverScreenViewer(menu);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void draw(){
        //arrange
        int entries = menu.getNumberEntries();

        //act
        gameOverScreenViewer.drawElements(gui);

        //assert
        Mockito.verify(gui, Mockito.times(entries)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
