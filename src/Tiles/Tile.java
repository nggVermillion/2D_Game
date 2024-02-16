package Tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Tile extends JPanel {
    private Image tileType;
    private int tileSize;

    public int x;
    public int y;
    public String tileName;
    public Tile(int tileSize, String tileType, int x, int y){
        try {
            if(tileType == "wall"){
                this.tileType = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            }else if (tileType == "water") {
                this.tileType = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
            }else if(tileType == "grass"){
                this.tileType = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));
            }else if(tileType == "sand"){
                this.tileType = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));
            }else if(tileType == "earth"){
                this.tileType = ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));
            }else{
                this.tileType = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.tileSize = tileSize;
        this.x = x;
        this.y = y;
        this.tileName = tileType;
    }

    public void draw(Graphics2D g) {
        super.paintComponent(g);
        g.drawImage(this.tileType, x, y, this.tileSize, this.tileSize, null);
    }

    public void position(){
        System.out.println("y: " + this.y + ", x: " + this.x);
    }

}
