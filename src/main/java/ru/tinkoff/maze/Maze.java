package ru.tinkoff.maze;

import java.util.ArrayList;
import java.util.List;

public final class Maze {
    private final Cell[][] grid;
    int height;
    int width;

    public Maze(Cell[][] grid) {
        this.grid = grid;
        this.height = grid.length;

        if (this.height > 0) {
            this.width = grid[0].length;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Checks, that path doesn't cross walls
     */
    public void validatePath(List<Position> path) throws IllegalArgumentException {
        Position currPos;
        Cell currCell;

        Position nextPos;
        Cell nextCell;

        for (int i = 0; i < path.size() - 1; i++) {
            currPos = path.get(i);
            nextPos = path.get(i + 1);

            currCell = grid[currPos.y()][currPos.x()];
            nextCell = grid[nextPos.y()][nextPos.x()];

            if (currPos.x() < nextPos.x() && (currCell.hasRightWall() || nextCell.hasLeftWall())) {
                throw new IllegalArgumentException(
                        "if we pass from left to right, "
                                + "currCell must not have right wall and "
                                + "nextCell must not have left wall");
            }
            if (currPos.x() > nextPos.x() && (currCell.hasLeftWall() || nextCell.hasRightWall())) {
                throw new IllegalArgumentException(
                        "if we pass from right to left, "
                                + "currCell must not have left wall and "
                                + "nextCell must not have right wall");
            }
            if (currPos.y() < nextPos.y() && (currCell.hasBottomWall() || nextCell.hasTopWall())) {
                throw new IllegalArgumentException(
                        "if we pass from top to bottom, "
                                + "currCell must not have bottom wall and "
                                + "nextCell must not have top wall");
            }
            if (currPos.y() > nextPos.y() && (currCell.hasTopWall() || nextCell.hasBottomWall())) {
                throw new IllegalArgumentException(
                        "if we pass from bottom to top, "
                                + "currCell must not have top wall and "
                                + "nextCell must not have bottom wall");
            }
        }
    }

    /**
     * Get list of neighbours of cell in given position
     *
     * @param pos - cell's position
     * @return list of neighbours
     */
    public List<Position> getNeighbours(Position pos) {
        Cell currCell = grid[pos.y()][pos.x()];
        List<Position> neighbours = new ArrayList<>();

        if (!currCell.hasLeftWall()) {
            neighbours.add(new Position(pos.x() - 1, pos.y()));
        }
        if (!currCell.hasTopWall()) {
            neighbours.add(new Position(pos.x(), pos.y() - 1));
        }
        if (!currCell.hasRightWall()) {
            neighbours.add(new Position(pos.x() + 1, pos.y()));
        }
        if (!currCell.hasBottomWall()) {
            neighbours.add(new Position(pos.x(), pos.y() + 1));
        }

        return neighbours;
    }

    public Cell[][] getGrid() {
        return grid.clone();
    }
}
