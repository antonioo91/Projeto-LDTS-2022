package org.example.controller.game;

import org.example.Game;
import org.example.controller.Controller;
import org.example.controller.Controllers.game.BombController;
import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomb;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.BreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BombControllerTest {
    private BombController controller;
    private Arena arena;
    private Game game;
    private Bomb bomb;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(10,10);
        bomb = new Bomb(new Position(0,0));
        bomb.setTimer(1);
        arena.addBomb(bomb);

        controller = new BombController(arena);

        game = Mockito.mock(Game.class);
    }

    @Test
    void step() throws IOException {
        //arrange
        BombController controllerSpy = Mockito.spy(controller);
        Mockito.doNothing().when(controllerSpy).explodes(Mockito.any(Bomb.class));

        //assert
        assertEquals(1, arena.getBombs().size());
        assertEquals(1, arena.getBombs().get(0).getTimer());

        //act
        controllerSpy.step(game, GUI.ACTION.NONE, 200);


        //assert
        assertEquals(0, bomb.getTimer());
        Mockito.verify(controllerSpy, Mockito.times(1)).explodes(bomb);
        assertEquals(0, arena.getBombs().size());


    }

    @Test
    void explosionSurroundedWithBWalls(){
        //arrange
        arena.setBreakableWalls(new ArrayList<>(Arrays.asList(new BreakableWall(new Position(-1,0)),
                                                                new BreakableWall(new Position(1,0)),
                                                                new BreakableWall(new Position(0,1)),
                                                                new BreakableWall(new Position(0,-1)))));
                                                                //walls surrounding the explosion

        arena.setBomberman(new Bomberman(new Position(3,3)));

        BombController controllerSpy = Mockito.spy(controller);

        //assert
        assertEquals(0, arena.getExplosions().size());
        assertEquals(1, arena.getBomberman().getBombQuantity());

        //act
        controllerSpy.explodes(bomb);

        //assert
        assertEquals(1, arena.getExplosions().size());
        assertEquals(2, arena.getBomberman().getBombQuantity());
        Mockito.verify(controllerSpy, Mockito.times(27)).getModel();
        //Mockito.verify(Mockito.spy(arena), Mockito.times(4)).generateExit(Mockito.any(Position.class));
    }

    @Test
    void explosionOneGap(){
        //arrange
        arena.setBreakableWalls(new ArrayList<>(Arrays.asList(new BreakableWall(new Position(-1,0)),
                new BreakableWall(new Position(1,0)),
                new BreakableWall(new Position(0,1)))));

        arena.setBomberman(new Bomberman(new Position(3,3)));

        //assert
        assertEquals(0, arena.getExplosions().size());
        assertEquals(1, arena.getBomberman().getBombQuantity());

        //act
        controller.explodes(bomb);

        //assert
        assertEquals(2, arena.getExplosions().size());
        assertEquals(2, arena.getBomberman().getBombQuantity());
    }

    @Test
    void explosionOneGapLargerRadius(){
        //arrange
        arena.setBreakableWalls(new ArrayList<>(Arrays.asList(new BreakableWall(new Position(-1,0)),
                new BreakableWall(new Position(1,0)),
                new BreakableWall(new Position(0,1)))));

        Bomberman bomberman = new Bomberman(new Position(3,3));
        bomberman.setExplosionRadius(2);
        arena.setBomberman(bomberman);

        //assert
        assertEquals(0, arena.getExplosions().size());
        assertEquals(1, arena.getBomberman().getBombQuantity());

        //act
        controller.explodes(bomb);

        //assert
        assertEquals(3, arena.getExplosions().size());
        assertEquals(2, arena.getBomberman().getBombQuantity());
    }

    @Test
    void animationBWall() throws IOException {
        //arrange
        BreakableWall bWall = new BreakableWall(new Position(0,1));
        arena.setBreakableWalls(Arrays.asList(bWall));
        arena.setBomberman(new Bomberman(new Position(3,3)));

        //assert
        assertEquals('Ṕ' , bWall.getAnimation());
        assertEquals("#B2B2B2", bWall.getColor());
        assertEquals("#6C6F6E", bWall.getBackgroundColor());
        assertEquals(4, bWall.getTimer());
        assertEquals(0, bWall.getLastTime());

        //act
        controller.step(game, GUI.ACTION.NONE, 300);

        //assert
        assertEquals('Ṕ' , bWall.getAnimation());
        assertEquals("#e25822", bWall.getColor());
        assertEquals("#3e403f", bWall.getBackgroundColor());
        assertEquals(3, bWall.getTimer());
        assertEquals(300, bWall.getLastTime());

        //act
        controller.step(game, GUI.ACTION.NONE, 600);

        //assert
        assertEquals('▒' , bWall.getAnimation());
        assertEquals("#e25822", bWall.getColor());
        assertEquals("#149300", bWall.getBackgroundColor());
        assertEquals(2, bWall.getTimer());
        assertEquals(600, bWall.getLastTime());

        //act
        controller.step(game, GUI.ACTION.NONE, 900);

        //assert
        assertEquals('░' , bWall.getAnimation());
        assertEquals("#f1bc31", bWall.getColor());
        assertEquals("#149300", bWall.getBackgroundColor());
        assertEquals(1, bWall.getTimer());
        assertEquals(900, bWall.getLastTime());
    }

    @Test
    void animationEnd() throws IOException {
        //arrange
        BreakableWall bWall = new BreakableWall(new Position(0,1));
        bWall.setTimer(1);
        List<BreakableWall> bWalls = new ArrayList<>();
        bWalls.add(bWall);
        arena.setBreakableWalls(bWalls);
        arena.setBomberman(new Bomberman(new Position(3,3)));

        //assert
        assertEquals('Ṕ' , bWall.getAnimation());
        assertEquals("#B2B2B2", bWall.getColor());
        assertEquals("#6C6F6E", bWall.getBackgroundColor());
        assertEquals(1, bWall.getTimer());

        //act
        controller.step(game, GUI.ACTION.NONE, 300);

        //assert
        assertEquals('Ṕ' , bWall.getAnimation());
        assertEquals("#e25822", bWall.getColor());
        assertEquals("#3e403f", bWall.getBackgroundColor());
        assertEquals(0, bWall.getTimer());
        assertEquals(0, arena.getBreakableWalls().size());
    }
}
