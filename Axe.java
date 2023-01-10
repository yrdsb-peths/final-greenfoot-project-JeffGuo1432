  import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Axe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Axe extends Entity
{
    /**
     * The Axe is the first weapon that I've added to the game.
     * Originally, I intended it to have a primary fire and a secondary fire.
     * The primary fire would swing the axe damaging enemies near the player, 
     * and the secondary fire would throw the axe hitting enemies along the 
     * way until the axe gets stuck in a wall. Kind of like the real sport, axe throwing.
     * The player would then have up to the axe that is stuck in the wall to pick it back up.
     * During implementation, I noticed that if the player walks backwards after the axe was thrown, 
     * the axe would return to the player. Instead of fixing this bug, I thought to myself making the Axe a controllable
     * boomerang type weapon would be much more interesting and could open the door to new gameplay.
     * Now, after the axe is thrown you can control its direction using the player movement keys, allowing you to weave the 
     * Axe past obstacles and through enemies.
     * 
     * 
     * The Axe Object is simply an image placed ontop of the AxeHitbox.
     * This is because the Axe itself is a long and rectangular that 
     * I found was often times too frustrating to control.
     */
    
    GreenfootImage imageRight = new GreenfootImage("images/axe_.png");
    GreenfootImage imageLeft = new GreenfootImage("images/axe_.png");
    GreenfootImage swingingImageRight = new GreenfootImage("images/swingAxe_.png");
    GreenfootImage swingingImageLeft = new GreenfootImage("images/swingAxe_.png");
    
    GreenfootImage attackImageRight = new GreenfootImage("images/swingingAxe_.png");
    GreenfootImage attackImageLeft = new GreenfootImage("images/swingingAxe_.png");
    SimpleTimer attackTimer = new SimpleTimer();
    GreenfootImage[] swingAnimationRight={new GreenfootImage("images/axeSwing_/swing_0.png"),
    new GreenfootImage("images/axeSwing_/swing_1.png"),
    new GreenfootImage("images/axeSwing_/swing_2.png"),
    new GreenfootImage("images/axeSwing_/swing_3.png")};
    GreenfootImage[] swingAnimation = new GreenfootImage[swingAnimationRight.length];
    GreenfootImage[] swingAnimationLeft={new GreenfootImage("images/axeSwing_/swing_0.png"),
    new GreenfootImage("images/axeSwing_/swing_1.png"),
    new GreenfootImage("images/axeSwing_/swing_2.png"),
    new GreenfootImage("images/axeSwing_/swing_3.png")};

    int attackCooldown = 0;
    boolean attack = false;
    int weaponDirection = 0;
    int attackingWeaponDirection = 1;
    public Axe()
    {
        //Initalizes the images
        setImage(imageLeft);
        imageRight.scale(36,72);
        imageLeft.mirrorHorizontally();
        imageLeft.scale(36,72);
        attackImageRight.scale(36,144);
        attackImageLeft.mirrorHorizontally();
        attackImageLeft.scale(36,144);
        
        for (int i = 0 ; i<swingAnimationRight.length ; i++){
            swingAnimationRight[i].scale(144,72);
            
            //swingAnimationLeft[i]=swingAnimationRight[i];
            swingAnimationLeft[i].mirrorHorizontally();
            swingAnimationLeft[i].scale(144,72);
        }
        
    }
    public void act()
    {
        //Copies the axeHitboxes rotation, position, and direction
        setLocation(AxeHitbox.getXPosition(),AxeHitbox.getYPosition());
        setRotation(AxeHitbox.getAxeRotation());
        
        MyWorld world = (MyWorld) getWorld();
        int x = getX(), y = getY();
        world.removeObject(this);
        world.addObject(this, x, y); 
        attackCooldown-=1;
        if(AxeHitbox.isThrown()){
            if(AxeHitbox.getWeaponDirection()==1){
                        setImage(imageRight);
                        //System.out.println("r");
                }
                else{
                        setImage(imageLeft);
                        //System.out.println("l");
                }
        }
        if(AxeHitbox.isThrown()==false){
            if(Greenfoot.isKeyDown("space")&attack==false&attackCooldown<=0){
                attackTimer.mark();
                attackCooldown=30;
                attack=true;
                attackingWeaponDirection=AxeHitbox.getWeaponDirection();
                if(attackingWeaponDirection>0){
                    swingAnimation=swingAnimationRight;
                    System.out.println("r");
                }
                if(attackingWeaponDirection<0){
                    swingAnimation=swingAnimationLeft;
                    System.out.println("l");
                }
            }
            if(attack==true){
                if(attackTimer.millisElapsed()<40){
                    setImage(swingAnimation[0]);
                }
                else if(attackTimer.millisElapsed()<80){
                    setImage(swingAnimation[1]);
                }
                else if(attackTimer.millisElapsed()<120){
                    setImage(swingAnimation[2]);
                }
                else if(attackTimer.millisElapsed()<160){
                    setImage(swingAnimation[3]);
                }
                
                if(attackTimer.millisElapsed()<300){
                    setLocation(Hero.getXPos(),Hero.getYPos());
                    setRotation(((attackTimer.millisElapsed()/2)-100)*attackingWeaponDirection);
                    move(80*attackingWeaponDirection);
                    setRotation(getRotation()+120*attackingWeaponDirection);
                }
                else if(attackTimer.millisElapsed()>300){
                    attack=false;
                    setRotation(0);
                }
            }
            if(attack==false){
                if(AxeHitbox.getWeaponDirection()==1){
                        setImage(imageRight);
                        //System.out.println("r");
                }
                else{
                        setImage(imageLeft);
                        //System.out.println("l");
                }
            }
        }
    }
}