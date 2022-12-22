import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Skeleton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goblin extends Enemy
{
    /**
     * Act - do whatever the Skeleton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int count=0;
    //variables used in animation
    GreenfootImage[] idle = new GreenfootImage[4];  
    SimpleTimer animationTimer = new SimpleTimer();
    String action="Idle_";
    int imageIndex=0;
    int animationDelay = 100;
    
    //variables used in movement along the x axis
    int xVelocity=0;
    int xSpeed = 1;
    int deltaX;
    static int xDirection=0;
    static char xDirectionChar = 'r';
    static int x;
    int xOffset;
    
    //variables used in movement along the y axis
    int yVelocity=0;
    int ySpeed = 1;
    int deltaY;
    int yDirection=0;
    static int y;
    int yOffset;
    
    //variables used in the ai
    String personality="robotic";
    String moveState="routing";
    String previousMoveState="attacking";
    int changeStateMillis;
    SimpleTimer moveStateTimer = new SimpleTimer();
    SimpleTimer hurtPlayerTimer = new SimpleTimer();
    int angle;
    public Goblin(){
        animate(0);
        //offset to make sure the skeletons don't clump up onto one pixel
        xOffset=Greenfoot.getRandomNumber(100)-50;
        yOffset=Greenfoot.getRandomNumber(100)-50;
        
        //i gave each skeleton a "personality" to add some variation in their movements
        if(Greenfoot.getRandomNumber(2)==0){
            moveState="attacking";
        }
        if(Greenfoot.getRandomNumber(2)==0){
            personality="erratic";
        }
        if(Greenfoot.getRandomNumber(8)==0){
            personality="smart";
        }
        personality="smart";
        moveState="walking";
    }
    public void animate(int animationDelay)
    {
        
        if(animationTimer.millisElapsed() < animationDelay)
        {
            return;
        }
        animationTimer.mark();
        imageIndex = (imageIndex + 1 )% idle.length;
        GreenfootImage currentImage = new GreenfootImage("images/goblin_/"+action+"/"+xDirectionChar+action+imageIndex+".png");
        currentImage.scale(72,72);
        setImage(currentImage);
    }
    
    public void act()
    {
        // Add your action code here.
        System.out.println(moveState);
        if(moveState=="walking"){
            if(Hero.getXPos()>this.getX()){
                setLocation(getX()+xSpeed,getY());
            }
            if(Hero.getXPos()<this.getX()){
                setLocation(getX()-xSpeed,getY());
            }
            if(Hero.getYPos()>this.getY()){
                setLocation(getX(),getY()+ySpeed);
            }
            if(Hero.getYPos()<this.getY()){
                setLocation(getX(),getY()-ySpeed);
            }
        }
        System.out.println(getObjectsInRange(200,Hero.class).toString());
        if(getObjectsInRange(200,Hero.class).toString().equals("[]")==false){
            System.out.print("yes");
            moveState="aiming";
        }
        else{
            moveState="walking";
        }
        if(moveState=="aiming"){
            animationDelay=160;
        }
        animate(animationDelay);
    }
    public boolean canKill(){
        if(moveState!="hurt"&moveState!="dead"){
            return true;
        }
        return false;
    }
    //this resets the offset for even more randomness
    public void changeOffset(){
        xOffset=Greenfoot.getRandomNumber(100)-50;
        yOffset=Greenfoot.getRandomNumber(100)-50;
        xOffset=0;
        yOffset=0;
    }
   public boolean canMoveLeft()
    {
        for(Class c: new Class[]{Tile.class})
        {
            
            List<Tile> bs = getIntersectingObjects(c);
            
            for(Tile b: bs)
            {
                if(b.getX() < this.getX()&b.getTileDirection().indexOf("R")!=-1){
                    if(b.getX()>=this.getX()-33){
                        return false;
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
                if(b.getX() > this.getX()&b.getTileDirection().indexOf("L")!=-1)
                    if(b.getX()<=this.getX()+33){
                        return false;
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
                    if(b.getY()>=this.getY()-6){
                        return false;
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
                
                if(b.getY() > this.getY()&b.getTileDirection().indexOf("U")!=-1)
                    return false;
            }
        }
        return true;
    }
}
