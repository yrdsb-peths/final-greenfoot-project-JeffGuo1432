import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CoinUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoinUI extends Entity
{
    /**
     * Act - do whatever the CoinUI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] idle = new GreenfootImage[4];  
    SimpleTimer animationTimer = new SimpleTimer();
    int imageIndex=0;
    int size=30;
    public void animate(int animationDelay,int size)
    {
        MyWorld world = (MyWorld) getWorld();
        if(animationTimer.millisElapsed() < animationDelay)
        {
            return;
        }
        animationTimer.mark();
        imageIndex = (imageIndex + 1 )% idle.length;
        GreenfootImage currentImage = new GreenfootImage("images/coin_/coin_"+imageIndex+".png");
        currentImage.scale(size,size);
        setImage(currentImage);
        world.removeObject(this);
        world.addObject(this,20,30);
    }
    public void act()
    {
        animate(150,size);
        if(isTouching(Coin.class)){
            animate(-1,40);
            
        }
        else{
            size=30;
        }
    }
}
