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
    private int currentPart = 0;
    
    public PartsCollectedMessage(){
        images = new GreenfootImage[parts + 2];
        for(int i = 0; i <= parts + 1; ++i){
            images[i] = new GreenfootImage("parts_collected" + i + ".png");
        }
        
    }
    
    public void act() 
    {
        this.setImage(images[currentPart]);
    }
    
    public void advance(){
        this.setImage(images[++currentPart]);
    }
    
    public int getCurrentPart(){
        return currentPart;
    }
    
    public void setPartsCollected(int partsCollected){
        this.currentPart = partsCollected;
    }
}
