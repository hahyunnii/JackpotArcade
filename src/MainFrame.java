import slotMachineGame.SlotMachineGame;
import pacmanGame.PacmanGame; // ✅ PacmanGame 패키지 임포트 추가

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("🎮 Jackpot Arcade");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 레이아웃
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 제목
        JLabel title = new JLabel("🎰 잭팟 아케이드", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        mainPanel.add(title, BorderLayout.NORTH);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // 게임 버튼들
        JButton omokBtn = new JButton("오목 게임");
        JButton puzzleBtn = new JButton("그림 맞추기");
        JButton pacmanBtn = new JButton("팩맨");
        JButton tetrisBtn = new JButton("테트리스");
        JButton slotBtn = new JButton("슬롯머신");

        // 오목 버튼
        omokBtn.addActionListener((ActionEvent e) -> {
            new Omok("오목 게임");
        });

        // 그림 맞추기 버튼
        puzzleBtn.addActionListener(e -> {
            puzzleGame.PuzzleGame g = new puzzleGame.PuzzleGame();
            g.addLayout();
            g.initChar();
            g.showAnswer();
        });

        // 팩맨 버튼: 실행 연결
        pacmanBtn.addActionListener(e -> {
            // PacmanGame이 JFrame을 직접 실행하는 구조이므로 main 호출
            try {
                PacmanGame.main(new String[0]);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "팩맨 실행 오류: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        // 테트리스 준비 중
        tetrisBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "테트리스 게임은 아직 준비 중입니다.");
        });

        // 슬롯머신 버튼: 새 창으로 띄우기
        slotBtn.addActionListener(e -> {
            JFrame slotFrame = new JFrame("🎰 슬롯머신");
            slotFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            slotFrame.setSize(400, 300);
            slotFrame.setLocationRelativeTo(null);
            slotFrame.setContentPane(new SlotMachineGame()); // JPanel 기반 클래스여야 함
            slotFrame.setVisible(true);
        });

        // 버튼 패널에 추가
        buttonPanel.add(omokBtn);
        buttonPanel.add(puzzleBtn);
        buttonPanel.add(pacmanBtn);
        buttonPanel.add(tetrisBtn);
        buttonPanel.add(slotBtn);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // 하단 제작 정보
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
