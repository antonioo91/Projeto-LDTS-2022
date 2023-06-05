package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Bomb;

public class BombRenderer implements Renderer<Bomb>{

    @Override
    public void draw(Bomb bomb, GUI gui){
        gui.drawBomb(bomb.getPosition());
    }
}
