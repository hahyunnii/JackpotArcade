import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SlotMachineGame extends JPanel {
    private JLabel[] slots = new JLabel[3];
    private String[] symbols = {"🍒", "🔔", "🍋", "⭐", "7️⃣"};
    private Random rand = new Random();
    private JLabel resultLabel;

    public SlotMachineGame() {
        setLayout(new BorderLayout());

        // 슬롯 영역
        JPanel slotPanel = new JPanel();
        slotPanel.setLayout(new GridLayout(1, 3, 10, 10));
        for (int i = 0; i < 3; i++) {
            slots[i] = new JLabel("❔", SwingConstants.CENTER);
            slots[i].setFont(new Font("SansSerif", Font.BOLD, 50));
            slotPanel.add(slots[i]);
        }

        // 버튼 영역
        JButton spinButton = new JButton("돌리기 🎲");
        spinButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        spinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spinSlots();
            }
        });

        // 결과 메시지
        resultLabel = new JLabel("버튼을 눌러 잭팟을 돌려보세요!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        add(slotPanel, BorderLayout.CENTER);
        add(spinButton, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.NORTH);
    }

    private void spinSlots() {
        for (int i = 0; i < 3; i++) {
            String symbol = symbols[rand.nextInt(symbols.length)];
            slots[i].setText(symbol);
        }
        checkResult();
    }

    private void checkResult() {
        String s1 = slots[0].getText();
        String s2 = slots[1].getText();
        String s3 = slots[2].getText();

        if (s1.equals(s2) && s2.equals(s3)) {
            resultLabel.setText("🎉 잭팟! 3개 모두 일치!");
        } else if (s1.equals(s2) || s2.equals(s3) || s1.equals(s3)) {
            resultLabel.setText("😊 두 개 일치! 아쉽네요~");
        } else {
            resultLabel.setText("😢 아쉽습니다. 다시 도전하세요!");
        }
    }
}
