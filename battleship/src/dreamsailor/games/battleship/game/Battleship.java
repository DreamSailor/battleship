package dreamsailor.games.battleship.game;

import dreamsailor.games.battleship.msgs.BattleshipError;
import dreamsailor.games.battleship.view.Menu;
import dreamsailor.games.battleship.frames.*;


//import battleship.Game;





/**
 *
 * @author jeremyrklein
 */


public class Battleship {
    
    private static MainFrame mainFrame=null;
    String welcome = 
              "\n\t***********************************************************************"
            + "\n\t* Prepare your fleet. A battle is about to begin!                     *"                            
            + "\n\t* You will be playing against the computer. The object of the game    *"
            + "\n\t* is to find and destroy your opponent's Battleship before yours is   *"
            + "\n\t* destroyed.                                                          *" 
            + "\n\t*                                                                     *"
            + "\n\t* Good Luck!!!!                                                       *"
            + "\n\t***********************************************************************"
            + "\n";

    
    public Battleship() {
        
        //Emty Constructor
    }
    
    
        
    public static void main(String[] args) 
    {   
        Battleship battleship = null;
        try {
        //Text based
//        Battleship battleship = new Battleship();
//        battleship.display();     
//        Menu mainMenu = new Menu();
//        mainMenu.getInput();
        
        //Gui based
        java.awt.EventQueue.invokeLater(new Runnable()
        {
                @Override
                public void run()
                {
                    Battleship.mainFrame = new MainFrame();
                    Battleship.mainFrame.setVisible(true);
                }
        });
        
        
            } catch(Throwable ex)
            {
            BattleshipError.displayLine("Unexpected error: " + ex.getMessage());
            BattleshipError.displayLine(ex.getStackTrace().toString());
            }
              finally
        {
        BattleshipError.displayLine("Dust thou art, unto dust thou shalt become");
        }
    }
    
        private void display() {
        System.out.println(this.welcome);
        
        
    }
    
}   
