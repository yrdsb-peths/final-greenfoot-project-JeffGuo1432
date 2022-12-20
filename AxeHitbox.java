  import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopyOfAxe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AxeHitbox extends Hero
{
    /**
     * Act - do whatever the CopyOfAxe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //variables used in animation
    GreenfootImage imageRight = new GreenfootImage("images/axe_.png");
    GreenfootImage imageLeft = new GreenfootImage("images/axe_.png");
    GreenfootImage attackImageRight = new GreenfootImage("images/swingingAxe_.png");
    GreenfootImage attackImageLeft = new GreenfootImage("images/swingingAxe_.png");
    GreenfootSound axeStuck = new GreenfootSound("sounds/AxeStuck.mp3");
    GreenfootImage hitbox = new GreenfootImage("images/axeHitbox_.png");
    int count;
    int weaponDirection=1;
    int weaponDirectionY=1;
    public static boolean thrown=false;
    public static boolean attack=false;
    public static boolean stuck=false;
    boolean catchable=false;
    SimpleTimer attackTimer = new SimpleTimer();
    int thrownDirection;
    int speed = 5;
    int attackCooldown=0;
    public static int x;
    public static int y;
    public static int rotation;
    SimpleTimer thrownTimer = new SimpleTimer();
    public AxeHitbox()
    {
        setImage(hitbox);
                    
    }
    public void act()
    {
        x=getX();
        y=getY();
        rotation=getRotation();
        count++;
        attackCooldown-=1;
        MyWorld world = (MyWorld) getWorld();
        if(Greenfoot.isKeyDown("q")){
            thrown=true;
            thrownTimer.mark();
            thrownDirection=weaponDirection;
        }
        if(stuck==false&thrown==false){
            if(super.getXDirectionChar()=='r'){
                weaponDirection=1;
            }
            else if(super.getXDirectionChar()=='l'){
                weaponDirection=-1;
            }
        }   
        //this code executes when the axe is not on the player, or an obstacle
        if(thrown==false){
            thrownTimer.mark();
            if(Greenfoot.isKeyDown("space")&attack==false&attackCooldown<=0){
                attackTimer.mark();
                attackCooldown=30;
                attack=true;
            }
            if(attackTimer.millisElapsed() < 100){
                if(super.getXDirectionChar()=='r'){
                    weaponDirection=1;
                }
                else{
                    weaponDirection=-1;
                }
                System.out.println("1");
                setLocation(super.getXPos()+weaponDirection*40,super.getYPos()+22);
                setRotation(getRotation()+15*weaponDirection); 
            }
            if(attackTimer.millisElapsed() > 200){
                
                attack=false;
                setRotation(0);
            }
            
            if(attack==false){
                System.out.println("2");
                setLocation(super.getXPos()+weaponDirection*40,super.getYPos());
                x=getX();
                y=getY();
                world.removeObject(this);
                world.addObject(this, x, y);
            }
        }
        else{
            attack=false;
            if(Math.abs(this.getX()-Hero.getXPos())>50){
                catchable=true;
            }
            if(catchable){
                if(isTouching(Hero.class)&weaponDirection!=thrownDirection){
                    stuck=false;
                    
                    thrown=false;
                    System.out.println("3");
                    setLocation(super.getXPos()+weaponDirection*40,super.getYPos());
                    setRotation(0);
                    }
            }
            
            if((isAtEdge()||isTouching(Obstacle.class))){
                if(stuck==false){
                    axeStuck.play();
                }
                stuck=true;
                
                
                if(getX()>Hero.getXPos()){
                    setRotation(20);
                }
                else{
                    setRotation(-20);
                }
                
                if(isTouching(Hero.class)&thrownTimer.millisElapsed()>100){
                    stuck=false;
                    thrown=false;
                    catchable=false;
                    System.out.println("4");
                    setLocation(super.getXPos()+weaponDirection*40,super.getYPos());
                    setRotation(0);
                }
                
            }
            else{
                if(isAtEdge()==false&isTouching(Obstacle.class)==false){
                   if(Greenfoot.isKeyDown("right")||weaponDirection==1){
                        weaponDirection=1;
                        weaponDirectionY=0;
                    }
                   if(Greenfoot.isKeyDown("left")||weaponDirection==-1){
                        weaponDirection=-1;
                        weaponDirectionY=0;
                    } 
                   if(Greenfoot.isKeyDown("up")){
                        weaponDirectionY=-1;
                        weaponDirection=0;
                    }
                   if(Greenfoot.isKeyDown("down")){
                        weaponDirectionY=1;
                        weaponDirection=0;
                    }
                   if(weaponDirection!=0){
                       setRotation(getRotation()+20*(weaponDirection)); 
                   }
                   else{
                       setRotation(getRotation()-20*(weaponDirectionY)); 
                   }
                   
                   
                   setLocation(getX()+speed*weaponDirection,getY()+speed*weaponDirectionY);
                   
                }
    
            }
        }
        /**if(count%50==0){
            System.out.println(super.getXPos());
        }
        **/
    }
    //these functions are useful to tell enemies that the axe is in a position to harm them
    public static int getXPosition(){
        return x;
    }
    public static int getYPosition(){
        return y;
    }
    public static int getAxeRotation(){
        return rotation;
    }
    static boolean isThrown()
    {
        return thrown;
    }
    static boolean isAttacking()
    {
        return attack;
    }
    static boolean isStuck()
    {
        return stuck;
    }
}
