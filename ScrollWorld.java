import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        
    private GreenfootImage background = null;
    private GreenfootImage treesBg = null;
    
    
    /**
     * Constructor for objects of class ScrollWorld.
     * 
     */
    public ScrollWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 600, 1);
        worldWidth = getWidth();
        worldHeight = getHeight();
        
        GreenfootImage snowBg = new GreenfootImage("Background_101.png");
        treesBg = new GreenfootImage("Background_38.png");
        
        setScrollingBackground(snowBg);
        //Background forest = new Background();
        //addObject(forest, 300, 300);
        
        
        Slippy slippy = new Slippy();
        addObject(slippy, 480, 430);
    }
    
    private void setScrollingBackground(GreenfootImage image) {
        background = new GreenfootImage(image);
        //background.scale(1500, 600);
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
        setBackground("white.jpg");
        getBackground().drawImage(background, (dx / 20), 0);
        getBackground().drawImage(treesBg, (dx / 2), 250);
        getBackground().drawImage(treesBg, (dx + 2000) / 2, 250);
        dx += direction;
   
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("right")) {
            scrollBackground(-1);
        }
        if (Greenfoot.isKeyDown("left")) {
            scrollBackground(1);
        }
    }
}
