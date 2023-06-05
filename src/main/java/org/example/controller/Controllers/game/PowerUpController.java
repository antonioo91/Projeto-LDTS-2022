package org.example.controller.Controllers.game;

import org.example.Game;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.powerups.PowerUp;

import java.util.*;
import java.io.IOException;
import java.util.ArrayList;

public class PowerUpController extends Controller<Arena> {

    private List<PowerUp> activePowerUps = new ArrayList<>();
    public PowerUpController(Arena arena) {
        super(arena);
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException{

        List<PowerUp> tmpPowerUps = new ArrayList<PowerUp>(getModel().getPowerUps());
        for(PowerUp powerUp : tmpPowerUps){
            if(powerUp.getPosition().equals(getModel().getBomberman().getPosition())){
                powerUp.doEffect(getModel().getBomberman());
                getModel().getPowerUps().remove(powerUp);
                activePowerUps.add(powerUp);
            }

            if(time - powerUp.getLastAnimation() > 300){
                powerUp.nextAnimation();
                powerUp.setLastAnimation(time);
            }
        }

        List<PowerUp> tmpActivePowerUps = new ArrayList<PowerUp>(activePowerUps);
        for(PowerUp powerUp : tmpActivePowerUps){
            if(time - powerUp.getLastStepTime() > 1000){
                powerUp.setTimer(powerUp.getTimer() - 1);
                if(powerUp.getTimer() <= 0){
                    powerUp.removeEffect(getModel().getBomberman());
                    activePowerUps.remove(powerUp);
                }
                powerUp.setLastStepTime(time);
            }
        }
    }

}
