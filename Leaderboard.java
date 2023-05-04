import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class Leaderboard extends JFrame {
    private JPanel contentPane;
    private ArrayList<Player> players;

    public Leaderboard(ArrayList<Player> players) {
        this.players = players;
        initComponents();
    }


    private void initComponents() {
        setTitle("Leaderboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocation(1600, 200);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Leaderboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerPanel.add(headerLabel);
        contentPane.add(headerPanel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        listPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        listPanel.setBackground(Color.WHITE);
        for (Player p : players) {
            JLabel nameLabel = new JLabel(p.getName());
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            nameLabel.setForeground(Color.BLACK);

            JLabel scoreLabel = new JLabel("" + p.getScore());
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            scoreLabel.setForeground(Color.BLUE);

            JPanel playerPanel = new JPanel(new BorderLayout());
            playerPanel.setBackground(Color.WHITE);
            playerPanel.add(nameLabel, BorderLayout.WEST);
            playerPanel.add(scoreLabel, BorderLayout.EAST);
            listPanel.add(playerPanel);
            ImageIcon icon = new ImageIcon("./AUIS_symbol.png");
            setIconImage(icon.getImage());
        }
        JScrollPane scrollPane = new JScrollPane(listPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER
);

    setVisible(true);
}


}

