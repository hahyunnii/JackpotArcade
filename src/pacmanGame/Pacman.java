package pacmanGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Pacman extends JFrame {
    public static void main(String[] args) {
        final JFrame frame = new JFrame();

        final ImageIcon successIcon = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/successIcon.png"));
        final ImageIcon smallDot     = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/smallDot.png"));
        final ImageIcon bigDot       = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/bigDot.png"));
        final ImageIcon wall         = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/wall.png"));
        final ImageIcon enemy        = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/enemy.png"));
        final ImageIcon pacman       = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/pacman.png"));
        final ImageIcon empty        = new ImageIcon(Pacman.class.getResource("/pacmanGame/img/empty.png"));


        final JButton button = new JButton(successIcon);
        final CardLayout card = new CardLayout();
        final JDialog dialog = new JDialog();

        random = new Random();

        dialog.setSize(690,650);
        dialog.setVisible(false);

        pacmanH=12;  pacmanW=7;  enemyH=7;  enemyW=7;  numOfDot=79;  start=2;  temp=empty;


        final JLabel[][] f = new JLabel[14][14];

        for (int i=0; i<14; i++) {
            for(int j=0; j<14; j++) {
                f[i][j] = new JLabel();
            }
        }

        class Blistener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        }


        class TListener implements ActionListener {   //timerŬ���� ActionListener
            public void actionPerformed(ActionEvent event)
            {
                if(start<=0) where = 1+random.nextInt(4);
                else { where = 1; start--; }
                switch(where) {
                    case 1:
                        if(!(f[enemyH-1][enemyW].getIcon()).equals(wall)) {
                            temp1=f[enemyH-1][enemyW].getIcon();
                            f[enemyH-1][enemyW].setIcon(enemy);
                            f[enemyH][enemyW].setIcon(temp);
                            temp=temp1;
                            enemyH--;
                        }
                        break;
                    case 2:
                        if(!(f[enemyH+1][enemyW].getIcon()).equals(wall)) {
                            temp2=f[enemyH+1][enemyW].getIcon();
                            f[enemyH+1][enemyW].setIcon(enemy);
                            f[enemyH][enemyW].setIcon(temp);
                            temp=temp2;
                            enemyH++;
                        }
                        break;
                    case 3:
                        if(!(f[enemyH][enemyW-1].getIcon()).equals(wall)) {
                            temp3=f[enemyH][enemyW-1].getIcon();
                            f[enemyH][enemyW-1].setIcon(enemy);
                            f[enemyH][enemyW].setIcon(temp);
                            temp=temp3;
                            enemyW--;
                        }
                        break;
                    case 4:
                        if(!(f[enemyH][enemyW+1].getIcon()).equals(wall)) {
                            temp4=f[enemyH][enemyW+1].getIcon();
                            f[enemyH][enemyW+1].setIcon(enemy);
                            f[enemyH][enemyW].setIcon(temp);
                            temp=temp4;
                            enemyW++;
                        }
                        break;
                }
                if(enemyH==pacmanH && enemyW==pacmanW) {
                    f[enemyH][enemyW].setIcon(enemy);
                    dialog.add(button);
                    dialog.setVisible(true);
                }

                if(enemyH==5 && enemyW==7) { f[6][7].setIcon(wall); }
                System.out.println(where);
            }
        }
        class KListener extends KeyAdapter{  //Ű�����Է¿� ���� KeyListener
            public void keyPressed(KeyEvent e) {
                if(numOfDot<=0) {
                    dialog.add(button);
                    dialog.setVisible(true);
                }
                int key = e.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_UP:
                        if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot) || (f[pacmanH-1][pacmanW].getIcon()).equals(empty)) {
                            if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman)) numOfDot--;
                            f[pacmanH-1][pacmanW].setIcon(pacman);
                            f[pacmanH][pacmanW].setIcon(empty);
                            pacmanH--;
                        }
                        if((f[pacmanH-1][pacmanW].getIcon()).equals(enemy)) {
                            f[enemyH][enemyW].setIcon(enemy);
                            dialog.add(button);
                            dialog.setVisible(true);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot) || (f[pacmanH+1][pacmanW].getIcon()).equals(empty)) {
                            if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman)) numOfDot--;
                            f[pacmanH+1][pacmanW].setIcon(pacman);
                            f[pacmanH][pacmanW].setIcon(empty);
                            pacmanH++;
                        }
                        if((f[pacmanH+1][pacmanW].getIcon()).equals(enemy)){
                            f[enemyH][enemyW].setIcon(enemy);
                            dialog.add(button);
                            dialog.setVisible(true);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot) || (f[pacmanH][pacmanW-1].getIcon()).equals(empty)) {
                            if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman)) numOfDot--;
                            f[pacmanH][pacmanW-1].setIcon(pacman);
                            f[pacmanH][pacmanW].setIcon(empty);
                            pacmanW--;
                        }
                        if((f[pacmanH][pacmanW-1].getIcon()).equals(enemy)){
                            f[enemyH][enemyW].setIcon(enemy);
                            dialog.add(button);
                            dialog.setVisible(true);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot) || (f[pacmanH][pacmanW+1].getIcon()).equals(empty)) {
                            if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman)) numOfDot--;
                            f[pacmanH][pacmanW+1].setIcon(pacman);
                            f[pacmanH][pacmanW].setIcon(empty);
                            pacmanW++;
                        }
                        if((f[pacmanH][pacmanW+1].getIcon()).equals(enemy)) {
                            f[enemyH][enemyW].setIcon(enemy);
                            dialog.add(button);
                            dialog.setVisible(true);
                        }
                        break;
                }
                System.out.println(numOfDot);
            }
        }
        KListener listener = new KListener();
        TListener tListener = new TListener();

        button.addActionListener(new Blistener());

        Timer t = new Timer(500, tListener);
        t.start();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14,14));
        frame.requestFocus();
        frame.addKeyListener(new KListener());


        for(int i=0; i<14; i++) {
            for(int j=0; j<14; j++) {
                f[i][j].setIcon(wall);
                f[i][j].addKeyListener(listener);
                panel.add(f[i][j]);
            }
        }
        f[1][1].setIcon(smallDot);	f[2][1].setIcon(smallDot);	f[3][1].setIcon(smallDot);	f[4][1].setIcon(smallDot);	f[5][1].setIcon(smallDot);
        f[5][2].setIcon(smallDot);	f[5][3].setIcon(smallDot);	f[1][3].setIcon(smallDot);	f[2][3].setIcon(smallDot);	f[3][3].setIcon(smallDot);
        f[4][3].setIcon(smallDot);	f[1][4].setIcon(smallDot);	f[1][5].setIcon(smallDot);	f[1][6].setIcon(smallDot);	f[1][7].setIcon(smallDot);
        f[1][8].setIcon(smallDot);	f[1][9].setIcon(smallDot);	f[1][10].setIcon(smallDot);	f[1][11].setIcon(smallDot);	f[1][12].setIcon(smallDot);
        f[2][9].setIcon(smallDot);	f[2][12].setIcon(smallDot);	f[3][12].setIcon(smallDot);	f[4][12].setIcon(smallDot);	f[5][12].setIcon(smallDot);
        f[3][4].setIcon(smallDot);	f[3][5].setIcon(smallDot);	f[3][10].setIcon(smallDot);	f[3][11].setIcon(smallDot);
        f[4][5].setIcon(smallDot);	f[4][6].setIcon(smallDot);	f[4][7].setIcon(smallDot);	f[4][8].setIcon(smallDot);	f[4][9].setIcon(smallDot);
        f[4][10].setIcon(smallDot);	f[2][7].setIcon(smallDot);	f[3][7].setIcon(smallDot);	f[4][10].setIcon(smallDot);	f[4][11].setIcon(smallDot);
        f[5][11].setIcon(smallDot);	f[6][11].setIcon(smallDot);	f[7][11].setIcon(smallDot);	f[7][12].setIcon(smallDot);	f[8][12].setIcon(smallDot);
        f[9][12].setIcon(smallDot);	f[10][12].setIcon(smallDot);f[11][12].setIcon(smallDot);	f[5][7].setIcon(empty);	f[6][2].setIcon(smallDot);
        f[7][1].setIcon(smallDot);	f[9][11].setIcon(smallDot);	f[11][1].setIcon(smallDot);	f[11][3].setIcon(smallDot);	f[11][11].setIcon(smallDot);
        f[7][2].setIcon(smallDot);	f[7][3].setIcon(smallDot);	f[8][1].setIcon(smallDot);	f[9][1].setIcon(smallDot);	f[9][2].setIcon(smallDot);
        f[9][3].setIcon(smallDot);	f[9][4].setIcon(smallDot);	f[9][5].setIcon(smallDot);	f[9][6].setIcon(smallDot);	f[12][1].setIcon(smallDot);
        f[12][2].setIcon(smallDot);	f[12][3].setIcon(smallDot);	f[12][4].setIcon(smallDot);	f[12][5].setIcon(smallDot);	f[12][9].setIcon(smallDot);
        f[12][10].setIcon(smallDot);f[12][11].setIcon(smallDot); f[10][5].setIcon(smallDot); f[11][5].setIcon(smallDot); f[10][6].setIcon(smallDot);
        f[10][7].setIcon(smallDot);	f[10][8].setIcon(smallDot); f[10][9].setIcon(smallDot); f[11][7].setIcon(smallDot); f[12][7].setIcon(pacman);
        f[9][8].setIcon(smallDot); f[9][9].setIcon(smallDot); f[11][9].setIcon(smallDot); f[6][5].setIcon(empty);f[6][6].setIcon(empty);
        f[6][7].setIcon(empty); f[6][8].setIcon(empty);f[6][9].setIcon(empty); f[7][5].setIcon(empty);f[7][6].setIcon(empty);
        f[7][7].setIcon(enemy); f[7][8].setIcon(empty);f[7][9].setIcon(empty);

        frame.add(panel);
        frame.setTitle("Pacman");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private static Random random;
    private static int pacmanH, pacmanW, enemyH, enemyW, numOfDot, where, start;
    private static int fieldMin=0, fieldMax=13;
    private static Icon temp1, temp2, temp3, temp4, temp;

    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 650;
}