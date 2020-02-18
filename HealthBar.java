import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    /**
     * Act - do whatever the PartsCollectedMessage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static final int healthPoints = 4;
    private GreenfootImage images[];
    private int damage = 0;
    
    public HealthBar(){
        images = new GreenfootImage[healthPoints];
        for(int i = 0; i < healthPoints; ++i){
            images[i] = new GreenfootImage("health" + i + ".png");
            images[i].scale(270, 70);
        }
        
    }
    
    public void act() 
    {
        this.setImage(images[damage]);
    }
    
    public void detractHealth(){
        this.setImage(images[++damage]);
    }
    
    public int getCurrentDamage(){
        return damage;
    }
    
    public void setCurrentDamage(int newDamage){
        this.damage = newDamage;
    }    
}
