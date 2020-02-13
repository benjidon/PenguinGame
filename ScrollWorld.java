import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class ScrollWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollWorld extends World
{
    private int cameraOffsetX, cameraOffsetY;
    private int worldX, worldY, worldWidth, worldHeight;
    private int scrolledX = 0;
    private int scrolledY = 0;
    private int dx = 0;
    
    static GreenfootSound music  = new GreenfootSound("overworld.mp3");
    private boolean playing = false;
        
    private GreenfootImage background = null;
    private GreenfootImage treesBg = null;
    private GreenfootImage caveBg = null;
    private Message m = new Message(1);
    private Actor mainActor = null;
    private Blippy blippy;
    private PartsCollectedMessage partsCollected = null;
    
    private boolean gameOver = false;
    
    /**
     * Constructor for objects of class ScrollWorld.
     * 
     */
    public ScrollWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 600, 1, false);
        worldWidth = getWidth();
        worldHeight = getHeight();
        
        // Initialize Background Images
        GreenfootImage snowBg = new GreenfootImage("Background_101.png");
        treesBg = new GreenfootImage("Background_38.png");
        caveBg = new GreenfootImage("Background_165.png");
        setScrollingBackground(snowBg);
        
        // Start Music Loop
        if(!music.isPlaying() && playing == false)
        {
            music.playLoop();
            playing = true;
            music.setVolume(0);
        }
        
        buildWorld();
        
        Slippy slippy = new Slippy();
        blippy = new Blippy();
        mainActor = slippy;
        addObject(slippy, 480, 430);
        addObject(blippy, 530, 430);
        addObject(m, 480, 570);
        addObject(new Part(0), 2500, 480);
        addObject(new Part(1), 6000, 420);
        addObject(new Part(2), 6000, -260);
        addObject(new Part(3), 3000, -500);
        addObject(new Snowman(-1), 3680, 430);
        addObject(new Wolf(), 3500, -450);
        
    }
    
    private void setScrollingBackground(GreenfootImage image) {
        background = new GreenfootImage(image);
        setBackground(background);
        scrollBackground(1);
    }
    
    public int getCameraX(){
        return cameraOffsetX;
    }
    
    public int getCameraY()
    {
        return cameraOffsetY;
    }
    
    public void setCameraX(int x)
    {
        cameraOffsetX = x;
    }
    
    public void setCameraY(int y)
    {
        cameraOffsetY = y;
    }
    
    public int getWorldX(){
        return worldX;
    }
    
    public int getWorldY(){
        return worldY;
    }
    
    public int getWorldWidth(){
        return worldWidth;
    }
    
    public int getWorldHeight(){
        return worldHeight;
    }
    
    public PartsCollectedMessage getPartsCollected(){
        return partsCollected;
    }
    
    public void setWorldDimensions(int x, int y, int width, int height)
    {
        worldX = x;
        worldY = y;
        worldWidth = width;
        worldHeight = height;
    }
    
    public void scrollBackground(int direction) {
        setBackground("starrynight.png");
        
        if (scrolledX <= 0) {
            dx = 0;
        }
        
        getBackground().drawImage(background, (dx / 50), 0);
        getBackground().drawImage(treesBg, (dx / 2), 280);
        getBackground().drawImage(treesBg, (dx - 2020) / 2, 280);
        getBackground().drawImage(treesBg, (dx + 2000) / 2, 280);
    
        //for (int i = 0; i < 1000; i += 65) {
        //   for (int k = 0; k < 400; k += 96) {
        //        getBackground().drawImage(caveBg, (-1*scrolledX + i), (-1*scrolledY + k));
        //    }
        //}
        GreenfootImage cave = caveBg;
        //cave.scale(2000, 600);
        
        //getBackground().drawImage(cave, (-1*scrolledX + 0), (-1*scrolledY + 0));

        //getBackground().drawImage(caveBg, (-1*scrolledX + 1200), (-1*scrolledY + 480));
        //getBackground().drawImage(caveBg, (-1*scrolledX + 1200), (-1*scrolledY + 550));
        //getBackground().drawImage(caveBg, (-1*scrolledX + 1300), (-1*scrolledY + 480));
        //getBackground().drawImage(caveBg, (-1*scrolledX + 1300), (-1*scrolledY + 550));
        

        dx += direction;
    }
    
    // Scroll all actors around Slippy
    private void scrollObjects() {
       List<Actor> objects = getObjects(Actor.class);
       int dx = 0;
       int dy = 0;
        
       dx = mainActor.getX() - 480;
       dy = mainActor.getY() - 300;
       
       scrolledX += dx;
       scrolledY += dy;
       
       if (scrolledX <= 0 ) {
           scrolledX = 0;
       }
       if (scrolledY > 0) {
           scrolledY = 0;
       }

       if (scrolledX > 0) {
           if (dx < 0) {
               mainActor.setLocation(480, mainActor.getY());
               scrollBackground(1);
            }
           else if (dx > 0) {
               mainActor.setLocation(480, mainActor.getY());
               scrollBackground(-1);
            }
        }
      
       
       if (scrolledX > 0) {
           for (Actor object : objects) {
               if(object != null){
                   if (!object.equals(mainActor) && !(object instanceof PartsCollectedMessage)) {
                       object.setLocation(object.getX() - dx, object.getY());
                   }
                  
               }
           }
        }
        
        if (scrolledY < 0) {
           
           if (dy < 0) {
               mainActor.setLocation(mainActor.getX(), 300);
               scrollBackground(0);
           }
           else if (dy > 0) {
               mainActor.setLocation(mainActor.getX(), 300);
               scrollBackground(0);
            }
            
           for (Actor object : objects) {
              if(object != null && !(object instanceof PartsCollectedMessage) &&!gameOver){
                   if (!object.equals(mainActor) ) {        
                       object.setLocation(object.getX(), object.getY() - dy);
                   }
                   if (scrolledY > -5 && scrolledY < 0) {
                       object.setLocation(object.getX(), object.getY() - 1);
                   }
                   
               }
           }
        }
        
     
    }
    
    
    public void act() {
        if(blippy != null){
            Slippy s = (Slippy)mainActor;
            s.freeze(new GreenfootImage("slippyAngry.png"));
            if(blippy.isAtEdge()){
                removeObject(blippy);
                blippy = null;
                removeObject(m);
                m = null;
                s.unfreeze();
                partsCollected = new PartsCollectedMessage();
                this.addObject(partsCollected, 730, 20);
            }
        }
        scrollObjects();
        moveWorld();
        if(gameOver){
            Slippy s = (Slippy)mainActor;
            s.freeze(new GreenfootImage("flight_slippy1.png"));
            if(!s.isAtEdge()){
                s.setLocation(s.getX(), s.getY() - 5);
            } else {
                SplashScreen splash = new SplashScreen();
                addObject(splash, 470, 300);
            }
        }

    }
    
    private void drawGroundChunk (int xStart, int yStart, int length,
    int height,
    boolean surface) {
        
        for (int k = yStart; k < yStart + height; k+= 30) { 
           for (int i = xStart; i < xStart + length; i+=30) {
               if (surface && k == yStart) {
                   addObject(new Ground(3), i, k);
                }
                else {
                    addObject(new Ground(4), i, k);
                }
            }
        }
        
    }
    
    private void drawStairs (int xStart, int yStart, int length, boolean surface) {
        
        for (int i = xStart; i < xStart + length; i+= 30) { 
           yStart -= 10;
           for (int k = yStart; k < yStart + 300; k+=30) {
                if (surface && k == yStart) {
                   addObject(new Ground(3), i, k);
                }
                else {
                    addObject(new Ground(4), i, k);
                }
            }
        }
        
    }
    
    private void drawCeilingChunk (int xStart, int yStart, boolean surface) {
        
        for (int k = yStart; k > yStart - 100; k -= 29) { 
           for (int i = xStart; i < xStart + 600; i+=30) {
               if (surface && k == yStart) {
                   Ground ceiling = new Ground(3);
                   ceiling.setRotation(180);
                   addObject(ceiling, i, k);
                }
                else {
                    addObject(new Ground(4), i, k);
                }
            }
        }
        
    }
    
    
    private void drawWall(int xStart, int yStart) {
        for (int k = yStart; k < yStart + 900; k+= 30) {
            addObject(new Ground(4), xStart, k);     
        }
    }
    
    private void moveWorld() {
            List<Actor> objects = getObjects(Actor.class);
            if (Greenfoot.isKeyDown("5")) {
                for (Actor object : objects) {
                    if(object != null){
                   if (!object.equals(mainActor)) {
                       object.setLocation(object.getX() - 10, object.getY() - 20);
                   }
                  
               }
            }
            }
    }
    
    private void buildWorld() {
        drawWall(-15, 0);
        drawWall(-15, 300);
        
        drawGroundChunk(0, 475, 500, 200, true);
        drawGroundChunk(300, 475, 500, 200, true);
        drawGroundChunk(720, 475, 500, 200,  true);
        drawGroundChunk(1400, 475, 500, 200, true);
        drawStairs(1820, 475, 380, true);
        drawGroundChunk(2170, 530, 500, 200, true);
        drawGroundChunk(2670, 310, 300, 380, true);
        
        // Area with ice ice and first enemy after jump glide part
        drawGroundChunk(2965, 475, 750, 200, true);
        
        // Landing after ice, goes out ot penguin room
        drawGroundChunk(3940, 475, 2900, 200, true);
        
        // Big mountain
        int ystart = 420;
        int currLength = 150;
        for (int i = 4240; i < 5600; i += 30) {
            for (int k = ystart; k < 410; k += 30) {
                if (i > 4330) {
                if (k == ystart) {
                   addObject(new Ground(3), i, k);
                }
                else {
                    addObject(new Ground(4), i, k);
                }
            }
                
            }
            currLength += 150;
            ystart -= 10;
        }
        for (int i = 4360; i < 5500; i += 30) {
            addObject(new Ground(4), i, 405);
        }
        
        // Three platforms
        drawGroundChunk(4350, -170, 700, 100, true);
        drawGroundChunk(3650, -290, 700, 230, true);
        drawGroundChunk(2950, -410, 700, 360, true);
       
        
        // Final area (underdeveloped)
        drawGroundChunk(5608, -200, 500, 450, true);
        drawGroundChunk(6108, -200, 480, 1000, true);

        
       
        //drawCeilingChunk(0, 100, true);
        //drawCeilingChunk(600, 100, true);
        //drawCeilingChunk(1200, 100, true);
        // drawCeilingChunk(1800, 100, true);
        int start = 480;
       
        
    }
    
    public Actor getMainActor(){
        return mainActor;
    }
    
    public void setMainActor(Actor newActor){
        mainActor = newActor;
    }
    
    public void setGameOver(){
        gameOver = true;
    }
}