package org.example.model.game.entities;

import org.example.model.Position;

import java.util.Objects;

public class Bomberman extends Entity{
    private int lives;
    private char animation = 'ṗ';
    private int explosionRadius = 1;
    private boolean invincible = false;
    private long lastAnimation;
    private int bombQuantity = 1;
    private String color;


    public Bomberman(Position pos){
        super(pos);
        this.lives = 3;
    }


    /////////////////////////// LIVES ///////////////////////////
    public int getLives(){
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    public void increaseLife(){
        if (lives < 5){
            lives++;
        }
    }

    /////////////////////////// ANIMATION ///////////////////////////

    public void nextAnimation() {
        this.animation = this.animation == 'ṙ' ? 'ṗ' : 'ṙ';

        if(invincible){
            this.color = Objects.equals(this.color, "#F9F295") ? "#FFFFFF" : "#F9F295";
        } else {
            this.color = "#FFFFFF";
        }
    }

    public char getAnimation() {
        return this.animation;
    }

    public long getLastAnimation() {
        return lastAnimation;
    }
    public void setLastAnimation(long lastAnimation) {
        this.lastAnimation = lastAnimation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    /////////////////////////// POWER UPS ///////////////////////////
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
    public boolean isInvincible() {
        return invincible;
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }

    public void setExplosionRadius(int explosionRadius) {
        this.explosionRadius = explosionRadius;
    }
    public int getBombQuantity() {
        return this.bombQuantity;
    }

    public void setBombQuantity(int bombQuantity) {
        this.bombQuantity = bombQuantity;
    }

    public void decreaseLife() {
        if(!invincible){
            if (lives > 0){
                lives--;
            }
        }
    }
}