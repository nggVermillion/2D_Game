import Tiles.Tile;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int tileAmount = maxScreenCol * maxScreenRow;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;

    int mapSize = 48;
    int mapWidth = mapSize * tileSize;
    int mapHeight = mapSize * tileSize;

    int playerStartX = 480;
    int playerStartY = 386;


    Thread gameThread;

    Player player;

    Listener keyListener;

    TileManager tileManager;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.keyListener = new Listener();
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        int x0 = (playerStartX - screenWidth/2)/tileSize;
        int y0 = (playerStartY - screenHeight/2)/tileSize;
        this.tileManager = new TileManager(this.tileSize, this.tileAmount, this.screenWidth, this.screenHeight, x0, y0);
        this.player = new Player(tileSize, this.screenWidth, this.screenHeight, this.tileManager, this.mapWidth, this.mapHeight, playerStartX, playerStartY);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        this.tileManager.draw(g2, this.player.mapX, this.player.mapY);

        player.draw(g2);

        g2.dispose();
    }





    public void startGame(){
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>= 1){
                this.player.update(keyListener.getCurrentKey());
                //this.player.printPlayerPosition();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
}
