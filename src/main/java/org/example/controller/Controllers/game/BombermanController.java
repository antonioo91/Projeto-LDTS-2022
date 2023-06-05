package org.example.controller.Controllers.game;

import org.example.Game;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomb;
import org.example.model.game.entities.powerups.Invincibility;

public class BombermanController extends Controller<Arena> {

    public BombermanController(Arena arena) {
        super(arena);
    }

    public void moveBombermanLeft() {
        moveBomberman(getModel().getBomberman().getPosition().getLeft());
    }

    public void moveBombermanRight() {
        moveBomberman(getModel().getBomberman().getPosition().getRight());
    }

    public void moveBombermanUp() {
        moveBomberman(getModel().getBomberman().getPosition().getUp());
    }

    public void moveBombermanDown() {
        moveBomberman(getModel().getBomberman().getPosition().getDown());
    }

    private void moveBomberman(Position position) {
        if (getModel().isNotWall(position)) {
            if (getModel().isBomb(position)) return;
            getModel().getBomberman().setPosition(position);

            if(!getModel().getBomberman().isInvincible()){
                if (getModel().isMonster(position) || getModel().isExplosion(position)){
                    getModel().getBomberman().decreaseLife();
                    Invincibility invencibility = new Invincibility(new Position(1,2), 5);
                    getModel().getPowerUps().add(invencibility);
                    getModel().getBomberman().setPosition(new Position(1,2));
                }

            }

        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP) moveBombermanUp();
        if (action == GUI.ACTION.RIGHT) moveBombermanRight();
        if (action == GUI.ACTION.DOWN) moveBombermanDown();
        if (action == GUI.ACTION.LEFT) moveBombermanLeft();
        if (action == GUI.ACTION.SPACE) if(getModel().getBomberman().getBombQuantity()!=0) placeBomb();


        if(time - getModel().getBomberman().getLastAnimation() > 500){
            getModel().getBomberman().nextAnimation();
            getModel().getBomberman().setLastAnimation(time);
        }
    }

    private void placeBomb() {
        Bomb bomb = new Bomb(getModel().getBomberman().getPosition());
        getModel().addBomb(bomb);

        //remove 1 bomb from bomberman's bomb quantity
        getModel().getBomberman().setBombQuantity(getModel().getBomberman().getBombQuantity() - 1);

    }
}
