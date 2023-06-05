package org.example.states.fakes;

import org.example.Music;
import org.example.controller.Controller;
import org.example.states.State;
import org.example.viewer.Viewer;

public class FakeState extends State<FakeModel> {

    public FakeState(FakeModel model, Music music) {
        super(model, music);
    }

    public Controller<FakeModel> getControllerForTesting(){
        return getController();
    }

    public Viewer<FakeModel> getViewerForTesting(){
        return getViewer();
    }

    @Override
    protected Viewer<FakeModel> getViewer() {
        return  FakeProvider.getInstance().getViewer();
    }

    @Override
    protected Controller<FakeModel> getController() {
        return FakeProvider.getInstance().getController();
    }
}
