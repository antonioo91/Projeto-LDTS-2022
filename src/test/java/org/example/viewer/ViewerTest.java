package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ViewerTest {

    private GUI gui;
    private MenuViewer menuViewer;

    private Menu menu;


    @BeforeEach
    void setUp() throws IOException {
        gui = Mockito.mock(GUI.class);

        menu = new Menu(0);
        menuViewer = new MenuViewer(menu);
    }

    @Test
    void draw() throws IOException {
        //arrange
        MenuViewer menuViewerSpy = Mockito.spy(menuViewer);
        //act
        menuViewerSpy.draw(gui);

        //assert
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(menuViewerSpy, Mockito.times(1)).drawElements(Mockito.any(GUI.class));
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
