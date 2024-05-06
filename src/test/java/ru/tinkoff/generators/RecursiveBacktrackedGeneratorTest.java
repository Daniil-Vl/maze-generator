package ru.tinkoff.generators;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;
import ru.tinkoff.rendering.ANSIRenderer;
import ru.tinkoff.rendering.Renderer;
import ru.tinkoff.solvers.DFSSolver;
import ru.tinkoff.solvers.Solver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class RecursiveBacktrackedGeneratorTest {

    private static final MazeGenerator mazeGenerator = new RecursiveBacktrackedGenerator();
    private static final Solver solver = new DFSSolver();
    private static final Renderer renderer = new ANSIRenderer();

    // This test method relies on DFSSolver implementation
    @Test
    void generatedMazeDoesNotHaveUnreachableZones() {
        int height = 5;
        int width = 5;
        Maze maze = mazeGenerator.generate(height, width);
        List<Position> path;
        Position start = new Position(0, 0);
        Position end;

        log.info("generatedMazeDoesNotHaveUnreachableZones\n");
        log.info("Maze\n{}", renderer.render(maze));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                end = new Position(x, y);
                path = solver.solve(maze, start, end);

                log.info("{}{}", "Path from (%d, %d) to (%d, %d)\n".formatted(start.x(), start.y(), end.x(), end.y()), renderer.render(maze, path));

                assertThat(path).size().isGreaterThan(0);
                assertThat(path.getLast()).isNotNull();
                assertThat(path.getLast()).isEqualTo(end);
            }
        }
    }
}
