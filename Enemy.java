import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int knockbackStrength;
    public static int x;
    public static int y;
    public static int enemyNumber;
    GreenfootImage[] idle = new GreenfootImage[4];  
    SimpleTimer animationTimer = new SimpleTimer();
    String action="Idle_";
    int imageIndex=0;
    int animationDelay = 100;
    char xDirectionChar='r';
    public Enemy(){
        
    }
    public void animate(int animationDelay,String enemy)
    {
        if(animationTimer.millisElapsed() < animationDelay)
        {
            return;
        }
        animationTimer.mark();
        imageIndex = (imageIndex + 1 )% idle.length;
        GreenfootImage currentImage = new GreenfootImage("images/"+enemy+"_/"+action+"/"+xDirectionChar+action+imageIndex+".png");
        currentImage.scale(72,72);
        setImage(currentImage);
    }
    public void setEnemyNumber(int enemyNumber){
        this.enemyNumber=enemyNumber;
    }
    public void act()
    {
        x=getX();
        y=getY();
    }
    public void knockback(int strength){
        knockbackStrength=strength;
    }
    public static int getXPos(){
        return x;
    }
    public static int getYPos(){
        return y;
    }
}
