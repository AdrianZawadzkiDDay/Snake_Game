package game;

public class Main {
    public static void main(String[] args) {

        SnakeGame snakeGame = new SnakeGame(10, 10) {
            @Override
            public void onGameEnded() {
                System.out.println("Gra się skończyła");
            }

            @Override
            public void onNextStep() {
                System.out.println(getSnake());
            }
        };
        snakeGame.start();
    }
}
