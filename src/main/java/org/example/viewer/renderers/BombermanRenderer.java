package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Bomberman;

public class BombermanRenderer implements Renderer<Bomberman> {
    @Override
    public void draw(Bomberman bomberman, GUI gui) {
        gui.drawBomberman(bomberman.getPosition(), bomberman.getAnimation(), bomberman.getColor());
    }
}
