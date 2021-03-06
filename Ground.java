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
                                          
    private String fading_right[] = {"ground1_fading_right.png",
                                     "ground2_fading_right.png",
                                     "ground3_fading_right.png"};
                                     
    private String surface_cave[] = {"Surface_cave_ground1.png",
                                     "Surface_cave_ground2.png",
                                     "Surface_cave_ground3.png"
                                    };
                                    
    private String subsurface_cave[] = {"Cave_ground1.png",
                                        "Cave_ground2.png",
                                        "Cave_ground3.png"
                                       };
                                    
    
    // 1 for surface, 0 for subsurface  
    public Ground(int type, int width, int height) {
        super();
        Random r = new Random();
        GreenfootImage img;
        int index = r.nextInt(3);
        
        
        if (type == 3) {
            img = new GreenfootImage(width, height);
            img.drawImage(new GreenfootImage("Cave_ground3.png"), 0, 0);
            int xStart = 0;
            int yStart = height;
            for (int y = yStart; y > 0; y -= 30) {
                for (int x = xStart; x < width; x += 30) {
                    GreenfootImage draw;
                    if (x == xStart) {
                        draw = new GreenfootImage(surface_cave[index]);
                    } else {
                        draw = new GreenfootImage(subsurface_cave[index]);
                    }
                    draw.scale(30, 30);
                    img.drawImage(draw, x, y);
                    index = r.nextInt(3);
                }
                xStart += 30;
                yStart -= 30;
            }
            setImage(img);    
       }
        
       if (type == 2) {
        
            img = new GreenfootImage(width, height);
            img.drawImage(new GreenfootImage("Cave_ground3.png"), 0, 0);
            
            for (int y = 0; y < height; y += 30) {
                for (int x = 0; x < width; x += 30) {
                    GreenfootImage draw;
                    if ((y + 30) >= height) {
                        draw = new GreenfootImage(surface_cave[index]);
                        draw.mirrorVertically();
                    } else {
                        draw = new GreenfootImage(subsurface_cave[index]);
                    }
                    draw.scale(30, 30);
                    
                    img.drawImage(draw, x, y);
                    index = r.nextInt(3);
                }
            }
            setImage(img);
        }
        
        if (type == 1) {

            img = new GreenfootImage(width, height);
            img.drawImage(new GreenfootImage("Cave_ground3.png"), 0, 0);
            
            for (int y = 0; y < height; y += 30) {
                for (int x = 0; x < width; x += 30) {
                    GreenfootImage draw;
                    if (y == 0) {
                        draw = new GreenfootImage(surface_cave[index]);
                    } else {
                        draw = new GreenfootImage(subsurface_cave[index]);
                    }
                    draw.scale(30, 30);
                    img.drawImage(draw, x, y);
                    index = r.nextInt(3);
                }
            }
            setImage(img);
        }
        
    }
    
    public Ground(int type) {
        super();
        Random r = new Random();
        GreenfootImage img;
        int index = r.nextInt(3);
        if (type == 6) {
            img = new GreenfootImage(3000, 200);
            img.drawImage(new GreenfootImage("Cave_ground3.png"), 0, 0);
            
            for (int y = 0; y < 200; y += 30) {
                for (int x = 0; x < 3000; x += 30) {
                    GreenfootImage draw;
                    if (y == 0) {
                        draw = new GreenfootImage(surface_cave[index]);
                    } else {
                        draw = new GreenfootImage(subsurface_cave[index]);
                    }
                    draw.scale(30, 30);
                    img.drawImage(draw, x, y);
                    index = r.nextInt(3);
                }
            }
            setImage(img);
        }
        if (type == 5) {
            img = new GreenfootImage(surface_images[index]);
            img.scale(15, 15);
            setImage(img);
            
        }
        if (type == 4) {
            img = new GreenfootImage(subsurface_cave[index]);
            img.scale(30, 30);
            setImage(img);
        }
        
        if (type == 3) {
            img = new GreenfootImage(surface_cave[index]);
            img.scale(30, 30);
            setImage(img);
        }
        if (type == 2) {
            img = new GreenfootImage(fading_right[index]);
            img.scale(30, 30);
            setImage(img);
        }
        if (type == 1) { 
            img = new GreenfootImage(surface_images[index]);
            img.scale(30, 30);
            setImage(img);
        } 
        else if (type == 0) {
            img = new GreenfootImage (subsurface_images[index]);
            img.scale(30, 30);
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
