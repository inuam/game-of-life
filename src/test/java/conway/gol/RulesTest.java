package conway.gol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class RulesTest {

    /**
     * 1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */

    @ParameterizedTest(name = "state {0} live neighbours {1} expected {2}")
    @CsvSource(value = {
//          |  Current state | Live Neighbours | Expected State ~|
            "     ALIVE      ,      0          ,     DEAD        ",
            "     ALIVE      ,      1          ,     DEAD        ",
            "     ALIVE      ,      2          ,     ALIVE       ",
            "     ALIVE      ,      3          ,     ALIVE       ",
            "     ALIVE      ,      4          ,     DEAD        ",
            "     ALIVE      ,      5          ,     DEAD        ",
            "     ALIVE      ,      6          ,     DEAD        ",
            "     ALIVE      ,      7          ,     DEAD        ",
            "     ALIVE      ,      8          ,     DEAD        ",
            "     DEAD       ,      0          ,     DEAD        ",
            "     DEAD       ,      1          ,     DEAD        ",
            "     DEAD       ,      2          ,     DEAD        ",
            "     DEAD       ,      3          ,     ALIVE       ",
            "     DEAD       ,      4          ,     DEAD        ",
            "     DEAD       ,      5          ,     DEAD        ",
            "     DEAD       ,      6          ,     DEAD        ",
            "     DEAD       ,      7          ,     DEAD        ",
            "     DEAD       ,      8          ,     DEAD        ",
    })
    public void shouldApplyCellRules(Cell currentState, int liveNeighbours, Cell expected) {
        // Given // When
        var nextState = Rules.gameOfLifeRules.apply(currentState, liveNeighbours);

        // Then
        assertThat(nextState).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Any cell with more than {0} cell should thrown illegal argument exception")
    @CsvSource(value = {"DEAD:9", "ALIVE:9"}, delimiter = ':')
    public void shouldThrowIllegalArgumentException_GivenACell_WithMoreThanEight_Neighbours(Cell cell, int neighbours) {
        // Given

        // when
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rules.gameOfLifeRules.apply(cell, neighbours);
            ;
        });

        // Then
        assertThat(illegalArgumentException).isInstanceOf(IllegalArgumentException.class);
    }
}