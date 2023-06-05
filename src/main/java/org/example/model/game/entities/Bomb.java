package org.example.model.game.entities;

import org.example.model.Position;

public class Bomb extends Entity{
    private long timer = 20;

    public Bomb(Position pos){
        super(pos);
    }

    public long getTimer() {
        return timer;
    }
    public void setTimer(long timer) {
        this.timer = timer;
    }

}
