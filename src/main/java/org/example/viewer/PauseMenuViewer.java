package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.menu.Menu;

public class PauseMenuViewer extends Viewer<Menu> {

    public PauseMenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        drawMenuEntries(gui);
    }

    private void drawMenuEntries(GUI gui) {
        for(int i = 0; i < getEntity().getNumberEntries(); i++) {
            String entry = getEntity().getEntry(i);
            boolean selected = getEntity().isSelected(i);

            gui.drawText(new Position(7-(entry.length()/2), 5 + i), entry, selected ? "#FFD700" : "#FFFFFF");
            if(selected){
                gui.drawText(new Position(6-(entry.length()/2), 5 + i), "Ḿ", "#000000");
                gui.drawText(new Position(8+(entry.length()/2), 5+ i), "Ḿ", "#000000");
            }
        }
    }
}
