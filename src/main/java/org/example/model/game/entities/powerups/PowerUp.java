package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.Entity;

public abstract class PowerUp extends Entity {
    private long lastStepTime;

    public long getLastStepTime() {
        return lastStepTime;
    }

    public void setLastStepTime(long lastStepTime) {
        this.lastStepTime = lastStepTime;
    }

    private int duration;

    private long lastAnimation=0;

    public PowerUp(Position pos, int duration){
        super(pos);
        this.duration = duration;
    }

    public abstract void doEffect(Bomberman bomberman);

    public void removeEffect(Bomberman bomberman){}

    public long getLastAnimation() {
        return lastAnimation;
    }

    public void setLastAnimation(long lastAnimation) {
        this.lastAnimation = lastAnimation;
    }

    public abstract char getAnimation();

    public abstract String getColor();

    public abstract void nextAnimation();

    public int getTimer() {
        return duration;
    }

    public void setTimer(int duration) {
        this.duration = duration;
    }
}

