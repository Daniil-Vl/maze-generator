package ru.tinkoff;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;
import ru.tinkoff.rendering.ANSIRenderer;
import ru.tinkoff.rendering.Renderer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
class ANSIRendererTest {
    public static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String PASSAGE = "   ";
    private static final String WALL = ANSI_YELLOW_BACKGROUND + PASSAGE + ANSI_RESET;
    private static final String PATH_PASSAGE = ANSI_RED + " * " + ANSI_RESET;
    private static final String START_OR_END = ANSI_GREEN + " ! " + ANSI_RESET;

    private final static Renderer ANSI_RENDERER = new ANSIRenderer();

    private static void renderExpectedAndActualMaze(String expectedRenderResult, String actual) {
        log.info("Expected maze rendering\n{}", expectedRenderResult);
        log.info("Actual maze rendering\n{}", actual);
    }

    @Test
    void testRenderOnlyMaze() {
        Maze maze = StaticMaze.getMaze();

        // Maze 3x3 have 7 wall blocks on the boundaries
        String expectedRenderResult = WALL.repeat(7) + "\n"
                + WALL + PASSAGE.repeat(3) + WALL + PASSAGE + WALL + "\n"
                + WALL.repeat(3) + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + PASSAGE + PASSAGE + PASSAGE + PASSAGE + WALL + "\n"
                + WALL.repeat(7) + "\n";

        String actual = ANSI_RENDERER.render(maze);

        log.info("testRenderOnlyMaze");
        renderExpectedAndActualMaze(expectedRenderResult, actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderWithPath() {
        Maze maze = StaticMaze.getMaze();
        List<Position> path = List.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(1, 1),
                new Position(1, 2),
                new Position(2, 2),
                new Position(2, 1),
                new Position(2, 0)
        );

        // Maze 3x3 have 7 wall blocks on the boundaries
        String expectedRenderResult = WALL.repeat(7) + "\n"
                + WALL + START_OR_END + PATH_PASSAGE.repeat(2) + WALL + START_OR_END + WALL + "\n"
                + WALL.repeat(3) + PATH_PASSAGE + WALL + PATH_PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PATH_PASSAGE + WALL + PATH_PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PATH_PASSAGE + WALL + PATH_PASSAGE + WALL + "\n"
                + WALL + PASSAGE + PASSAGE + PATH_PASSAGE + PATH_PASSAGE + PATH_PASSAGE + WALL + "\n"
                + WALL.repeat(7) + "\n";

        String actual = ANSI_RENDERER.render(maze, path);

        log.info("testRenderWithPath");
        renderExpectedAndActualMaze(expectedRenderResult, actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderPathWithOneCell() {
        Maze maze = StaticMaze.getMaze();
        List<Position> path = List.of(
                new Position(0, 0)
        );

        // Maze 3x3 have 7 wall blocks on the boundaries
        String expectedRenderResult = WALL.repeat(7) + "\n"
                + WALL + START_OR_END + PASSAGE.repeat(2) + WALL + PASSAGE + WALL + "\n"
                + WALL.repeat(3) + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + PASSAGE + PASSAGE + PASSAGE + PASSAGE + WALL + "\n"
                + WALL.repeat(7) + "\n";

        String actual = ANSI_RENDERER.render(maze, path);

        log.info("testRenderPathWithOneCell");
        renderExpectedAndActualMaze(expectedRenderResult, actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderMazeWithUnreachableCells() {
        Maze maze = StaticMaze.getMazeWithUnreachableCells();
        String expectedRenderResult = WALL.repeat(7) + "\n"
                + WALL + PASSAGE.repeat(3) + WALL + PASSAGE + WALL + "\n"
                + WALL.repeat(3) + PASSAGE + WALL + WALL + WALL + "\n"
                + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
                + WALL + PASSAGE + PASSAGE + PASSAGE + PASSAGE + PASSAGE + WALL + "\n"
                + WALL.repeat(7) + "\n";

        String actual = ANSI_RENDERER.render(maze);

        log.info("testRenderMazeWithUnreachableCells");
        renderExpectedAndActualMaze(expectedRenderResult, actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void renderingInvalidMaze() {
        Maze maze = StaticMaze.getMazeWithUnreachableCells();
        List<Position> path = List.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(1, 1),
                new Position(1, 2),
                new Position(2, 2),
                new Position(2, 1),
                new Position(2, 0)
        );

        log.info("testRenderingInvalidMaze");
        log.info("Maze\n{}", ANSI_RENDERER.render(maze));
        log.info("Path: {}", path);

        assertThrows(IllegalArgumentException.class, () -> ANSI_RENDERER.render(maze, path));
    }

    @Test
    void renderingInvalidPath() {
        Maze maze = StaticMaze.getMaze();
        List<Position> path = List.of(
                new Position(0, 0),
                new Position(0, 0)
        );

        assertThrows(IllegalArgumentException.class, () -> ANSI_RENDERER.render(maze, path));
    }
}
