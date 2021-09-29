/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication41;

/**
 *
 * @author Tatheer Zahra
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import sun.audio.*;
import java.io.*;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tatheer Zahra
 */
public class Main{
    public static void main(String[] args) {
        // TODO code application logic here
         PianoTiles gamePlay =new PianoTiles(); //Object of our main class 
        gamePlay.drum("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\au final\\Jazz-Funk-Drum-au.AU");
        
        String Name =
        JOptionPane.showInputDialog( "Enter Name: " );
        JOptionPane.showMessageDialog( null, "Hello! " + Name+" Best of luck for the game\nChoose the music of your own choice:\nenter shape for *shape of you*\nenter faded for *faded*\nenter got for *GOT* music\nenter mash for a mashup\nenter potter for *harry potter theme*","Welcome to Piano Tiles",JOptionPane.PLAIN_MESSAGE );
        String choice= JOptionPane.showInputDialog( "Enter your song choice: " );
          
        // music 1 will play other music
        
        if(choice.equals("shape")){
          
         gamePlay.music("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\au final\\pianoshape.AU");
        }
        else if(choice.equals("faded")){
            gamePlay.music("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\au final\\fadeddd.AU");
        }
        else if(choice.equals("got")){
            gamePlay.music("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\au final\\got.AU");
        }
        else if(choice.equals("mash")){
           
         gamePlay.music("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\au final\\mash.AU");
        }
        else if(choice.equals("potter")){
          gamePlay.music("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\au final\\potter.AU");
        }
       

            JFrame frame=new JFrame(); //Object of JFrame
            frame.setSize(500,700); // width is 500 because one tile is 125 so 4*125=500, similary height is 700 because one tile is of 175 height 
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setTitle("Piano Tiles");
            frame.add(gamePlay); // Object of our main class is added 
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
         
         
         
    }
   
}
class PianoTiles extends JPanel implements ActionListener, MouseListener, KeyListener{
    /**
     * @param args the command line arguments
     */
         Tiles[][] tile=new Tiles[5][4]; //A 2-D array of objects is created in which every object indicates a single tile 
        //Initialization of varaibles  
        public int scores=0; // public because we've to call it in another class
        public static int Tile_width=125; // constant because Tile widght will always remain the same
        public static int Tile_height= 175;
        public boolean Play= false; //A varaible that determines when the game will start
        public boolean DisplayScore= true;// A variable that determines when to show scores 
        private int Level=1; //Level counter
        Image image;
        
        //Instantiation of different timers for different levels
        private Timer timer;
        private Timer timer2=new Timer(18,this);
        private Timer timer3=new Timer(15,this);
        private Timer timer4=new Timer(12,this);
        private Timer timer5=new Timer(10,this);
        private Timer timer6=new Timer(9,this);
        
         // the following 4 statements are to be used by both music and stop methods
        public String a;
        public String b;
       // the following 4 statements are to be used by both music and stop methods
        AudioPlayer play = AudioPlayer.player;
        AudioStream AS;
        AudioData AD;
        ContinuousAudioDataStream loop = null;
     
        
        // the following 4 statements are for drum and stopdrum methods
        AudioPlayer playdrum = AudioPlayer.player;
        AudioStream AS1;
        AudioData AD1;
        ContinuousAudioDataStream loop1 = null;
        
        //DEFAULT CONSTRUCTOR 
        PianoTiles(){
            
          addKeyListener(this);
          addMouseListener(this);
          setFocusable(true);
          timer = new Timer(20, this);
          game();
          timer.start();
       
        }//End of default constructor 
    

//FUNCTIONS THAT ARE USED TO RUN THE GAME
      
