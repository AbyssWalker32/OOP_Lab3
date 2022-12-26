package bouncingClasses;

import rectClasses.ColoredRect;
import rectClasses.DrawableRect;
import rectClasses.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BouncingRectangles extends JPanel {
    private static final int BOX_WIDTH = 900;
    private static final int BOX_HEIGHT = 600;
    private final Rectangle[] rectangles;
    private final float[][] rectSpeed;
    private int width;
    private int height;
    private int j = 0;
    private static final int UPDATE_RATE = 30;

    public BouncingRectangles(Rectangle[] rectangles) {
        this.rectangles = rectangles;
        rectSpeed = new float[rectangles.length][2];
        Random random = new Random();
        for (int i = 0; i < rectSpeed.length; i++) {
            rectSpeed[i][0] = random.ints(3, 13)
                    .findFirst()
                    .getAsInt();
            rectSpeed[i][1] = random.ints(2, 12)
                    .findFirst()
                    .getAsInt();
        }
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        for (Rectangle rectangle : rectangles) {
            height = (rectangle.getY(2) - rectangle.getY(1));
            width = (rectangle.getX(2) - rectangle.getX(1));
            Thread gameThread = new Thread(() -> {
                while (true) {
                    rectangle.move((int) rectSpeed[j][0], (int) rectSpeed[j][1]);
                    if (rectangle.getX(1) < 0) {
                        rectSpeed[j][0] = -rectSpeed[j][0];
                        rectangle.setX(1, 0);
                    } else if (rectangle.getX(2) > BOX_WIDTH) {
                        rectSpeed[j][0] = -rectSpeed[j][0];
                        rectangle.setX(1, BOX_WIDTH - width);
                    }
                    if (rectangle.getY(1) < 0) {
                        rectSpeed[j][1] = -rectSpeed[j][1];
                        rectangle.setY(1, 0);
                    } else if (rectangle.getY(1) + height > BOX_HEIGHT) {
                        rectSpeed[j][1] = -rectSpeed[j][1];
                        rectangle.setY(1, BOX_HEIGHT - width);
                    }
                    repaint();
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
            gameThread.start();
            j++;
        }
        j = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        for (Rectangle rectangle : rectangles) {
            if (rectangle.getClass() == DrawableRect.class) {
                ((DrawableRect) rectangle).setOutColor(Color.red);
                ((DrawableRect) rectangle).draw(g);
            } else if (rectangle.getClass() == ColoredRect.class) {
                ((ColoredRect) rectangle).setOutColor(Color.red);
                ((ColoredRect) rectangle).setInColor((Color.white));
                ((ColoredRect) rectangle).draw(g);
            } else {
                g.setColor(Color.green);
                g.fillRect((int) (rectangle.getX(1) - width), (int) (rectangle.getY(1) - height),
                        (int) (2 * width), (int) (2 * height));
            }
        }
    }
}
