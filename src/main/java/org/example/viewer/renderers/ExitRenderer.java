package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Exit;

public class ExitRenderer implements Renderer<Exit>{

    @Override
    public void draw(Exit exit, GUI gui){
        gui.drawExit(exit.getPosition(), exit.getColor());

    }
}
