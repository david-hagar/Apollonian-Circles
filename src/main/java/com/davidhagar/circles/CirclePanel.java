package com.davidhagar.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: Jun 12, 2010
 * Time: 12:07:41 PM
 */
public class CirclePanel extends JPanel {

    private int numberOfCircles = 4;
    private int numberOfNested = 52; // number from the inside to the outside (needs to be divisible by 4)
    private int numberOfLines = 5000;
    private float scale = 500.0f;   // simple zoom

    public CirclePanel() {
        this.setBackground(Color.white);

        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed. key = " + e.getKeyChar());
                if (e.getKeyChar() == 'p') {
                    System.out.println("Printing ...");
                    printCircles();
                }
            }
        });
    }


    @Override
    public void paint(Graphics g1) {
        super.paint(g1);

        Graphics2D g = (Graphics2D) g1;
        Dimension d = this.getSize();


        drawAll(g, 0, 0, d.width, d.height);
    }

    public void drawAll(Graphics2D g, double x, double y, double width, double height) {
        RenderingHints renderHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(renderHints);

        double screenCenterX = width / 2.0f;
        double screenCenterY = height / 2.0f;

        g.setColor(Color.BLACK);

        g.translate(x + screenCenterX, y + screenCenterY);


        draw(g, width, numberOfNested / 4);
        draw(g, width, numberOfNested);
        draw(g, width, numberOfNested * 4);
        draw(g, width, numberOfNested * 16);
    }

    private void draw(Graphics2D g, double width, int numberOfNested) {
        for (int i = 0; i < numberOfCircles; i++) {
            g.rotate(1.0 / numberOfCircles * Math.PI * 2);

            for (int r = 1; r <= numberOfNested; r++) {
                double radius = distort((float) r / numberOfNested, i, numberOfCircles) * width / 4 * scale;

                float thicknessRatio = 0.5f / numberOfNested;
                drawCircle(g, radius, thicknessRatio);
            }

        }
    }

    private double distort(float v, int i, int max) {
        final int pow = 15;
        return Math.pow(2, v * pow) / Math.pow(2, pow);
    }


    private void drawCircle(Graphics2D g, double radius, double thicknessRatio) {

        Point2D.Double[] innerPoints = makeCircle(radius * (1 - thicknessRatio));
        Point2D.Double[] outerPoints = makeCircle(radius * (1 + thicknessRatio));

        GeneralPath p = new GeneralPath();
        p.moveTo(innerPoints[0].x, innerPoints[0].y);

        for (int i = 1; i < numberOfLines; i++) {
            p.lineTo(innerPoints[i].x, innerPoints[i].y);
        }

        for (int i = numberOfLines - 1; i >= 0; i--) {
            p.lineTo(outerPoints[i].x, outerPoints[i].y);
        }

        p.closePath();
        g.fill(p);

    }


    private Point2D.Double[] makeCircle(double radius) {

        Point2D.Double[] points = new Point2D.Double[numberOfLines];
        for (int i = 0; i < numberOfLines; i++) {
            double angle = ((double) i) / numberOfLines * Math.PI * 2 + Math.PI;

            double x = radius * Math.cos(angle) + radius;
            double y = radius * Math.sin(angle);

            points[i] = new Point2D.Double(x, y);

        }

        return points;
    }


    private float upDown(int i, int n) {
        int index = i * 2;
        if (index > n)
            index = (n - i) * 2;

        return index;
    }


    public void printCircles() {

        CirclesPrintUtil cpu = new CirclesPrintUtil(this);
        cpu.print();
    }

}
