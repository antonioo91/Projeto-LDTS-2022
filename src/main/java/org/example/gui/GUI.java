package org.example.gui;
import org.example.model.Position;
import java.io.IOException;

public interface GUI {
    //////////////////////////////// GUI IMPLEMENTATIONS ////////////////////////////////
    void drawBomberman(Position pos, char c, String color);
    void drawBomb(Position pos);
    void drawExplosion(Position pos, char animation, String color);
    void drawBreakableWall(Position pos, char animation, String color, String backgroundColor);
    void drawUnbreakableWall(Position pos);
    void drawMonster(Position pos, char c, String color);
    void drawText(Position position, String text, String color);
    void drawPowerUp(Position position, char animation, String color);
    void drawExit(Position pos, String color);
    void clear();
    void refresh() throws IOException;

    void close() throws IOException;

    ACTION getNextAction() throws IOException;
    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, SPACE}
}
