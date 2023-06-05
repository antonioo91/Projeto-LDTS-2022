package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Entity;

public interface Renderer<T extends Entity> {
    public void draw(T entity, GUI gui);
}
