package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Entity;
import org.example.viewer.renderers.*;
import org.example.viewer.renderers.PowerUpRenderer;


import java.util.List;

public class GameViewer extends Viewer<Arena> {

    public GameViewer(Arena arena){
        super(arena);
    }
    @Override
    public void drawElements(GUI gui) {

        drawElements(gui, getEntity().getUnbreakableWalls(), new UnbreakableWallRenderer());
        drawElement(gui, getEntity().getExit(), new ExitRenderer());

        drawElements(gui, getEntity().getBombs(), new BombRenderer());
        drawElements(gui, getEntity().getExplosions(), new ExplosionRenderer());

        drawElements(gui, getEntity().getPowerUps(), new PowerUpRenderer());
        drawElements(gui, getEntity().getBreakableWalls(), new BreakableWallRenderer());


        drawElements(gui, getEntity().getMonsters(), new MonsterRenderer());
        drawElement(gui, getEntity().getBomberman(), new BombermanRenderer());

        gui.drawText(new Position(0,0), "Lives", "#FFD700");
        gui.drawText(new Position(10,0), "Score", "#FFD700");
        gui.drawText(new Position(0,1), "♥".repeat(getEntity().getBomberman().getLives()), "#FF0000");
        gui.drawText(new Position(15-getEntity().getScore().toString().length(),1), getEntity().getScore().toString(), "#FFFFFF");
    }

    public  <T extends Entity> void drawElement(GUI gui, T entity, Renderer<T> viewer){
        viewer.draw(entity, gui);
    }

    public <T extends  Entity> void drawElements(GUI gui, List<T> listEntities, Renderer<T> viewer){
        for (T item: listEntities){
            drawElement(gui, item, viewer);
        }
    }
}
