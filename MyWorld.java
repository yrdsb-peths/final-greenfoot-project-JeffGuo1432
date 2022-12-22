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
    Tile[] borderMap = {new Tile("wallR",24,19),new Tile("wallR",15,52),new Tile("wallR",15,146),new Tile("wallR",18,182),new Tile("wallR",21,225),new Tile("wallR",26,253),new Tile("wallR",18,297),new Tile("wallR",17,339),new Tile("wallD",71,25),new Tile("wallD",105,19),new Tile("wallD",145,20),new Tile("wallD",194,23),new Tile("wallR",18,105),new Tile("wallD",224,23),new Tile("wallD",267,22),new Tile("wallD",309,22),new Tile("wallD",346,23),new Tile("wallD",392,24),new Tile("wallD",421,22),new Tile("wallD",460,22),new Tile("wallD",504,22),new Tile("wallD",534,22),new Tile("wallL",580,62),new Tile("wallL",588,103),new Tile("wallL",581,142),new Tile("wallL",582,191),new Tile("wallL",590,224),new Tile("wallL",577,259),new Tile("wallL",583,296),new Tile("wallL",593,332),new Tile("wallL",592,19),new Tile("wallCornerUR",33,378),new Tile("wallU",72,378),new Tile("wallU",98,378),new Tile("wallU",133,382),new Tile("wallU",174,383),new Tile("wallU",218,384),new Tile("wallU",251,385),new Tile("wallU",299,381),new Tile("wallU",342,383),new Tile("wallU",384,383),new Tile("wallU",421,386),new Tile("wallU",461,388),new Tile("wallU",500,387),new Tile("wallU",536,386),new Tile("wallCornerUL",572,385)};
    Tile[] level1Map = {

new Tile("wallR",182,344),
new Tile("wallR",176,298),
new Tile("wallR",176,260),
new Tile("wallR",180,218),
new Tile("wallR",176,183),
new Tile("wallR",334,60),
new Tile("wallR",332,94),
new Tile("wallR",334,138),
new Tile("wallR",333,172),
new Tile("wallR",336,211),
new Tile("wallR",497,353),
new Tile("wallR",493,301),
new Tile("wallR",493,269),
new Tile("wallR",495,225),
new Tile("wallR",495,181),
new Tile("wallL",142,343),
new Tile("wallL",142,302),
new Tile("wallL",143,251),
new Tile("wallL",140,221),
new Tile("wallL",137,178),
new Tile("wallL",299,215),
new Tile("wallL",298,182),
new Tile("wallL",300,141),
new Tile("wallL",298,108),
new Tile("wallL",299,53),
new Tile("wallL",467,340),
new Tile("wallL",458,296),
new Tile("wallL",458,253),
new Tile("wallL",457,221),
new Tile("wallL",451,176),
new Tile("wallUR",183,141),
new Tile("wallUR",494,144),
new Tile("wallD",298,244),
new Tile("wallD",340,245),
new Tile("wallUL",136,136),
new Tile("wallUL",463,146),
new Tile("wallL",302,24),
new Tile("wallR",342,26),};
Tile[] gabrielsMap={
    new Tile("wallD",44,135),
new Tile("wallD",93,138),
new Tile("wallD",138,138),
new Tile("wallD",179,179),
new Tile("wallD",219,213),
new Tile("wallD",204,56),
new Tile("wallD",259,102),
new Tile("wallD",293,143),
new Tile("wallD",338,184),
new Tile("wallD",218,268),
new Tile("wallD",214,293),
new Tile("wallD",388,174),
new Tile("wallD",413,169),
new Tile("wallD",420,148),
new Tile("wallD",450,177),
new Tile("wallD",492,137),
new Tile("wallD",457,141),
new Tile("wallD",387,139),
new Tile("wallD",343,148),
new Tile("wallD",494,219),
new Tile("wallD",452,256),
new Tile("wallD",354,297),
new Tile("wallD",50,293),
new Tile("wallD",102,288),
new Tile("wallD",131,298),

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
        
        
        
        spawnGoblin(500,Greenfoot.getRandomNumber(300+50));
        
        
        

        
        
        
        
    
        
        spawnMap(borderMap);
        addObject(new CoinUI(),20,30);
        //spawnMap(level1Map);
    }
    
    public void act(){
        String tileName="wallR";
        
        if(Greenfoot.mouseClicked(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            
            System.out.println("new Tile(\""+tileName+"\","+mouse.getX()+","+mouse.getY()+"),");
            addObject(new Tile(tileName,mouse.getX(),mouse.getY()),mouse.getX(),mouse.getY());
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
    public void spawnGoblin(int x, int y)
    {
        addObject(new Goblin(),x,y);
    }
    public void spawnCoin(int x, int y)
    {
        addObject(new Coin(), x, y);
    }
    public static String getTileDirection(Tile t)
    {
        return t.tileName.substring(t.tileName.length()-1);
    }
}
