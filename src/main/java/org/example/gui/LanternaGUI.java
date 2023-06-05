package org.example.gui;
import com.googlecode.lanterna.TerminalSize;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.example.model.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private Screen screen;
    private String backgroundColor = "#149300";

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws URISyntaxException, FontFormatException, IOException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    public Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontconfig) throws IOException {
        TerminalSize size = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontconfig);
        Terminal terminal = terminalFactory.createTerminal();

        return terminal;
    }

    public Screen createScreen(Terminal terminal) throws IOException {
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        screen.startScreen();

        TextGraphics graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
        return screen;
    }

    public AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/Kitchen Sink.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 60);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Escape) return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') return ACTION.SPACE;

        return ACTION.NONE;
    }

    @Override
    public void clear() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(15, 16), ' ');
    }

    private void drawCharacter(int x, int y, char c, String color, String backgroundColor) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.putString(x, y + 1, "" + c);
    }

    //////////////////////////////// GUI IMPLEMENTATIONS ////////////////////////////////
    @Override
    public void drawPowerUp(Position position, char animation, String color) {
        drawCharacter(position.getX(), position.getY(), animation, color, this.backgroundColor);
    }

    @Override
    public void drawBomberman(Position pos, char c, String color) {
        drawCharacter(pos.getX(), pos.getY(), c, color, this.backgroundColor);
    }

    @Override
    public void drawBomb(Position pos) {
        drawCharacter(pos.getX(), pos.getY(), 'Ḿ', "#121111", this.backgroundColor);
    }

    @Override
    public void drawBreakableWall(Position pos, char animation, String color, String backgroundColor) {
        drawCharacter(pos.getX(), pos.getY(), animation, color, backgroundColor);
    }

    @Override
    public void drawExplosion(Position pos, char animation, String color) {
        drawCharacter(pos.getX(), pos.getY(),animation ,color, this.backgroundColor); }

    @Override
    public void drawMonster(Position pos, char c, String color) {
        drawCharacter(pos.getX(), pos.getY(), c, color, this.backgroundColor);
    }

    @Override
    public void drawUnbreakableWall(Position pos) {
        drawCharacter(pos.getX(), pos.getY(), 'ṛ', "#3e403f", "#939795");
    }

    @Override
    public void drawExit(Position pos, String color) {
        drawCharacter(pos.getX(), pos.getY(), '□', color, this.backgroundColor);
    }


    @Override
    public void drawText(Position position, String string, String color){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        graphics.putString(position.getX(), position.getY(), string);
    }
}
