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
    private GreenfootImage slippyHappy = new GreenfootImage("slippyHappy.png");
    private boolean isSliding = false;
    private boolean facingForwards = true;
    
    private static final int SCROLL_WIDTH = 160;
    private static final float JUMP = 8.0f, WALK = 4.0f;
    
    // Actors directly around Slippy
    private Actor right_low;
    private Actor right_high;
    private Actor left_low;
    private Actor left_high;
    private Actor above;
    private Actor below;

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
    private boolean frozen = false;
    
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
        if(frozen){
            moveVertically();
            return;
        }
        updateAnimation();
        moveVertically();
        moveHorizontally();
        updateOrientation();
        // Kill him if he falls out of the world
        if(getY() >= getWorld().getHeight() - 1){
            kill();
        }
        
    }
    
    private void updateAnimation(){
        if(Greenfoot.isKeyDown("down") || (isSliding && above != null)){
            this.disableAnimation();
            isSliding = true;
            setImage(slippyDown);
        }/* else if(!onGround){
            this.disableAnimation();
            setImage(slippyUp);
            isSliding = false;
        } */
        else {
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
        
        left_low = getOneObjectAtOffset(-13, 30, Actor.class);
        left_high = getOneObjectAtOffset(-13, -18, Actor.class);
   
        right_low = getOneObjectAtOffset(13, 30, Actor.class);
        right_high = getOneObjectAtOffset(13, -18, Actor.class);


        if (isSliding) {
            maxXVelocity = 7;
            right_high = getOneObjectAtOffset(13, 10, Actor.class);
            left_high = getOneObjectAtOffset(-13, 10, Actor.class);

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
            
            if (right_low != null && right_high == null) {
                setLocation(getX(), getY() - 10);
            }
            else if (right_low != null || right_high != null) {
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
        
            if (left_low != null && left_high == null) {
                setLocation(getX(), getY() - 10);
            }
            else if (left_low != null || left_high != null) {
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
        
        below = getOneObjectAtOffset(-5, 
        (getImage().getHeight()/2 - 2) + (int)yVelocity, Actor.class);
        above = getOneObjectAtOffset(0, -30 , Actor.class);
        
        if (isSliding) {
            above = getOneObjectAtOffset(0, -2 , Actor.class);
        } else {
            above = getOneObjectAtOffset(0, -30 , Actor.class);
        }
        
        if (below != null) {
            onGround = true;
            yVelocity = 0;
            initYVelocity = 0;
            timeStep = 0;
            /*Got rid of this functinality -- keeping in case switch back*/
            //setLocation(getX(), below.getY());
            onGround = true;
        } else {
            onGround = false;
        }

        if (above != null  && yVelocity < 0) {
            yVelocity = Math.abs(yVelocity) + 3;
            initYVelocity = yVelocity;
            //setLocation(getX(), above.getY() + 40);
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
       
        
        if (holding && timeStep < 7 && !onGround && above == null) {
            yVelocity -= 5;
        }
            
        if (onGround == false) {
           
            setLocation(getX(), (int)(getY() + yVelocity));
            
            if (isSliding)  {   
                yVelocity = (int)2;
                initYVelocity = 2;
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
    
    public void freeze(GreenfootImage image){
        disableAnimation();
        this.setImage(image);
        frozen = true;
    }
    
    public void unfreeze(){
        enableAnimation();
        frozen = false;
    }
    
    public void kill(){
        ScrollWorld restartedWorld = new ScrollWorld();
        Greenfoot.setWorld(restartedWorld);
    }
    
    public void damage(){
        ScrollWorld currentWorld = (ScrollWorld)getWorld();
        if(currentWorld.getHealthBar().getCurrentDamage() == 3){
            kill();
            return;
        }
        getImage().setColor(Color.RED);
        currentWorld.getHealthBar().detractHealth();
    }
    
    public Actor getRightLow() {
        return right_low;
    } 
    public Actor getRightHigh() {
        return right_high;
    }
    public Actor getLeftLow() {
        return left_low;
    } 
    public Actor getLeftHigh() {
        return left_high;
    }  
    
    public boolean isSliding(){
        return isSliding;
    }
}
