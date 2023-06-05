package org.example.viewer;

import org.example.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T entity;

    public Viewer(T entity){
        this.entity = entity;
    }
    public T getEntity() {
        return entity;
    }

    public void draw(GUI gui)throws IOException{
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }
    public abstract void drawElements(GUI gui);
}