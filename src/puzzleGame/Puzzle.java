package puzzleGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.*;
public class Puzzle implements ActionListener{
    JFrame f;
    int getsu = 4;
    JButton [][] btn = new JButton [getsu][getsu];

    int [][] answer = new int[getsu][getsu];

    JLabel[] imglb = new JLabel[8];

    JButton firstClick = null;
    int firstRow=0, firstCol=0;

    public Puzzle() {
        f = new JFrame("게임");
        for(int i = 0; i<4;i++) {
            for(int j = 0;j<4;j++) {
                btn[i][j] = new JButton();

            }
        }

    }//end of Game

    public void addLayout() {

        f.setLayout(new GridLayout(getsu, getsu));
        for(int i = 0;i<getsu;i++) {
            for (int j = 0 ;j<getsu;j++) {
                f.add(btn[i][j]);



                //문자를 '0'
                answer[i][j] = '0';
                btn[i][j].addActionListener(this);
            }
        }
        f.setSize(700, 700);
        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //  퍼즐 게임 창만 닫음

    }//end of addLayout

    public void initChar() {
        int alpha =0;

        DASI:
        for(int i=0;i<getsu*getsu;) {
            //임의의 알파벳을 만들기
            if(i%2==0) {
                alpha = (int)(Math.random()*8);

                // 기존의 알파벳과 동일한걸 발견하면
                for(int r = 0;r<getsu;r++) {
                    for (int c = 0 ;c<getsu;c++) {
                        if( answer[r][c] == alpha) continue DASI;
                    }

                }// end of 기존 알파벳

            }

            // 임의의 위치에 지정
            boolean ok = false;
            do {
                int row = (int)(Math.random()*getsu);
                int col = (int)(Math.random()*getsu);
                if(answer[row][col] == '0') {
                    answer[row][col]=alpha;
                    i++;
                    ok = true;
                }
            }while(!ok);
        }
    }

//    public void showAnswer() {
//        // 문자를 버튼에 지정하기
//        for(int i=0 ; i<getsu;i++) {
//            for(int j=0;j<getsu;j++) {
//                btn[i][j].setIcon(new ImageIcon(getClass().getResource("/puzzleGame/img/b" + answer[i][j] + ".png")));
//
//
//            }
//        }
//
//        //버튼에서 문자를 1초후에 지우기
//        try {
//            Thread.sleep(1000);
//        }catch(Exception ex) {}
//
//
//        for(int i=0 ; i<getsu;i++) {
//            for(int j=0;j<getsu;j++) {
//                btn[i][j].setIcon(null);
//            }
//
//        }//end of 1초후에 지우기
//
//    }//end of showAnswer

    public void showAnswer() {
        // 문자를 버튼에 이미지로 지정
        for (int i = 0; i < getsu; i++) {
            for (int j = 0; j < getsu; j++) {
                btn[i][j].setIcon(new ImageIcon(getClass().getResource("/puzzleGame/img/b" + answer[i][j] + ".png")));
            }
        }

        // 1초 후에 이미지 지우기 (Timer 사용)
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < getsu; i++) {
                    for (int j = 0; j < getsu; j++) {
                        btn[i][j].setIcon(null);
                    }
                }
            }
        }) {{
            setRepeats(false); // 한 번만 실행되도록
            start();
        }};
    }


//    public void actionPerformed(ActionEvent e) {
//
//        JButton b = (JButton)e.getSource();
//
//        for(int i=0 ; i<getsu;i++) {
//            for(int j=0;j<getsu;j++) {
//                if(b == btn[i][j]) {
//
//
//                    if(firstClick == null) { //첫번째 선택시
//                        firstClick = b;
//                        firstRow = i;
//                        firstCol = j;
//                        firstClick.setIcon(new ImageIcon(getClass().getResource("/puzzleGame/img/b" + answer[firstRow][firstCol] + ".png")));
//
//                    }else { //두번째 선택시
//                        if(answer[i][j]==answer[firstRow][firstCol]) {
//                            firstClick.setBackground(Color.gray);
//                            b.setIcon(new ImageIcon(getClass().getResource("/puzzleGame/img/b" + answer[firstRow][firstCol] + ".png")));
//
//                        }else {
//                            firstClick.setIcon(null);
//
//                        }
//                        if(i== firstRow && j == firstCol) {
//                            continue;
//                        }
//
//
//                        firstClick= null;
//
//
//
//
//                    }
//
//                }
//
//            }
//        }
//
//
//
//
//    }//end of actionPerformed

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        for (int i = 0; i < getsu; i++) {
            for (int j = 0; j < getsu; j++) {
                if (b == btn[i][j]) {
                    if (firstClick == null) {
                        // 첫 번째 클릭
                        firstClick = b;
                        firstRow = i;
                        firstCol = j;
                        firstClick.setIcon(new ImageIcon(getClass().getResource("/puzzleGame/img/b" + answer[firstRow][firstCol] + ".png")));
                    } else {
                        // 같은 버튼 다시 누르면 무시
                        if (firstClick == b) return;

                        // 두 번째 클릭
                        b.setIcon(new ImageIcon(getClass().getResource("/puzzleGame/img/b" + answer[i][j] + ".png")));

                        // 맞을 경우
                        if (answer[i][j] == answer[firstRow][firstCol]) {
                            firstClick.setBackground(Color.GRAY);
                            b.setBackground(Color.GRAY);
                            firstClick = null;
                        } else {
                            // 다른 경우: 클릭된 두 버튼을 final로 복사
                            JButton first = firstClick;
                            JButton second = b;

                            Timer timer = new Timer(800, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    first.setIcon(null);
                                    second.setIcon(null);
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();

                            firstClick = null;
                        }
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



    }//end of main




}
