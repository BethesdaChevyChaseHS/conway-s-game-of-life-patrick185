package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {
    private int rows;
    private int cols;
    private int cellSize;
    private int[][] grid;  // 2D array representing the grid state

    public Grid(int rows, int cols, int cellSize, boolean randomStart, boolean customStart) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.grid = new int[rows][cols];  // Initialize the grid with default state 0 (empty)
        
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        if(customStart){
            //ADD YOUR INTIALIZATION HERE
        }else if(randomStart){
            //initialize grid to random values:
            for(int i = 0; i < rows;i++){
                for(int j = 0; j < cols;j++){
                    this.grid[i][j] = (int)(Math.random()*2);
                }
            }
        } else {
            //create a glider for testing
            this.grid[2][0] = 1;
            this.grid[3][1] = 1;
            this.grid[1][2] = 1;
            this.grid[2][2] = 1;
            this.grid[3][2] = 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Loop through each cell and paint based on alive or dead
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                if (grid[row][col] == 0) {//dead
                    g.setColor(Color.BLACK);
                } else if (grid[row][col] == 1) {
                    // Wall (brown)
                    g.setColor(Color.WHITE); // alive
                } else {
                    throw new Error("Not a valid grid value");
                }
                

                // Draw the cell
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

                // Draw grid lines (optional)
                g.setColor(Color.BLACK);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    //ALL YOUR CODE GOES HERE
    public void nextGeneration() {
        // Create a new temporary array to store the next generation
        int[][] nextGrid = new int[rows][cols];
    
        // Loop through each cell and apply the rules of the game
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col);
    
                if (grid[row][col] == 1) {
                    // Apply Conway's rules for live cells
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        nextGrid[row][col] = 0;  // Cell dies
                    } else {
                        nextGrid[row][col] = 1;  // Cell lives
                    }
                } else {
                    // Apply Conway's rule for dead cells
                    if (liveNeighbors == 3) {
                        nextGrid[row][col] = 1;  // Cell becomes alive
                    } else {
                        nextGrid[row][col] = 0;  // Cell remains dead
                    }
                }
            }
        }
    
        // Copy the values of nextGrid to the real grid
        grid = nextGrid;
    
        // Repaint the grid
        repaint();
    }
    
    private int countLiveNeighbors(int row, int col) {
        int liveNeighbors = 0;
    
        // Loop through the 3x3 grid surrounding the cell, including itself
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Skip the current cell
                }
                int neighborRow = row + i;
                int neighborCol = col + j;
    
                // Make sure the neighbor is within the grid bounds
                if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < cols) {
                    liveNeighbors += grid[neighborRow][neighborCol];
                }
            }
        }
    
        return liveNeighbors;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //don't put code here
    }
}