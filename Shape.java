import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingUtilities;

public class Shape {
    private int delayNormal = 600;
    private int delayFast = 100;
    private long beginTime;
    private int speedSwitch = delayNormal;
    private int x , y ;
    private int deltaX = 0;
    private boolean moveX = false;
    public static final int BoardWidth = 10;
    public static final int BoardHieght = 20;
    public static final int BlockSize = 30;
    private boolean barkawtn = false;

    private int timePassedFromCollision = -1;

    private boolean gamePaused = false;
    private boolean gameOver = false;
    private Board board;
    private Color color;
    private long time, lastTime;
    private static int score;

    private int[][] cords;
    public Shape(int[][] cords, Board board, Color color) {
        this.cords = cords;
        this.board = board;
        this.color = color;
        deltaX = 0;
        x = 4;
        y = 0;
        lastTime = System.currentTimeMillis();
      
    }
    long deltaTime;

    public void movementAndTimer() {
        moveX = true;
        deltaTime = System.currentTimeMillis() - lastTime;
        time += deltaTime;
        lastTime = System.currentTimeMillis();

       
        if (barkawtn && timePassedFromCollision > 500) {
            for (int row = 0; row < cords.length; row++) {
                for (int col = 0; col < cords[0].length; col++) {
                    if (cords[row][col] != 0) {
                        board.getBoard()[y + row][x + col] = color;
                    }
                }
            }
            checkLine();
            board.setCurrentShape();
            timePassedFromCollision = -1;
        };
        if (!(x + deltaX + cords[0].length > 10) && !(x + deltaX < 0)) {
            for (int row = 0; row < cords.length; row++) {
                for (int col = 0; col < cords[row].length; col++) {
                    if (cords[row][col] != 0) {
                        if (board.getBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }
                    }
                }
            }

            if (moveX) {
                x += deltaX;
            }
        }
        // Check position + height(number of row) of shape
        if (timePassedFromCollision == -1) {
            if (!(y + 1 + cords.length > 20)) {

                for (int row = 0; row < cords.length; row++) {
                    for (int col = 0; col < cords[row].length; col++) {
                        if (cords[row][col] != 0) {
                            if (board.getBoard()[y + 1 + row][x + col] != null) {
                                collision();
                            }
                        }
                    }
                }
                if (time > speedSwitch) {
                    y++;
                    time = 0;
                }
            } else {
                collision();
            }
        } else {
            timePassedFromCollision += deltaTime;
        }

        deltaX = 0;
    }
    private void collision() {
        barkawtn = true;
        timePassedFromCollision = 0;
    }
    public void render(Graphics g) {
        //creating the shapes
        for (int i = 0; i < cords.length; i++) {
            for (int j = 0; j < cords[0].length; j++) {
                if (cords[i][j] != 0) {
                    g.setColor(Color.RED);
                    //we added x and y and BLock size after them so that the cordss can move based on the timer that we have created above 
                    g.fillRect(j * BlockSize + x * BlockSize, i * BlockSize + y * BlockSize, BlockSize, BlockSize);
                }
            }
        }
        
        g.drawString("Score: " + score, 400, 400);

    }
    private void checkLine() {
        int size = board.getBoard().length - 1;

        for (int i = board.getBoard().length - 1; i > 0; i--) {
            int count = 0;
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                if (board.getBoard()[i][j] != null) {
                    count++;
                                System.out.println("score");

                }

                board.getBoard()[size][j] = board.getBoard()[i][j];
            }
            if (count < board.getBoard()[0].length) {
                size--;
                score++;
            }
        }
    }
    public void rotateShape() {

        int[][] rotatedShape = null;

        rotatedShape = transposeMatrix(cords);

        rotatedShape = reverseRows(rotatedShape);

        if ((x + rotatedShape[0].length > 10) || (y + rotatedShape.length > 20)) {
            return;
        }

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int col = 0; col < rotatedShape[row].length; col++) {
                if (rotatedShape[row][col] != 0) {
                    if (board.getBoard()[y + row][x + col] != null) {
                        return;
                    }
                }
            }
        }
        cords = rotatedShape;
    }

    private int[][] transposeMatrix(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
        return temp;
    }

    private int[][] reverseRows(int[][] matrix) {

        int middle = matrix.length / 2;

        for (int i = 0; i < middle; i++) {
            int[] temp = matrix[i];

            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        return matrix;

    }

 
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void speedUp() {
        speedSwitch = delayFast;
        score++;
    }
    public Color getColor() {
        return color;
    }

    public void speedDown() {
        speedSwitch = delayNormal;
    }
    public void moveRight() {
        deltaX = 1;
    }
    public void moveLeft() {
        deltaX = -1;
    }

    public int[][] getCoords() {
        return cords;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}