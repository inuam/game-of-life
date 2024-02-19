package conway.gol;

import java.io.PrintWriter;

import static java.util.Arrays.deepEquals;

public class GameOfLife {
    private final Grid grid;
    private final PrintWriter log;

    public GameOfLife(Grid grid, PrintWriter log) {
        this.grid = grid;
        this.log = log;
    }

    public void start() throws InterruptedException {
        Cell[][] currentFrame = grid.getCurrentFrame();
        Cell[][] previousFrame = grid.getCurrentFrame();

        printFrame(currentFrame);

        boolean unstable = true;

        while (unstable) {
            Cell[][] nextFrame = grid.getNextFrame();
            printFrame(nextFrame);
            Thread.sleep(500);

            if (deepEquals(currentFrame, nextFrame) || deepEquals(previousFrame, nextFrame)) {
                unstable = false;
            } else {
                previousFrame = currentFrame;
                currentFrame = nextFrame;
            }
        }
    }

    private void printFrame(Cell[][] frame) {
        for (Cell[] cells : frame) {
            for (int y = 0; y < frame.length; y++) {
                log.write(cells[y] == Cell.DEAD ? " . " : " 0 ");
            }
            log.println();
        }
        log.println();
        log.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintWriter log = new PrintWriter(System.out);
        new GameOfLife(GridBuilder.newGrid(9), log).start();
    }

}
