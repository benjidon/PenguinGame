import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Slippy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slippy extends Character
{
    private GreenfootImage slippyDown = new GreenfootImage("slippyDown.png");
    private GreenfootImage slippyUp = new GreenfootImage("slippyUp.png");
    private boolean isSliding = false;
    private boolean facingForwards = true;
    
    private static final int SCROLL_WIDTH = 160;
    private static final float JUMP = 8.0f, WALK = 4.0f;

    private int absoluteScroll, initialXPosition, initialYPosition;
    // Vars to control movement
    private float xVelocity = 0;
    private float maxXVelocity = 5;
    private float initYVelocity = 0;
    private float yVelocity = 0;
    private float jumpSpeed = 6;
    private double timeStep = 0.5;
    private boolean onGround = true;
    private boolean holding = false;
    
    static GreenfootSound jump  = new GreenfootSound("jump.wav");

    
    /**
     * Act - do whatever the Slippy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Slippy(){
        super("slippy", 8, 11);
        absoluteScroll = 0;
        jump.setVolume(87);
    }
    
    
    public void act() 
    {
        GreenfootImage image = getImage();
        image.scale(75, 75);
        setImage(image);
        
        
        super.act();
        updateAnimation();
        moveVertically();
        moveHorizontally();
        updateOrientation();
        
    }
    
    private void updateAnimation(){
        if(Greenfoot.isKeyDown("down")){
            this.disableAnimation();
            isSliding = true;
            setImage(slippyDown);
        } else if(!onGround){
            this.disableAnimation();
            setImage(slippyUp);
        } else {
            this.enableAnimation();
            isSliding = false;
            if(!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")){
                setIdle();
            } else {
                setMoving();
            }
        }
    }
    
    private void moveHorizontally() {
        
        Actor left = getOneObjectAtOffset(-13, 0, Actor.class);
        Actor right = getOneObjectAtOffset(13, 0, Actor.class);

        if (isSliding) {
            maxXVelocity = 7;
        } else {
            maxXVelocity = 5;
        }
        
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
            if (xVelocity > maxXVelocity && !isSliding) {
                xVelocity = maxXVelocity;
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
            if (xVelocity > -maxXVelocity && !isSliding) {
                xVelocity = -maxXVelocity;
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
       
        if (below != null) {
            onGround = true;
            yVelocity = 0;
            initYVelocity = 0;
            timeStep = 0;
            setLocation(getX(), below.getY() - 50);
        } else {
            onGround = false;
        }

        if (above != null  && yVelocity < 0) {
            System.out.println("HIT");
            yVelocity = Math.abs(yVelocity) + 3;
            initYVelocity = yVelocity;
           setLocation(getX(), above.getY() + 40);
            return;
        }
        
        if (Greenfoot.isKeyDown("up") && onGround == true  && !holding) {
            yVelocity -= jumpSpeed;
            initYVelocity = -jumpSpeed;
            onGround = false;
            holding = true;
            
            jump.play();
        }
        
        if (!Greenfoot.isKeyDown("up")) {
            holding = false;
        }
       
        
        if (holding && timeStep < 7 && !onGround) {
            yVelocity -= 5;
        }
                
        if (onGround == false) {
           
            setLocation(getX(), (int)(getY() + yVelocity));
            
            if (isSliding)  {   
                yVelocity = (int)2;
                initYVelocity = 0;
                timeStep = 0;    
            }
            else if (yVelocity < 12 && !isSliding) {
                yVelocity = (int)(initYVelocity) + (int)(.5*Math.pow(timeStep,1.1));
            }
            timeStep += .4;
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
        slippyUp.mirrorHorizontally();
    }
}
