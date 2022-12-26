import bouncingClasses.BouncingBallSimple;
import bouncingClasses.BouncingRectangleSimple;
import bouncingClasses.BouncingRectangles;
import rectClasses.ColoredRect;
import rectClasses.DrawableRect;
import rectClasses.Rectangle;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("A Bouncing Figures");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.out.println("""
                    Enter the task number

                    1 - Bouncing ball;
                    2 - Bouncing rectangle
                    3 - Many bouncing rectangles""");
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> frame.setContentPane(new BouncingBallSimple());
                case 2 -> frame.setContentPane(new BouncingRectangleSimple(new ColoredRect(0, 10, 100, 50)));
                case 3 -> {
                    Rectangle[] rectangles = new Rectangle[30];
                    Random random = new Random();
                    for (int i = 0; i < rectangles.length; i++) {
                        if (i < 10) {
                            rectangles[i] = new Rectangle(70, 30);
                            rectangles[i].move(random.ints(40, 80).findFirst().getAsInt(),
                                    random.ints(20, 60).findFirst().getAsInt());
                        }
                        if (i >= 10 && i < 20) {
                            rectangles[i] = new DrawableRect(100, 100);
                            rectangles[i].move(random.ints(10, 100).findFirst().getAsInt(),
                                    random.ints(20, 40).findFirst().getAsInt());
                        }
                        if (i >= 20) {
                            rectangles[i] = new ColoredRect(50, 50);
                            rectangles[i].move(random.ints(50, 120).findFirst().getAsInt(),
                                    random.ints(20, 80).findFirst().getAsInt());
                        }
                    }
                    frame.add(new BouncingRectangles(rectangles));
                }
            }
            frame.pack();
            frame.setVisible(true);
        });
    }
}

