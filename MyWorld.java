import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.List;
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
    static int worldWidth=400;
    int health = 5;
    int maxHealth=5;
    int coins =0;
    int level =0;
    int volume=0;
    public String tileName;
    static boolean doorsRemoved = false;
    Color coinsColor = new Color(255,200,126);
    public Label coinsLabel = new Label(coins,30);
    GreenfootSound music = new GreenfootSound("sounds/jeff_video_game_INTRO.mp3");
    GreenfootSound musicMain = new GreenfootSound("sounds/jeff_video_game_MAIN.mp3");
    SimpleTimer musicTimer = new SimpleTimer();
    Label instructions1 = new Label("",30);
            Label instructions2 = new Label("",30);
    Tile[] level1Map = {
    new Tile("wallR",24,19),new Tile("wallR",15,52),new Tile("wallR",15,146),new Tile("wallR",26,253),
    new Tile("wallR",18,297),new Tile("wallR",17,339),new Tile("wallD",71,25),new Tile("wallD",105,19),
    new Tile("wallD",145,20),new Tile("wallD",194,23),new Tile("wallR",18,105),new Tile("wallD",224,23),
    new Tile("wallD",267,22),new Tile("wallD",309,22),new Tile("wallD",346,23),new Tile("wallD",392,24),
    new Tile("wallD",421,22),new Tile("wallD",460,22),new Tile("wallD",504,22),new Tile("wallD",534,22),
    new Tile("wallL",580,62),new Tile("wallL",588,103),new Tile("wallL",581,142),new Tile("wallL",577,259),
    new Tile("wallL",583,296),new Tile("wallL",593,332),new Tile("wallL",592,19),new Tile("wallCornerUR",33,378),
    new Tile("wallU",72,378),new Tile("wallU",98,378),new Tile("wallU",133,382),new Tile("wallU",174,383),
    new Tile("wallU",218,384),new Tile("wallU",251,385),new Tile("wallU",299,381),new Tile("wallU",342,383),
    new Tile("wallU",384,383),new Tile("wallU",421,386),new Tile("wallU",461,388),new Tile("wallU",500,387),
    new Tile("wallU",536,386),new Tile("wallCornerUL",572,385),new Tile("wallUL",576,263),new Tile("wallD",577,132),
    new Tile("wallD",16,147),new Tile("wallUR",20,259),new Tile("door2LR",582,168),new Tile("door2LR",583,213),};
    
    Tile[] level0Map = {
    new Tile("wallR",24,19),new Tile("wallR",15,52),new Tile("wallR",15,146),new Tile("wallR",26,253),
    new Tile("wallR",18,297),new Tile("wallR",17,339),new Tile("wallD",71,25),new Tile("wallD",105,19),
    new Tile("wallD",145,20),new Tile("wallD",194,23),new Tile("wallR",18,105),new Tile("wallD",224,23),
    new Tile("wallD",267,22),new Tile("wallD",309,22),new Tile("wallD",346,23),new Tile("wallD",392,24),
    new Tile("wallD",421,22),new Tile("wallD",460,22),new Tile("wallD",504,22),new Tile("wallD",534,22),
    new Tile("wallL",580,62),new Tile("wallL",588,103),new Tile("wallL",581,142),new Tile("wallL",577,259),
    new Tile("wallL",583,296),new Tile("wallL",593,332),new Tile("wallL",592,19),new Tile("wallCornerUR",33,378),
    new Tile("wallU",72,378),new Tile("wallU",98,378),new Tile("wallU",133,382),new Tile("wallU",174,383),
    new Tile("wallU",218,384),new Tile("wallU",251,385),new Tile("wallU",299,381),new Tile("wallU",342,383),
    new Tile("wallU",384,383),new Tile("wallU",421,386),new Tile("wallU",461,388),new Tile("wallU",500,387),
    new Tile("wallU",536,386),new Tile("wallCornerUL",572,385),new Tile("wallUL",576,263),new Tile("wallD",577,132),
    new Tile("wallR",16,147),new Tile("wallR",20,259),new Tile("wallR",18,176),new Tile("wallR",20,222),
/**new Tile("wallR",221,34),
new Tile("wallR",227,72),
new Tile("wallR",223,103),
/**new Tile("wallR",220,136),
/**new Tile("wallR",220,179),
/**new Tile("wallR",218,218),
new Tile("wallR",220,256),
new Tile("wallR",220,290),
new Tile("wallR",218,338),
new Tile("wallL",181,30),
new Tile("wallL",180,51),
new Tile("wallL",182,95),
/**new Tile("wallL",184,134),
new Tile("wallL",182,180),
new Tile("wallL",179,213),
new Tile("wallL",182,253),
new Tile("wallL",182,287),
new Tile("wallL",184,329),
new Tile("wallD",167,132),
new Tile("wallD",222,142),
new Tile("wallUL",175,263),
new Tile("wallUR",217,262),
new Tile("wallCornerUR",227,375),
new Tile("wallCornerUL",182,377),
new Tile("door2LR",216,170),
new Tile("door2LR",221,209),
new Tile("door2LR",188,175),
new Tile("door2LR",183,218),*/};

    Tile[][]mapArray={level0Map,level1Map,level1Map,level1Map,level1Map,level1Map,level1Map,level1Map};
    
    Enemy[]level0Army={};
    Enemy[]level1Army={new Skeleton(),new Skeleton(),new Skeleton(),new Skeleton()};
    Enemy[]level2Army={new Goblin(), new Goblin(), new Skeleton(), new Skeleton()};
    Enemy[]level3Army={new Goblin(), new Goblin(), new Skeleton(), new Skeleton(), new Skeleton(), new Skeleton(), new Skeleton(), new Skeleton(), new Skeleton(), new Skeleton()};
    Enemy[]level4Army={new Goblin(), new Goblin(),new GoblinKing(), new Skeleton(), new Skeleton()};
    Enemy[][]armyArray={level0Army,level1Army,level2Army,level3Army,level4Army};
   
    
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(worldHeight, worldWidth, 1, false); 
        doorsRemoved=false;
        
        
        volume=25;
        Hero hero = new Hero();
        addObject(hero,300,200);
        
        addObject(new AxeHitbox(),60,200);
        addObject(new Axe(),70,200);
        
        
        musicTimer.mark();
        
        addObject(new CoinUI(),580,30);
        
        addObject(coinsLabel, 550,30);
        coinsLabel.setFillColor(coinsColor);
        updateHealth();
        spawnArmy(level);
        spawnMap(level);
        //armyArray[level].set(1, new Nothing());
    }
    public void act(){
        if(level==0&getObjects(Label.class).size()<5){
            instructions1.setValue("Press space to meleee attack");
            instructions2.setValue("Press q and arrow keys to ranged attack");
            
            addObject(instructions1,300,150);
            addObject(instructions2,300,250);
        }
        if(level>0){
            
            
            instructions1.setValue("");
            instructions2.setValue("");
        }
        if(musicTimer.millisElapsed()>16000){
            musicMain.play();
        }
        else{
            music.play();
        }
        music.setVolume(volume);
        musicMain.setVolume(volume);
        //Whenever the mouse button is clicked, a tile with the name of tileName is spawned on the location of the mouse
        if ( Greenfoot.isKeyDown ("=")&volume<100 ){
            
            volume++;
            
        }
        if ( Greenfoot.isKeyDown ("-")&volume>0 ) {
            volume--;
        } 
        
        if(Greenfoot.mouseClicked(null)){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            addObject(new Tile(tileName,mouse.getX(),mouse.getY()),mouse.getX(),mouse.getY());
            //When a tile is created, the code prints out "newTile(tileName,x,y)" which I can then easily 
            //copy and paste into the arrays above
            System.out.println("new Tile(\""+tileName+"\","+mouse.getX()+","+mouse.getY()+"),");
        }
        if(Greenfoot.isKeyDown("p")){
            spawnSkeletonScreen();
            health=100;
            maxHealth=100;
            updateHealth();
        }
        if(health==0){
            removeObjects(getObjects(Enemy.class));
        }
        if(level>0&getObjects(Enemy.class).size()<=0){
            
            doorsRemoved=true;
        }
        else if(level>0){
            doorsRemoved=false;
        }
        //if(level==0&getObjects(Demon.class).size()==0){
         //   addObject(new Demon(),260,190);
        //}
        
        
    }
    public void setDoors(boolean bool){
        doorsRemoved=bool;
    }
    public static boolean doorsRemoved(){
       return doorsRemoved;
    }
    public void levelUp(){
        level++;
        
        spawnArmy(level);
        spawnMap(level);
    }
    public void levelDown(){
        
        level--;
        spawnArmy(level);
        spawnMap(level);
    }
    public void spawnArmy(int level){
        
        
        removeObjects(getObjects(Enemy.class));
        for(int i = 0 ; i < armyArray[level].length ; i++){
            
            spawnRandomInArea(armyArray[level][i],100,50,550,350);
            
            armyArray[level][i].setEnemyNumber(i);
        }
    }
    public void spawnMap(int level){
        //Loops through the entire map array, adding every tileObject[i]
        removeObjects(getObjects(Tile.class));
        for(int i = 0 ; i < mapArray[level].length ; i++){
            
            addObject(mapArray[level][i],0,0);
        }
    }
    public int getRandomNum(int x, int y){
        return Greenfoot.getRandomNumber(y-x)+x;
    }
    public void spawnRandomInArea(Enemy c, int x1, int y1, int x2, int y2){
        addObject(c,getRandomNum(x1,x2),getRandomNum(y1,y2));
    }    
    public void increaseCoins(){
        coins++;
        coinsLabel.setValue(coins);

    }
    public void decreaseCoins(int amount){
        coins-=amount;
        coinsLabel.setValue(coins);

    }
    public void increaseHealth(){
        health++;
        updateHealth();
    }   
    public void decreaseHealth(){
        health--;
        new GreenfootSound("sounds/Hit_/PlayerHurt_0.mp3").play();
        updateHealth();
    }
    public void updateHealth(){
        removeObjects(getObjects(HeartUI.class));
        for(int i = 0 ; i <maxHealth ; i++){
            if(i<health){
                addObject(new HeartUI("images/heart0.png"),40*i+35,30);
            }
            else{
                addObject(new HeartUI("images/heart1.png"),40*i+35,30);
            }
        }
    }
    public void spawnSkeletonScreen(){
        removeObjects(getObjects(Skeleton.class));
        for(int x = 50 ; x <= 550 ; x = x + 20){
            for(int y = 50 ; y <= 350 ; y = y +20){
                spawnGoblin(x,y);
            }
        }
    }
    public void spawnGoblinGang(int xPos, int yPos){
        for(int x = 0 ; x <= 100 ; x = x + 20){
            for(int y = 0 ; y <= 100 ; y = y +20){
                spawnGoblin(x+xPos,y+yPos);
            }
        }
    }
    public void spawnEnemies(Enemy[] e){
        for(int i = 0 ; i < e.length ; i++){
            addObject(e[i],0,0);
        }
    }
    
    public void spawnSkeleton(int x, int y)
    {
        //Spawns a skeleton at x y
        addObject(new Skeleton(),x,y);
    }
    public void spawnGoblin(int x, int y)
    {
        //Spawns a goblin at x y
        addObject(new Goblin(),x,y);
    }
    public void spawnCoin(int x, int y)
    {
        //Spawns a coin at x y
        addObject(new Coin(), x, y);
    }
    public static String getTileDirection(Tile t)
    {
        //This returns the tileDirection, I will use this in the code for the hitboxes, since each tiles hitbox 
        //varies based on its direction
        return t.tileName.substring(t.tileName.length()-1);
    }
}
