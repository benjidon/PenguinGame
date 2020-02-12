import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intro extends World
{
    private boolean playing = false;
    
    public Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 600, 1);
        SplashScreen splash = new SplashScreen();
        addObject(splash, 470, 300);
    }
    
    public void act(){
    }
}
