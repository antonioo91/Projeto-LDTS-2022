package org.example.states.fakes;

public class FakeProvider{
    private FakeController controller;
    private FakeViewer viewer;

    private static final FakeProvider instance = new FakeProvider();

    // private constructor to avoid client applications using the constructor
    private FakeProvider(){}

    public static FakeProvider getInstance() {
        return instance;
    }


    public FakeController getController() {
        return controller;
    }

    public void setController(FakeController controller) {
        this.controller = controller;
    }

    public void setViewer(FakeViewer viewer) {
        this.viewer = viewer;
    }

    public FakeViewer getViewer() {
        return viewer;
    }

}
