  import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopyOfAxe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AxeHitbox extends Entity
{
    /**
     * The AxeHitbox is a weapon
     */
    //variables used in animation
    GreenfootImage imageRight = new GreenfootImage("images/axe_.png");
    GreenfootImage imageLeft = new GreenfootImage("images/axe_.png");
    GreenfootImage attackImageRight = new GreenfootImage("images/swingingAxe_.png");
    GreenfootImage attackImageLeft = new GreenfootImage("images/swingingAxe_.png");
    GreenfootSound axeStuck = new GreenfootSound("sounds/AxeStuck.mp3");
    GreenfootImage hitbox = new GreenfootImage("images/axeHitbox_.png");
    GreenfootImage swingHitbox = new GreenfootImage("images/axeSwingingHitbox_test1.png");
    GreenfootImage swingingImageRight = new GreenfootImage("images/swingAxe_.png");
    GreenfootImage swingingImageLeft = new GreenfootImage("images/swingAxe_.png");
    
    int count;
    public static int weaponDirection=1;
    int weaponDirectionY=1;
    public static boolean thrown=false;
    public static boolean attack=false;
    public static boolean stuck=false;
    boolean catchable=false;
    SimpleTimer attackTimer = new SimpleTimer();
    int thrownDirection;
    int speed = 5;
    int attackCooldown=0;
    int attackTicks = 0;
    public static int attackingWeaponDirection=0;
    public static int x;
    public static int y;
    public static int rotation;
    SimpleTimer thrownTimer = new SimpleTimer();
    public AxeHitbox()
    {
        thrown=false;           
        stuck=false;
        setImage(hitbox);   
        //swingingImageRight.scale(40,40);
        
        
        
        swingHitbox.scale(12,24);
        
    }
    public void act()
    {
        x=getX();
        y=getY();
        rotation=getRotation();
        count++;
        attackCooldown-=1;
        MyWorld world = (MyWorld) getWorld();
        
        //When a player presses "Q" the thrown variable is set to true so the Axe gets thrown
        if(Greenfoot.isKeyDown("q")){
            thrown=true;
            thrownTimer.mark();
            thrownDirection=weaponDirection;
        }
        if(thrown==true){
            attack=false;
            setImage(hitbox);

            if(Math.abs(this.getX()-Hero.getXPos())>60){
                catchable=true;
            }
            //catching code
            if(catchable){
                if(isTouching(Hero.class)){
                    stuck=false;
                    thrown=false;
                    catchable=false;
                    setLocation(Hero.getXPos()+weaponDirection*40,Hero.getYPos());
                    
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
                    followHero();
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
                   
                   //Moves the axe
                   setLocation(getX()+speed*weaponDirection,getY()+speed*weaponDirectionY);
                   
                }
    
            }
        }
        //If (the Axe is in the players hands){
        if(stuck==false&thrown==false){
            //The weapon's direction corresponds with the player's
            
            allignDirectionWithHero();
        }   
        
        
        if(thrown==false){
            thrownTimer.mark();
            //Melee attack
            if(attackCooldown>0){
                setImage(hitbox);
            }
            if(Greenfoot.isKeyDown("space")&attack==false&attackCooldown<=0){
                attackTimer.mark();
                attackCooldown=50;
                attack=true;
                attackingWeaponDirection=weaponDirection;

                //System.out.println("        pqwieufgpupiqwucbpiuqwbec              ");
            }
            if(attack==true){
                setImage(swingHitbox);
                /**
                if(attackTimer.millisElapsed()==80){
                    setImage(new GreenfootImage("images/axeSwing_/swing_1.png"));
                    getImage().scale(144,72);
                }
                if(attackTimer.millisElapsed()==120){
                    setImage(new GreenfootImage("images/axeSwing_/swing_2.png"));
                    getImage().scale(144,72);
                }
                if(attackTimer.millisElapsed()==160){
                    setImage(new GreenfootImage("images/axeSwing_/swing_3.png"));
                    getImage().scale(144,72);
                }
                **/
                
                if(attackTimer.millisElapsed()<200){
                    setLocation(Hero.getXPos()-20*attackingWeaponDirection,Hero.getYPos()+30);
    
                    setRotation(((attackTimer.millisElapsed()*3/10)-50)*attackingWeaponDirection);
                    int moveDistance= (int)Math.round(0.001*(Math.pow((attackTimer.millisElapsed()-100),2))+95);
                    
                    move(moveDistance*attackingWeaponDirection-attackTimer.millisElapsed()/8*attackingWeaponDirection);
                     
                    
                    setRotation(getRotation()+120*attackingWeaponDirection);
                    
                }
                else if(attackTimer.millisElapsed()>200){
                    attack=false;
                    setRotation(0);
                    setImage(hitbox);
                    followHero();
                }
                /**
                if(attackTimer.millisElapsed() < 100){
                    
                    world.addObject(new AxeSwingingHitbox(),Hero.getXPos()+weaponDirection*35,Hero.getYPos()+10);
                    
                    allignDirectionWithHero();
                    setLocation(Hero.getXPos()+weaponDirection*40,Hero.getYPos()+22);
                    setRotation(getRotation()+15*weaponDirection); 
                    
                }
                
                if(attackTimer.millisElapsed() > 200){
                    attack=false;
                    setRotation(0);
                }
                **/
            }
            if(attack==false){
                
                followHero();
                x=getX();
                y=getY();
                world.removeObject(this);
                world.addObject(this, x, y);
            }
        }
        
        /**if(count%50==0){
            System.out.println(Hero.getXPos());
        }
        **/
    }
    //these functions are useful to tell enemies that the axe is in a position to harm them
    
    public void followHero(){
        setLocation(Hero.getXPos()+weaponDirection*40,Hero.getYPos());
        setRotation(0);
    }
    public void allignDirectionWithHero(){
        if(Hero.getXDirectionChar()=='r'){
                weaponDirection=1;
            }
            else if(Hero.getXDirectionChar()=='l'){
                weaponDirection=-1;
            }
    }
    public static int getAttackingWeaponDirection(){
        return attackingWeaponDirection;
    }
    public static int getWeaponDirection(){
        return weaponDirection;
    }
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
