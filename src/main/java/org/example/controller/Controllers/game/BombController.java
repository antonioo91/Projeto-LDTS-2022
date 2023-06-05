package org.example.controller.Controllers.game;

import org.example.Game;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomb;
import org.example.model.game.entities.BreakableWall;
import org.example.model.game.entities.Explosion;

import java.util.*;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.*;


public class BombController extends Controller<Arena> {

    private long LastTick;
    private List<BreakableWall> explodingWalls = new ArrayList<BreakableWall>();
    public BombController(Arena arena) { super(arena); }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        if (time - LastTick > 100) {
            List<Bomb> tmpBombs = new ArrayList<Bomb>(getModel().getBombs());
            for(Bomb bomb: tmpBombs ) {

                bomb.setTimer(bomb.getTimer()-1);

                if(bomb.getTimer() == 0){
                    explodes(bomb);
                    getModel().getBombs().remove(bomb);
                }
            }
            this.LastTick = time;
        }

        //animate walls breaking
        List<BreakableWall> tmpExplodingWalls = new ArrayList<BreakableWall>(explodingWalls);
        for(BreakableWall wall: tmpExplodingWalls){
            if (time - wall.getLastTime() > 250) {
                wall.setTimer(wall.getTimer()-1);
                wall.nextAnimation();
                if(wall.getTimer()<=0){
                    getModel().getBreakableWalls().remove(wall);
                    explodingWalls.remove(wall);
                }
                wall.setLastTime(time);
            }
        }

    }

    public void explodes(Bomb bomb) {

        //add a bomb to bomberman
        getModel().getBomberman().setBombQuantity(getModel().getBomberman().getBombQuantity()+1);

        //explode previously placed bomb
        getModel().addExplosion(new Explosion(bomb.getPosition()));

        boolean againstWall = false;

        for (int angle = 0; angle <= 270; angle += 90) {

            for (int distance = 1; distance <= getModel().getBomberman().getExplosionRadius(); distance++) {

                againstWall = false;

                Position pos = new Position( bomb.getPosition().getX()+ distance * (int) cos(toRadians(angle)), bomb.getPosition().getY() - distance * (int) sin(toRadians(angle)));

                if (getModel().isNotWall(pos))
                    getModel().addExplosion(new Explosion(pos));

                else if (getModel().isBWall(pos)) {

                    for (BreakableWall wall : getModel().getBreakableWalls()) {
                        if (wall.getPosition().equals(pos)) {
                            explodingWalls.add(wall);
                            getModel().generatePowerUp(wall.getPosition());
                            getModel().generateExit(wall.getPosition());
                            againstWall = true;
                            break;
                        }
                    }

                    if (againstWall) break;
                }

                else break;

            }
        }
    }

}
