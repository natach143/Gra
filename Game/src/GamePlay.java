import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePlay extends JPanel implements ActionListener, KeyListener, MouseListener {
    public final int width = 800;
    public final int height = 800;
    private Timer timer;
    public int flow;
    public int positionY;
    private final int delay = 20;
    public Random rnd = new Random();
    ArrayList<Rectangle> columns = new ArrayList<Rectangle>();
    public boolean gameOver,started;
    public Rectangle ball = new Rectangle(width / 2 , height / 2 , 25, 25);
    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void jump()
    {
        if (gameOver)
        {
            ball = new Rectangle(width / 2, height / 2 , 25, 25);
            columns.clear();
            positionY = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }

        if (!started)
        {
            started = true;
        }
        else {
            if (positionY > 0)
            {
                positionY = 0;
            }

            positionY -= 10;
        }
    }
    public void addColumn(boolean start) {
        int space = 300;
        int w = 100;
        int h = 50 + rnd.nextInt(300);

        if (start) {
            columns.add(new Rectangle(width + w + columns.size() * 300, height - h - 120, w, h));
            columns.add(new Rectangle(width + w + (columns.size() - 1) * 300, 0, w, height - h - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, height - h - 120, w, h));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, w, height - h - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.yellow.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void paint(Graphics g) {

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.GREEN.darker());
        g.fillRect(0, height - 150, width, 150);

        g.setColor(Color.RED);
        g.fillOval(ball.x, ball.y, ball.width, ball.height);
        for (Rectangle column : columns) {
            paintColumn(g, column);
        }
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 50));

        if (gameOver)
        {
            g.drawString("Game Over! " , width/2-150, height / 2);
        }

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jump();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        int speed = 10;
        flow++;
        if(started) {
            for (Rectangle column : columns) {
                column.x -= speed;
            }

            if (flow % 2 == 0 && positionY < 15) {
                positionY += 2;
            }
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }
            ball.y += positionY;
            for (Rectangle column : columns) {

                if (column.intersects(ball)) {
                    gameOver = true;

                    if (ball.x <= column.x) {
                        ball.x = column.x - ball.width;

                    } else {
                        if (column.y != 0) {
                            ball.y = column.y - ball.height;
                        } else if (ball.y < column.height) {
                            ball.y = column.height;
                        }
                    }
                }
            }

            if (ball.y > height - 120 || ball.y < 0) {
                gameOver = true;
            }

            if (ball.y + positionY >= height - 120) {
                ball.y = height - 120 - ball.height;
                gameOver = true;
            }
        }

        repaint();
    }
}

