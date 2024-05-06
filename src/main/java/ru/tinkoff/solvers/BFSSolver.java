package ru.tinkoff.solvers;

import ru.tinkoff.maze.Maze;
import ru.tinkoff.maze.Position;

import java.util.*;

/**
 * Find a path between two points, using breadth-first search algorithm
 */
public class BFSSolver implements Solver {

    /**
     * Find a path between two points
     *
     * @param maze  - maze
     * @param start - starting point
     * @param end   - destination point
     * @return ordered sequence of points in the path
     */
    @Override
    public List<Position> solve(Maze maze, Position start, Position end) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        HashMap<Position, Position> parents = new HashMap<>();
        HashSet<Position> visited = new HashSet<>();
        List<Position> notVisitedNeighbours;
        Position currPos;

        queue.addLast(start);
        while (!queue.isEmpty()) {
            currPos = queue.pollFirst();

            // If we reached end position, then we can start collecting a path
            if (currPos.equals(end)) {
                return backtrackPath(parents, start, end);
            }

            visited.add(currPos);

            // Get all unvisited neighbours
            notVisitedNeighbours = maze.getNeighbours(currPos).stream().filter(el -> !visited.contains(el)).toList();

            // Add them to the queue and give them parent
            for (Position neighbour : notVisitedNeighbours) {
                queue.addLast(neighbour);
                parents.put(neighbour, currPos);
            }
        }

        return new LinkedList<>();
    }

    /**
     * Collect path from parents' mapping
     *
     * @param parents - parents mapping
     * @param start   - start of the path
     * @param end     - end of the path
     * @return path
     */
    private List<Position> backtrackPath(Map<Position, Position> parents, Position start, Position end) {
        LinkedList<Position> path = new LinkedList<>(List.of(end));
        Position currPos = end;

        while (!currPos.equals(start)) {
            currPos = parents.get(currPos);
            path.addFirst(currPos);
        }

        return path;
    }
}
