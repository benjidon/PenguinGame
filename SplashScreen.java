import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SplashScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SplashScreen extends Actor
{
    /**
     * Act - do whatever the SplashScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] slides = null;
    public static final int NUM_SLIDES = 3;
    
    public int currentSlide = 0;
    
    public SplashScreen(){
        
        slides = new GreenfootImage[NUM_SLIDES];
        for(int i = 0; i < NUM_SLIDES; ++i){
            slides[i] = new GreenfootImage("splash" + i + ".png");
        }
    }
    
    public void act() 
    {
        setImage(slides[currentSlide]);
        this.getImage().scale(700, 400);
        if (Greenfoot.mouseClicked(this)) {
            if(currentSlide == slides.length - 1){
                Intro currentWorld = (Intro)(getWorld());
                Greenfoot.setWorld(new CutSceneWorld());
                return;
            }
            currentSlide++;      
        }
    }    
}
