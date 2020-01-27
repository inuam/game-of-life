package conway.gol;

import static conway.gol.Cell.ALIVE;

public class Grid {
    private final Cell[][] currentFrame;
    private Cell[][] nextFrame;
    private final int gridSize;

    public Grid(Cell[][] currentFrame) {
        this.currentFrame = currentFrame;
        this.nextFrame = currentFrame;
        this.gridSize = currentFrame.length;
    }

    public Cell[][] getNextFrame() {
        return nextFrame;
    }

    public int getLiveNeighbours(int x, int y) {
        int liveNeighbours = 0;

        if (gridSize == 1 && currentFrame[0].length == 1)
            return 0;

        // next right cell
        if (gridSize > x + 1 && currentFrame[x + 1][y] == ALIVE)
            liveNeighbours++;
        // next diagonally right down cell
        if (gridSize > y + 1 && gridSize > x + 1 && currentFrame[x + 1][y + 1] == ALIVE)
            liveNeighbours++;
        // cell below
        if (gridSize > y + 1 && currentFrame[x][y + 1] == ALIVE)
            liveNeighbours++;

        // next left cell
        if (x - 1 >= 0 && currentFrame[x - 1][y] == ALIVE)
            liveNeighbours++;

        // diagonally down left
        if (gridSize > y + 1 && x - 1 >= 0 && currentFrame[x - 1][y + 1] == ALIVE)
            liveNeighbours++;

        // cell above
        if (y - 1 >= 0 && currentFrame[x][y - 1] == ALIVE)
            liveNeighbours++;

        // diagonally above right
        if (y - 1 >= 0 && x + 1 < gridSize && currentFrame[x + 1][y - 1] == ALIVE)
            liveNeighbours++;

        // diagonally above right
        if (y - 1 >= 0 && x - 1 >= 0 && currentFrame[x - 1][y - 1] == ALIVE)
            liveNeighbours++;

        return liveNeighbours;
    }
}
