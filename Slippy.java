import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slippy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slippy extends Character
{
    private GreenfootImage slippyDown = new GreenfootImage("slippyDown.png");
    private boolean isSliding = false;
    private boolean facingForwards = true;
    
    private static final int SCROLL_WIDTH = 160;
    private static final float JUMP = 8.0f, WALK = 4.0f;

    private int absoluteScroll, initialXPosition, initialYPosition;
    
    /**
     * Act - do whatever the Slippy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Slippy(){
        super("slippy", 8, 11);
        absoluteScroll = 0;
    }
    
    
    public void act() 
    {
        GreenfootImage image = getImage();
        image.scale(75, 75);
        super.act();
        if(Greenfoot.isKeyDown("down")){
            this.disableAnimation();
            isSliding = true;
            setImage(slippyDown);
        } else {
            this.enableAnimation();
            isSliding = false;
            if(!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")){
                setIdle();
            } else {
                setMoving();
            }
        }
        updateOrientation();
        
    }
    
    public void updateOrientation(){
        if((Greenfoot.isKeyDown("left") && facingForwards) 
            || (Greenfoot.isKeyDown("right") && !facingForwards)){
            reverseOrientation();
        }
    }
    
    public void reverseOrientation(){
        facingForwards = !facingForwards;
        mirrorAll();
        slippyDown.mirrorHorizontally();
    }
}
