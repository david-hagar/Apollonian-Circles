package com.davidhagar.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: Jun 12, 2010
 * Time: 12:05:21 PM
 */
public class Circles extends JFrame {

    CirclePanel circlePanel;

    public Circles() throws HeadlessException {

        circlePanel = new CirclePanel();
        this.getContentPane().add(circlePanel, BorderLayout.CENTER);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth()) - 50;
        int ySize = ((int) tk.getScreenSize().getHeight()) - 50;
        this.setSize(xSize, ySize);
        //this.pack();
        this.setLocationRelativeTo(getRootPane());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        javax.swing.JMenu       jMenu = new javax.swing.JMenu();

        javax.swing.JMenuBar jMenuBar = new javax.swing.JMenuBar();


        jMenu.setText("Circles");

        JMenuItem printMenuItem = new javax.swing.JMenuItem("Print ...");
        printMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printMenuItemActionPerformed(evt);
            }
        });
        jMenu.add(printMenuItem);


        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);

    }

    private void printMenuItemActionPerformed(ActionEvent evt) {
        circlePanel.printCircles();
    }


    public static void main(String args[]) {
        try {
            Circles c = new Circles();
            c.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
