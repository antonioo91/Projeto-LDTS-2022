package org.example.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.example.gui.GUI;
import org.example.gui.LanternaGUI;
import org.example.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LanternaGUITest {

    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;


    @BeforeEach
    void setup(){
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }

    @Test
    void refresh() throws IOException {
        gui.refresh();

        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void close() throws IOException{
        gui.close();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    void clear(){
        gui.clear();

        Mockito.verify(screen, Mockito.times(1)).newTextGraphics();
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(15, 16), ' ');
    }

    @Test
    void drawBomberman(){
            gui.drawBomberman(new Position(2,2), 'ṗ', "#FFFFFF");

            Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
            Mockito.verify(tg, Mockito.times(1)).putString(2,3, "ṗ");
    }

    @Test
    void drawBomb(){
        gui.drawBomb(new Position(2,2));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#121111"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3, "Ḿ");
    }

    @Test
    void drawBreakableWall(){
        gui.drawBreakableWall(new Position(2,2), 'Ṕ', "#B2B2B2", "#6C6F6E");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#B2B2B2"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#6C6F6E"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3, "Ṕ");
    }

    @Test
    void drawMonster(){
        gui.drawMonster(new Position(2,2), 'A', "#FFFFFF");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3, "A");
    }

    @Test
    void drawUnbreakableWall(){
        gui.drawUnbreakableWall(new Position(2,2));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#3e403f"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#939795"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3, "ṛ");
    }

    @Test
    void drawExit(){
        gui.drawExit(new Position(2,2), "#ffffff");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3, "□");
    }

    @Test
    void testActions() throws IOException {
        //arrange
        List<KeyStroke> keystrokes = Arrays.asList(new KeyStroke(KeyType.ArrowUp), new KeyStroke(KeyType.ArrowDown),
                new KeyStroke(KeyType.ArrowLeft), new KeyStroke(KeyType.ArrowRight), new KeyStroke(KeyType.EOF),
                new KeyStroke(KeyType.Escape), new KeyStroke('q', false, false),
                new KeyStroke(KeyType.Enter), new KeyStroke(' ', false, false), null);

        List<GUI.ACTION> actions = Arrays.asList(GUI.ACTION.UP, GUI.ACTION.DOWN, GUI.ACTION.LEFT, GUI.ACTION.RIGHT,
                GUI.ACTION.QUIT, GUI.ACTION.QUIT, GUI.ACTION.QUIT, GUI.ACTION.SELECT, GUI.ACTION.SPACE, GUI.ACTION.NONE);


        for (int i = 0; i < 10; i++){
            KeyStroke ks = keystrokes.get(i);
            GUI.ACTION ac = actions.get(i);
            Mockito.when(screen.pollInput()).thenReturn(ks);

            //act
            GUI.ACTION res = gui.getNextAction();

            //assert
            assertEquals(ac, res);
        }


    }

    @Test
    void drawPowerUp(){
        gui.drawPowerUp(new Position(2,2), 'P', "#FFFFFF");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3,"P");

    }

    @Test
    void drawExplosion(){
        gui.drawExplosion(new Position(2,2), 'ḻ', "#e25822");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#e25822"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,3,"ḻ");
    }

    @Test
    void drawText(){
        gui.drawText(new Position(2,2), "Hello", "#FFFFFF");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#149300"));
        Mockito.verify(tg, Mockito.times(1)).putString(2,2,"Hello");
    }

}
