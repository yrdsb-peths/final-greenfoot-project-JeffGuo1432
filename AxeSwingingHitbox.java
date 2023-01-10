import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AxeSwingingHitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AxeSwingingHitbox extends AxeHitbox
{
    /**
     * Act - do whatever the AxeSwingingHitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage swingHitbox = new GreenfootImage("images/axeSwingingHitbox_.png");
    SimpleTimer timer = new SimpleTimer();
    public AxeSwingingHitbox(){
        timer.mark();
        setImage(swingHitbox);
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(timer.millisElapsed()>100){
            world.removeObject(this);
        }
    }
}
