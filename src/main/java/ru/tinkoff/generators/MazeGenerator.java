package ru.tinkoff.generators;

import ru.tinkoff.maze.Maze;

public interface MazeGenerator {
    /**
     * Generate maze with given height and width
     *
     * @param height - height of generated maze
     * @param width  - width of generated maze
     * @return maze
     */
    Maze generate(int height, int width);
}
