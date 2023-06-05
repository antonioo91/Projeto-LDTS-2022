package org.example.states;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {

    private Music music;
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model, Music music) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
        this.music = music;
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();


    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }

    public Music getMusic() {
        return music;
    }
}
