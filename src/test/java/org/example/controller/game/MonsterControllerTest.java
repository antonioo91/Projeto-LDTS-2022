package org.example.controller.game;

import org.example.Game;
import org.example.controller.Controllers.game.BombermanController;
import org.example.controller.Controllers.game.MonsterController;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.UnbreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MonsterControllerTest {
    private MonsterController monsterController;
    private Bomberman bomberman;
    private Arena arena;
    private Game game;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10,10);

        bomberman = new Bomberman(new Position(5,6));
        arena.setBomberman(bomberman);

        arena.setUnbreakableWalls(Arrays.asList());
        arena.setMonsters(Arrays.asList());

        monsterController = new MonsterController(arena);

        game = Mockito.mock(Game.class);
    }

    @Test
    void moveMonsters() throws IOException {
        Monster monster = new Monster(new Position(5,5));
        arena.setMonsters(Arrays.asList(monster));

        monsterController.step(game, GUI.ACTION.NONE, 1000);
        assertNotEquals(new Position(5,5), monster.getPosition());
    }

    @Test
    void moveMonsterEnclosed() throws IOException{
        Monster monster = new Monster(new Position(5,5));
        arena.setMonsters(Arrays.asList(monster));
        arena.setUnbreakableWalls(Arrays.asList(
                new UnbreakableWall(new Position(4,5)),
                new UnbreakableWall(new Position(6,5)),
                new UnbreakableWall(new Position(5,4)),
                new UnbreakableWall(new Position(5,6))
        ));

        //simulate game loop

        for(int i=0; i<10; i++){
            monsterController.step(game, GUI.ACTION.NONE, 1000);
        }

        assertEquals(new Position(5,5), monster.getPosition());

    }

    @Test
    void moveMonsterGap() throws IOException{
        Monster monster = new Monster(new Position(5,5));
        arena.setMonsters(Arrays.asList(monster));
        arena.setUnbreakableWalls(Arrays.asList(
                new UnbreakableWall(new Position(4,5)),
                new UnbreakableWall(new Position(6,5)),
                new UnbreakableWall(new Position(5,4))));

        //simulate game loop
        long time=0;
        while(monster.getPosition().equals(new Position(5,5))){
            time +=500;
            monsterController.step(game, GUI.ACTION.NONE, time);
        }

        assertEquals(new Position(5,6), monster.getPosition());
    }

}
