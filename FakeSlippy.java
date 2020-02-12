import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FakeSlippy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FakeSlippy extends Actor
{
    /**
     * Act - do whatever the FakeSlippy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image;
    public void act() 
    {
        image = new GreenfootImage("slippyAngry.png");
        image.scale(75, 75);
        this.setImage(image);
        
    }    
}
