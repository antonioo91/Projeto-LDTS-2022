package org.example;

import org.example.gui.LanternaGUI;
import org.example.model.menu.Menu;
import org.example.states.MenuState;
import org.example.states.State;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Game {
    private LanternaGUI gui;

    private State state;
    private State previousState;


    public Game() throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGUI(15, 13);
        Integer highscore = readHighscore();
        this.state = new MenuState(new Menu(highscore), new Music("Menu.wav"));
        getState().getMusic().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }


    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().run();
    }

    private void run() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

        gui.close();
    }

    public Integer readHighscore() {
        try {
            File file = new File("highscore.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

        try {
            File file = new File("highscore.txt");
            Scanner reader = new Scanner(file, UTF_8);
            Integer highscore = Integer.parseInt(reader.nextLine());
            return highscore;
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
            return 0;
        }
    }

    public State getPreviousState() {
        return this.previousState;
    }

    public void setPreviousState(State state) {
        this.previousState = state;
    }
}