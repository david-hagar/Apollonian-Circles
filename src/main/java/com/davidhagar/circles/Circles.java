package com.davidhagar.circles;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: Jun 12, 2010
 * Time: 12:05:21 PM
 */
public class Circles extends JFrame {

    public Circles() throws HeadlessException {

        CirclePanel circlePanel = new CirclePanel();
        this.getContentPane().add(circlePanel, BorderLayout.CENTER);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth())-50;
        int ySize = ((int) tk.getScreenSize().getHeight())-50;
        this.setSize(xSize, ySize);
        //this.pack();
        this.setLocationRelativeTo(getRootPane());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String args[]) {
        try {
            Circles c = new Circles();
            c.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
