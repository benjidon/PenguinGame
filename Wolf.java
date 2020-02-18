import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wolf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wolf extends Character
{
    /**
     * Act - do whatever the Wolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xSpawn;
    int direction = -1;
    int counter = 0;
    int range = 100;
    
    public Wolf(){
        super("wolf", 7, 0);
        this.xSpawn = xSpawn;
        this.setScale(115, 75);
        this.delay = 20;
    }
    public void act() 
    {
        super.act();
        if(Math.abs(counter) == range){
            counter = 0;
            direction *= -1;
            mirrorAll();
        }
        counter++;
        setLocation(getX() + direction * 5, getY());
        
        try{
           Actor a = getOneIntersectingObject(Slippy.class);  
          
            if(a != null)  
            {  
                Slippy slippy = (Slippy)a;
                slippy.damage();
                slippy.setLocation(slippy.getX() + 100, slippy.getY());
                
            }
        } catch (IllegalStateException e){
            return;
        } 
    }    
}
