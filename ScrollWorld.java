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
    private int dx = 0;
    
    static GreenfootSound music  = new GreenfootSound("overworld.mp3");
    private boolean playing = false;
        
    private GreenfootImage background = null;
    private GreenfootImage treesBg = null;
    private GreenfootImage caveBg = null;
    Message m = new Message(1);
    private Slippy mainActor = null;
    private Blippy blippy;
    
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
            music.setVolume(80);
        }
        
        buildWorld();
        
        Slippy slippy = new Slippy();
        blippy = new Blippy();
        mainActor = slippy;
        addObject(slippy, 480, 430);
        addObject(blippy, 530, 430);
        addObject(m, 480, 570);
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
        getBackground().drawImage(treesBg, (dx / 2), 250);
        getBackground().drawImage(treesBg, (dx - 2020) / 2, 250);
        getBackground().drawImage(treesBg, (dx + 2000) / 2, 250);
        
    
    
        for (int i = 0; i < 2000; i += 65) {
            for (int k = 0; k < 400; k += 96) {
                getBackground().drawImage(caveBg, (-1*scrolledX + i), k);
            }
        }
        
        dx += direction;
    }
    
    // Scroll all actors around Slippy
    private void scrollObjects() {
       List<Actor> objects = getObjects(Actor.class);
       int dx = 0;
       int dy = 0;
        
       dx = mainActor.getX() - 480;
       scrolledX += dx;
       if (scrolledX <= 0 ) {
           scrolledX = 0;
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
                   if (!object.equals(mainActor)) {
                       object.setLocation(object.getX() - dx, object.getY());
                   }
               }
            }
        }
    }
    
    
    public void act() {
        if(blippy != null){
            mainActor.freeze();
            if(blippy.isAtEdge()){
                removeObject(blippy);
                blippy = null;
                removeObject(m);
                m = null;
                mainActor.unfreeze();
                PartsCollectedMessage partsMessage = new PartsCollectedMessage();
                this.addObject(partsMessage, 730, 20);
            }
        }
        scrollObjects();
    }
    
    private void drawGroundChunk (int xStart, int yStart, boolean surface) {
        
        for (int k = yStart; k < yStart + 400; k+= 30) { 
           for (int i = xStart; i < xStart + 600; i+=30) {
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
        
        for (int k = yStart; k > yStart - 400; k -= 29) { 
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
    
    private void buildWorld() {
     
        drawGroundChunk(-480, 475, true);
        drawGroundChunk(120, 475, true);
        drawGroundChunk(720, 475, true);
        drawGroundChunk(1320, 475, true);
        drawWall(-15, 0);
        drawWall(-15, 900);
        drawCeilingChunk(0, 200, true);
        drawCeilingChunk(600, 300, true);
    }
}