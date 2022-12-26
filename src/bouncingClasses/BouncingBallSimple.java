package bouncingClasses;

import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

public class BouncingBallSimple extends JPanel {
    private static final int BOX_WIDTH = 900;
    private static final int BOX_HEIGHT = 600;

    private float ballRadius = 200;
    private float ballX = ballRadius + 50;
    private float ballY = ballRadius + 20;
    private float ballSpeedX = 3;
    private float ballSpeedY = 2;

    private static final int UPDATE_RATE = 30;

    public BouncingBallSimple() {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    ballX += ballSpeedX;
                    ballY += ballSpeedY;
                    if (ballX - ballRadius < 0) {
                        ballSpeedX = -ballSpeedX;
                        ballX = ballRadius;
                    } else if (ballX + ballRadius > BOX_WIDTH) {
                        ballSpeedX = -ballSpeedX;
                        ballX = BOX_WIDTH - ballRadius;
                    }
                    if (ballY - ballRadius < 0) {
                        ballSpeedY = -ballSpeedY;
                        ballY = ballRadius;
                    } else if (ballY + ballRadius > BOX_HEIGHT) {
                        ballSpeedY = -ballSpeedY;
                        ballY = BOX_HEIGHT - ballRadius;
                    }
                    repaint();
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        g.setColor(Color.BLUE);
        g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
                (int) (2 * ballRadius), (int) (2 * ballRadius));

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("Ball @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", ballX, ballY, ballSpeedX, ballSpeedY);
        g.drawString(sb.toString(), 20, 30);
    }
}
