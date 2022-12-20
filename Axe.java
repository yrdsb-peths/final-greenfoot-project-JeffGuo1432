  import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Axe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Axe extends Actor
{
    /**
     * Act - do whatever the Axe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //variables used in animation
    
    GreenfootImage imageRight = new GreenfootImage("images/axe_.png");
    GreenfootImage imageLeft = new GreenfootImage("images/axe_.png");
    
    GreenfootImage attackImageRight = new GreenfootImage("images/swingingAxe_.png");
    GreenfootImage attackImageLeft = new GreenfootImage("images/swingingAxe_.png");
    /**
    GreenfootSound axeStuck = new GreenfootSound("sounds/AxeStuck.mp3");
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
    
    SimpleTimer thrownTimer = new SimpleTimer();
    **/
    public Axe()
    {
        setImage(imageLeft);
        imageRight.scale(36,72);
        imageLeft.mirrorHorizontally();
        imageLeft.scale(36,72);
        attackImageRight.scale(36,144);
        attackImageLeft.mirrorHorizontally();
        attackImageLeft.scale(36,144);
    }
    public void act()
    {
        setLocation(AxeHitbox.getXPosition(),AxeHitbox.getYPosition());
        setRotation(AxeHitbox.getAxeRotation());
        if(Hero.getXDirectionChar()=='r'){
                    setImage(imageRight);
        }
        else{
                    setImage(imageLeft);
        }
        /**
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
                setImage(imageRight);
            }
            else if(super.getXDirectionChar()=='l'){
                weaponDirection=-1;
                setImage(imageLeft);
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
                if(super.getXDirection()=='r'){
                    weaponDirection=1;
                    setImage(attackImageRight);
                }
                else{
                    weaponDirection=-1;
                    setImage(attackImageLeft);
                }
                
                setLocation(super.getXPos()+weaponDirection*28,super.getYPos()+22);
                setRotation(getRotation()+15*weaponDirection); 
            }
            if(attackTimer.millisElapsed() > 200){
                
                attack=false;
                setRotation(0);
            }
            
            if(attack==false){
                this.setLocation(super.getXPos()+weaponDirection*28,super.getYPos());
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
                    setLocation(super.getXPos()+weaponDirection*28,super.getYPos());
                    setRotation(0);
                    }
            }
            
            if((isAtEdge()||isTouching(Obstacle.class))){
                if(stuck==false){
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
                    setLocation(super.getXPos()+weaponDirection*28,super.getYPos());
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
    /**
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
    **/
}