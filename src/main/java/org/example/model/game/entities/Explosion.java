package org.example.model.game.entities;

import org.example.model.Position;

public class Explosion extends Entity{

    private long lastStepTime;
    private char animation = 'ḻ';
    private String color="#e25822";
    private long timer = 16;
    public Explosion(Position pos){ super(pos); }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public long getLastStepTime() {
        return lastStepTime;
    }

    public void setLastStepTime(long time) {
        this.lastStepTime = time;
    }

    public void nextAnimation() {
        this.animation = this.animation == 'ḻ' ? 'Ḽ' : 'ḻ';
        if(timer <= 10){
            this.color="#f1bc31";
        }
        //this.color = this.color == "#e25822" ? "#f1bc31" : "#e25822";
    }

    public char getAnimation() {
        return animation;
    }

    public String getColor() {
        return color;
    }
}
