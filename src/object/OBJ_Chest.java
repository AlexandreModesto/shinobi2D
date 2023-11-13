package object;

import main.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        name="Chest";
        try {
            image= ImageIO.read(getClass().getResource("/res/objects/chest.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}