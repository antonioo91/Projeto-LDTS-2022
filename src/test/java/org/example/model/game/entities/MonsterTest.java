package org.example.model.game.entities;

import org.example.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonsterTest {
    private Monster monster;

    @BeforeEach
    void setup(){
        monster = new Monster(new Position(5,5));
    }

    @Test
    void test(){
        monster.setDirection(1);

        assertTrue(monster.getDirection() == 1);
    }
}
