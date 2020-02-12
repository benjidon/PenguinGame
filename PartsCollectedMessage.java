import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PartsCollectedMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartsCollectedMessage extends Actor
{
    /**
     * Act - do whatever the PartsCollectedMessage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static final int parts = 4;
    private GreenfootImage images[];
    int currentImage = 0;
    
    public PartsCollectedMessage(){
        images = new GreenfootImage[parts + 1];
        for(int i = 0; i <= parts; ++i){
            images[i] = new GreenfootImage("parts_collected" + i + ".png");
        }
        this.setImage(images[currentImage]);
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
    public void advance(){
        this.setImage(images[++currentImage]);
    }
}
