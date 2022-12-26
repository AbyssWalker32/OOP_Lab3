package rectClasses;

public class Rectangle {
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int width;
    protected int height;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public Rectangle(int x, int y) {
        x1 = y1 = 0;
        x2 = x;
        y2 = y;
        width = x2 - x1;
        height = y2 - y1;
    }

    public Rectangle() {
        x1 = x2 = 0;
        y1 = y2 = 0;
    }

    public void rect_print() {
        System.out.printf("Состояние экземпляра\nx1=%d y1=%d\nx2=%d y2=%d\n",
                x1, y1, x2, y2);
    }

    public void move(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;

        this.x2 += dx;
        this.y2 += dy;
    }

    protected Rectangle Union(Rectangle rect) {
        return new Rectangle(Math.min(this.x1, rect.x1),
                Math.min(this.y1, rect.y1),
                Math.max(this.x2, rect.x2),
                Math.max(this.y2, rect.y2));
    }

    public int getX(int x) {
        if (x == 1) {
            return x1;
        } else if (x == 2) {
            return x2;
        }
        return 0;
    }

    public void setX(int x, int value) {
        if (x == 1) {
            x1 = value;
        } else if (x == 2) {
            x2 = value;
        }
    }

    public int getY(int y) {
        if (y == 1) {
            return y1;
        } else if (y == 2) {
            return y2;
        }
        return 0;
    }

    public void setY(int y, int value) {
        if (y == 1) {
            y1 = value;
        } else if (y == 2) {
            y2 = value;
        }
    }
}
