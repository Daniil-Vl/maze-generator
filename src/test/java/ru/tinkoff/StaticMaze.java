package ru.tinkoff;

import lombok.extern.log4j.Log4j2;
import ru.tinkoff.maze.Cell;
import ru.tinkoff.maze.Maze;
import ru.tinkoff.rendering.ANSIRenderer;
import ru.tinkoff.rendering.Renderer;

@Log4j2
public class StaticMaze {
    private final static Renderer renderer = new ANSIRenderer();

    /**
     * Maze like this
     * ##########
     * #     #  #
     * ####  #  #
     * #  #  #  #
     * #        #
     * ##########
     */
    public static Maze getMaze() {
        Cell[][] grid = new Cell[][]{
                {
                        new Cell(true, true, false, true),
                        new Cell(false, true, true, false),
                        new Cell(true, true, true, false)
                },
                {
                        new Cell(true, true, true, false),
                        new Cell(true, false, true, false),
                        new Cell(true, false, true, false),
                },
                {
                        new Cell(true, false, false, true),
                        new Cell(false, false, false, true),
                        new Cell(false, false, true, true),
                }
        };
        return new Maze(grid);
    }

    /**
     * Maze like this
     * ##########
     * #     #  #
     * ####  ####
     * #  #  #  #
     * #        #
     * ##########
     */
    public static Maze getMazeWithUnreachableCells() {
        Cell[][] grid = new Cell[][]{
                {
                        new Cell(true, true, false, true),
                        new Cell(false, true, true, false),
                        new Cell(true, true, true, true)
                },
                {
                        new Cell(true, true, true, false),
                        new Cell(true, false, true, false),
                        new Cell(true, true, true, false),
                },
                {
                        new Cell(true, false, false, true),
                        new Cell(false, false, false, true),
                        new Cell(true, false, true, true),
                }
        };
        return new Maze(grid);
    }

    public static Maze getMazeWithCycles() {
        Cell[][] grid = new Cell[][]{
                {
                        new Cell(true, true, false, false),
                        new Cell(false, true, false, false),
                        new Cell(false, true, true, true),
                },
                {
                        new Cell(true, false, true, false),
                        new Cell(true, false, false, true),
                        new Cell(false, true, true, false),
                },
                {
                        new Cell(true, false, false, true),
                        new Cell(false, true, false, true),
                        new Cell(false, false, true, true),
                },
        };

        return new Maze(grid);
    }

    private static void renderMaze() {
        log.info("Maze: \n{}", renderer.render(getMaze()));
    }

    private static void renderMazeWithUnreachableCells() {
        log.info("Maze with unreachable cells: \n{}", renderer.render(getMazeWithUnreachableCells()));
    }

    private static void renderMazeWithCycles() {
        log.info("Maze with cycles: \n{}", renderer.render(getMazeWithCycles()));
    }

    public static void main(String[] args) {
        renderMaze();
        renderMazeWithUnreachableCells();
        renderMazeWithCycles();
    }
}
