package conway.gol;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static conway.gol.Cell.Alive;
import static conway.gol.Cell.Dead;
import static org.assertj.core.api.Assertions.assertThat;


class RulesTest {

    /**
     * 1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */

    @DisplayName("Any live cell with fewer than two live neighbours dies, as if by underpopulation.")
    @ParameterizedTest(name="Live cell with {0} live neighbour dies")
    @CsvSource(value = {"0", "1"})
    public void shouldDieGivenALiveCellWith_FewerThanTwoLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(Alive, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(Dead);
    }

    @DisplayName("Any live cell with two or three live neighbours lives on to the next generation.")
    @ParameterizedTest(name="Live cell with {0} live neighbour lives")
    @CsvSource(value = {"2", "3"})
    public void shouldStayAliveGivenALiveCellWith_TwOrThreeLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(Alive, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(Alive);
    }

    @DisplayName("Any live cell with more than three live neighbours dies, as if by overpopulation.")
    @ParameterizedTest(name="Live cell with  more {0} live neighbour dies")
    @CsvSource(value = {"4", "5", "6", "7", "8"})
    public void shouldDieGivenALiveCellWith_MoreThanThreeLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(Alive, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(Dead);
    }

    @DisplayName("Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.")
    @ParameterizedTest(name="Live cell with  more {0} live neighbour dies")
    @CsvSource(value = {"3"})
    public void shouldComeAliveGivenADeadCellWith_ExactlyThreeLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(Dead, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(Alive);
    }

}