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
    GreenfootImage swingImage0=new GreenfootImage("images/axeSwing_/swing_0.png");
    GreenfootImage swingImage1=new GreenfootImage("images/axeSwing_/swing_1.png");
    GreenfootImage swingImage2=new GreenfootImage("images/axeSwing_/swing_2.png");
    GreenfootImage swingImage3=new GreenfootImage("images/axeSwing_/swing_3.png");
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
    int attackTicks = 0;
    int attackingWeaponDirection=0;
    public static int x;
    public static int y;
    public static int rotation;
    SimpleTimer thrownTimer = new SimpleTimer();
    public AxeHitbox()
    {
                            System.out.println("--------------------");

        setImage(hitbox);   
        swingingImageRight.scale(72,72);
        swingImage0.scale(144,72);
        swingImage1.scale(144,72);
        swingImage2.scale(144,72);
        swingImage3.scale(144,72);
        swingHitbox.scale(36,72);
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
            if(Math.abs(this.getX()-Hero.getXPos())>50){
                catchable=true;
            }
            if(catchable){
                if(isTouching(Hero.class)){
                    System.out.println("b");
                }
                if(isTouching(Hero.class)&weaponDirection!=thrownDirection){
                    stuck=false;
                    thrown=false;
                    setLocation(Hero.getXPos()+weaponDirection*40,Hero.getYPos());
                    System.out.println("a");
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
            if(Greenfoot.isKeyDown("space")&attack==false&attackCooldown<=0){
                attackTimer.mark();
                attackCooldown=30;
                attack=true;
                attackingWeaponDirection=weaponDirection;
                //System.out.println("        pqwieufgpupiqwucbpiuqwbec              ");
            }
            /**
            if(attackTimer.millisElapsed()<40){
                setLocation(Hero.getXPos(),Hero.getYPos());
                
                setRotation(-20*attackingWeaponDirection);
                move(80*attackingWeaponDirection);
            }
            **/
            if(attack==true){
                if(attackTimer.millisElapsed()<40){
                    setImage(swingHitbox);
                }
                else if(attackTimer.millisElapsed()<80){
                    //setImage(swingImage1);
                }
                else if(attackTimer.millisElapsed()<120){
                    //setImage(swingImage2);
                }
                else if(attackTimer.millisElapsed()<160){
                    //setImage(swingImage3);
                }
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
                if(attackTimer.millisElapsed()<160){
                    
                    setLocation(Hero.getXPos(),Hero.getYPos());
                    
                    setRotation(((attackTimer.millisElapsed()/2)-80)*attackingWeaponDirection);
                    move(80*attackingWeaponDirection);
                    setRotation(getRotation()+120*attackingWeaponDirection);
                    
                }
                else if(attackTimer.millisElapsed()<300){
                    setLocation(Hero.getXPos(),Hero.getYPos());
    
                    setRotation(((attackTimer.millisElapsed()/2)-80)*attackingWeaponDirection);
                    move(80*attackingWeaponDirection);
                    setRotation(getRotation()+120*attackingWeaponDirection);
                }
                else if(attackTimer.millisElapsed()>300){
                    attack=false;
                    setRotation(0);
                    setImage(hitbox);
                    System.out.println("yes");
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
    }
    public void allignDirectionWithHero(){
        if(Hero.getXDirectionChar()=='r'){
                weaponDirection=1;
            }
            else if(Hero.getXDirectionChar()=='l'){
                weaponDirection=-1;
            }
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
