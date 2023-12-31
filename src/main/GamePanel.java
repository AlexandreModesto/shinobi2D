package main;

import Entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16;//16x16 tile
    final int scale =3;

    public final int tileSize = originalTileSize * scale;//48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow=12;
    public final int screenWidth = tileSize * maxScreenCol;//768px
    public final int screenHeight = tileSize* maxScreenRow;//576px

    // World settings
    public final int maxWorldCol=50;
    public final int maxWorldRow = 50;


    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public  AssetSetter aSetter=new AssetSetter(this);
    public UI ui= new UI(this);
    Thread gameThread;


    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject obj[]=new SuperObject[10];


    //FPS
    int FPS=60;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta=0;
        long  lastTime=System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;

            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        long drawStart =0;
        if(keyH.checkDrawTime == true){
            drawStart=System.nanoTime();
        }


        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i=0;i<obj.length;i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd=System.nanoTime();
            long passed=drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: "+passed,10,400);
            System.out.println("Draw Time: "+passed);
        }


        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

}
