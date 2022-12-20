import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class BasicHero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Entity
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
    int xSpeed = 4;
    static int xDirection=0;
    static char xDirectionChar = 'r';
    static int x;
    //variables used in movement along the y axis
    int yVelocity=0;
    int ySpeed = 1;
    static int yDirection=0;
    static int y;
    
    public Hero()
    {
        xDirection=0;
        xDirectionChar = 'r';
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
        if(xDirection<0&canMoveLeft()==false){
            xDirection=0;
        }
        if(xDirection>0&canMoveRight()==false){
            xDirection=0;
        }
        if(yDirection<0&canMoveUp()==false){
            yDirection=0;
        }
        if(yDirection>0&canMoveDown()==false){
            yDirection=0;
        }
        setLocation(getX()+xVelocity,getY()+yVelocity);
        
        xVelocity=xDirection*xSpeed;
        if((xVelocity==0)&(yVelocity==0)){
            action="Idle_";
            animationDelay=160;
        }
        else{
            action="WalkRun_";
            animationDelay=100;
        }
        //this slows the player down when they are carrying an axe, because it is heavy
        if(AxeHitbox.isThrown()){
            xSpeed=3;
            ySpeed=3;
        }
        else if(AxeHitbox.isAttacking()){
            xSpeed=2;
            ySpeed=2;
        }
        else{
            xSpeed=3;
            ySpeed=3;
        }
        
        if(Greenfoot.isKeyDown("right")){
            xDirection=1;
            xDirectionChar='r';
        }
        if(Greenfoot.isKeyDown("left")){
            xDirection=-1;
            xDirectionChar='l';
        }
        if(Greenfoot.isKeyDown("right")==false&(Greenfoot.isKeyDown("left")==false)){
            xDirection=0;
        }
        yVelocity=yDirection*ySpeed;
        
        if(Greenfoot.isKeyDown("down")){
            yDirection=1;
        }
        if(Greenfoot.isKeyDown("up")){
            yDirection=-1;
        }
        if(Greenfoot.isKeyDown("down")==false&(Greenfoot.isKeyDown("up")==false)){
            yDirection=0;
        }
        if(Greenfoot.isKeyDown("p")){
            System.out.println(getY());
        }
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
                        if(b.getY()<=this.getY()+35){
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
