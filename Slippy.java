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
    
    /**
     * Act - do whatever the Slippy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Slippy(){
        super("slippy", 8, 11); 
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
        if(Greenfoot.isKeyDown("left") && facingForwards){
            facingForwards = false;
            mirrorAll();
        } else if(Greenfoot.isKeyDown("right") && !facingForwards){
            facingForwards = true;
            mirrorAll();
        }
    }
}
