package conway.gol;

import static conway.gol.Cell.Alive;
import static conway.gol.Cell.Dead;

public class Rules {

    public Cell apply(Cell currentState, int liveNeighbours) {

        if (liveNeighbours > 8)
            throw new IllegalArgumentException("Cell neighbours can't exceed 8");

        switch (currentState) {
            case Alive:
                if (liveNeighbours < 2 || liveNeighbours > 3)
                    return Dead;
            case Dead:
                if (currentState == Dead && liveNeighbours == 3)
                    return Alive;
            default:
                return currentState;

        }
    }
}
