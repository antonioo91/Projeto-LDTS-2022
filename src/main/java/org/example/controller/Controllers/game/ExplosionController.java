package org.example.controller.Controllers.game;

import org.example.Game;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomb;
import org.example.model.game.entities.Explosion;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.powerups.Invincibility;
import org.example.model.game.entities.powerups.PowerUp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class ExplosionController extends Controller<Arena> {

    private long LastTick;
    public ExplosionController(Arena arena) {super(arena); }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - LastTick > 100) {
            List<Explosion> tmpExplosions = new ArrayList<Explosion>(getModel().getExplosions());

            for(Explosion explosion: tmpExplosions) {
                explosion.setTimer(explosion.getTimer()-2);

                if(!getModel().getBomberman().isInvincible()){
                    if(getModel().getBomberman().getPosition().equals(explosion.getPosition())) {
                        getModel().getBomberman().decreaseLife();
                        Invincibility invencibility = new Invincibility(new Position(1,2), 5);
                        getModel().getPowerUps().add(invencibility);
                        invencibility.doEffect(getModel().getBomberman());
                        getModel().getBomberman().setPosition(new Position(1,2));
                        break;
                    }
                }

                for(Bomb bomb : getModel().getBombs()){
                    if (bomb.getPosition().equals(explosion.getPosition()))
                            bomb.setTimer(1);
                }

                List<PowerUp> powerUps = new ArrayList<PowerUp>(getModel().getPowerUps());
                for(PowerUp powerUp : powerUps){
                    if (powerUp.getPosition().equals(explosion.getPosition()))
                        getModel().getPowerUps().remove(powerUp);
                }

                List<Monster> monsters = new ArrayList<Monster>(getModel().getMonsters());
                for(Monster monster: monsters)
                    if (monster.getPosition().equals(explosion.getPosition())){
                        getModel().getMonsters().remove(monster);
                        getModel().setScore(getModel().getScore() + 100*(int)pow(2, monster.getLevel()));
                    }



                if (explosion.getTimer() <= 0) {
                    getModel().getExplosions().remove(explosion);
                }

                if (getModel().getExit().getPosition().equals(explosion.getPosition()) && explosion.getTimer() == 0){
                    getModel().addMonster(getModel().getExit().getPosition());
                }

                if(time - explosion.getLastStepTime() > 100){
                    explosion.nextAnimation();
                    explosion.setLastStepTime(time);
                }

            }
            this.LastTick = time;
        }





    }
}
