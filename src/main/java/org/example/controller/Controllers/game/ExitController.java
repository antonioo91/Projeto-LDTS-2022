package org.example.controller.Controllers.game;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.game.arena.Arena;
import org.example.model.game.arena.ArenaBuilder;
import org.example.model.game.entities.Bomb;
import org.example.states.GameState;

import java.io.IOException;
import java.util.Random;

public class ExitController extends Controller<Arena> {

    public ExitController(Arena arena){
        super(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (getModel().getMonsters().isEmpty()){
            getModel().getExit().open();
        }
        else{
            getModel().getExit().close();
        }

        if (getModel().getBomberman().getPosition().equals(getModel().getExit().getPosition()) && getModel().getExit().isOpen()){
            Random rand = new Random();
            int i = rand.nextInt(2);
            game.getState().getMusic().stop();
            getModel().getBomberman().setInvincible(false);

            for (Bomb unused: getModel().getBombs()){
                getModel().getBomberman().setBombQuantity(getModel().getBomberman().getBombQuantity()+1);
            }

            game.setState(new GameState(new ArenaBuilder(i + 1).createArena(getModel().getBomberman(), getModel().getScore()), new Music("Level.wav")));
            game.getState().getMusic().start();
        }
    }
}
