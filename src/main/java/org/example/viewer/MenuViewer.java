package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.menu.Menu;

public class MenuViewer extends Viewer<Menu>{
    public MenuViewer(Menu menu){
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(5, 2), "Super", "#FFD700");
        gui.drawText(new Position(3,3), "Bomberman", "#FFFFFF");

        drawMenuEntries(gui);

        gui.drawText(new Position(3, 10), "Highscore", "#FFD700");
        gui.drawText(new Position(7-getEntity().getHighscore().toString().length()/2, 11), getEntity().getHighscore().toString(), "#FFFFFF");
    }

    public void drawMenuEntries(GUI gui){
        for(int i = 0; i < getEntity().getNumberEntries(); i++) {
            String entry = getEntity().getEntry(i);
            boolean selected = getEntity().isSelected(i);

            gui.drawText(new Position(5, 6 + i), entry, selected ? "#FFD700" : "#FFFFFF");

            if(selected){
                gui.drawText(new Position(4, 6 + i), "Ḿ", "#000000");
                gui.drawText(new Position(5 + entry.length(), 6 + i), "Ḿ", "#000000");
            }

        }
    }
}

