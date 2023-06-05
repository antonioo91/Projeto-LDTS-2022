package org.example.model.menu;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> entries;
    private int currentEntry = 0;

    private Integer highscore = 0;

    public Menu(List<String> entries) throws IOException {
        this.entries = entries;
    }

    public Menu(Integer highscore) throws IOException {
        this.entries = Arrays.asList("Start", "Exit ");
        this.highscore = highscore;
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public String getSelectedEntry() {
        return getEntry(currentEntry);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }

    public Integer getHighscore() {
        return highscore;
    }

}
