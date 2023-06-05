package org.example.controller.Controllers.game;

import org.example.Game;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.powerups.Invincibility;

import java.io.IOException;
import java.util.Random;

public class MonsterController extends Controller<Arena> {
    private long lastMovement=0;
    public MonsterController(Arena arena){
        super(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {


        for (Monster monster: getModel().getMonsters()) {
            monster.setCanMove(false);
            int direction = monster.getDirection();
            switch (direction) {
                case 0: //left
                    directionDecide(monster, monster.getPosition().getLeft());
                    break;
                case 1: //right
                    directionDecide(monster, monster.getPosition().getRight());
                    break;
                case 2: //up
                    directionDecide(monster, monster.getPosition().getUp());
                    break;
                case 3: //down
                    directionDecide(monster, monster.getPosition().getDown());
                    break;
            }
        }

        if (time - lastMovement > 750) {
            for (Monster monster : getModel().getMonsters()) {
                if (monster.CanMove()) {
                    int direction = monster.getDirection();
                    switch (direction) {
                        case 0: //left
                            moveMonster(monster, monster.getPosition().getLeft());
                            break;
                        case 1: //right
                            moveMonster(monster, monster.getPosition().getRight());
                            break;
                        case 2: //up
                            moveMonster(monster, monster.getPosition().getUp());
                            break;
                        case 3: //down
                            moveMonster(monster, monster.getPosition().getDown());
                            break;
                    }
                }
            }
            this.lastMovement = time;
        }
    }

    private void directionDecide(Monster monster, Position position){
        Random rand = new Random();
        if (!getModel().isEmpty(position) || getModel().isMonster(position)) {
            monster.setDirection(rand.nextInt(4));
        }
        else {
            monster.setCanMove(true);
        }
    }

    private void moveMonster(Monster monster, Position position){
        monster.setPosition(position);
        if(getModel().getBomberman().getPosition().equals(position) && !getModel().getBomberman().isInvincible()){
            getModel().getBomberman().decreaseLife();
            Invincibility invencibility = new Invincibility(new Position(1,2), 5);
            getModel().getPowerUps().add(invencibility);
            getModel().getBomberman().setPosition(new Position(1,2));
        }
    }

}
