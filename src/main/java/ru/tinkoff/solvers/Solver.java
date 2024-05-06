package ru.tinkoff.solvers;

import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;

import java.util.List;

public interface Solver {
    /**
     * Find a path between two points
     * If a path doesn't exist returns empty list
     *
     * @param maze  - maze
     * @param start - starting point
     * @param end   - destination point
     * @return ordered sequence of points in the path
     */
    List<Position> solve(Maze maze, Position start, Position end);
}
