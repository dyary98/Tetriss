/**
 * Board
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.security.auth.kerberos.KeyTab;
import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener {
    public static final Color Blue = new Color(0,40,85);
    public static final Color Gold = new Color(201,151,0);
    public static final int BoardWidth = 10;
    public static final int BoardHieght = 20;
    public static final int BlockSize = 30;
    private Timer loop;
    private int delay = 60 / 1000;
    private static int arrowKeyPressCount;
    
    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
    Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};

    private int delayNormal = 600;
    private int delayFast = 50;
    private long beginTime;
    private boolean barkawtn;
    private int speedSwitch = delayNormal;
    private int x = 4, y = 0;
    private int deltaX= 0;
    private Color[][] board = new Color [BoardHieght ] [BoardWidth];
    private Shape[] shapes = new Shape[7];
    private Random random;  
    private static Shape currentShape, nextShape;
    private int count = 0;
    private int score;


    public Board(){
        random = new Random();
        	// create shapes
            shapes[0] = new Shape(new int[][]{
                {1, 1, 1, 1} // I shape;
            }, this, colors[0]);
    
            shapes[1] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 1, 0}, // T shape;
            }, this, colors[1]);
    
            shapes[2] = new Shape(new int[][]{
                {1, 1, 1},
                {1, 0, 0}, // L shape;
            }, this, colors[2]);
    
            shapes[3] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 0, 1}, // J shape;
            }, this, colors[3]);
    
            shapes[4] = new Shape(new int[][]{
                {0, 1, 1},
                {1, 1, 0}, // S shape;
            }, this, colors[4]);
    
            shapes[5] = new Shape(new int[][]{
                {1, 1, 0},
                {0, 1, 1}, // Z shape;
            }, this, colors[5]);
    
            shapes[6] = new Shape(new int[][]{
                {1, 1},
                {1, 1}, // O shape;
            }, this, colors[6]);
    
            currentShape = shapes[0];
        loop = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                movementAndTimer();
                repaint();
            }
        });
    startGame();    
}
    private void movementAndTimer (){
        currentShape.movementAndTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Blue);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * BlockSize, row * BlockSize, BlockSize, BlockSize);
                }

            }
        }
        currentShape.render(g);

        //Creating the grid and the board 
        g.setColor(Gold);
        for (int i = 0; i < BoardHieght; i++) {
            g.drawLine(0, BlockSize * i , BlockSize * BoardWidth, BlockSize * i);
        }
        for (int i = 0; i < BoardWidth + 1; i++) {
            g.drawLine(i *BlockSize, 0, i * BlockSize , BlockSize * BoardHieght);
        }
    
    }
    public void startGame() {
        setNextShape();
        setCurrentShape();
        loop.start();


    }
    public Color[][] getBoard() {
        return board;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    public void setNextShape() {
        int index = random.nextInt(shapes.length);
        int colorIndex = random.nextInt(colors.length);
        nextShape = new Shape(shapes[index].getCoords(), this, colors[colorIndex]);
    }

    public void setCurrentShape() {
        currentShape = nextShape;
        setNextShape();

        for (int row = 0; row < currentShape.getCoords().length; row++) {
            for (int col = 0; col < currentShape.getCoords()[0].length; col++) {
                if (currentShape.getCoords()[row][col] != 0) {
                    if (board[currentShape.getY() + row][currentShape.getX() + col] != null) {
                        System.out.println("gameover");
                    }
                }
            }
        }
    }
    public void checkGameOver(){

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.setDeltaX(1);
            arrowKeyPressCount++;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.setDeltaX(-1);
            arrowKeyPressCount++;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedUp();
            arrowKeyPressCount++;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentShape.rotateShape();
            arrowKeyPressCount++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            currentShape.speedDown();
        }
    }
    public static int getArrowKeyPressCount() {
        return arrowKeyPressCount;
    }
}