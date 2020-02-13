import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Part here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part extends Actor
{
    /**
     * Act - do whatever the Part wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage image;
    private ScrollWorld world;
    
    public Part(int number){
        image = new GreenfootImage("plane" + number + ".png");
        this.setImage(image);
        
    }
    
    public void act() 
    {
        Actor b = getOneIntersectingObject(Slippy.class);  
      
        if(b != null)  
        {  
            world = (ScrollWorld)(getWorld());
            world.getPartsCollected().advance();  
            world.removeObject(this);
            if(world.getPartsCollected().getCurrentPart() == 4){
                world.setGameOver();
            }
        }  
    }    
}
