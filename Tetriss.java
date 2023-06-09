import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Tetriss extends JPanel {
    private static JFrame window;
    private static JLabel minuteField;
    private Board board;
    public static final int WIDTH = 445, HEIGHT = 629;
    private static long startTime;
    private static JLabel arrowKeyPressCountField; // Add this label to display the arrow key press count
    private JTextField textField;


    
    public Tetriss() {
        window = new JFrame("AUIS Tetriss");
        window.setSize(WIDTH +200, HEIGHT + 100);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        minuteField = new JLabel();
        minuteField.setForeground(Color.RED); // Set text color to white
        minuteField.setFont(new Font("Arial", Font.BOLD, 24)); // Set font style
        panel.add(minuteField);
       
        window.add(panel, "North");
        arrowKeyPressCountField = new JLabel("Arrow Key Press Count: 0"); // Initialize the arrow key press count label
        arrowKeyPressCountField.setFont(new Font("Arial", Font.PLAIN, 16));
        ImageIcon icon = new ImageIcon("./AUIS_symbol.png");
        window.setIconImage(icon.getImage());
        window.add(arrowKeyPressCountField, "South"); // Add the label to the bottom of the window
        board = new Board();
        window.add(board);

        window.addKeyListener(board);
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
                                minuteField.setText("<html><span style='font-size:36px;'>Minutes: " + timeDiff + "</span></html>");
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
            while (true) {
                int arrowKeyPressCount = Board.getArrowKeyPressCount(); 
                arrowKeyPressCountField.setText("Arrow Key Press Count: " + arrowKeyPressCount); // Update the label with the current count
    
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }).start();
    new Thread(new Runnable() {
        @Override
        public void run() {
            List l1 = new List();
            ArrayList<Player> players = new ArrayList<>();
            l1.players.add(new Player("Alice", 10));
            l1.players.add(new Player("Bob", 5));
            l1.players.add(new Player("Charlie", 15));
            l1.players.add(new Player("Dave", 8));

            Leaderboard leaderboard = new Leaderboard( l1.players
            );
        }
    }).start();
}
}