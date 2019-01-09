package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class SnakeGame {
    private Snake snake;
    private Direction direction;
    private int xBound;
    private int yBound;
    private Point apple;

    public SnakeGame(int xBound, int yBound) {
        this.xBound = xBound;
        this.yBound = yBound;
        Point head = new Point(2,2);
        List<Point> body = Arrays.asList(new Point(3,2), new Point(3,3));
        this.snake = new Snake(head, body);
        this.direction = Direction.DOWN;
        randomizeApple();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void start(){
        while (!checkIfSnakeCrashed()){
            onNextStep();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           boolean appleEaten = snake.getHead().move(direction).equals(apple);
           snake.move(direction, !appleEaten);
            if(appleEaten) {
               randomizeApple();
            }

        }
        onGameEnded();
    }

    public abstract void onGameEnded();

    public abstract void onNextStep();

    private boolean checkIfSnakeCrashed(){
        Point head = snake.getHead();
        return checkIfHeadIsOutsideOfBounds(head)
                || snake.getBody().contains(head);
    }

    private boolean checkIfHeadIsOutsideOfBounds(Point point) {
        return point.getX() < 0 || point.getY() < 0
                || point.getX() >= xBound || point.getY() >= yBound;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public void randomizeApple() {
        Random random = new Random();
        do{
            apple = new Point(random.nextInt(xBound), random.nextInt(yBound));
        } while(snake.contains(apple));
    }
}
