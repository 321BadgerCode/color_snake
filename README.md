# Snake Game

[![Video Example](./ex.png)](https://github.com/321BadgerCode/color_snake/assets/83559316/adeff446-7327-4ebc-a691-5fa00217b68f)

## Description

Snake Game is a classic arcade game where you control a snake to collect food and grow while avoiding collisions with walls and yourself. This Java-based game uses squares for the snake segments and the food, with a gradient effect over the snake's segments between a given array of colors to make the game more visually appealing.

## Features

- Control the snake's movement using arrow keys.
- Collect food to increase the snake's length.
- Game over when the snake collides with walls or itself.
- Colorful gradient effect over the snake's body.

## Installation

1. Clone this repository to your local machine:

	```bash
	git clone https://github.com/321BadgerCode/color_snake.git
	```

2. Compile the Java source files:

	```bash
	javac *.java
	```

3. Run the game:

	```bash
	java SnakeGame
	```

## How to Play

- Use the arrow keys (Up, Down, Left, Right) to control the snake's direction.
- Collect food (red squares) to increase the snake's length.
- Avoid colliding with the walls or the snake's own body.
- Press "R" to restart the game after a game over.

## Customization

You can customize the game by modifying the following parameters in the `SnakeGame.java` file:

- Grid size (`GRID_WIDTH` and `GRID_HEIGHT`)
- Game speed (`Timer` delay in milliseconds)
- Colors for the snake and food segments
- Gradient colors for the snake's body

## Dependencies

This game was built using Java and the Swing library for the graphical interface.

## Author

- Badger Code
- GitHub: [Badger Code](https://github.com/321BadgerCode)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
