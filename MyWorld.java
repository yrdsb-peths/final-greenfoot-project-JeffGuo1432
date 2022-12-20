import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    static int worldHeight=600;   
    static int worldWidth=400;;
    //here is the array of all the tiles, it has the name of the tile, and the location
    Tile[] borderMap = {new Tile("wallR",24,19),new Tile("wallR",15,52),new Tile("wallR",15,146),new Tile("wallR",18,182),new Tile("wallR",21,225),new Tile("wallR",26,253),new Tile("wallR",18,297),new Tile("wallR",17,339),new Tile("wallD",71,25),new Tile("wallD",105,19),new Tile("wallD",145,20),new Tile("wallD",194,23),new Tile("wallD",224,23),new Tile("wallD",267,22),new Tile("wallD",309,22),new Tile("wallD",346,23),new Tile("wallD",392,24),new Tile("wallD",421,22),new Tile("wallD",460,22),new Tile("wallD",504,22),new Tile("wallD",534,22),new Tile("wallL",580,62),new Tile("wallL",588,103),new Tile("wallL",581,142),new Tile("wallL",582,191),new Tile("wallL",590,224),new Tile("wallL",577,259),new Tile("wallL",583,296),new Tile("wallL",593,332),new Tile("wallL",592,19),new Tile("wallCornerUR",33,378),new Tile("wallU",72,378),new Tile("wallU",98,378),new Tile("wallU",133,382),new Tile("wallU",174,383),new Tile("wallU",218,384),new Tile("wallU",251,385),new Tile("wallU",299,381),new Tile("wallU",342,383),new Tile("wallU",384,383),new Tile("wallU",421,386),new Tile("wallU",461,388),new Tile("wallU",500,387),new Tile("wallU",536,386),new Tile("wallCornerUL",572,385)};
    Tile[] level1Map = {

new Tile("wallD",299,178),
new Tile("wallD",331,181),
};
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(worldHeight, worldWidth, 1); 
        Hero hero = new Hero();
        addObject(hero,100,200);
        addObject(new Axe(),100,200);
        addObject(new AxeHitbox(),100,200);
        //this spawns the beginning wave of skeleton. I was too lazy to make a for loop
        
        spawnSkeleton(500,Greenfoot.getRandomNumber(300+50));
        spawnSkeleton(100,Greenfoot.getRandomNumber(300+50));
        //spawnSkeleton(500,Greenfoot.getRandomNumber(300+50));
        
        
        

        
        
        
        
    
        spawnMap(level1Map);
        spawnMap(borderMap);
    }
    
    public void act(){
        
        
        if(Greenfoot.mouseClicked(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            
            System.out.println("new Tile(\"wallU\","+mouse.getX()+","+mouse.getY()+"),");
            addObject(new Tile("wallR",mouse.getX(),mouse.getY()),mouse.getX(),mouse.getY());
        }
        
    }
    public void spawnMap(Tile[] map){
        for(int i = 0 ; i < map.length ; i++){
            addObject(map[i],0,0);
        }
    }
    public void spawnSkeleton(int x, int y)
    {
        addObject(new Skeleton(),x,y);
    }
    public static String getTileDirection(Tile t)
    {
        return t.tileName.substring(t.tileName.length()-1);
    }
}
