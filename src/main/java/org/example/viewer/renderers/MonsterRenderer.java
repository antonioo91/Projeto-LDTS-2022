package org.example.viewer.renderers;

import org.example.gui.GUI;
import org.example.model.game.entities.Monster;

public class MonsterRenderer implements Renderer<Monster>{

    @Override
    public void draw(Monster monster, GUI gui){
        gui.drawMonster(monster.getPosition(), monster.getCharacter(), monster.getColor());
    }
}
