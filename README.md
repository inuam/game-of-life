# Conway's Game of Life 

## Rules

1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

## Test
It's a typical maven project using Junit 5
```shell script
mvn clean test
``` 

## Running the Application
To run the application:

```mvn clean compile exec:java```

The application stops if the currentFrame == nextFrame || previousFrame == nextFrame or could go
on forever if the patterns are 3 frames apart.