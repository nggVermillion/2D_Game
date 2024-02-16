package Tiles;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {


    int tileSize;
    int tileAmount;
    int screenWidth;
    int screenHeight;
    Tile[][] map;
    int mapSize = 48;
    int x0;
    int y0;

    public TileManager(int tileSize, int tileAmount, int screenWidth, int screenHeight, int x0, int y0){
        this.tileSize = tileSize;
        this.tileAmount = tileAmount;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.map = new Tile[this.mapSize][this.mapSize];
        this.x0 = x0;
        this.y0 = y0;
        this.loadMap();
    }


    public void draw(Graphics2D g2, int mapX, int mapY){
        int x0 = (mapX - screenWidth/2)/tileSize; int x1 = (mapX + screenWidth/2)/tileSize;
        if(x0 > 0){x0--;}
        if(x1 < mapSize){x1++;}
        int y0 = (mapY - screenHeight/2)/tileSize; int y1 = (mapY + screenHeight/2)/tileSize;
        if(y0 > 0){y0--;}
        if(y1 < mapSize){y1++;}
        for(int i = x0; i< x1; i++){
            for(int j = y0; j<y1; j++){
                this.map[j][i].draw(g2);
            }
        }
    }

    private void createTiles(int row, int col, Character currentChar){
        String tileType;
        if(currentChar=='0'){
            tileType = "grass";
        }else if(currentChar == '1'){
            tileType = "wall";
        }else if(currentChar == '2'){
            tileType = "water";
        }else if(currentChar == '3'){
            tileType = "earth";
        }else if(currentChar == '4'){
            tileType = "tree";
        }else{
            tileType = "sand";
        }
        this.map[row][col] = new Tile(this.tileSize, tileType, (col-x0)*tileSize, (row-y0)*tileSize);
    }


    private void loadMap(){
        try{
            InputStream inputStream = getClass().getResourceAsStream("/Tiles/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String strCurrentLine;
            int row = 0;
            int col = 0;
            while((strCurrentLine = br.readLine()) != null){
                for(int i = 0; i<strCurrentLine.length(); i++) {
                    Character currentChar = strCurrentLine.charAt(i);
                    if(currentChar!=' '){
                        this.createTiles(row, col, currentChar);
                        col++;
                    }
                }
                row++;
                col = 0;
            }

        }catch(Exception e){

        }
    }

    public void moveMapLeft(){
        for(int i = 0; i<this.mapSize; i++){
            for(int j = 0; j<this.mapSize; j++){
                this.map[j][i].x += 4;
            }
        }
    }

    public void moveMapRight(){
        for(int i = 0; i<this.mapSize; i++){
            for(int j = 0; j<this.mapSize; j++){
                this.map[j][i].x -= 4;
            }
        }
    }

    public void moveMapUp(){
        for(int i = 0; i<this.mapSize; i++){
            for(int j = 0; j<this.mapSize; j++){
                this.map[j][i].y += 4;
            }
        }
    }

    public void moveMapDown(){
        for(int i = 0; i<this.mapSize; i++){
            for(int j = 0; j<this.mapSize; j++){
                this.map[j][i].y -= 4;
            }
        }
    }

    public boolean isWalkable(int x, int y){
        x = x/tileSize;
        y = y/tileSize;
        String name = map[y][x].tileName;
        return Objects.equals(name, "grass") | Objects.equals(name, "sand") | Objects.equals(name, "earth");
    }
}
