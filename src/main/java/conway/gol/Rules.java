package conway.gol;

import java.util.function.BiFunction;

import static conway.gol.Cell.ALIVE;
import static conway.gol.Cell.DEAD;

public class Rules {

    public static final BiFunction<Cell, Integer, Cell> gameOfLifeRules = (currentState, liveNeighbours) -> {
        if (liveNeighbours > 8)
            throw new IllegalArgumentException("Cell neighbours can't exceed 8");

        return switch(currentState) {
            case ALIVE -> liveNeighbours < 2 || liveNeighbours > 3 ? DEAD : ALIVE;
            case DEAD -> liveNeighbours == 3 ? ALIVE : DEAD;
        };
    };
}
