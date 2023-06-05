package org.example.model.game.entities;

import org.example.model.Position;

public class Entity {
    private Position pos;

    public Entity(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }
}
