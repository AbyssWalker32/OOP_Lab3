package bouncingClasses;

import rectClasses.ColoredRect;
import rectClasses.Rectangle;

import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

public class BouncingRectangleSimple extends JPanel {
    private static final int BOX_WIDTH = 1000;
    private static final int BOX_HEIGHT = 800;
    private float width;
    private float height;
    private float rectX = width + 60;
    private float rectY = height + 30;
    private float rectSpeedX = 3;
    private float rectSpeedY = 2;

    private static final int UPDATE_RATE = 60;

    public BouncingRectangleSimple(ColoredRect rect) {
        width = rect.getX(2) - rect.getX(1);
        height = rect.getY(2) - rect.getY(1);
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        Thread gameThread = new Thread(() -> {
            while (true) {
                rectX += rectSpeedX;
                rectY += rectSpeedY;
                if (rectX - width < 0) {
                    rectSpeedX = -rectSpeedX;
                    rectX = width;
                } else if (rectX + width > BOX_WIDTH) {
                    rectSpeedX = -rectSpeedX;
                    rectX = BOX_WIDTH - width;
                }
                if (rectY - height < 0) {
                    rectSpeedY = -rectSpeedY;
                    rectY = height;
                } else if (rectY + height > BOX_HEIGHT) {
                    rectSpeedY = -rectSpeedY;
                    rectY = BOX_HEIGHT - height;
                }
                repaint();
                try {
                    Thread.sleep(1000 / UPDATE_RATE);
                } catch (InterruptedException ex) {
                }
            }
        });
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        g.setColor(Color.red);
        g.drawRect((int) (rectX - width), (int) (rectY - height),
                (int) (2 * width), (int) (2 * height));
        g.setColor(Color.blue);
        g.fillRect((int) (rectX - width), (int) (rectY - height),
                (int) (2 * width), (int) (2 * height));

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("Rectangle @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", rectX, rectY, rectSpeedX, rectSpeedY);
        g.drawString(sb.toString(), 20, 30);
    }
}

