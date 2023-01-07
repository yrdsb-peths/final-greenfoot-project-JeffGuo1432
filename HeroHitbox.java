import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class BasicHero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HeroHitbox extends Hero
{
    /**
     * Act - do whatever the BasicHero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //variables used in animation
    GreenfootImage[] idle = new GreenfootImage[4];  
    SimpleTimer animationTimer = new SimpleTimer();
    String action="Idle_";
    int imageIndex=0;
    int animationDelay = 100;
    
    //variables used in movement along the x axis
    int xVelocity=0;
    int xSpeed = 3;
    static int xDirection=0;
    static char xDirectionChar = 'r';
    static int x;
    //variables used in movement along the y axis
    int yVelocity=0;
    int ySpeed = 3;
    static int yDirection=0;
    static int y;
    
    public HeroHitbox()
    {
        
    }
    public void animate(int animationDelay)
    {
        if(animationTimer.millisElapsed() < animationDelay)
        {
            return;
        }
        animationTimer.mark();
        imageIndex = (imageIndex + 1 )% idle.length;
        GreenfootImage currentImage = new GreenfootImage("images/mHero_/"+action+"/"+xDirectionChar+action+imageIndex+".png");
        currentImage.scale(72,72);
        setImage(currentImage);
    }
    public void act()
    {
        // basic movement
        MyWorld world = (MyWorld) getWorld();
        x=getX();
        y=getY();
        
        setLocation(Hero.getXPos(),Hero.getYPos());
        setRotation(Hero.getXDirection());
        /**
         * //the code below was meant to slow down the player when they were moving diagonally, using pythagorean theorem
        if((xVelocity!=0)&(yVelocity!=0)){
            double xVelocityD = xVelocity;
            xVelocity=(int) Math.round(xVelocityD/1.41);
            double yVelocityD = yVelocity;
            yVelocity=(int) Math.round(yVelocityD/1.41);
        }
        **/
        animate(animationDelay);
        setLayers();
    }
    public boolean isTouchingSkeleton()
    {
        for(Class c: new Class[]{Skeleton.class})
        {
            
            List<Skeleton> bs = getIntersectingObjects(c);
            
            for(Skeleton b: bs)
            {
                if(b.canKill()){
                if(b.getX()+30>=this.getX()&b.getX()-30<=this.getX()&b.getY()+60>=this.getY()&b.getY()-60<=this.getY()){
                    return true;
                }
                }
            }
        }
        return false;
    }
    public void setLayers(){
        MyWorld world = (MyWorld) getWorld();
        for(Class c: new Class[]{Tile.class})
        {
            List<Tile> bs = getIntersectingObjects(c);
            for(Tile b :bs)
            {
                if(b.getY() < this.getY()+24)
                {
                    
                    int x = getX(), y = getY();
                    world.removeObject(this);
                    world.addObject(this, x, y);   
                    
                }
            }
        }
    }
    public boolean canMoveLeft()
    {
        for(Class c: new Class[]{Tile.class})
        {
            
            List<Tile> bs = getIntersectingObjects(c);
            
            for(Tile b: bs)
            {
                if(b.getX() < this.getX()&b.getTileDirection().indexOf("R")!=-1){
                    if(b.getY()>=this.getY()-10&b.getY()<=this.getY()+46){
                        if(b.getX()>=this.getX()-42){
                            return false;
                        }
                    }
                }
                if(b.getX() < this.getX()&b.getTileDirection().indexOf("D")!=-1){
                    if(b.getY()>=this.getY()-3&b.getY()<=this.getY()+33){
                        if(b.getX()>=this.getX()-42){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public boolean canMoveRight()
    {
        for(Class c: new Class[]{Tile.class})
        {
            
            List<Tile> bs = getIntersectingObjects(c);
            for(Tile b: bs)
            {
                if(b.getX() > this.getX()&b.getTileDirection().indexOf("L")!=-1){
                    if(b.getY()>=this.getY()-10&b.getY()<=this.getY()+46){
                        if(b.getX()<=this.getX()+42){
                            return false;
                        }
                    }
                }
                if(b.getX() > this.getX()&b.getTileDirection().indexOf("D")!=-1){
                    if(b.getY()>=this.getY()-3&b.getY()<=this.getY()+33){
                        if(b.getX()<=this.getX()+42){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public boolean canMoveUp()
    {
        MyWorld world = (MyWorld) getWorld();
        for(Class c: new Class[]{Tile.class})
        {
            
            List<Tile> bs = getIntersectingObjects(c);
            
            for(Tile b: bs)
            {
                
                if(b.getY() < this.getY()&b.getTileDirection().indexOf("D")!=-1)
                    if(b.getX()<=this.getX()+30&b.getX()>=this.getX()-30){
                        if(b.getY()>=this.getY()-9){
                            return false;
                        }
                }
            }
        }
        return true;
    }
    public boolean canMoveDown()
    {
        for(Class c: new Class[]{Tile.class})
        {
            
            List<Tile> bs = getIntersectingObjects(c);
            
            for(Tile b: bs)
            {   
                
                if(b.getY() > this.getY()&b.getTileDirection().indexOf("U")!=-1){
                    if(b.getX()<=this.getX()+30&b.getX()>=this.getX()-30){
                        if(b.getY()<=this.getY()+35){
                            return false;
                        }
                    }
                }
                if(b.getY() > this.getY()&b.getTileDirection().indexOf("D")!=-1){
                    if(b.getX()<=this.getX()+30&b.getX()>=this.getX()-30){
                        if(b.getY()<=this.getY()+40){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    //some static getter functions to communicate with other objects
    public static int getXPos()
    {
        return x;
    }
    public static int getYPos()
    {
        return y;
    }
    public static int getXDirection()
    {
        return xDirection;
    }
    public static char getXDirectionChar()
    {
        return xDirectionChar;
    }
    public static int getYDirection()
    {
        return yDirection;
    }
}
