package com.example;

import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        // Create a new JFrame (the window)
        JFrame frame = new JFrame("Conway's Game of Life");

        // Set the size of the window
        frame.setSize(1000, 600);

        // Add a simple label
        JLabel label = new JLabel("Conway's Game of Life", JLabel.CENTER);
        frame.add(label);
        int rows = 60;
        int cols = 100;
        int cellSize = 10;  // Size of each cell
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Grid grid = new Grid(rows, cols, cellSize, true, false);
        frame.add(grid);
        frame.pack();
        // Make the window visible
        startRepaintTimer(100,grid);
        frame.setVisible(true);
        System.out.println("Success");
    }
    public static void startRepaintTimer(int delay, Grid grid) {
        Timer timer = new Timer(delay, new ActionListener() {  // 1000 ms = 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.nextGeneration();  // Trigger the repaint method
            }
        });
        timer.start();
    }
}