        public void game(){ //START of method GAME
          
       int tempX=0; //Its stores the starting x position of a tile
       int tempY=525; //Its stores the starting x position of a tile
       int tempRandom; //Variable to save random numbers
       
       for(int y = 0; y<5; y++){  
           
           
             tempRandom = Randomnum(); //A random number is generated that determines which tile will be black in a row
            
            for(int x = 0; x<4; x++){  // START of nested for 
                  
                tile[y][x] = new Tiles(tempX, tempY); //A tile is created 
                
                
                if(x == tempRandom){
                    tile[y][x].turnBlack(); //if tile number is equal to random number then change its colour to black
                    if(y==4&&Level>1){
                        tile[y][x].checkSarah();
                        image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\FinalGif.gif");
                        
                    }else{
                                  tile[y][x].NotSarah();
                    }
                    
                }
                else{
                    tile[y][x].turnWhite(); //else change it to white 
                    tile[y][x].NotSarah();
                }
                
                
                tempX+=Tile_width;
            }
            
            tempY-=Tile_height;
            tempX = 0;
            
        } //END OF NESTED FOR 
      } //END OF METHOD GAME
      
      
      public int Randomnum(){ //RANDOM NUMBER METHOD THAT DETERMINES RANDOM NUMBER
        Random rand=new Random();
        return rand.nextInt(4);
    }
     //method to play user choice music
      public void music(String x){
        
        try
        {
            InputStream IS = new FileInputStream(x);
            AS= new AudioStream(IS);
            AudioPlayer.player.start(AS);
          
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        play.start(loop);
        a=x;
      
    }
      //method that will stop music 1 method
 public void stop(){
     
      AudioPlayer.player.stop(AS);
      play.stop(loop);
 }
      public  void drum(String x){
      
        try
        {
            InputStream IS1 = new FileInputStream(x);
            AS1 = new AudioStream(IS1);
            AudioPlayer.player.start(AS1);
          
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        playdrum.start(loop1);
        b=x;
        }
      public void stopdrum(){
     
      AudioPlayer.player.stop(AS1);
      playdrum.stop(loop1);
 }
 
    
    @Override
    public void paintComponent(Graphics g){ //JPanel method is overriden 
        
        super.paintComponent(g);
        //First Screen formatting 
        g.setColor(Color.BLACK); //Background is set to black 
        g.fillRect(0, 0, 700,700);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", 1, 50));
        g.drawString("PIANO TILES", 100, 100);
        g.drawString("INSTRUCTIONS:",10,150);
       
        g.setFont(new Font("Times New Roman", 1, 20));
        g.drawString("Enter Left Arrow Key to Start the game",10,300);
        g.drawString("Enter Upward Key to exit the game",10,350);
        g.setFont(new Font("Times New Roman", 1, 30));
        g.setColor(Color.WHITE);
        g.drawString("\nGood Choice Of Music!",10,400);
        g.setFont(new Font("Times New Roman", 1, 20));
        g.setColor(Color.PINK);
        g.drawString("\nEnjoy the game with background music that will ",10,455);
        g.drawString(" not only get you in the mood but also get you energetic",10,500);
        //End of first screen formatting 
        
        g.setFont(new Font("Times New Roman", 1, 50));
       
        //Start of if conditions 
        
       if(DisplayScore&&Play){ //This will work only if Play and DisplayScore are true
            
           for(int y = 0; y<5;y++){ //START OF NESTED FOR BECAUSE 2-D ARRAY IS USED 
             
               for(int x = 0; x<4; x++){
               
                   //FOR BLACK TILE
                   g.setColor(tile[y][x].activated ? Color.BLACK : Color.WHITE); //activated varaible is true when it is black
                   g.fillRect(tile[y][x].xPos, tile[y][x].yPos, Tile_width, Tile_height); // if activated then draw a black tile otherwise draw a white tile 
               
                   //FOR WHITE TILE
                   g.setColor(tile[y][x].activated ? Color.WHITE : Color.BLACK);
                   g.drawRect(tile[y][x].xPos, tile[y][x].yPos, Tile_width, Tile_height); 
                   
                   if (image != null&&tile[y][x].Sarah){
        
                     g.drawImage(image, tile[y][x].xPos,tile[y][x].yPos, this);
                   }
               }
           }
            //DISPLAY SCORES 
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", 1, 80));
	    g.drawString(String.valueOf(scores), 225, 100);
            
            //DISPLAY LEVEL NUMBER 
            g.setFont(new Font("Times New Roman", 1, 30));
            g.drawString("Level: ", 10, 100);
            g.drawString(String.valueOf(Level), 100, 100);
           } //END OF IF 
           else if(!DisplayScore){ //START OF ELSE IF (IT WORKS WHEN GAME ENDS ONLY 
                
                  stopdrum(); //will stop drum music
                  stop(); // will stop user's choice music
                //WHITE BACKGROUD 
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 700,700);
                
                //COLOUR FOR FONT 
                g.setColor(Color.RED);
		g.drawString("Game Over!", 100, Tile_height);
                
                
                g.setColor(Color.WHITE);
		g.drawString("SCORES:"+String.valueOf(scores), 100, 500);
                
                
                g.setFont(new Font("Times New Roman", 1, 20));
                g.drawString("Enter Right Arrow Key to restart",10,550);
                
           }
            
        
        
        
}       
    
    @Override
    public void actionPerformed(ActionEvent ae) { //A method from interface 
        
        //Initialization of varaibles 
         int rand,tempx,tempy;
     
         
         if(Play){ //Starts when game starts 
             for(int y = 0; y<5; y++){//Start of nested for 
                 
                 rand=Randomnum(); // a random number is again generated 
                 
                 for(int x = 0; x<4; x++){ //Start of inner for 
                     
                     tile[y][x].yPos+=6; //The speed of flow of tile 
                     repaint();
                     
                     
                        if(tile[y][x].yPos>700){ //This causes the 4 by 4 array to move in an infinite loop 
                          
                               tile[y][x].yPos=-168;
                               if(x==rand){
                                   tile[y][x].turnBlack(); //The same process of deteminination of  colour is repeated 
                                   if(y==4&&Level>1){
                                       tile[y][x].checkSarah();
                                       image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Tatheer Zahra\\Desktop\\Courses\\OOP\\FinalGif.gif");
                                   }else{
                                  tile[y][x].NotSarah();     
                                   }
                         }
                         else{
                             tile[y][x].turnWhite();
                             tile[y][x].NotSarah();
                         }//End of internal if 
                         repaint(); // repaints 
                         
                     }
                 }//End of internal for loop 
       }//END of external for loop 
             
     }//End of main if 
         else if(!DisplayScore&&!Play){
               stopdrum(); //will stop drum music
               stop(); // will stop user's choice music
               
        
    }
         
    }//END OF METHOD ACTION PERFORMED 
               
      
    @Override
    public void mouseClicked(MouseEvent me) {} //This method is not used 
    
    @Override
    public void mousePressed(MouseEvent e) { //This method is used to check when mouse is clicked
        
        //Initialization 
        //Variables that store the x position and y position of where the mouse is clicked 
        int clickX;
        int clickY;
        clickX=e.getX();
        clickY=e.getY();
        
        
        int xTile=0;
        int yTile=0; 
        
        for(int y = 0; y<5; y++){ //Start of nested for 
             for(int x = 0; x<4; x++){ //Start of inner for 
                 
                 
                 if(tile[y][x].activated){ //This if works only when the tile is black 
                         
                         xTile=x*125; //Stores the width of the black tile 
                         yTile=tile[y][x].yPos; //Stored starting y position of the black tile 
                         
                         if(clickX>= xTile && clickX<=(xTile+125) && clickY>=yTile && clickY<=(yTile+175)){ //internal if that is activated only when we clcik the black til
                             
                             scores+=10; // if the user clicks the black tile then an increment of 10 is made in scores
                              if(tile[y][x].Sarah){
                                 
                                 scores+=10;
                             }
                             if(scores==50){
                                 System.out.println("hi");
                                 timer.stop(); //if score is 50 then the timer of first level is stopped and second level starts  
                                 Level+=1;
                                 timer2.start();
                          }//end of if of level 1
                         else if(scores==150){
                             
                             timer2.stop(); //Level 2 ends and level 3 begins 
                             Level+=1;
                             timer3.start();
                              
                          } //end of if level 2 if 
                          else if(scores==200){
                              
                              timer3.stop();
                              Level+=1;
                              timer4.start();
                              
                          } // end of if of level 3
                          else if(scores==350){
                              
                              timer4.stop();
                              Level+=1;
                              timer5.start();
                              
                          }
                          else if(scores== 600){
                              timer5.stop();
                              Level+=1;
                              timer6.start();
                          }// end of if of level 5
                          
                     }//End of if of black tile
                 }
                 else if(!tile[y][x].activated){//Start of if of white tile, this is true for white tiles only 
                          
                     //Storing x and y position of tiles 
                          xTile=x*125;
                          yTile=tile[y][x].yPos;
                     
                         if(clickX>= xTile && clickX<=(xTile+125) && clickY>=yTile && clickY<=(yTile+175)){//this if is true when white tile is clicked
                             
                             repaint();
                             tile[y][x].NotSarah();
                             DisplayScore=false;//game ends when white tile is clicked 
                             Play=false; //scores are displayed 
                         
                         }//end of internal if
                 } //end of white if
             } //END OF INTERNAL FOR 
        }//END OF EXTERNAL FOR 
    }// END OF METHOD MOUSE PRESSED 
                     
                    
             
             
     @Override
    public void mouseReleased(MouseEvent me) {
    }
    @Override
    public void mouseEntered(MouseEvent me) {
    }
    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) { //This method handles different keys when the user presses them 
        
        int key = e.getKeyCode(); //stores the key that is pressed
        if(key == KeyEvent.VK_LEFT){
            
            Play=true; //if left key is pressed then game starts 
            scores=0;
            Level=1;
            
        }
        else if (key == KeyEvent.VK_RIGHT){
            //Game is replayed 
        drum(b);
        music(a);
        timer2.stop();
        timer3.stop();
        timer4.stop();
        timer5.stop();
        timer6.stop();
        
        Play=true;
        DisplayScore=true;
        scores=0;
        Level=1;
        Play=true;
        DisplayScore=true;
        scores=0;
        Level=1;
        
        timer.start();
        
        }else if (key == KeyEvent.VK_UP){ //game ends 
      System.exit(0);
        }
    }//END OF METHOD KEYPRESSED 
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    
} //END OF CLASS PIANO TILES 
  
class Tiles{ //Start of class tiles that determines x, y position of a tile and its colour 
    
     //Initialization 
     int xPos; //Starting x position 
     int yPos; //Starting y position 
     
     public boolean activated= false; // a variable that is true when the tile is black and false when it is white 
     public boolean Sarah=false;
     
     //Constructor of Tiles 
     Tiles(int x,int y){
         
         this.xPos=x;
          this.yPos=y;
          
     }//End of constructor 
     
     //METHODS OF CLASS TILES 
     
     //Setters 
     public void setY(int y){
         yPos= y;
     }
     public void setX(int x){
         xPos= x;
     }
     
     //Getters 
     public int getY(int y){
         return yPos;
     }
     public int getX(int x){
         return xPos;
     }
     
     //Methods that determine the colour of a tile 
    
     
     public void turnBlack(){ //for black tile 
            activated=true;
        }
     
     public void turnWhite(){ //for white tile 
           activated=false;
        }
     
     //Methods for Sarah Tile
     
     
     public void checkSarah(){ //for sarah tile 
           Sarah=true;
        }
     
     public void NotSarah(){ //not for sarah tile 
           Sarah=false;
        }
}