package dk.kattehale;

import javax.swing.*;
import java.awt.*;

public class DrawAnt extends JPanel {

    // Board size
    private final int[][] board;
    private final int boardX;
    private final int boardY;

    // Ant position and direction
    private int antX;
    private int antY;
    private int antDir;

    // Directions
    private final int UP=0, RIGHT=1, DOWN=2, LEFT=3;

    // Frame size
    final int width = 1200;
    private final int height = 800;

    public DrawAnt(int x, int y) {
        this.boardX = x;
        this.boardY = y;
        this.board = new int[x][y];

        this.antX = x/2;
        this.antY = y/2;
    }

    public DrawAnt() {
        System.out.println(width);
        this.boardX = 100;
        this.boardY = 100;
        this.board = new int[100][100];
        this.antX = 100/2;
        this.antY = 100/2;
    }

    public void startAnt() {
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                board[i][j] = 0;
            }
        }
        board[antX][antY] = 1;
        antDir = UP;

        while(true) {
            checkPosition(antX, antY);
        }

    }

    private void repaintAnt() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    // Checks the state of the current position and determines what to do next.
    private void checkPosition(int antX, int antY) {
        int state = board[antX][antY];

        // If tile is white make it black and turn clockwise or vice versa
        if(state == 0) {
            turnClockwise();
            board[antX][antY] = 1;
        } else if (state == 1) {
            turnCounterClockwise();
            board[antX][antY] = 0;
        }

        // Update the board
        repaintAnt();

        // Move the ant forward
        moveForward();

    }

    private void turnClockwise() {
        antDir++;
        if (antDir > LEFT)
            antDir = UP;
    }

    private void turnCounterClockwise() {
        antDir--;
        if (antDir < UP)
            antDir = LEFT;
    }

    // Checks the direction of the ant and moves forward
    private void moveForward() {

        switch (antDir) {
            case UP -> antY--;
            case RIGHT -> antX++;
            case DOWN -> antY++;
            case LEFT -> antX--;
        }

        // If the ant is moving off screen, place ant at the opposite side of the screen
        if (antX > boardX - 1) {
            antX = 0;
        } else if (antX < 0) {
            antX = boardX -1;
        }

        if (antY > boardY -1) {
            antY = 0;
        } else if (antY < 0) {
            antY = boardY - 1;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                if(board[i][j] == 0) {
                    g.setColor(Color.WHITE);
                } else if (board[i][j] == 1) {
                    g.setColor(Color.BLACK);
                } else if (board[i][j] == 2) {
                    g.setColor(new Color(139, 6, 6));
                }
                g.fillRect(i*(width/boardX),j*(height/boardY),width/boardX,height/boardY);
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
