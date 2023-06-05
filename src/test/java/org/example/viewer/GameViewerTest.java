package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.game.arena.Arena;
import org.example.model.game.arena.ArenaBuilder;
import org.example.model.game.entities.*;
import org.example.model.game.entities.powerups.PowerUp;
import org.example.viewer.renderers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class GameViewerTest {
    private GUI gui;
    private GameViewer gameViewer;
    private Arena arena;


    @BeforeEach
    void setup() throws IOException {
        gui = Mockito.mock(GUI.class);
        arena = new ArenaBuilder(69).createArena();
        arena.generateExit(new Position(0, 0));
        arena.addBomb(Mockito.mock(Bomb.class));
        arena.addPowerUp(Mockito.mock(PowerUp.class));
        arena.addExplosion(Mockito.mock(Explosion.class));
        gameViewer = new GameViewer(arena);
    }

    @Test
    void drawElements(){
        //arrange
        GameViewer gameViewerSpy = Mockito.spy(gameViewer);
        int numberElements = arena.getUnbreakableWalls().size()
                            + arena.getBreakableWalls().size()
                            + arena.getBombs().size()
                            + arena.getExplosions().size()
                            + arena.getPowerUps().size()
                            + arena.getMonsters().size()
                            + 1 //bomberman
                            + 1; //exit;

        //act
        gameViewerSpy.drawElements(gui);

        //assert
        Mockito.verify(gameViewerSpy, Mockito.times(6)).drawElements(Mockito.any(GUI.class), Mockito.any(List.class), Mockito.any(Renderer.class));
        Mockito.verify(gameViewerSpy, Mockito.times(numberElements)).drawElement(Mockito.any(GUI.class), Mockito.any(Entity.class), Mockito.any(Renderer.class));
        Mockito.verify(gui, Mockito.times(4)).drawText(Mockito.any(Position.class), Mockito.anyString(), Mockito.anyString());
    }
    @Test
void drawElement(){
        //arrange
        GameViewer gameViewerSpy = Mockito.spy(gameViewer);
        Entity entity = Mockito.mock(Entity.class);
        Renderer renderer = Mockito.mock(Renderer.class);

        //act
        gameViewerSpy.drawElement(gui, entity, renderer);

        //assert
        Mockito.verify(renderer, Mockito.times(1)).draw(Mockito.any(Entity.class), Mockito.any(GUI.class));
    }
}
