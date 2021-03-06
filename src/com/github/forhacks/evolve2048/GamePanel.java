package com.github.forhacks.evolve2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GamePanel extends JPanel {

    Game game;

    GamePanel(boolean allowKeypresses) {

        setPreferredSize(new Dimension(500, 500));

        game = new Game();

        if (allowKeypresses) {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ESCAPE:
                            initGame();
                            break;
                        case KeyEvent.VK_UP:
                            move(0);
                            repaint();
                            break;
                        case KeyEvent.VK_LEFT:
                            move(1);
                            repaint();
                            break;
                        case KeyEvent.VK_DOWN:
                            move(2);
                            repaint();
                            break;
                        case KeyEvent.VK_RIGHT:
                            move(3);
                            repaint();
                            break;
                    }
                }
            });
        }

        initGame();

    }

    void move(int direction) {
        game.move(direction);
        repaint();
    }

    void initGame() {

        game.initGame();
        game.repaint();

    }

    @Override
    public void paint(Graphics g2) {

        super.paint(g2);

        Graphics2D g = (Graphics2D) g2;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        g.setColor(new Color(0xbbada0));
        g.fillRect(0, 0, 500, 500);

        int[][] grid = game.grid;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                g.setFont(g.getFont().deriveFont(grid[i][j] < 100 ? 36f : grid[j][i] < 1000 ? 32f : 24f).deriveFont(Font.BOLD));

                FontMetrics fm = getFontMetrics(g.getFont());

                g.setColor(getBackground(grid[i][j]));
                g.fillRoundRect(20 + 120 * j, 20 + 120 * i, 100, 100, 3, 3);


                if (grid[i][j] != 0) {
                    String s = Integer.toString(grid[i][j]);
                    g.setColor(Color.DARK_GRAY);
                    g.drawString(s, 70 + 120 * j - fm.stringWidth(s) / 2, 120 * (i + 1) - (100 + fm.getLineMetrics(s, g).getBaselineOffsets()[2]) / 2 - 2);
                }

            }
        }

    }

    private Color getBackground(int value) {
        switch (value) {
            case 0:    return new Color(0xcdc1b4);
            case 2:    return new Color(0xeee4da);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xedcc61);
            case 512:  return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
            default:   return new Color(0x3E86A0);
        }
    }

}
