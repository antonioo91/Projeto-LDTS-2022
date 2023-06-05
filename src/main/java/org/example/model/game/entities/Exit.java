package org.example.model.game.entities;

import org.example.model.Position;

import java.util.Objects;

public class Exit extends Entity{

    private String color = "#000000";
    public Exit(Position pos){
        super(pos);
    }

    public void open(){
        color = "#FFFFFF";
    }

    public void close(){
        color = "#000000";
    }

    public String getColor() {
        return color;
    }

    public boolean isOpen(){
        return (Objects.equals(color, "#FFFFFF"));
    }
}
