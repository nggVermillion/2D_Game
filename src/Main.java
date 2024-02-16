import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        // make the JFrame adjust to the panel size? -> aka mache screen visible
        window.pack();

        //window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGame();
    }
}