package org.example.states;

import org.example.Music;
import org.example.controller.Controller;
import org.example.controller.Controllers.menu.PauseMenuController;
import org.example.model.menu.Menu;
import org.example.viewer.PauseMenuViewer;
import org.example.viewer.Viewer;

public class PauseMenu extends State<Menu> {

    public PauseMenu(Menu menu, Music music) {
        super(menu, music);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new PauseMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new PauseMenuController(getModel());
    }

}


