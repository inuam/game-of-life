package conway.gol;

import org.junit.jupiter.api.Test;

import static conway.gol.Cell.Alive;
import static org.assertj.core.api.Assertions.assertThat;


class RulesTest {

    /**
     * 1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */

    @Test
    public void shouldDieGivenACellWith_ZeroLiveNeighbours() {
        // Given
        Rules classToTest = new Rules();
        int liveNeighbours = 0;

        // When
        Cell actual = classToTest.apply(Alive, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(Alive);
    }


}