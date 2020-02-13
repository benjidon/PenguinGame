import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class ScrollWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWonWorld extends World
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
    public GameWonWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 600, 1, false);
        worldWidth = getWidth();
        worldHeight = getHeight();
        
        // Initialize Background Images
        treesBg = new GreenfootImage("Background_38.png");
        caveBg = new GreenfootImage("Background_165.png");
        
        // Start Music Loop
        if(!music.isPlaying() && playing == false)
        {
            music.playLoop();
            playing = true;
            music.setVolume(0);
        }
               
        FlightSlippy slippy = new FlightSlippy();
        mainActor = slippy;
        addObject(slippy, 480, 430);
        
        PartsCollectedMessage message = new PartsCollectedMessage();
        message.setPartsCollected(5);
        addObject(message, 610, 200);
        
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
        dx += direction;
    }
    
    // Scroll all actors around Slippy
   
    
    
    public void act() {
        
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