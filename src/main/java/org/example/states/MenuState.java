package org.example.states;


import org.example.Music;
import org.example.controller.Controller;
import org.example.controller.Controllers.menu.MenuController;
import org.example.model.menu.Menu;
import org.example.viewer.MenuViewer;
import org.example.viewer.Viewer;

public class MenuState extends State<Menu> {

    public MenuState(Menu model, Music music) {
        super(model, music);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

}
