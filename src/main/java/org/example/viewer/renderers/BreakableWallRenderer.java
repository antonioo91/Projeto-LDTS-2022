package org.example.viewer.renderers;
import org.example.gui.GUI;
import org.example.model.game.entities.BreakableWall;

public class BreakableWallRenderer implements Renderer<BreakableWall>{
    @Override
    public void draw(BreakableWall breakableWall, GUI gui){
        gui.drawBreakableWall(breakableWall.getPosition(), breakableWall.getAnimation(), breakableWall.getColor(), breakableWall.getBackgroundColor());
    }
}
