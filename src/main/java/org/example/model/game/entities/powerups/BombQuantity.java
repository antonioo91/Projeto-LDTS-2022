package org.example.model.game.entities.powerups;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;

public class BombQuantity extends PowerUp {

    private char animation = 'Ḿ';
    private String color = "#F9F295";

    public BombQuantity(Position pos, int duration){
        super(pos, duration);
    }

    @Override
    public void doEffect(Bomberman bomberman){
        bomberman.setBombQuantity(bomberman.getBombQuantity() + 1);
    }

    @Override
    public String getColor() {
        return this.color;
    }
    @Override
    public void nextAnimation() {
        this.color = this.color.equals("#F9F295") ? "#E0AA3E" : "#F9F295";
    }
    @Override
    public char getAnimation() {
        return animation;
    }
}
