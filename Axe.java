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
    
    GreenfootImage attackImageRight = new GreenfootImage("images/swingingAxe_.png");
    GreenfootImage attackImageLeft = new GreenfootImage("images/swingingAxe_.png");
    
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
    }
    public void act()
    {
        //Copies the axeHitboxes rotation, position, and direction
        setLocation(AxeHitbox.getXPosition(),AxeHitbox.getYPosition());
        setRotation(AxeHitbox.getAxeRotation());
        if(Hero.getXDirectionChar()=='r'){
                    setImage(imageRight);
        }
        else{
                    setImage(imageLeft);
        }
        MyWorld world = (MyWorld) getWorld();
        int x = getX(), y = getY();
        world.removeObject(this);
        world.addObject(this, x, y); 
    }
    
}