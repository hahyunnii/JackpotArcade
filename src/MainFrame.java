
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import omokGame.Omok;
import puzzleGame.Puzzle;
import slotMachineGame.SlotMachine;
import pacmanGame.Pacman;
import tetrisGame.Tetris;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Jackpot Arcade");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(9, 14, 34));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(9, 14, 34));
        JLabel title = new JLabel("JACKPOT ARCADE");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.YELLOW);
        title.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(9, 14, 34));

        Font btnFont = new Font("Comic Sans MS", Font.BOLD, 20);
        Color btnColor = new Color(255, 173, 51);

        JButton omokBtn = createStyledButton("OMOK GAME", btnFont, btnColor);
        JButton puzzleBtn = createStyledButton("PUZZLE MATCHING", btnFont, btnColor);
        JButton pacmanBtn = createStyledButton("PACMAN", btnFont, btnColor);
        JButton tetrisBtn = createStyledButton("TETRIS", btnFont, btnColor);
        JButton slotBtn = createStyledButton("SLOT MACHINE", btnFont, btnColor);

        omokBtn.addActionListener(e -> new Omok("오목 게임"));
        puzzleBtn.addActionListener(e -> {
            Puzzle g = new Puzzle();
            g.addLayout();
            g.initChar();
            g.showAnswer();
        });
        pacmanBtn.addActionListener(e -> {
            try {
                Pacman.main(new String[0]);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "팩맨 실행 오류: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        tetrisBtn.addActionListener(e -> {
            try {
                Tetris.main(new String[0]);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "테트리스 실행 오류: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        slotBtn.addActionListener(e -> {
            JFrame slotFrame = new JFrame("🎰 슬롯머신");
            slotFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            slotFrame.setSize(400, 300);
            slotFrame.setLocationRelativeTo(null);
            slotFrame.setContentPane(new SlotMachine());
            slotFrame.setVisible(true);
        });

        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(omokBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(puzzleBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(pacmanBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(tetrisBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(slotBtn);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color bgColor) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}