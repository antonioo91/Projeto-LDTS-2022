package org.example.states.fakes;

import org.example.Game;
import org.example.controller.Controller;
import org.example.gui.GUI;

import java.io.IOException;

public class FakeController extends Controller<FakeModel> {

    public FakeController(FakeModel entity) {
        super(entity);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }
}


