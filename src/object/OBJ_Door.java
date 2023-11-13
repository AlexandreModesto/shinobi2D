package object;

import main.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        name="Door";
        try {
            image= ImageIO.read(getClass().getResource("/res/objects/door.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}