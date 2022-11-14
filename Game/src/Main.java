import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePlay gameplay= new GamePlay();
        frame.add(gameplay);

        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setVisible(true );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameplay.addColumn(true);
        gameplay.addColumn(true);
        gameplay.addColumn(true);
        gameplay.addColumn(true);

    }
}