package conway.gol;

import java.util.Random;

import static conway.gol.Cell.ALIVE;
import static conway.gol.Cell.DEAD;

public class GridBuilder {

    public static Grid newGrid(int gridSize) {

        Random randomInt = new Random();

        Cell[][] cells = new Cell[gridSize][gridSize];

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                cells[x][y] = randomInt.nextInt(2) == 0 ? DEAD : ALIVE;
            }
        }
        return new Grid(cells, new Rules());
    }
}
