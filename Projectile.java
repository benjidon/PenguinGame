import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    GreenfootImage image = null;
    int direction;
    int velocity;
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Projectile(String name, int direction, int velocity){
        this.direction = direction;
        this.velocity = velocity;
        image = new GreenfootImage(name + "_projectile.png");
        setImage(image);
    }
    public void act() 
    {
        if (isAtEdge()){
            getWorld().removeObject(this);
        } else {
            setLocation(getX() + direction * velocity, getY());
        }
        try{
           Slippy slippy = (Slippy)getOneIntersectingObject(Slippy.class);  
          
            if(slippy != null && !slippy.isSliding())  
            {  
                slippy.damage();
                getWorld().removeObject(this);
            }
        } catch (IllegalStateException e){
            return;
        }
    }
}
