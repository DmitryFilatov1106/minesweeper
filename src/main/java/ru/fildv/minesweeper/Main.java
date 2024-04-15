package ru.fildv.minesweeper;

import ru.fildv.minesweeper.controller.Game;
import ru.fildv.minesweeper.service.Box;
import ru.fildv.minesweeper.service.Coord;
import ru.fildv.minesweeper.util.Ranges;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

// Добавить установку
public final class Main extends JFrame {
    private final Game game;
    private JPanel panel;
    private JLabel label;
    private final int total = 9;
    private final int cols = total;
    private final int rows = total;
    private final int bombs = 10;
    private final int imageSize = 50;

    public static void main(final String[] args) {
        new Main();
    }

    private Main() {
        game = new Game(cols, rows, bombs);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper on Java");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).getImage(),
                            coord.x() * imageSize,
                            coord.y() * imageSize, this);
                }

            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                int x = e.getX() / imageSize;
                int y = e.getY() / imageSize;
                Coord coord = new Coord(x, y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> game.pressLeft(coord);
                    case MouseEvent.BUTTON2 -> game.start();
                    case MouseEvent.BUTTON3 -> game.pressRight(coord);
                    default -> game.start();
                }
                label.setText(getMassage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(
                new Dimension(
                        Ranges.getSize().x() * imageSize,
                        Ranges.getSize().y() * imageSize
                )
        );
        add(panel);
    }

    private String getMassage() {
        switch (game.getGameState()) {
            case WIN -> {
                return "You win!";
            }
            case LOSE -> {
                return "You lose!";
            }
            case PLAY -> {
                return "The game continues";
            }
            default -> {
                return "";
            }
        }
    }

    private Image getImage(final String name) {
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource(fileName)));
        return icon.getImage();
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.setImage(getImage(box.name()));
        }
    }
}
