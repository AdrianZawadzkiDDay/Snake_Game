package game;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point move(Direction direction){
        switch (direction){
            case UP:
               return new Point(x, y -1);
            case DOWN:
                return new Point(x, y +1);
            case LEFT:
                return new Point(x -1, y);
            case RIGHT:
                return new Point(x+1,y);
            default:
                throw new IllegalArgumentException("nie podano prowidłowego kierunku");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}
