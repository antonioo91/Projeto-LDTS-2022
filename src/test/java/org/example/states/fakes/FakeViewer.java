package org.example.states.fakes;

import org.example.gui.GUI;
import org.example.viewer.Viewer;

import java.io.IOException;

public class FakeViewer extends Viewer<FakeModel>{

    public FakeViewer(FakeModel entity) {
        super(entity);
    }

    @Override
    public FakeModel getEntity() {
        return super.getEntity();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        super.draw(gui);
    }

    @Override
    public void drawElements(GUI gui) {

    }

}
