package conway.gol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static conway.gol.Cell.ALIVE;
import static conway.gol.Cell.DEAD;
import static org.assertj.core.api.Assertions.assertThat;


class RulesTest {

    /**
     * 1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */

    @DisplayName("Any live cell with fewer than two live neighbours dies, as if by underpopulation.")
    @ParameterizedTest(name = "Live cell with {0} live neighbour dies")
    @CsvSource(value = {"0", "1"})
    public void shouldDieGivenALiveCellWith_FewerThanTwoLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(ALIVE, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(DEAD);
    }

    @DisplayName("Any live cell with two or three live neighbours lives on to the next generation.")
    @ParameterizedTest(name = "Live cell with {0} live neighbour lives")
    @CsvSource(value = {"2", "3"})
    public void shouldStayAliveGivenALiveCellWith_TwOrThreeLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(ALIVE, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(ALIVE);
    }

    @DisplayName("Any live cell with more than three live neighbours dies, as if by overpopulation.")
    @ParameterizedTest(name = "Live cell with  more {0} live neighbour dies")
    @CsvSource(value = {"4", "5", "6", "7", "8"})
    public void shouldDieGivenALiveCellWith_MoreThanThreeLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(ALIVE, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(DEAD);
    }

    @DisplayName("Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.")
    @ParameterizedTest(name = "Dead cell with  more {0} live neighbours come alive")
    @CsvSource(value = {"3"})
    public void shouldComeAliveGivenADeadCellWith_ExactlyThreeLiveNeighbours(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(DEAD, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(ALIVE);
    }

    @DisplayName("Any dead cell with exactly not three live neighbours becomes stays dead")
    @ParameterizedTest(name = "Dead cell with {0} live neighbour stays dead")
    @CsvSource(value = {"0", "1", "2", "4", "5", "6", "7", "8"})
    public void shouldStayDeadWithAnyNumberOfLiveNeighboursOtherThanThree(int liveNeighbours) {
        // Given
        Rules classToTest = new Rules();

        // When
        Cell actual = classToTest.apply(DEAD, liveNeighbours);

        // Then
        assertThat(actual)
                .isEqualTo(DEAD);
    }

    @ParameterizedTest(name = "Any cell with more than {0} cell should thrown illegal argument exception")
    @CsvSource(value = {"DEAD:9", "ALIVE:9"}, delimiter = ':')
    public void shouldThrowIllegalArgumentException_GivenACell_WithMoreThanEight_Neighbours(Cell cell, int neighbours) {
        // Given
        Rules classToTest = new Rules();

        // when
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            classToTest.apply(cell, neighbours);
            ;
        });

        // Then
        assertThat(illegalArgumentException).isInstanceOf(IllegalArgumentException.class);
    }
}