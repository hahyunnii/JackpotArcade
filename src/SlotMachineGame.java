import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SlotMachineGame extends JPanel {

    private final String[] symbols = {"🍒", "🍋", "🔔", "7️⃣", "⭐", "🍎"};
    private final JLabel[] slots = new JLabel[3];
    private final Random random = new Random();
    private final JLabel resultLabel = new JLabel("버튼을 눌러 돌려보세요!", SwingConstants.CENTER);

    public SlotMachineGame() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 144, 255)); // 파란 배경

        // 슬롯 영역
        JPanel slotPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        slotPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        slotPanel.setBackground(new Color(255, 215, 0)); // 노란 슬롯 배경

        for (int i = 0; i < 3; i++) {
            JLabel label = new JLabel("❔", SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 60));
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            slots[i] = label;
            slotPanel.add(label);
        }

        // 버튼
        JButton spinButton = new JButton("🎲 슬롯 돌리기!");
        spinButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        spinButton.setBackground(new Color(255, 105, 180));
        spinButton.setForeground(Color.WHITE);
        spinButton.setFocusPainted(false);
        spinButton.setPreferredSize(new Dimension(0, 50));
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinSlots();
            }
        });

        // 결과 표시
        resultLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 조립
        add(resultLabel, BorderLayout.NORTH);
        add(slotPanel, BorderLayout.CENTER);
        add(spinButton, BorderLayout.SOUTH);
    }

    private void spinSlots() {
        for (int i = 0; i < 3; i++) {
            String symbol = symbols[random.nextInt(symbols.length)];
            slots[i].setText(symbol);
        }
        checkResult();
    }

    private void checkResult() {
        String s1 = slots[0].getText();
        String s2 = slots[1].getText();
        String s3 = slots[2].getText();

        if (s1.equals(s2) && s2.equals(s3)) {
            resultLabel.setText("🎉 잭팟! 3개 일치!");
        } else if (s1.equals(s2) || s2.equals(s3) || s1.equals(s3)) {
            resultLabel.setText("😊 두 개 일치! 아깝네요~");
        } else {
            resultLabel.setText("😢 다시 도전해보세요!");
        }
    }
}
