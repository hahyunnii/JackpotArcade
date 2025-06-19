
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

        // JACKPOT ARCADE 제목 (비버튼 형태)
        JLabel title = new JLabel("JACKPOT ARCADE", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                Color bgColor = new Color(255, 173, 51);
                Color top = bgColor.brighter();
                Color bottom = bgColor.darker();
                GradientPaint gp = new GradientPaint(0, 0, top, 0, height, bottom);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, width, height, 30, 30);

                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, width - 1, height - 1, 30, 30);

                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2.setColor(getForeground());
                g2.drawString(getText(), (width - textWidth) / 2, (height + textHeight) / 2 - 4);

                g2.dispose();
            }
        };
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(350, 70));
        title.setOpaque(false);
        title.setFocusable(false);
        title.setBorder(BorderFactory.createEmptyBorder());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(9, 14, 34));
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

        omokBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        puzzleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pacmanBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        tetrisBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        slotBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(9, 14, 34));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(buttonPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                Color top = bgColor.brighter();
                Color bottom = bgColor.darker();
                GradientPaint gp = new GradientPaint(0, 0, top, 0, height, bottom);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, width, height, 30, 30);

                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, width - 1, height - 1, 30, 30);

                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2.setColor(getForeground());
                g2.drawString(getText(), (width - textWidth) / 2, (height + textHeight) / 2 - 4);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) { }

            @Override
            public void updateUI() {
                super.updateUI();
                setContentAreaFilled(false);
                setFocusPainted(false);
                setOpaque(false);
            }
        };

        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(300, 50));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
