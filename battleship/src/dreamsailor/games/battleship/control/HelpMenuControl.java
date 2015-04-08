/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamsailor.games.battleship.control;

/**
 *
 * @author katiewalker
 */
public class HelpMenuControl  {
    
    public HelpMenuControl() {
        
    } 

    public String displayBoardHelp() {
                       
        return "The game boards for Battleship consist of a grid of "
                + "locations 10 rows by 10 columns. There are two boards "
                + "one for the Ships and one for the Player to use to shoot. "
                +"\n\nPlayers place their ships on various locations on the Ship board "
                + "they use the Shot board to place shots against the other Players "
                +"ships.";
     
    }
   
        
    public String displayGameHelp() {

        return "The objective of the game is to be the first player to find "
                + "and sink the other player's Battleship."
                + "\n\nPlayers each take turns firing shots until one Battleship "
                + "is sunk.";
   
    }
            
    public String displayPlayerHelp() {

        return "The real player manually takes their turn by choosing a "
              + "location within the grid to attempt to hit and sink the other "
              + "player's ships, including the Battleship."
              + "\n\nThe computer based player automatically takes its turn "
              + "immediatly after a real player in the game."; 

    }
    
    public String displayWinHelp() {

        return "A player wins by sinking the opponent's Battleship. The Battleship "
              + "has 4 hits before it is sunk...thus it is sink or be sunk."; 
 
    }
                 
             
    public String displayShipHelp() {

        return "Player will choose a row and column (ex. A1, D5) "
             + "as well as the orientation of the ship down or to the right."; 

    }
                 
    public String displayHitHelp() {

        return"A symbol that \"marks\" the locations in the board that have "
                + "been hit or missed by a player. "
                + "\n\nThe default markers are \"H\" for hit, \"M\" for miss, "
                + "and \" \" (blank) for any untried areas."
                ; 
   
    }
    
        public String displayShotHelp() {

        return"Player will use their Shot board to place a shot looking for "
                + "the opponents Battleship. "
                + "\n\nPlayer will choose a row and column (ex. A1, D5) for each shot. "
                + "\n\nThe default markers will be displayed on your Shot board "
                + "are \"H\" for hit, \"M\" for miss, "
                + "and \" \" (blank) for any untried areas."
                ; 
   
    }
    
    
    public void displayHelpBorder() {       
        System.out.println(
        "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
  
}
