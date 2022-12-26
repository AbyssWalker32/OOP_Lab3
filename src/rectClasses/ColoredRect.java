package rectClasses;

import java.awt.*;

public class ColoredRect extends DrawableRect {
    protected Color inColor = Color.red;

    public ColoredRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public ColoredRect(int x, int y) {
        super(x, y);
    }

    public ColoredRect() {
        super();
    }

    public void setInColor(Color newColor) {
        inColor = newColor;
    }

    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect(x1, y1, (int) (2 * width), (int) (2 * height));
        g.setColor(inColor);
        g.fillRect(x1, y1, (int) (2 * width), (int) (2 * height));
    }
}
