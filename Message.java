import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Message extends Actor
{
    GreenfootImage image;
    /**
     * Act - do whatever the Message wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Message(int num){
        this.image = new GreenfootImage("message" + num + ".png");
        image.scale(800, 200);
        this.setImage(image);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
