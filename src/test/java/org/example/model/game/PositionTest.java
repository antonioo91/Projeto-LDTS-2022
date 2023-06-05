package org.example.model.game;

import org.example.model.Position;
import org.example.model.game.entities.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    private Position position;
    @BeforeEach
    void setup(){
        position = new Position(5,5);
    }

    @Test
    void equals(){
        Position position2 = new Position(5,5);
        assertTrue(position.equals(position2));
    }

    @Test
    void notEquals(){
        Position position2 = new Position(5,6);
        assertFalse(position.equals(position2));
    }

    @Test
    void notEquals2(){
        Entity entity = new Entity(new Position(5,5));
        assertFalse(position.equals(entity));
    }

    @Test
    void hashCodeTest(){
        int hash = position.hashCode();
        assertEquals(position.hashCode(), hash);
    }
}
