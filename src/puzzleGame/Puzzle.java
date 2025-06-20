package puzzleGame;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class Puzzle implements ActionListener {
    JFrame f;
    int getsu = 4;
    JButton[][] btn = new JButton[getsu][getsu];
    int[][] answer = new int[getsu][getsu];

    JButton firstClick = null;
    int firstRow = 0, firstCol = 0;

    public Puzzle() {
        f = new JFrame("그림 맞추기");
        for (int i = 0; i < getsu; i++) {
            for (int j = 0; j < getsu; j++) {
                btn[i][j] = new JButton();
            }
        }
    }

    public void addLayout() {
        f.setLayout(new GridLayout(getsu, getsu));
        for (int i = 0; i < getsu; i++) {
            for (int j = 0; j < getsu; j++) {
                f.add(btn[i][j]);
                answer[i][j] = '0';
                btn[i][j].addActionListener(this);
            }
        }
        f.setSize(700, 700);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initChar() {
        int alpha = 0;
        DASI:
        for (int i = 0; i < getsu * getsu; ) {
            if (i % 2 == 0) {
                alpha = (int) (Math.random() * 8);
                for (int r = 0; r < getsu; r++) {
                    for (int c = 0; c < getsu; c++) {
                        if (answer[r][c] == alpha) continue DASI;
                    }
                }
            }

            boolean ok = false;
            do {
                int row = (int) (Math.random() * getsu);
                int col = (int) (Math.random() * getsu);
                if (answer[row][col] == '0') {
                    answer[row][col] = alpha;
                    i++;
                    ok = true;
                }
            } while (!ok);
        }
    }

//    public void showAnswer() {
//        for (int i = 0; i < getsu; i++) {
//            for (int j = 0; j < getsu; j++) {
//                btn[i][j].setIcon(new ImageIcon("src/puzzleGame/img/b" + answer[i][j] + ".png"));
//            }
//        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {}
//
//        for (int i = 0; i < getsu; i++) {
//            for (int j = 0; j < getsu; j++) {
//                btn[i][j].setIcon(null);
//            }
//        }
//    }

    public void showAnswer() {
        // 1. 버튼에 이미지 설정
        for (int i = 0; i < getsu; i++) {
            for (int j = 0; j < getsu; j++) {
                btn[i][j].setIcon(new ImageIcon("src/puzzleGame/img/b" + answer[i][j] + ".png"));
            }
        }

        // 2. 화면 갱신 후 1초 뒤에 이미지 제거
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < getsu; i++) {
                    for (int j = 0; j < getsu; j++) {
                        btn[i][j].setIcon(null);
                    }
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }


    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        for (int i = 0; i < getsu; i++) {
            for (int j = 0; j < getsu; j++) {
                if (b == btn[i][j]) {
                    if (firstClick == null) {
                        firstClick = b;
                        firstRow = i;
                        firstCol = j;
                        firstClick.setIcon(new ImageIcon("src/puzzleGame/img/b" + answer[firstRow][firstCol] + ".png"));
                    } else {
                        if (i == firstRow && j == firstCol) return; // 같은 버튼 무시

                        b.setIcon(new ImageIcon("src/puzzleGame/img/b" + answer[i][j] + ".png"));

                        if (answer[i][j] == answer[firstRow][firstCol]) {
                            firstClick.setBackground(Color.GRAY);
                            b.setBackground(Color.GRAY);
                        } else {
                            try {
                                Thread.sleep(800);
                            } catch (Exception ex) {}

                            firstClick.setIcon(null);
                            b.setIcon(null);
                        }
                        firstClick = null;
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Puzzle g = new Puzzle();
        g.addLayout();
        g.initChar();
        g.showAnswer();
    }
}
