package ru.tinkoff.solvers;

import ru.tinkoff.StaticMaze;
import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BFSSolverTest extends SolverTest<BFSSolver> {

    @Override
    protected BFSSolver createInstance() {
        return new BFSSolver();
    }

    @Override
    void findPathInMazeWithCycles() {
        Solver solver = new BFSSolver();
        Position start = new Position(0, 0);
        Position end = new Position(2, 1);
        Maze maze = StaticMaze.getMazeWithCycles();

        List<Position> actualPath = solver.solve(maze, start, end);
        List<Position> expectedShortestPath = List.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(1, 1),
                new Position(2, 1)
        );

        assertThat(actualPath).size().isGreaterThan(1);
        assertThat(actualPath).isEqualTo(expectedShortestPath);
    }
}
