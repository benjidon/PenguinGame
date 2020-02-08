import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intro extends World
{
    static GreenfootSound menuMusic  = new GreenfootSound("menu.mp3");
    private boolean playing = false;
    
    public Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 600, 1);
        SplashScreen splash = new SplashScreen();
        addObject(splash, 470, 300);
        
        if(!menuMusic.isPlaying() && playing == false)
        {
            menuMusic.playLoop();
            playing = true;
            menuMusic.setVolume(65);
        }
        
    }
}
