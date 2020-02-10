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
    private int dx = 0;
    
    static GreenfootSound music  = new GreenfootSound("overworld.mp3");
    private boolean playing = false;
        
    private GreenfootImage background = null;
    private GreenfootImage treesBg = null;
    
    private Actor mainActor = null;
    
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
        mainActor = slippy;
        addObject(slippy, 200, 430);
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
        getBackground().drawImage(background, (dx / 50), 0);
        getBackground().drawImage(treesBg, (dx / 2), 220);
        getBackground().drawImage(treesBg, (dx - 2020) / 2, 220);
        getBackground().drawImage(treesBg, (dx + 2000) / 2, 220);
        dx += direction;
   
    }
    
    // Scroll all actors around Slippy
    private void scrollObjects() {
        List<Actor> objects = getObjects(Actor.class);
        int dx = 0;
        int dy = 0;
        
        dx = mainActor.getX() - 480;
        
        if (dx < 0) {
            mainActor.setLocation(480, mainActor.getY());
        }
        else if (dx > 0) {
            mainActor.setLocation(480, mainActor.getY());
        }
       
        
       for (Actor object : objects) {
            if (!object.equals(mainActor)) {
                object.setLocation(object.getX() - dx, object.getY());
            }   
        }
    }
    
    
    public void act() {
        if (Greenfoot.isKeyDown("right")) {
            scrollBackground(-1);
        }
        if (Greenfoot.isKeyDown("left")) {
            scrollBackground(1);
        }
        
        scrollObjects();
    }
    
    private void drawGroundChunk (int xStart, int yStart, boolean surface) {
        
        for (int k = yStart; k < yStart + 800; k+= 30) { 
           for (int i = xStart; i < xStart + 600; i+=25) {
               if (surface && k == yStart) {
                   addObject(new Ground(1), i, k);
                }
                else {
                    addObject(new Ground(0), i, k);
                }
            }
        }
        
    }
    
    private void buildWorld() {
     
    
        drawGroundChunk(120, 475, true);
        drawGroundChunk(720, 475, true);
        
        drawGroundChunk(400, 300, false);
        
       
        
       // for (int i = 475; i > 0; i-=25)
       // addObject(new Ground(2), 90, i);
        
    }
}
