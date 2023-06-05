package org.example.model.game.arena;

import org.example.model.Position;
import org.example.model.game.entities.*;
import org.example.model.game.entities.powerups.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    ////////////////////////////// Constructors //////////////////////////////
    public Arena(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
    }

    /////////////////////////// ARENA SIZE ///////////////////////////
    private final int width;
    private final int height;

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    /////////////////////////// Bomberman ///////////////////////////

    private Bomberman bomberman;
    public Bomberman getBomberman() {
        return bomberman;
    }
    public void setBomberman(Bomberman bomberman) {
        this.bomberman = bomberman;
    }

    /////////////////////////// Monsters ///////////////////////////
    private List<Monster> monsters = new ArrayList<>();
    public List<Monster> getMonsters() { return monsters; }
    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
    public void addMonster(Position position){
        monsters.add(new Monster(position));
    }

    /////////////////////////// BREAKABLE WALLS ///////////////////////////
    private List<BreakableWall> breakableWalls = new ArrayList<>();

    public List<BreakableWall> getBreakableWalls() {
        return breakableWalls;
    }
    public void setBreakableWalls(List<BreakableWall> breakableWalls) {
        this.breakableWalls = breakableWalls;
    }

    /////////////////////////// UNBREAKABLE WALLS ///////////////////////////
    private List<UnbreakableWall> unbreakableWalls = new ArrayList<>();
    public List<UnbreakableWall> getUnbreakableWalls() {
        return unbreakableWalls;
    }
    public void setUnbreakableWalls(List<UnbreakableWall> unbreakableWalls) {
        this.unbreakableWalls = unbreakableWalls;
    }

    /////////////////////////// BOMBS ///////////////////////////
    private List<Bomb> bombs = new ArrayList<>();
    public List<Bomb> getBombs() { return bombs; }

    public void addBomb(Bomb bomb) {
        this.bombs.add(bomb);
    }

    /////////////////////////// EXPLOSIONS ///////////////////////////
    private List<Explosion> explosions = new ArrayList<>();
    public List<Explosion> getExplosions() {
        return explosions; }
    public void addExplosion(Explosion explosion) {
        this.explosions.add(explosion);
    }

    /////////////////////////// "COLISIONS" ///////////////////////////
    public boolean isNotWall(Position position){

        if (isUWall(position)) return false;

        if (isBWall(position)) return false;

        return true;
    }

    public boolean isUWall(Position position){
        for (UnbreakableWall wall : unbreakableWalls){
            if (wall.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }
    public boolean isBWall(Position position) {
        for (BreakableWall wall : breakableWalls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }
    public boolean isMonster(Position position){
        for (Monster monster : monsters)
            if (monster.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isBomb(Position position){
        for (Bomb bomb : bombs)
            if (bomb.getPosition().equals(position)) {
                return true;
            }
        return false;
    }

    public boolean isExplosion(Position position){
        for (Explosion explosion : explosions)
            if (explosion.getPosition().equals(position)) { return true; }
        return false;
    }




    public boolean isEmpty(Position position){
        if (isNotWall(position) && !isMonster(position) && !isBomb(position))
            return true;
        return false;
    }


    /////////////////////////// SCORE ///////////////////////////
    private Integer score;
    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer highScore) {
        this.score = highScore;
    }

    /////////////////////////// POWER UPS ///////////////////////////
    private List<PowerUp> powerUps = new ArrayList<>();
    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
    public void addPowerUp(PowerUp powerUp) {
        this.powerUps.add(powerUp);
    }

    public void generatePowerUp(Position position) {
        Random rand = new Random();
        int i = rand.nextInt(6);

        if(i == 0){ // 16% of chance of generating a power up
            int j = rand.nextInt(4);

            //randomly generate a power up
            switch (j){
                case 0:
                    addPowerUp(new HP(position, 0)); //0 seconds means it stays active for the rest of the game
                    break;
                case 1:
                    addPowerUp(new Invincibility(position, 6));
                    break;
                case 2:
                    addPowerUp(new BombQuantity(position, 0));
                    break;
                case 3:
                    addPowerUp(new ExplosionRadius(position, 0));
                    break;
            }

        }
    }

    /////////////////////////// EXIT ///////////////////////////

    private Exit exit = new Exit(new Position(99,99));
    private boolean exitAlreadyExists = false;

    public void generateExit(Position position){
        Random rand = new Random();
        int i = rand.nextInt(10);

        if ((i == 8 || breakableWalls.size() == 1) && !exitAlreadyExists){ // 10% chance
            this.exit = new Exit(position);
            exitAlreadyExists = true;
        }
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public Exit getExit() {
        return exit;
    }
}
