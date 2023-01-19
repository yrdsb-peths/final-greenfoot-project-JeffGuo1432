import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Skeleton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skeleton extends Enemy
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
    String enemy = "skeleton_";
    int size = 72;
    //variables used in movement along the x axis
    int xVelocity=0;
    int xSpeed = 2;
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
    
    
    //variables used in combat
    SimpleTimer hurtPlayerTimer = new SimpleTimer();
    int knockbackStrength=0;
    SimpleTimer damageCooldownTimer = new SimpleTimer();
    int health=1;
    int coins=5;
    boolean canDropCoins=true;
    public Skeleton(){
        animate(0);
        //offset to make sure the skeletons don't clump up onto one pixel
        xOffset=Greenfoot.getRandomNumber(100)-50;
        yOffset=Greenfoot.getRandomNumber(100)-50;
        MyWorld world = (MyWorld) getWorld();
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
        
        if(moveState=="dead"){
            world.removeObject(this);
        }
        personality="erratic";
    }
    
    public void ai(){
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
                        setLocation(getX()+xSpeed,getY());
                        //xSpeed;
                        xDirection=1;
                        xDirectionChar='r';
                    }
                }
                if(deltaX>0){
                    if(canMoveLeft()){
                        setLocation(getX()-xSpeed,getY());
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
                        setLocation(getX(),getY()-ySpeed);
    
                        yDirection=-1;
                        
                    }
                }
                if(deltaY<0){
                    if(canMoveDown()){
                        setLocation(getX(),getY()+ySpeed);
                        
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
                changeStateMillis=1000;
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
            //moveState="waiting";
            if(isTouching(AxeHitbox.class)&((AxeHitbox.isThrown()&AxeHitbox.isStuck()==false)||AxeHitbox.isAttacking())){
                takeDamage();
                moveStateTimer.mark();
                if(AxeHitbox.isAttacking()){
                    knockbackStrength=10;
                }
                
            }
            
        }
        if(knockbackStrength>0){
            turnTowards(Hero.getXPos(),Hero.getYPos());
            turn(180);
            move(knockbackStrength);
            smartMove(knockbackStrength);
            
            

            knockbackStrength-=1;
            setRotation(0);
            
            }
            //System.out.println(moveState);
        //here is the animation for the hurt and death, I wanted the hurt animation to be faster therefore the delay is only 70
        if(moveState=="hurt"){
            if(action!="Hurt_"){
                imageIndex=1;
                
            }
            action="Hurt_";
            animationDelay=125;
            if(damageCooldownTimer.millisElapsed()>501){
                moveState="attacking";
                action="Idle_";
            }
        }
        
        if(health<=0||moveState=="dead"){
            xVelocity=0;
            yVelocity=0;
            action="Death_";
            animationDelay=200;
            moveState="dead";
            if(moveStateTimer.millisElapsed()>animationDelay*4){
                
        
                if(canDropCoins){
                    dropLoot(1);
                    
                }
                world.removeObject(this);
                canDropCoins=false;
            }
        }
        
        animate(animationDelay);
    }
    public void dropLoot(int coins)
    {
        MyWorld world = (MyWorld) getWorld();
        for(int i = 0 ; i < coins ; i++){
            world.spawnCoin(getX()+i*10-(coins*5),getY()+(i%2)*5);
        }
        world.removeObject(this);
    }
    public void animate(int animationDelay)
    {
        if(animationTimer.millisElapsed() < animationDelay)
        {
            return;
        }
        animationTimer.mark();
        imageIndex = (imageIndex + 1 )% idle.length;
        GreenfootImage currentImage = new GreenfootImage("images/"+enemy+"/"+action+"/"+xDirectionChar+action+imageIndex+".png");
        currentImage.scale(size,size);
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
        ai();
        
        
    }
    public void takeDamage(){
        if(damageCooldownTimer.millisElapsed()>1000){
        
            health--;
            moveState="hurt";
            imageIndex=1;
                action="Hurt_";
            animate(0);
            new GreenfootSound("sounds/Hit_/EnemyHurt_"+Greenfoot.getRandomNumber(1)+".mp3").play();
            damageCooldownTimer.mark();
            

        }
    }
    public void smartMove(int distance){
        if(0<getRotation()&getRotation()<=90){
            if(canMoveDown()&canMoveRight()){
                move(distance);
            }
            else{
                moveState="dead";
            }
        }
        if(90<getRotation()&getRotation()<=180){
            if(canMoveDown()&canMoveLeft()){
                move(distance);
            }
            else{
                moveState="dead";
            }
        }
        if(180<getRotation()&getRotation()<=270){
            if(canMoveUp()&canMoveLeft()){
                move(distance);
            }
            else{
                moveState="dead";
                
            }
        }
        if(270<getRotation()&getRotation()<=360){
            if(canMoveUp()&canMoveRight()){
                move(distance);
            }
            else{
                moveState="dead";
            }
        }
    }
    
    public void knockback(int strength){
        knockbackStrength=strength;
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
    /**
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
    **/
}
