package conway.gol;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static conway.gol.Cell.ALIVE;
import static conway.gol.Cell.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

class GridTest {

    private Rules rules = new Rules();

    @Test
    public void shouldReturnLiveNeighbours_GivenZeroLengthGrid() {
        // Given
        int gridSize = 0;
        Grid grid = new Grid(new Cell[gridSize][gridSize], rules);

        // When
        int liveNeighbours = grid.getLiveNeighbours(0, 0);

        // Then
        assertThat(liveNeighbours).isEqualTo(0);
    }

    @Test
    public void shouldReturnLiveNeighbours_GivenSingleCellGrid() {
        // Given
        int gridSize = 1;
        Grid grid = new Grid(new Cell[gridSize][gridSize], rules);

        // When
        int liveNeighbours = grid.getLiveNeighbours(0, 0);

        // Then
        assertThat(liveNeighbours).isEqualTo(0);
    }

    @DisplayName("Should return live neighbours for a 2x2 live grid")
    @ParameterizedTest(name = "live neighbours of [{0}, {1}] = 3")
    @CsvSource(value = {"0:0","1:0","0:1","1:1"}, delimiter = ':')
    public void shouldReturnLiveNeighbours_Given2x2Grid(int x, int y) {
        // Given
        Cell[][] cells = {
                {ALIVE, ALIVE},
                {ALIVE, ALIVE}
        };
        Grid grid = new Grid(cells, rules);

        // When
        int liveNeighbours = grid.getLiveNeighbours(x, y);

        // Then
        assertThat(liveNeighbours).isEqualTo(3);
    }

    @DisplayName("Should return live neighbours for a 3x3 live grid")
    @ParameterizedTest(name = "live neighbours of [{0}, {1}] = 3")
    @CsvSource(value = {"0:0:3","0:1:5","0:2:3","1:0:5","1:1:8","1:2:5","2:0:3","2:1:5","2:2:3"}, delimiter = ':')
    public void shouldReturnLiveNeighbours_Given3x3Grid(int x, int y, int expected) {
        // Given
        Cell[][] cells = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE}
        };
        Grid grid = new Grid(cells, rules);

        // When
        int liveNeighbours = grid.getLiveNeighbours(x, y);

        // Then
        assertThat(liveNeighbours).isEqualTo(expected);
    }

    @Test
    public void shouldReturnNextFrameForGivenCurrentFrameOfAllAliveCells() {
        // Given
        Cell[][] cells = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE}
        };
        Grid grid = new Grid(cells, rules);

        // When
        Cell[][] nextFrame = grid.getNextFrame();

        // Then
        assertThat(nextFrame[0]).containsExactly(ALIVE, DEAD, ALIVE);
        assertThat(nextFrame[1]).containsExactly(DEAD, DEAD, DEAD);
        assertThat(nextFrame[2]).containsExactly(ALIVE, DEAD, ALIVE);
    }

    @Test
    public void shouldReturnGridAfter2Ticks() {
        // Given
        Cell[][] cells = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE}
        };
        Grid grid = new Grid(cells, rules);

        // When
        grid.getNextFrame();
        Cell[][] nextFrame = grid.getNextFrame();

        // Then
        assertThat(nextFrame[0]).containsExactly(DEAD, DEAD, DEAD);
        assertThat(nextFrame[1]).containsExactly(DEAD, DEAD, DEAD);
        assertThat(nextFrame[2]).containsExactly(DEAD, DEAD, DEAD);
    }
}