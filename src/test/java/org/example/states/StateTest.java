package org.example.states;

import org.example.Game;
import org.example.Music;
import org.example.controller.Controller;
import org.example.gui.GUI;
import org.example.viewer.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.example.states.fakes.*;

import java.io.IOException;

public class StateTest {

    private FakeState state;
    private FakeModel model;
    private Game game;
    private GUI gui;

    private Controller<FakeModel> controller;
    private Viewer<FakeModel> viewer;

    @BeforeEach
    void setup() {
        game = Mockito.mock(Game.class);

        model = new FakeModel();

        FakeProvider.getInstance().setViewer(Mockito.mock(FakeViewer.class));
        FakeProvider.getInstance().setController(Mockito.mock(FakeController.class));

        Music music = Mockito.mock(Music.class);
        state = new FakeState(model, music);

        controller = state.getControllerForTesting();
        viewer = state.getViewerForTesting();

        gui = Mockito.mock(GUI.class);

    }

    @Test
    void step() throws IOException {

        //arrange
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.RIGHT);
        Mockito.doNothing().when(controller).step(Mockito.any(Game.class), Mockito.any(GUI.ACTION.class), Mockito.any(long.class));
        Mockito.doNothing().when(viewer).draw(Mockito.any(GUI.class));

        //act
        state.step(game, gui, 10);

        //assert
        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Mockito.verify(controller, Mockito.times(1)).step(Mockito.any(Game.class), Mockito.any(GUI.ACTION.class), Mockito.any(long.class));
        Mockito.verify(viewer, Mockito.times(1)).draw(Mockito.any(GUI.class));
    }

    @Test
    void getModelTest(){
        //arrange

        //act
        FakeModel model = state.getModel();

        //assert
        assert(model == this.model);
    }
}