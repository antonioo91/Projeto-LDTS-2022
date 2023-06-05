package org.example.viewer;

import org.example.gui.GUI;
import org.example.gui.LanternaGUI;
import org.example.model.Position;
import org.example.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuViewerTest {

    private GUI gui;
    private MenuViewer menuViewer;

    private Menu menu;

    @BeforeEach
    void setUp() throws IOException {
        gui = Mockito.mock(LanternaGUI.class);

        menu = new Menu(0);
        //"Start", "Exit "
        menuViewer = new MenuViewer(menu);
    }

    @Test
    void drawElements() {
        //arrange
        MenuViewer menuViewerSpy = Mockito.spy(menuViewer);

        //act
        menuViewerSpy.drawElements(gui);

        //assert
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(5, 2), "Super", "#FFD700");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(3,3), "Bomberman", "#FFFFFF");
        Mockito.verify(menuViewerSpy).drawMenuEntries(Mockito.any(GUI.class));
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(3, 10), "Highscore", "#FFD700");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(7-menu.getHighscore().toString().length()/2, 11), menu.getHighscore().toString(), "#FFFFFF");
    }

    @Test
    void drawMenuEntries() {
        //arrange
        int entries = menu.getNumberEntries();
        //act
        menuViewer.drawMenuEntries(gui);

        //assert
        Mockito.verify(gui, Mockito.times(entries+2)).drawText(Mockito.any(Position.class), Mockito.anyString(), Mockito.anyString());
    }


}
