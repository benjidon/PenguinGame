import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimatedActor extends Actor
{
    private GreenfootImage[] frames = null;
    int frame = 0;
    int counter = 0;
    
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public AnimatedActor(String actorName, int imageCount){
        super();
        frames = new GreenfootImage[imageCount];
        
        for(int i = 0; i < imageCount; ++i){
            frames[i] = new GreenfootImage(actorName + i + ".png");
        }
    }
    
    public void act() 
    {
        if(frames.length == 0){
            return;
        }
    }    
}
