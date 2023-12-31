package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;

    }
    public void setObject(){
        gp.obj[0]=new OBJ_Key(gp);
        gp.obj[0].worldX=23*gp.tileSize;
        gp.obj[0].worldY=7*gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX=23*gp.tileSize;
        gp.obj[1].worldY=6*gp.tileSize;

        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX=28*gp.tileSize;
        gp.obj[2].worldY=5*gp.tileSize;

        gp.obj[3] = new OBJ_Chest(gp);
        gp.obj[3].worldX=2*gp.tileSize;
        gp.obj[3].worldY=16*gp.tileSize;

        gp.obj[4] = new OBJ_Boots(gp);
        gp.obj[4].worldX=33*gp.tileSize;
        gp.obj[4].worldY=5*gp.tileSize;

        gp.obj[5] = new OBJ_Chest(gp);
        gp.obj[5].worldX=46*gp.tileSize;
        gp.obj[5].worldY=16*gp.tileSize;
    }
}
