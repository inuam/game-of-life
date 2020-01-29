package conway.gol;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GridBuilderTest {

    @Test
    public void shouldReturnAGridForGivenSize() {
        // Given
        int gridSize = 10;

        // When
        Grid grid = GridBuilder.newGrid(gridSize);

        // Then
        assertThat(grid.getCurrentFrame().length).isEqualTo(gridSize);
        assertThat(grid.getCurrentFrame()[0].length).isEqualTo(gridSize);
    }


}