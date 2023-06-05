package org.example.model.game.entities;

import org.example.model.Position;

import java.util.Random;

public class Monster extends Entity{
    private char character;
    private String color;
    private int level;
    private int direction = 0;
    private boolean canMove = false;
    
    public Monster(Position pos){
        super(pos);
        Random rand = new Random();
        int i = rand.nextInt(4);
        int n = rand.nextInt(3);

        switch (i){
            case 0:
                this.character = 'Ṧ';
                break;
            case 1:
                this.character = 'Ṡ';
                break;
            case 2:
                this.character = 'ṍ';
                break;
            case 3:
                this.character = 'ṏ';
                break;
        }

        switch (n){
            case 0:
                this.color = "#0000FF";
                level = 1;
                break;
            case 1:
                this.color = "#FF0000";
                level = 3;
                break;
            case 2:
                this.color = "#E69B00";
                level = 2;
                break;
        }

    }
    public char getCharacter() {
        return character;
    }

    public String getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean CanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
