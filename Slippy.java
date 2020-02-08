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
    
    // Vars to control movement
    private float xVelocity = 0;
    private float initYVelocity = 0;
    private float yVelocity = 0;
    private float jumpSpeed = 10;
    private double timeStep = 0.5;
    private boolean onGround = true;
    private boolean holding = false;
    
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
        moveHorizontally();
        moveVertically();
        updateOrientation();
        
    }
    
    private void moveHorizontally() {
        setLocation(getX() + (int)xVelocity, getY());
        
        if (Greenfoot.isKeyDown("right"))
        {
            if (xVelocity < 0)
            {
                xVelocity = 0;
            }
            if (xVelocity < 5)
            {
                xVelocity += 1;
            }
        }
        
        if (Greenfoot.isKeyDown("left"))
        {
            if (xVelocity > 0)
            {
                xVelocity = 0;
            }
            if (xVelocity > -5)
            {
                xVelocity -= 1;
            }
        }
        
        if (Greenfoot.isKeyDown("left"))
        {
            if (xVelocity > 0)
            {
                xVelocity = 0;
            }
            if (xVelocity < -5)
            {
                xVelocity -= 1;
            }
        }
        
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            xVelocity = 0;
        }
        
    }
    
    private void moveVertically() {
        
        if (Greenfoot.isKeyDown("up") && onGround == true  && !holding) {
            yVelocity -= jumpSpeed;
            onGround = false;
            holding = true;
        }
        
        if (!Greenfoot.isKeyDown("up")) {
            holding = false;
        }
        
        if (holding && timeStep < 15 && !onGround) {
            yVelocity -= 14;
        }
                
        if (onGround == false) {
            if ((int)(getY() + yVelocity) > 430) {
                setLocation(getX(), 430);
            } else {
                setLocation(getX(), (int)(getY() + yVelocity));
            }
            
            if (yVelocity < 18) {
                yVelocity = -jumpSpeed + (int)(1.8*(timeStep / 2));
            }
            System.out.println(yVelocity);
            timeStep += 1;
        }
        if (getY() >= 430) {
            onGround = true;
            timeStep = 0;
        }
        
        
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
