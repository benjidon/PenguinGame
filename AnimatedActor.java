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
    int delay = 4;
    
    private boolean isAnimating = true;
    
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
    {   if(isAnimating){
            if(frames.length == 0){
                return;
            }
            if(counter++ > delay){
                counter = 0;
                frame = (frame + 1) % frames.length;
                setImage(frames[frame]);
            }
        }
        
    }
    
    public void disableAnimation(){
        isAnimating = false;
    }
    
    public void enableAnimation(){
        isAnimating = true;
    }
}
