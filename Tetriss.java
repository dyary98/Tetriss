import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Tetriss extends JPanel {
    private static JFrame window;
    private static JLabel minuteField;
    private static JLabel arrowKeyField;
    private Board board;
    public static final int WIDTH = 445, HEIGHT = 629;
    private static long startTime;
    private static int arrowKeyCount;
    
    public Tetriss() {
        window = new JFrame("Tetriss");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        minuteField = new JLabel();
        arrowKeyField = new JLabel();
        panel.add(minuteField);
        panel.add(arrowKeyField);
        window.add(panel, "North");

        board = new Board();
        ArrowKeyListener arrowKeyListener = new ArrowKeyListener();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(arrowKeyListener);
        board.addKeyListener(arrowKeyListener);
        window.add(board);

        window.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Tetriss();
        startTime = System.currentTimeMillis();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer(60000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        long currentTime = System.currentTimeMillis();
                        long timeDiff = (currentTime - startTime) / 1000 / 60; // in minutes

                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                minuteField.setText("Minutes: " + timeDiff);
                            }
                        });
                    }
                });

                timer.start();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                KeyEventDispatcher[] dispatchers = (KeyEventDispatcher[]) KeyboardFocusManager.getCurrentKeyboardFocusManager().getKeyEventDispatchers();
ArrowKeyListener arrowKeyListener = (ArrowKeyListener) dispatchers[0];

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            arrowKeyField.setText("Arrow Keys: " + arrowKeyCount);
                            arrowKeyCount = 0;
                        }
                    });
                }
            }
        }).start();
    }

    static class ArrowKeyListener implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
                        || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    arrowKeyCount++;
                }
            }
            return false;
        }
    }
}