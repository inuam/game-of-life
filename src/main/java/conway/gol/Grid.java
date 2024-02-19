package conway.gol;

import static conway.gol.Cell.ALIVE;

public class Grid {
    private Cell[][] currentFrame;

    private final Rules rules;
    private final int gridSize;

    public Grid(Cell[][] currentFrame, Rules rules) {
        this.currentFrame = currentFrame;
        this.gridSize = currentFrame.length;
        this.rules = rules;
    }

    public Cell[][] getNextFrame() {

        Cell[][] nextFrame = new Cell[gridSize][gridSize];

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                nextFrame[x][y] = Rules.gameOfLifeRules.apply(currentFrame[x][y], getLiveNeighbours(x, y));
            }
        }
        currentFrame = nextFrame;
        return nextFrame;
    }

    public int getLiveNeighbours(int x, int y) {
        int liveNeighbours = 0;

        // 1x1,0x0 grid has no neighbours
        if (gridSize <= 1)
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

    public Cell[][] getCurrentFrame() {
        return currentFrame;
    }
}
