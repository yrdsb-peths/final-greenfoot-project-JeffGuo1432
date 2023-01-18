import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Demon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Demon extends Enemy
{
    /**
     * Act - do whatever the Demon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    String text= "";
    String[]textArray={"Hey go pick up that axe [space to continue]","Press space to attack","Press q to throw your axe","Use arrow keys after you press q"};
    int textIndex=0;
    Label textLabel=new Label(text,30);
    SimpleTimer textTimer = new SimpleTimer();
    boolean spoken = false;
    boolean canTakeMoney=true;
    boolean skipped = false;
    public void act()
    {
        action="IdleWalkRun_";
        MyWorld world = (MyWorld) getWorld();
        animate(200,"Ghost");
        
        if(getObjectsInRange(100,Hero.class).toString().equals("[]")==false&world.level==0){
            
            world.addObject(textLabel,getX(),getY()-30);
            if(textIndex==0){
                text=textArray[textIndex];
            }
            textLabel.setValue(text); 
        }
        
        if(Greenfoot.isKeyDown("space")&skipped==false){
            
            skipped=true;
            if(textIndex<textArray.length-1){
                textIndex+=1;
                text=textArray[textIndex];
            }
            textLabel.setValue(text); 
        }
        if(Greenfoot.isKeyDown("space")==false){
            skipped=false;
        }
        if(world.level!=0){
            textLabel.setValue("");
            world.removeObject(this);
            
        }
              
        }
}
