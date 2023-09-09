import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
	private static final int TILE_SIZE = 20;
	private static final int GRID_WIDTH = 20;
	private static final int GRID_HEIGHT = 20;
	private static final int GAME_WIDTH = TILE_SIZE * GRID_WIDTH;
	private static final int GAME_HEIGHT = TILE_SIZE * GRID_HEIGHT;

	private ArrayList<Point> snake;
	private Point food;
	private int dx, dy;
	private boolean gameOver;
	private Color[] gradientColors = {Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

	private Timer timer;

	public SnakeGame() {
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new SnakeKeyListener());
		startGame();
	}

	private void startGame() {
		snake = new ArrayList<>();
		snake.add(new Point(5, 5)); // Initial position of the snake
		food = generateFoodLocation();
		dx = 1;
		dy = 0;
		gameOver = false;

		if (timer != null && timer.isRunning()) {
			timer.stop();
			timer = null;
		}

		timer = new Timer(100, this);
		timer.start();
	}

	private Point generateFoodLocation() {
		Random rand = new Random();
		int x, y;
		do {
			x = rand.nextInt(GRID_WIDTH);
			y = rand.nextInt(GRID_HEIGHT);
		} while (snake.contains(new Point(x, y)));
		return new Point(x, y);
	}

	private void move() {
		Point newHead = new Point(snake.get(0).x + dx, snake.get(0).y + dy);

		if (newHead.equals(food)) {
			snake.add(0, food);
			food = generateFoodLocation();
		} else {
			snake.add(0, newHead);
			snake.remove(snake.size() - 1);
		}

		if (checkCollision(newHead) || isOutOfBounds(newHead)) {
			gameOver = true;
		}
	}

	private boolean checkCollision(Point head) {
		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(i).equals(head)) {
				return true;
			}
		}
		return false;
	}

	private boolean isOutOfBounds(Point head) {
		return head.x < 0 || head.y < 0 || head.x >= GRID_WIDTH || head.y >= GRID_HEIGHT;
	}

	private void drawSnake(Graphics2D g2d) {
		for (int i = 0; i < snake.size(); i++) {
			int gradientIndex = i * gradientColors.length / snake.size();
			Color color = gradientColors[gradientIndex];
			g2d.setColor(color);
			Point segment = snake.get(i);
			g2d.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
	}
	/*private void drawSnake(Graphics2D g2d) {
		for (int i = 0; i < snake.size(); i++) {
			float gradientPosition = (float) i / (snake.size() - 1); // Calculate the position within the gradient
			int colorIndex1 = (int) (gradientPosition * (gradientColors.length - 1));
			int colorIndex2 = colorIndex1 + 1;

			if (colorIndex2 >= gradientColors.length) {
				colorIndex2 = gradientColors.length - 1;
			}

			float t = (gradientPosition - colorIndex1 / (float) (gradientColors.length - 1))
				* (gradientColors.length - 1);

			Color color1 = gradientColors[colorIndex1];
			Color color2 = gradientColors[colorIndex2];

			int red = (int) (color1.getRed() + t * (color2.getRed() - color1.getRed()));
			int green = (int) (color1.getGreen() + t * (color2.getGreen() - color1.getGreen()));
			int blue = (int) (color1.getBlue() + t * (color2.getBlue() - color1.getBlue()));

			Color interpolatedColor = new Color(red, green, blue);

			g2d.setColor(interpolatedColor);

			Point segment = snake.get(i);
			g2d.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
	}*/
	public static Color interpolateColor(Color color1, Color color2, double fraction) {
		int r = (int)((color2.getRed() - color1.getRed()) * fraction + color1.getRed());
		int g = (int)((color2.getGreen() - color1.getGreen()) * fraction + color1.getGreen());
		int b = (int)((color2.getBlue() - color1.getBlue()) * fraction + color1.getBlue());

		return new Color(r, g, b);
	}
	/*private void drawSnake(Graphics2D g2d) {
		for (int i = 0; i < snake.size(); i++) {
			Color color = interpolateColor(gradientColors[0], gradientColors[1], 1 - (i*2 / snake.size()));
			g2d.setColor(color);
			Point segment = snake.get(i);
			g2d.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
	}
	public static String getColorizedText(String text, Color[] colors, String type) {
		int textLength = text.length();
		int colorCount = colors.length;
		StringBuilder result = new StringBuilder();

		for (int i = 1; i < colorCount; i++) {
			int startIndex = (i - 1) * (textLength / colorCount);
			int endIndex = i * (textLength / colorCount);
			String subText = text.substring(startIndex, endIndex);

			// Assuming you have a method called interpolateColor similar to the one you provided earlier
			Color interpolatedColor = interpolateColor(colors[i - 1], colors[i], 0.5); // Example: interpolate at 50%

			// Apply the color to the subText and format it as needed
			String colorizedSubText = "<font color=\"" + getColorHexString(interpolatedColor) + "\">" + subText + "</font>";

			result.append(colorizedSubText);
		}

		return result.toString();
	}

	public static String getColorHexString(Color color) {
		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}*/

	private void drawFood(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

	private void drawGameOver(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));
		g2d.drawString("Game Over! Press R to Restart", GAME_WIDTH / 4, GAME_HEIGHT / 2);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (!gameOver) {
			move();
			drawSnake(g2d);
			drawFood(g2d);
		} else {
			drawGameOver(g2d);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	private class SnakeKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT && dx == 0) {
				dx = -1;
				dy = 0;
			} else if (key == KeyEvent.VK_RIGHT && dx == 0) {
				dx = 1;
				dy = 0;
			} else if (key == KeyEvent.VK_UP && dy == 0) {
				dx = 0;
				dy = -1;
			} else if (key == KeyEvent.VK_DOWN && dy == 0) {
				dx = 0;
				dy = 1;
			} else if (key == KeyEvent.VK_R && gameOver) {
				startGame();
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Snake Game");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(new SnakeGame());
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}