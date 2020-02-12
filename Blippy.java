import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blippy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blippy extends Character
{
    /**
     * Act - do whatever the Blippy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Blippy(){
        super("blippy", 8, 0);

    }
    
    public void act() 
    {
        GreenfootImage image = getImage();
        image.scale(75, 75);
        setImage(image);
        super.act();// Add your action code here.
        setLocation(getX() + 1, getY());
    }    
}
