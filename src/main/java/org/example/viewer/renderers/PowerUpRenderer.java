package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.powerups.PowerUp;

public class PowerUpRenderer implements Renderer<PowerUp> {

    @Override
    public void draw(PowerUp powerUp, GUI gui){
        gui.drawPowerUp(powerUp.getPosition(), powerUp.getAnimation(), powerUp.getColor());
    }
}
