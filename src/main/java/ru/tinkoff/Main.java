package ru.tinkoff;

import lombok.extern.log4j.Log4j2;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import ru.tinkoff.generators.MazeGenerator;
import ru.tinkoff.generators.RecursiveBacktrackedGenerator;
import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;
import ru.tinkoff.rendering.ANSIRenderer;
import ru.tinkoff.rendering.Renderer;
import ru.tinkoff.solvers.DFSSolver;
import ru.tinkoff.solvers.Solver;

import java.util.List;
import java.util.concurrent.Callable;

@Log4j2
@Command(name = "maze-generator", description = "Generates maze and finds path from start position to end position", sortOptions = false)
public class Main implements Callable<Integer> {

    @Option(names = {"--width"}, description = "maze width", required = true, order = 1)
    private int width;

    @Option(names = {"--height"}, description = "maze height", required = true, order = 2)
    private int height;

    @Option(names = {"--start"}, description = "start position", paramLabel = "x,y", order = 3, split = ",", hideParamSyntax = true)
    private int[] startPosArray;

    @Option(names = {"--end"}, description = "end position", paramLabel = "x,y", order = 4, split = ",", hideParamSyntax = true)
    private int[] endPosArray;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        MazeGenerator mg = new RecursiveBacktrackedGenerator();
        Renderer renderer = new ANSIRenderer();
        Maze maze = mg.generate(height, width);
        Solver solver = new DFSSolver();

        System.out.println("Initial maze: ");
        System.out.println(renderer.render(maze));

        if (startPosArray != null && endPosArray != null) {
            Position start = new Position(startPosArray[0], startPosArray[1]);
            Position end = new Position(endPosArray[0], endPosArray[1]);
            List<Position> path = solver.solve(maze, start, end);
            System.out.println("Maze with path: ");
            System.out.println(renderer.render(maze, path));
        }

        return 0;
    }
}
