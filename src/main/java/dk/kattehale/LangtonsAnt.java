package dk.kattehale;

import javax.swing.*;
import java.awt.*;

public class LangtonsAnt {

    // Creates the frame and ant-panel and shows it on screen
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Langton's ant");

        // Make DrawAnt object with a board size
        DrawAnt ant = new DrawAnt(300,200);

        // Add the ant-panel to the frame
        frame.add(ant);


        frame.setSize(new Dimension(ant.getWidth(),ant.getHeight()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Start the simulation
        ant.startAnt();
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }

}
