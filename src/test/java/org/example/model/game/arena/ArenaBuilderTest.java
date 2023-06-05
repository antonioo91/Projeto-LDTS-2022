package org.example.model.game.arena;

import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.arena.ArenaBuilder;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.BreakableWall;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.UnbreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaBuilderTest {
    private ArenaBuilder ab;
    private ArenaBuilder cd;

    @BeforeEach
    void setup() throws IOException{
        ab = new ArenaBuilder(1);
        cd = new ArenaBuilder(2);
    }

    @Test
    void getWidthTest(){
        assertEquals(ab.getWidth(), 15);
        assertEquals(cd.getWidth(), 15);
    }

    @Test
    void getHeightTest(){
        assertEquals(ab.getHeight(), 12);
        assertEquals(cd.getHeight(), 12);
    }

    @Test
    void breakableWallsTest(){
        List<BreakableWall> bwalls1 = ab.createBreakableWalls();
        List<BreakableWall> bwalls2 = cd.createBreakableWalls();

        assertEquals(35, bwalls1.size());
        assertEquals(32, bwalls2.size());

    }

    @Test
    void unbreakableWallsTest(){
        List<UnbreakableWall> uwalls = ab.createUnbreakableWalls();
        List<UnbreakableWall> uwalls2 = cd.createUnbreakableWalls();

        assertEquals(79, uwalls.size());
        assertEquals(79, uwalls2.size());
    }

    @Test
    void monstersTest(){
        List<Monster> monsters = ab.createMonsters();
        List<Monster> monsters2 = cd.createMonsters();

        assertEquals(5, monsters.size());
        assertEquals(7, monsters2.size());
    }

    @Test
    void bombermanTest(){
        Bomberman b = ab.createBomberman();
        Bomberman b2 = cd.createBomberman();

        assertNotNull(b);
        assertEquals(b.getPosition().getX(), 1);
        assertEquals(b.getPosition().getY(), 2);

        assertNotNull(b2);
        assertEquals(b2.getPosition().getX(), 1);
        assertEquals(b2.getPosition().getY(), 2);
    }

    @Test
    void throwsException(){
        assertThrows(NullPointerException.class,() -> ab = new ArenaBuilder(9999));
    }

    @Test
    void levelWhereBombermanDoesntExist() throws IOException{
        assertDoesNotThrow(() -> ab = new ArenaBuilder(42));
        ab = new ArenaBuilder(42);

        Bomberman b = ab.createBomberman();

        assertNull(b);
    }

    @Test
    void createArenaTest() throws IOException {
        //arrange
        Bomberman bomberman = new Bomberman(new Position(0,0));
        Integer score = 10;
        Arena arena;

        //act
        arena = ab.createArena(bomberman, score);

        //assert
        assertEquals(arena.getBomberman(), bomberman);
        assertEquals(arena.getBomberman().getPosition().getX(), 1);
        assertEquals(arena.getBomberman().getPosition().getY(), 2);
        assertEquals(arena.getScore(), score);
        assertEquals(arena.getMonsters().size(), 5);
        assertEquals(arena.getBreakableWalls().size(), 35);
        assertEquals(arena.getUnbreakableWalls().size(), 79);

    }

    @Test
    void testDefaultCreateArena() throws IOException {
        //arrange
        Arena arena;

        //act
        arena = ab.createArena();

        //assert
        assertEquals(arena.getBomberman().getPosition().getX(), 1);
        assertEquals(arena.getBomberman().getPosition().getY(), 2);
        assertEquals(arena.getScore(), 0);
        assertEquals(arena.getMonsters().size(), 5);
        assertEquals(arena.getBreakableWalls().size(), 35);
        assertEquals(arena.getUnbreakableWalls().size(), 79);
    }

}
