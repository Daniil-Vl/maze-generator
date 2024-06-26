package ru.tinkoff.maze;

public class Cell {
    private final CellType type;
    private boolean leftWall = true;
    private boolean rightWall = true;
    private boolean topWall = true;
    private boolean bottomWall = true;

    public Cell() {
        this.type = CellType.PASSAGE;
    }

    public Cell(boolean left, boolean top, boolean right, boolean bottom) {
        this();
        this.leftWall = left;
        this.topWall = top;
        this.rightWall = right;
        this.bottomWall = bottom;
    }

    public boolean hasLeftWall() {
        return leftWall;
    }

    public void destroyLeftWall() {
        this.leftWall = false;
    }

    public boolean hasRightWall() {
        return rightWall;
    }

    public void destroyRightWall() {
        this.rightWall = false;
    }

    public boolean hasTopWall() {
        return topWall;
    }

    public void destroyTopWall() {
        this.topWall = false;
    }

    public boolean hasBottomWall() {
        return bottomWall;
    }

    public void destroyBottomWall() {
        this.bottomWall = false;
    }

    public CellType getType() {
        return type;
    }

    /**
     * Return number of available passes
     */
    public int countPasses() {
        int count = 0;
        count += leftWall ? 0 : 1;
        count += topWall ? 0 : 1;
        count += rightWall ? 0 : 1;
        count += bottomWall ? 0 : 1;
        return count;
    }
}
