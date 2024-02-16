import Tiles.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Player extends JPanel{
    int mapX;
    int mapY;
    int screenX;
    int screenY;
    private final int speed = 4;
    private final int width;
    private final int height;

    private final Image walkingLeft1;
    private final Image walkingLeft2;
    private Image walkingRight1;
    private Image walkingRight2;
    private Image walkingUp1;
    private Image walkingUp2;
    private Image walkingDown1;
    private Image walkingDown2;

    private Image currentImage;

    private int scale;

    private int imgChoice;
    private int imgCount;
    int screenWidth;
    int screenHeight;
    int mapWidth;
    int mapHeight;
    TileManager tileManager;



    public Player(int tileSize, int screenWidth, int screenHeight, TileManager tileManager, int mapWidth, int mapHeight, int startX, int startY){
        this.width = tileSize;
        this.height = tileSize;
        this.mapX = startX;
        this.mapY = startY;
        try {
            walkingLeft1 = ImageIO.read(getClass().getResourceAsStream("/boy_left_1.png"));
            walkingLeft2 = ImageIO.read(getClass().getResourceAsStream("/boy_left_2.png"));
            walkingRight1 = ImageIO.read(getClass().getResourceAsStream("/boy_right_1.png"));
            walkingRight2 = ImageIO.read(getClass().getResourceAsStream("/boy_right_2.png"));
            walkingUp1 = ImageIO.read(getClass().getResourceAsStream("/boy_up_1.png"));
            walkingUp2 = ImageIO.read(getClass().getResourceAsStream("/boy_up_2.png"));
            walkingDown1 = ImageIO.read(getClass().getResourceAsStream("/boy_down_1.png"));
            walkingDown2 = ImageIO.read(getClass().getResourceAsStream("/boy_down_2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.currentImage = walkingLeft1;

        this.imgChoice = -1;
        this.imgCount = 1;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.tileManager = tileManager;
        this.screenX = this.screenWidth/2 - (this.width/2);
        this.screenY = this.screenHeight/2 - (this.width/2);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void draw(Graphics2D g){
        g.drawImage(this.currentImage, this.screenX, this.screenY, this.width, this.height, null);
    }


    private void adjust_image(Image img1, Image img2){
        if(this.imgChoice == -1){
            this.currentImage = img1;
        }else{
            this.currentImage = img2;
        }
        this.imgCount += 1;
        if(this.imgCount > 12){
            this.imgChoice = this.imgChoice * -1;
            this.imgCount = 1;
        }
    }

    public void update(Keys key){
        switch (key) {
            case Keys.UP_KEY:
                this.moveUp();
                break;
            case Keys.DOWN_KEY:
                this.moveDown();
                break;
            case Keys.LEFT_KEY:
                this.moveLeft();
                break;
            case Keys.RIGHT_KEY:
                this.moveRight();
                break;
        }
    }


    public void moveLeft() {
        this.adjust_image(this.walkingLeft1, this.walkingLeft2);
        if((this.mapX-(this.screenWidth/2 + this.width/2) > 0) && this.tileManager.isWalkable(mapX- speed, mapY)){
            this.mapX -= speed;
            this.tileManager.moveMapLeft();
        }
    }

    public void moveRight() {
        this.adjust_image(this.walkingRight1, this.walkingRight2);
        if((this.mapX+(this.screenWidth/2 + this.width/2) < this.mapWidth) && this.tileManager.isWalkable(mapX + speed, mapY)){
            this.mapX += speed;
            this.tileManager.moveMapRight();
        }
   }

    public void moveUp() {
        this.adjust_image(this.walkingUp1, this.walkingUp2);
        if(this.mapY - (this.screenHeight/2 +this.height/2) > 0 && this.tileManager.isWalkable(mapX, mapY- speed)){
            this.mapY -= speed;
            this.tileManager.moveMapUp();
        }
    }

    public void moveDown() {
        this.adjust_image(this.walkingDown1, this.walkingDown2);
        if(this.mapY + (this.screenHeight/2 + this.height/2) < this.mapHeight && this.tileManager.isWalkable(mapX, mapY + speed)){
            this.mapY += speed;
            this.tileManager.moveMapDown();
        }
    }

    public void printPlayerPosition(){
        System.out.println("x: " + this.mapX + ", y: " + this.mapY);
    }


}
