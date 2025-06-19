import omokGame.Omok;
import puzzleGame.Puzzle;
import slotMachineGame.SlotMachine;
import pacmanGame.Pacman;
import tetrisGame.Tetris; // ✅ Gist 기반 테트리스 클래스 import 추가

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("🎮 Jackpot Arcade");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("🎰 잭팟 아케이드", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton omokBtn = new JButton("오목 게임");
        JButton puzzleBtn = new JButton("그림 맞추기");
        JButton pacmanBtn = new JButton("팩맨");
        JButton tetrisBtn = new JButton("테트리스");
        JButton slotBtn = new JButton("슬롯머신");

        omokBtn.addActionListener((ActionEvent e) -> {
            new Omok("오목 게임");
        });

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

        // ✅ 테트리스 버튼 실행: tetrisGame.Tetris.main() 호출
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

        buttonPanel.add(omokBtn);
        buttonPanel.add(puzzleBtn);
        buttonPanel.add(pacmanBtn);
        buttonPanel.add(tetrisBtn);
        buttonPanel.add(slotBtn);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JLabel footer = new JLabel("ⓒ 이하현 2025", SwingConstants.CENTER);
        footer.setFont(new Font("굴림", Font.PLAIN, 12));
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
