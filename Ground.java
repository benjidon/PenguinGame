import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ground extends Solid
{
    private String surface_images[] = {"Surface_ground1.png", 
                                       "Surface_ground2.png",
                                       "Surface_ground3.png"};
    private String subsurface_images[] = {"ground1.png", 
                                          "ground2.png", 
                                          "ground3.png"}; 
    
    // 1 for surface, 0 for subsurface                            
    public Ground(int type) {
        super();
        Random r = new Random();
        GreenfootImage img;
        int index = r.nextInt(3);;
        if (type == 1) { 
            img = new GreenfootImage(surface_images[index]);
            img.scale(40, 40);
            setImage(img);
        } 
        else if (type == 0) {
            img = new GreenfootImage (subsurface_images[index]);
            img.scale(50, 50);
            setImage(img);
        }
        
    }
    /**
     * Act - do whatever the Ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
