package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;

import java.util.Random;

public class HP extends PowerUp {

    private char animation = '♥';
    private String color = "#F9F295";

    public HP(Position pos, int duration){
        super(pos, duration);
    }

    @Override
    public void doEffect(Bomberman bomberman){
        Random rand = new Random();
        int i = rand.nextInt(3);

        for(int j = 0; j <= i; j++){
            bomberman.increaseLife();
        }

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
        this.animation = this.animation == '♥' ? 'Ṉ' : '♥';
        this.color = "#F9F295".equals(this.color) ? "#E0AA3E" : "#F9F295";
    }
}
