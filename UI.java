import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UI extends Actor
{
    /**
     * Act - do whatever the UI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    public void bringToTop(){
        MyWorld world = (MyWorld) getWorld();
        
        int x = getX(), y = getY();
                    world.removeObject(this);
                    world.addObject(this, x, y); 
    }
}
