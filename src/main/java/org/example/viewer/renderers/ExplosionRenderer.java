package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Explosion;

public class ExplosionRenderer implements Renderer<Explosion>{
    @Override
    public void draw(Explosion explosion, GUI gui){
        gui.drawExplosion(explosion.getPosition(), explosion.getAnimation(), explosion.getColor());
    }
}
