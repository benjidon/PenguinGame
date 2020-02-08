import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimatedActor extends ScrollingActor
{
    private GreenfootImage[] movingFrames = null;
    private GreenfootImage[] idleFrames = null;
    
    private boolean isAnimating = true;
    private boolean isMoving = true;
    
    public static final int NORMAL_DELAY = 10;
    public static final int SLOW_DELAY = 50;
    
    int frame = 0;
    int counter = 0;
    int delay = NORMAL_DELAY;
    
    
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public AnimatedActor(String actorName, int movingCount, int idleCount){
        super();
        movingFrames = new GreenfootImage[movingCount];
        idleFrames = new GreenfootImage[idleCount];
        
        for(int i = 0; i < movingCount; ++i){
            movingFrames[i] = new GreenfootImage(actorName + i + ".png");
        }
        
        for(int i = 0; i < idleCount; ++i){
            idleFrames[i] = new GreenfootImage(actorName + "Idle" + i + ".png");
        }
    }
    
    public void act() 
    {   
        super.act();
        if(isAnimating){
            if(counter++ > delay && isMoving){
                if(movingFrames.length == 0){
                    return;
                }
                counter = 0;
                frame = (frame + 1) % movingFrames.length;
                setImage(movingFrames[frame]);
            } else if(counter++ > delay && !isMoving){
                if(idleFrames.length == 0){
                    return;
                }
                counter = 0;
                frame = (frame + 1) % idleFrames.length;
                setImage(idleFrames[frame]);
            }
        }
        
    }
    
    public void disableAnimation(){
        isAnimating = false;
    }
    
    public void enableAnimation(){
        isAnimating = true;
    }
    
    public void mirrorAll(){
        for(GreenfootImage image : movingFrames){
            image.mirrorHorizontally();
        }
        for(GreenfootImage image : idleFrames){
            image.mirrorHorizontally();
        }
    }
    
    public void setMoving(){
        isMoving = true;
        delay = NORMAL_DELAY;
    }
    
    public void setIdle(){
        isMoving = false;
        delay = SLOW_DELAY;
    }
}
