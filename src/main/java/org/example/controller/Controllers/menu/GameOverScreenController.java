package org.example.controller.Controllers.menu;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.menu.Menu;
import org.example.states.MenuState;

import java.io.IOException;

public class GameOverScreenController extends Controller<Menu> {
    public GameOverScreenController(Menu menu){
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case SELECT:
                int highScore = game.readHighscore();
                game.getState().getMusic().stop();
                game.setState(new MenuState(new Menu(highScore), new Music("Menu.wav")));
                game.getState().getMusic().start();
                break;
            default:
                break;
        }
    }

}
