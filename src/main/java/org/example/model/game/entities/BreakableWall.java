package org.example.model.game.entities;

import org.example.model.Position;

public class BreakableWall extends Entity{

    private char animation = 'Ṕ';
    private String color = "#B2B2B2";
    private String backgroundColor = "#6C6F6E";

    private long lastTime=0;
    private int timer = 4;

    public BreakableWall(Position pos){
        super(pos);
    }

    public char getAnimation() {
        return animation;
    }

    public String getColor() {
        return color;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    private int counter = 0;
    public void nextAnimation(){
        if(counter==0){
            this.color="#e25822";
            this.backgroundColor = "#3e403f";
        }
        if(counter==1){
            this.animation = '▒';
            this.color = "#e25822";
            this.backgroundColor = "#149300";
        }
        else if(counter == 2){
            this.animation = '░';
            this.color = "#f1bc31";
            this.backgroundColor = "#149300";
        }

        counter++;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
    public int getTimer() {
        return timer;
    }
    public void setTimer(int timer) {
        this.timer = timer;
    }
}
