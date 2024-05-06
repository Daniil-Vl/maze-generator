package ru.tinkoff.rendering;

import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;

import java.util.List;

public interface Renderer {

    /**
     * Render maze without path
     *
     * @param maze - maze to render
     * @return String - rendered maze
     */
    String render(Maze maze);

    /**
     * Render maze with path on it
     *
     * @param maze - maze to render
     * @param path - path to render
     * @return String - rendered maze
     */
    String render(Maze maze, List<Position> path);
}
