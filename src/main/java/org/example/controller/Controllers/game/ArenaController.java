package org.example.controller.Controllers.game;


import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.game.arena.Arena;
import org.example.model.menu.Menu;
import org.example.states.GameOverScreen;
import org.example.states.PauseMenu;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ArenaController extends Controller<Arena> {
    private BombermanController bombermanController;
    private MonsterController monsterController;
    private BombController bombController;
    private ExplosionController explosionController;
    private PowerUpController powerUpController;
    private ExitController exitController;

    public ArenaController(Arena arena) {
        super(arena);
        setBombermanController(new BombermanController(arena));
        setMonsterController(new MonsterController(arena));
        setBombController(new BombController(arena));
        setExplosionController( new ExplosionController(arena));
        setPowerUpController(new PowerUpController(arena));
        setExitController(new ExitController(arena));
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (getModel().getBomberman().getLives() == 0){
            game.getState().getMusic().stop();

            List<String> GameOverScreenEntries = new ArrayList<String>();
            if(getModel().getScore() > game.readHighscore()){
                writeHighScore(getModel().getScore());
                GameOverScreenEntries = Arrays.asList("New Highscore!", "Score: " + getModel().getScore());
            }
            else{
                GameOverScreenEntries = Arrays.asList("Game Over", "Score: " + getModel().getScore());
            }

            game.setState(new GameOverScreen(new Menu(GameOverScreenEntries), new Music("GameOver.wav")));
            game.getState().getMusic().start();

        } else if(action == GUI.ACTION.QUIT){
            game.getState().getMusic().stop();
            List<String> PauseMenuEntries = Arrays.asList("Resume ", "Restart", "Quit");
            game.setPreviousState(game.getState());
            game.setState(new PauseMenu(new Menu(PauseMenuEntries), new Music("Pause.wav")));
            game.getState().getMusic().start();
        }
        else {
            powerUpController.step(game, action, time);
            bombermanController.step(game, action, time);
            monsterController.step(game, action, time);
            bombController.step(game, action, time);
            explosionController.step(game, action, time);
            exitController.step(game, action, time);

        }
    }

    public void writeHighScore(Integer highScore) throws IOException {

        try {
            FileWriter writer = new FileWriter("highscore.txt", UTF_8);
            writer.write(highScore.toString());
            writer.close();
        } catch (IOException e) {
            System.out.printf("An exception occurred: %s", e);
        }
    }

    public void setBombermanController(BombermanController bombermanController) {
        this.bombermanController = bombermanController;
    }

    public void setMonsterController(MonsterController monsterController) {
        this.monsterController = monsterController;
    }

    public void setBombController(BombController bombController) {
        this.bombController = bombController;
    }

    public void setExplosionController(ExplosionController explosionController) {
        this.explosionController = explosionController;
    }

    public void setPowerUpController(PowerUpController powerUpController) {
        this.powerUpController = powerUpController;
    }

    public void setExitController(ExitController exitController) {
        this.exitController = exitController;
    }



}


