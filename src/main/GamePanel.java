package main;

import Entity.Player;
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
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    public Player player = new Player(this,keyH);


    //FPS
    int FPS=60;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }


}
