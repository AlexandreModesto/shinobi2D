package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.stream.Stream;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public  TileManager(GamePanel gp){
        this.gp=gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("src/res/maps/map01");
    }
    public void getTileImage(){
        try{

            tile[0] =new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("src/res/bg/grama.png"));

            tile[1] =new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("src/res/bg/parede.png"));

            tile[2] =new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("src/res/bg/agua.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col< gp.maxScreenCol && row<gp.maxScreenRow){
                String line = br.readLine();

                while (col <gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();


        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int col=0;
        int row=0;
        int x=0;
        int y=0;

        while(col <gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x+=gp.tileSize;

            if(col ==gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }
        }
    }
}