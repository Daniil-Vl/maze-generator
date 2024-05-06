package ru.tinkoff.maze;

/**
 * Represents position of cell on maze grid
 *
 * @param x - cell's column
 * @param y - cell's row
 */
public record Position(int x, int y) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Position other)) {
            return false;
        }

        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.x) + Integer.hashCode(this.y);
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(this.x, this.y);
    }
}
