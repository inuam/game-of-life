package conway.gol;

import static conway.gol.Cell.ALIVE;
import static conway.gol.Cell.DEAD;

public class Rules {

    public Cell apply(Cell currentState, int liveNeighbours) {

        if (liveNeighbours > 8)
            throw new IllegalArgumentException("Cell neighbours can't exceed 8");

        switch (currentState) {
            case ALIVE:
                if (liveNeighbours < 2 || liveNeighbours > 3)
                    return DEAD;
            case DEAD:
                if (currentState == DEAD && liveNeighbours == 3)
                    return ALIVE;
            default:
                return currentState;

        }
    }
}
