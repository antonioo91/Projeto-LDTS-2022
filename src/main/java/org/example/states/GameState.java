package org.example.states;

import org.example.Music;
import org.example.controller.Controller;
import org.example.controller.Controllers.game.ArenaController;
import org.example.model.game.arena.Arena;
import org.example.viewer.GameViewer;
import org.example.viewer.Viewer;

public class GameState extends State<Arena> {

    public GameState(Arena arena, Music music) {
        super(arena, music);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

}
