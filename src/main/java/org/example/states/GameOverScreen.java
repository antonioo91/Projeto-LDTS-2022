package org.example.states;

import org.example.Music;
import org.example.controller.Controller;
import org.example.controller.Controllers.menu.GameOverScreenController;
import org.example.model.menu.Menu;
import org.example.viewer.GameOverScreenViewer;
import org.example.viewer.Viewer;

public class GameOverScreen extends State<Menu> {

    public GameOverScreen(Menu menu, Music music) {
        super(menu, music);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new GameOverScreenViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new GameOverScreenController(getModel());
    }

}

