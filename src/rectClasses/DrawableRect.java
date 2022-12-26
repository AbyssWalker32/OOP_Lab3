package rectClasses;

import java.awt.*;

public class DrawableRect extends Rectangle {
    protected Color outColor = Color.white;

    public DrawableRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public DrawableRect(int x, int y) {
        super(x, y);
    }

    public DrawableRect() {
        super();
    }

    public void setOutColor(Color newColor) {
        outColor = newColor;
    }

    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect((int) (x1 - width), (int) (y1 - height),
                (int) (2 * width), (int) (2 * height));
    }

}
