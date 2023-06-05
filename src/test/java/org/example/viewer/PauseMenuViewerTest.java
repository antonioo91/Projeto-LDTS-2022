package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PauseMenuViewerTest {
    private PauseMenuViewer pauseMenuViewer;
    private GUI gui;
    private Menu menu;

    @BeforeEach
    void setup() throws IOException {
        List<String> PauseMenuEntries = Arrays.asList("Resume ", "Restart", "Quit");
        menu = new Menu(PauseMenuEntries);
        pauseMenuViewer = new PauseMenuViewer(menu);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void draw(){
        //arrange
        int entries = menu.getNumberEntries();

        //act
        pauseMenuViewer.drawElements(gui);

        //assert
        Mockito.verify(gui, Mockito.times(entries+2)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
