import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HeartUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HeartUI extends Entity
{
    /**
     * Act - do whatever the HeartUI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HeartUI(String image){
        setImage(new GreenfootImage(image));
        getImage().scale(50,50);
    }
    public void act()
    {
        // Add your action code here.
    }
}
