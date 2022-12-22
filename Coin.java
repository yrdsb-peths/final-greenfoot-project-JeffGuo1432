import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coin extends Entity
{
    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] idle = new GreenfootImage[4];  
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer timer = new SimpleTimer();
    int imageIndex=0;
    boolean collected=false;
    int speed = 10;
    int idleTime;
    public Coin(){
        timer.mark();
        idleTime=Greenfoot.getRandomNumber(2)*Greenfoot.getRandomNumber(12)*100+500;
    }
    public void animate(int animationDelay)
    {
        
        if(animationTimer.millisElapsed() < animationDelay)
        {
            return;
        }
        animationTimer.mark();
        imageIndex = (imageIndex + 1 )% idle.length;
        GreenfootImage currentImage = new GreenfootImage("images/coin_/coin_"+imageIndex+".png");
        currentImage.scale(30,30);
        setImage(currentImage);
        
    }
    public void act()
    {            
        if(timer.millisElapsed()>idleTime){
            turnTowards(20,30);
            move(speed);
            
        }
        if(isTouching(CoinUI.class)){
            if(speed>1){
                speed--;
            }
        }
        if(getX()==20&getY()==30){
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
            
        }
        animate(150);
    }
}
