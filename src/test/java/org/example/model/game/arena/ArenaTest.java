package org.example.model.game.arena;

import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.entities.Bomb;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import org.example.model.game.entities.UnbreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class ArenaTest {
    private Arena arena;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(14,14);
    }

    @Test
    void testWH(){

        assertEquals(14, arena.getWidth());
        assertEquals(14, arena.getHeight());
    }




}
