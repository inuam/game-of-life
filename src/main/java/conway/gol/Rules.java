package conway.gol;

import static conway.gol.Cell.Alive;
import static conway.gol.Cell.Dead;

public class Rules {

    public Cell apply(Cell currentState, int liveNeighbours) {
        if (currentState == Alive && liveNeighbours < 2)
            return Dead;
        if(currentState == Alive && liveNeighbours > 3)
            return Dead;

        return currentState;
    }
}
