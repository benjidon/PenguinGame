import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FlightSlippy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlightSlippy extends Character
{
    private boolean facingForwards = false;
    private float xVelocity = 0;
    private float maxXVelocity = 5;
    private float initYVelocity = 0;
    private float yVelocity = 0;
    private float jumpSpeed = 0.5f;
    private double timeStep = 10;
    private boolean onGround = true;
    private boolean holding = false;
    
    public FlightSlippy(){
        super("flight_slippy", 4, 0);
    }
    
    public void act() 
    {
        super.act();
        moveVertically();
        moveHorizontally();
        updateOrientation();
    }
    
    public void updateOrientation(){
        if((Greenfoot.isKeyDown("left") && facingForwards) 
            || (Greenfoot.isKeyDown("right") && !facingForwards)){
            reverseOrientation();
        }
        if(Greenfoot.isKeyDown("right")){
            setRotation(10);
        } else if(Greenfoot.isKeyDown("left")){
            setRotation(-10);
        } else {
            setRotation(0);
        }
    }
    
    public void reverseOrientation(){
        facingForwards = !facingForwards;
        mirrorAll();
    }
    
    private void moveHorizontally() {
        
        Actor left = getOneObjectAtOffset(-13, 0, Actor.class);
        Actor right = getOneObjectAtOffset(13, 0, Actor.class);
        
        if (Greenfoot.isKeyDown("right"))
        {
            if (xVelocity < 0)
            {
                xVelocity = 0;
            }
            if (xVelocity < maxXVelocity)
            {
                xVelocity += 1;
            }
            if (right != null) {
                xVelocity = 0;
            }
            
        }
        
        if (Greenfoot.isKeyDown("left"))
        {
            if (xVelocity > 0)
            {
                xVelocity = 0;
            }
            if (xVelocity > -maxXVelocity)
            {
                xVelocity -= 1;
            }
            if (left != null) {
                xVelocity = 0;
            }
            
        }
       
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            xVelocity = 0;
        }

        setLocation(getX() + (int)xVelocity, getY());
    }
    
    private void moveVertically() {
        
        Actor below = getOneObjectAtOffset(-5, 
        (getImage().getHeight()/2 - 2) + (int)yVelocity, Actor.class);
        Actor above = getOneObjectAtOffset(-5, 
        -30 , Actor.class);
        
        if(Greenfoot.isKeyDown("up") && above == null){
            setLocation(getX(), getY() - 5);
        } else if(Greenfoot.isKeyDown("down")){
            setLocation(getX(), getY() + 5);
        } else {
            setLocation(getX(), getY() + 1);
        }
       
        
        
    }
}
