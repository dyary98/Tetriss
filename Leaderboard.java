// import java.awt.*;
// import javax.swing.*;
// import javax.swing.border.*;
// import java.util.*;

// public class Leaderboard extends JFrame {
//     private JPanel contentPane;
//     private ArrayList<Player> players;
//     private JList<String> leaderboardList;

//     public Leaderboard(ArrayList<Player> players) {
//         this.players = players;
//         initComponents();
//     }

//     private void initComponents() {
//         setTitle("Leaderboard");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(400, 600);
//         setLocationRelativeTo(null);

//         contentPane = new JPanel(new BorderLayout());
//         contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
//         setContentPane(contentPane);

//         JPanel headerPanel = new JPanel();
//         JLabel headerLabel = new JLabel("Leaderboard");
//         headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
//         headerPanel.add(headerLabel);
//         contentPane.add(headerPanel, BorderLayout.NORTH);

//         JPanel listPanel = new JPanel(new GridLayout(0, 1, 0, 10));
//         listPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//         listPanel.setBackground(Color.WHITE);
//         for (Player p : players) {
//             JLabel nameLabel = new JLabel(p.getName());
//             nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
//             nameLabel.setForeground(Color.BLACK);

//             JLabel scoreLabel = new JLabel("" + p.getScore());
//             scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
//             scoreLabel.setForeground(Color.BLUE);

//             JPanel playerPanel = new JPanel(new BorderLayout());
//             playerPanel.setBackground(Color.WHITE);
//             playerPanel.add(nameLabel, BorderLayout.WEST);
//             playerPanel.add(scoreLabel, BorderLayout.EAST);
//             listPanel.add(playerPanel);
//         }
//         JScrollPane scrollPane = new JScrollPane(listPanel);
//         contentPane.add(scrollPane, BorderLayout.CENTER);
//         setVisible(true);
//     }
    
//     public static void main(String[] args) {
//         ArrayList<Player> players = new ArrayList<>();
//         players.add(new Player("Alice", 10));
//         players.add(new Player("Bob", 5));
//         players.add(new Player("Charlie", 15));
//         players.add(new Player("Dave", 8));
    
//         Leaderboard leaderboard = new Leaderboard(players);
//     }
// }

// class Player {
// private String name;
// private int score;


// public Player(String name, int score) {
//     this.name = name;
//     this.score = score;
// }

// public String getName() {
//     return name;
// }

// public int getScore() {
//     return score;
// }
// }