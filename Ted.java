import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ted here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ted extends Character
{   
    private boolean hasSpoken = false;
    private boolean doneSpeaking = false;
    private Message m = new Message(2);
    private int counter = 0;
    
    public Ted(){
        super("ted", 1, 0);
        setScale(60,75);
    }
    /**
     * Act - do whatever the Ted wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        ScrollWorld world = (ScrollWorld)getWorld();
        Slippy s = (Slippy)(world.getMainActor());
        if(Math.abs(getX() - s.getX()) < 350 && !hasSpoken){
            s.freeze(new GreenfootImage("slippyHappy.png"));
            world.addObject(m, 480, 570);
            m.getImage().scale(900, 125);
            hasSpoken = true;
        }
        if(hasSpoken && !doneSpeaking){
            m.setLocation(480, 570);
            ++counter;
        }
        if(counter == 300){
            doneSpeaking = true;
            s.unfreeze();
            world.removeObject(m);
        }
    }    
}
