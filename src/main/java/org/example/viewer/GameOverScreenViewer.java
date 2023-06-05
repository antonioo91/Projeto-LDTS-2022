package org.example.viewer;

import org.example.gui.GUI;
import org.example.model.Position;
import org.example.model.menu.Menu;

public class GameOverScreenViewer extends Viewer<Menu> {

    public GameOverScreenViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        drawMenuEntries(gui);
    }

    private void drawMenuEntries(GUI gui) {
        for(int i = 0; i < getEntity().getNumberEntries(); i++) {
            String entry = getEntity().getEntry(i);
            gui.drawText(new Position(7-(entry.length()/2),5+(i*2)), entry, "#FFFFFF");
        }
    }
}
