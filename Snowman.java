import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snowman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snowman extends Character
{
    /**
     * Act - do whatever the Snowman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int throwSpeed = 185;
    int counter = 90;
    int direction;
    
    public Snowman(int direction){
        super("snowman", 7, 0);
        this.direction = direction;
        delay = SLOW_DELAY;
        if(direction == 1){
            this.mirrorAll();
        }
    }
    
    public void act() 
    {
        super.act();
        if(counter == throwSpeed){
            counter = 0;
            throwSnowball();
        }
        ++counter;
    }
    
    public void throwSnowball(){
        Snowball snowball = new Snowball(direction);
        getWorld().addObject(snowball, getX(), getY() - 20);
    }
}
