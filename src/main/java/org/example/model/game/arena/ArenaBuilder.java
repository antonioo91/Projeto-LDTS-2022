package org.example.model.game.arena;

import org.example.model.Position;
import org.example.model.game.entities.Bomberman;
import org.example.model.game.entities.BreakableWall;
import org.example.model.game.entities.Monster;
import org.example.model.game.entities.UnbreakableWall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ArenaBuilder {
    private final List<String> lines;

    public ArenaBuilder(int level) throws IOException {
        URL resource = ArenaBuilder.class.getResource("/levels/level" + level + ".lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), UTF_8));

        lines = readLines(br);

    }

    public Arena createArena() throws IOException {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setBomberman(createBomberman());
        arena.setMonsters(createMonsters());
        arena.setBreakableWalls(createBreakableWalls());
        arena.setUnbreakableWalls(createUnbreakableWalls());
        arena.setScore(0);
        return arena;
    }

    public Arena createArena(Bomberman bomberman, Integer score) throws IOException {
        Arena arena = new Arena(getWidth(), getHeight());

        bomberman.setPosition(new Position(1,2));

        arena.setBomberman(bomberman);
        arena.setMonsters(createMonsters());
        arena.setBreakableWalls(createBreakableWalls());
        arena.setUnbreakableWalls(createUnbreakableWalls());
        arena.setScore(score);
        return arena;
    }


    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    public int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }


    public int getHeight() {
        return lines.size();
    }


    public List<BreakableWall> createBreakableWalls() {
        List<BreakableWall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'B') walls.add(new BreakableWall(new Position(x,y)));
        }

        return walls;
    }

    public List<UnbreakableWall> createUnbreakableWalls() {
        List<UnbreakableWall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'U') walls.add(new UnbreakableWall(new Position(x,y)));
        }

        return walls;
    }


    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'M') {

                    monsters.add(new Monster(new Position(x, y)));
                }
        }

        return monsters;
    }


    public Bomberman createBomberman() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'b') return new Bomberman(new Position(x,y));
        }
        return null;
    }
}


