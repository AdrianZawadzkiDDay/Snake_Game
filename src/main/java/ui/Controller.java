package ui;

import game.Direction;
import game.Point;
import game.Snake;
import game.SnakeGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    private final static int POINT_SIZE = 20;
    @FXML
    private Canvas canvas;
    @FXML
    private Label gameEndedLabel;
    @FXML
    private GridPane buttonsGridPane;
    private SnakeGame snakeGame;
    private GraphicsContext graphicsContext;


    public void initialize() {
        gameEndedLabel.setManaged(false);
        graphicsContext = canvas.getGraphicsContext2D();
        canvas.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();

            if (keyCode.equals(KeyCode.DOWN)) {
                snakeGame.setDirection(Direction.DOWN);
                return;
            }
            if (keyCode.equals(KeyCode.UP)) {
                snakeGame.setDirection(Direction.UP);
                return;
            }
            if (keyCode.equals(KeyCode.LEFT)) {
                snakeGame.setDirection(Direction.LEFT);
                return;
            }
            if (keyCode.equals(KeyCode.RIGHT)){
                snakeGame.setDirection(Direction.RIGHT);
                return;
            }
        });
        snakeGame = new SnakeGame((int) canvas.getWidth()/POINT_SIZE,
                (int) canvas.getHeight()/POINT_SIZE) {
            @Override
            public void onGameEnded() {
                gameEndedLabel.setManaged(true);
                buttonsGridPane.setManaged(false);
                buttonsGridPane.setVisible(false);
            }

            @Override
            public void onNextStep() {
                drawGame();
            }
        };

        Thread thread = new Thread(() -> {
            snakeGame.start();
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void drawGame(){
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(Color.BLACK);
        Snake snake = snakeGame.getSnake();
        drawPoint(snake.getHead());
        graphicsContext.setFill(Color.GREEN);
        snake.getBody().forEach(point -> drawPoint(point));
        graphicsContext.setFill(Color.RED);
        drawPoint(snakeGame.getApple());
    }

    private void drawPoint(Point point) {
        graphicsContext.fillRect(point.getX() * POINT_SIZE,
               point.getY() * POINT_SIZE, POINT_SIZE, POINT_SIZE );
    }

    public void onDownButtonClick(){ snakeGame.setDirection(Direction.DOWN);}

    public void onUpButtonClick(){
        snakeGame.setDirection(Direction.UP);
    }

    public void onRightButtonClick(){
        snakeGame.setDirection(Direction.RIGHT);
    }

    public void onLeftButtonClick(){
        snakeGame.setDirection(Direction.LEFT);
    }

}
