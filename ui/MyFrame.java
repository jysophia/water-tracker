package ui;

import javax.swing.*;
import java.awt.*;

//This is a JFrame that is used consistently throughout the classes to ensure the same set-up of the frame
public class MyFrame extends JFrame {

    //MODIFIES: this
    //EFFECTS: constructs MyFrame
    public MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Water Tracker");
        this.setVisible(true);
        this.setSize(800,800);
        this.setResizable(false);
        this.revalidate();
        this.repaint();
    }

}
