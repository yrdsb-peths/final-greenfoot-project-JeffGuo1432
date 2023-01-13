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
    SimpleTimer bobTimer = new SimpleTimer();
    int count = 0;
    
    public void bob(int bobDelay)
    {
        if(bobTimer.millisElapsed() < bobDelay)
        {
            return;
        }
        count++;
        bobTimer.mark();
        if(count%2==0){
            setLocation(getX(),getY()+3);
        }
        else{
            setLocation(getX(),getY()-3);
        }
    }
    public HeartUI(String image){
        setImage(new GreenfootImage(image));
        getImage().scale(40,40);
    }
    public void act()
    {
        
        bob(500);
    }
}
