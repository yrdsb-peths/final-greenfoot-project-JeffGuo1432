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
    int xSpeed = 4;
    int deltaX;
    static int xDirection=0;
    static char xDirectionChar = 'r';
    static int x;
    int xOffset;
    
    //variables used in movement along the y axis
    int yVelocity=0;
    int ySpeed = 2;
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
        count++;
        if(count%100==0){
            //System.out.println(moveState);
        }
        //
        MyWorld world = (MyWorld) getWorld();
        /**
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
        **/
        //System.out.println(getX());
        //setLocation(getX()+xVelocity,getY()+yVelocity);
        //xVelocity=xDirection*xSpeed;
        //yVelocity=yDirection*ySpeed;
        if(moveState!="hurt"&moveState!="dead"){
            animationDelay=200;
            //a skeleton with the personality "smart" will try to dodge the players axes
            if(personality=="smart"&AxeHitbox.isThrown()){
                if(Hero.getYPos()<getY()){
                    yDirection=1;
                    ySpeed=4;
                }
                if(Hero.getYPos()>getY()){
                    yDirection=-1;
                    ySpeed=4;
                }
            }
            if(moveState=="waiting"){
                xDirection=0;
                yDirection=0;
            }
            //i called moving in the x direction attacking, they always move towards the players x location while attacking
            if (moveState=="attacking"){
                deltaX = getX() - Hero.getXPos();
                yDirection=0;
                if(deltaX<0){
                    if(canMoveRight()){
                        setLocation(getX()+1,getY());
                        //xSpeed;
                        xDirection=1;
                        xDirectionChar='r';
                    }
                }
                if(deltaX>0){
                    if(canMoveLeft()){
                        setLocation(getX()-1,getY());
                        //xSpeed;
                        xDirection=(-1);
                        xDirectionChar='l';
                    }
                }
                if(Hero.getXPos()==getX()) {
                    moveState="routing";
                }
            }
            //i called moving in the y direction routing, they always move towards the players y location while routing
            if(moveState=="routing"){
                deltaY = getY() - Hero.getYPos();
                xDirection=0;

                if(deltaY>0){
                    if(canMoveUp()){
                        setLocation(getX(),getY()-1);
    
                        yDirection=-1;
                        ySpeed=1;
                    }
                }
                if(deltaY<0){
                    if(canMoveDown()){
                        setLocation(getX(),getY()+1);
                        ySpeed=1;
                        yDirection=1;
                    }
                }
                if(deltaY==0) {
                    moveState="attacking";
                }
            }
                //i added a waiting period to make the skeletons easier to handle, and to give the player some breathing room
            if(moveStateTimer.millisElapsed()>changeStateMillis&(moveState=="attacking"||moveState=="routing")){
                
                previousMoveState=moveState;
                moveState="waiting";
                moveStateTimer.mark();
                changeStateMillis=500;
            }
            
            if(moveStateTimer.millisElapsed()>changeStateMillis&(moveState=="waiting")){
                //a skeleton with the personality "robotic" will always move in the x axis, then y, then x, then y, then x ...
                
                if(personality=="robotic"){
                    if(previousMoveState=="attacking"){
                        moveState="routing";
                    }
                    if(previousMoveState=="routing"){
                        moveState="attacking";
                    }
                }
                //a skeleton with the personality "erratic" chooses randomly between moving in the x and the y axis.
                if(personality=="erratic"||personality=="smart"){
                    if(Greenfoot.getRandomNumber(2)==0){
                        moveState="attacking";
                    }
                    else{
                        moveState="routing";
                    }
                }
                
                changeOffset();
                moveStateTimer.mark();
                changeStateMillis=1000;
            }
            if(isTouching(AxeHitbox.class)&((AxeHitbox.isThrown()&AxeHitbox.isStuck()==false)||AxeHitbox.isAttacking())){
                moveState="hurt";
                moveStateTimer.mark();
            }
        }
        //here is the animation for the hurt and death, I wanted the hurt animation to be faster therefore the delay is only 70
        if(moveState=="hurt"){
            xVelocity=0;
            yVelocity=0;
            action="Hurt_";
            animationDelay=70;
            if(moveStateTimer.millisElapsed()>animationDelay*4){
                moveState="dead";

            }
        }
    
        if(moveState=="dead"){
            xVelocity=0;
            yVelocity=0;
            action="Death_";
            animationDelay=200;
            //upon death the skeleton spawns a new skeleton, but I may change this later
            if(moveStateTimer.millisElapsed()>animationDelay*4){
                world.spawnCoin(getX(),getY());
                world.removeObject(this);
                
            }
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
