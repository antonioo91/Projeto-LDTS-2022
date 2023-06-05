package org.example.controller.Controllers.menu;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.game.arena.ArenaBuilder;
import org.example.model.menu.Menu;
import org.example.states.GameState;

import java.io.IOException;
import java.util.Random;

public class MenuController extends Controller<Menu>{
    public MenuController(Menu menu){
        super(menu);
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
                game.getState().getMusic().stop();
                if (getModel().getSelectedEntry().equals("Exit ")){
                    game.setState(null);}
                if (getModel().getSelectedEntry().equals("Start")) {
                    Random rand = new Random();
                    int i = rand.nextInt(3);
                    game.setState(new GameState(new ArenaBuilder(i+1).createArena(), new Music("Level.wav")));
                    game.getState().getMusic().start();
                }
                break;
            default:
                break;
        }
    }
}
