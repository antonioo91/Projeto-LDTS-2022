package org.example.controller.Controllers.menu;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.game.arena.ArenaBuilder;
import org.example.model.menu.Menu;
import org.example.states.GameState;
import org.example.states.MenuState;

import java.io.IOException;
import java.util.Random;

public class PauseMenuController extends Controller<Menu> {
    public PauseMenuController(Menu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().getSelectedEntry().equals("Resume ")){
                    game.getState().getMusic().stop();
                    game.setState(game.getPreviousState());
                    game.setPreviousState(null);
                    game.getState().getMusic().start();
                }
                if (getModel().getSelectedEntry().equals("Quit")){
                    game.getState().getMusic().stop();
                    game.setState(new MenuState(new Menu(game.readHighscore()), new Music("Menu.wav")));
                    game.getState().getMusic().start();
                }
                if (getModel().getSelectedEntry().equals("Restart")){
                    Random rand = new Random();
                    int i = rand.nextInt(2);
                    game.getState().getMusic().stop();
                    game.setState(new GameState(new ArenaBuilder(i+1).createArena(), new Music("Level.wav")));
                    game.getState().getMusic().start();
                }
                break;
            case QUIT:
                game.getState().getMusic().stop();
                game.setState(game.getPreviousState());
                game.setPreviousState(null);
                game.getState().getMusic().start();
                break;

            default:
                break;

        }
    }

}
