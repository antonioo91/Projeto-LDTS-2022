package org.example.model.menu;

import org.example.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    void setup() throws IOException {
        menu = new Menu(Arrays.asList("Start", "Exit "));
    }

    @Test
    void SelectEntryTest() {
        //act
        menu.nextEntry();
        //assert
        assertEquals("Exit ", menu.getSelectedEntry());
        assertEquals(2, menu.getNumberEntries());
    }


    @Test
    void testHighscore() throws IOException {
        //act
        menu = new Menu(100);
        //assert
        assertEquals(100, menu.getHighscore());
    }

}
