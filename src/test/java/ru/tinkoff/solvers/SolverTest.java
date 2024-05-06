package ru.tinkoff.solvers;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.StaticMaze;
import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;
import ru.tinkoff.rendering.ANSIRenderer;
import ru.tinkoff.rendering.Renderer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base test class for Solver interface implementations
 */
@Log4j2
public abstract class SolverTest<T extends Solver> {

    private final static Renderer renderer = new ANSIRenderer();
    private T instance;

    private static void renderMazeWithExpectedAndActualPaths(Maze maze, List<Position> expectedPath, List<Position> actualPath) {
        log.info("Maze: \n{}", renderer.render(maze));

        if (expectedPath.isEmpty()) {
            log.info("No expected path");
        } else {
            log.info("Expected path: {}", expectedPath);
            log.info("Maze with expected path: \n{}", renderer.render(maze, expectedPath));
        }

        log.info("Actual path: {}", actualPath);
        log.info("Maze with actual path: \n{}", renderer.render(maze, actualPath));
    }

    protected abstract T createInstance();

    @BeforeEach
    public void setUp() {
        instance = createInstance();
    }

    /**
     * Test successful pathfinding procedure
     */
    @Test
    void findPathInPredefinedMaze() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);

        Maze maze = StaticMaze.getMaze();

        List<Position> actualPath = solver.solve(maze, start, end);
        List<Position> expectedPath = List.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(1, 1),
                new Position(1, 2),
                new Position(2, 2),
                new Position(2, 1),
                new Position(2, 0)
        );

        assertThat(actualPath).size().isGreaterThan(1);
        assertThat(actualPath).isEqualTo(expectedPath);

        log.info("findPathInPredefinedMaze for class {}", instance.getClass());
        renderMazeWithExpectedAndActualPaths(maze, expectedPath, actualPath);
    }

    /**
     * Tests, that solver return empty list, when path doesn't exist
     */
    @Test
    void findPathWhenItDoesNotExists() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);
        Maze maze = StaticMaze.getMazeWithUnreachableCells();

        List<Position> actualPath = solver.solve(maze, start, end);
        assertThat(actualPath).isEmpty();

        log.info("findPathWhenItDoesNotExists for class {}", instance.getClass());
        renderMazeWithExpectedAndActualPaths(maze, List.of(), actualPath);
    }

    @Test
    void findPathWhereStartEqualsEnd() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(0, 0);
        Maze maze = StaticMaze.getMaze();

        List<Position> actualPath = solver.solve(maze, start, end);
        List<Position> expectedPath = List.of(start);

        assertThat(actualPath).hasSize(1);
        assertThat(actualPath).isEqualTo(expectedPath);

        log.info("findPathWhereStartEqualsEnd for class {}", instance.getClass());
        renderMazeWithExpectedAndActualPaths(maze, expectedPath, actualPath);
    }

    @Test
    void findPathInMazeWithCycles() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 1);
        Maze maze = StaticMaze.getMazeWithCycles();

        List<Position> actualPath = solver.solve(maze, start, end);

        assertThat(actualPath).size().isGreaterThan(1);

        log.info("findPathInMazeWithCycles for class {}", instance.getClass());
        renderMazeWithExpectedAndActualPaths(maze, List.of(), actualPath);
    }
}
