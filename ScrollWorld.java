
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
    private HealthBar healthBar = null;
    
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
        GreenfootImage caveBackground = new GreenfootImage(3000, 900);
        for (int y = 0; y < 400; y += 95) {
                for (int x = 0; x < 1000; x += 96) {
                    GreenfootImage draw = new GreenfootImage("Background_165.png");
                    caveBackground.drawImage(draw, x, y);
                }
        }
        caveBg = caveBackground;
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
        addObject(new Part(2), 7750, -370);
        addObject(new Part(3), 2000, -500);
        addObject(new Snowman(-1), 3680, 430);
        addObject(new Wolf(), 3100, -390);
        addObject(new Ted(), 6000, -285);
;
        
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
    
    public HealthBar getHealthBar(){
        return healthBar;
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
        //getBackground().drawImage(caveBg, dx, 90 - (scrolledY / 2));
        
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
                   if (!object.equals(mainActor) && !(object instanceof PartsCollectedMessage) &&!(object instanceof HealthBar)) {
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
              if(object != null && !(object instanceof PartsCollectedMessage) &&!gameOver &&!(object instanceof HealthBar)){
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
                healthBar = new HealthBar();
                this.addObject(healthBar, 130, 30);
            }
        }
        scrollObjects();
        moveWorld();
        if(gameOver){
            Slippy s = (Slippy)mainActor;
            s.freeze(new GreenfootImage("flight_slippy1.png"));
            partsCollected.advance();
            Greenfoot.setWorld(new GameWonWorld());
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
        
        // Start
        addObject(new Ground(1, 1500, 200), 600, 560);
        addObject(new Ground(1, 500, 200), 1790, 560);
        
        // Stairs Jump
        drawStairs(1825, 475, 410, true);
        addObject(new Ground(1, 400, 400), 2920, 460);
        addObject(new Ground(1, 550, 200), 2470, 615);
        addObject(new Ground(1, 750, 200), 3490, 560);
        
        // Big mountain
        addObject(new Ground(1, 2000, 200), 5130, 560);
        addObject(new Ground(1, 1300, 50), 5000, 400);
        addObject(new Ground(1, 900, 600), 5200, 100);
        addObject(new Ground(1, 900, 500), 6100, 0);
        addObject(new Ground(1, 500, 950), 6380, 130);
        
        // Next part of big mountain
        addObject(new Ground(1, 500, 950), 7600, 130);
        
        
        // Platforms
        addObject(new Ground(1, 150, 30), 4300, 240);
        addObject(new Ground(1, 150, 30), 4500, 85);
        addObject(new Ground(1, 150, 30), 4300, -20);
        addObject(new Ground(1, 150, 30), 4600, -120);
        addObject(new Ground(1, 150, 30), 4380, -300);
        
        // Sky platforms
        addObject(new Ground(1, 750, 100), 3600, -220);
        addObject(new Ground(1, 750, 190), 2870, -265);
        addObject(new Ground(1, 750, 280), 2140, -310);
       
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