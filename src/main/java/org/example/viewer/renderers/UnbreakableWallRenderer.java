package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.UnbreakableWall;

public class UnbreakableWallRenderer implements Renderer<UnbreakableWall>{
    @Override
    public void draw(UnbreakableWall unbreakableWall, GUI gui){
        gui.drawUnbreakableWall(unbreakableWall.getPosition());
    }
}
