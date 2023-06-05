package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;

public class Invincibility extends PowerUp {
    private char animation = 'ṗ';
    private String color = "#F9F295";

    public Invincibility(Position pos, int duration){
        super(pos, duration);
    }

    @Override
    public void doEffect(Bomberman bomberman){
        bomberman.setInvincible(true);
    }

    @Override
    public void removeEffect(Bomberman bomberman){
        bomberman.setInvincible(false);
    }

    @Override
    public char getAnimation() {
        return this.animation;
    }
    @Override
    public String getColor() {
        return this.color;
    }
    @Override
    public void nextAnimation() {
        this.animation = this.animation == 'ṗ' ? 'ṙ' : 'ṗ';
        this.color = this.color.equals("#F9F295") ? "#E0AA3E" : "#F9F295";
    }
}
