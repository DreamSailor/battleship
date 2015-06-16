/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamsailor.games.battleship.control;

import dreamsailor.games.battleship.exceptions.PlayerException;
import dreamsailor.games.battleship.exceptions.BoatHitException;
import dreamsailor.games.battleship.exceptions.BattleshipSunkException;
import dreamsailor.games.battleship.exceptions.BoatException;
import dreamsailor.games.battleship.exceptions.BoatSunkException;
import dreamsailor.games.battleship.game.Game;
import dreamsailor.games.battleship.game.Player;
import dreamsailor.games.battleship.ships.Boat;
import dreamsailor.games.battleship.view.GetLocationView;
import dreamsailor.games.battleship.view.Help;
import dreamsailor.games.battleship.view.PlaceShipMenu;
import dreamsailor.games.battleship.enums.PlayerType;
import dreamsailor.games.battleship.boards.ShotBoard;
import dreamsailor.games.battleship.boards.ShipBoard;
import dreamsailor.games.battleship.msgs.BattleshipError;
import java.awt.Point;


/**
 *
 * @author Jeffry Simpson, Vehikite-John - BYUI CIT260 Section 03
 */
public class GameMenuControl
{
    private Game game;
    private GetLocationView getLocationView;
    private Player player;
    private PlaceShipMenu placeShipMenu;

    public GameMenuControl(Game game) {
        this.game = game;
        this.getLocationView = new GetLocationView(game);
    }

    /* 3/7 Katie: Changed placeShips() for Lesson 8...change back after assignment completed.
    public void placeShips()
    {
       
        placeShipMenu = new PlaceShipMenu(game);
        placeShipMenu.getInput();
    
    }
    */
    
    public void placeShips()
    {
       
        placeShipMenu = new PlaceShipMenu(game);
        placeShipMenu.getInput();
    
    }

    public GetLocationView getGetLocationView() {
        return getLocationView;
    }

    public void setGetLocationView(GetLocationView getLocationView) {
        this.getLocationView = getLocationView;
    }

    public PlaceShipMenu getPlaceShipMenu() {
        return placeShipMenu;
    }

    public void setPlaceShipMenu(PlaceShipMenu placeShipMenu) {
        this.placeShipMenu = placeShipMenu;
    }
    
    
    /*
    
    New Method to control Firing a Shot with End of Game capcity.
    
    */
    
    public String fireControl()
    {
        String passBack = "F";  //Use this to pass back F or Q deplending if the battleship got sunk.
        
        if(this.game.currentPlayer.checkReadyToPlay())
        {
            try
            {
                fireAShot();
                this.game.switchPlayers();
            }catch (BattleshipSunkException bse)
            {
                BattleshipError.displayLine(game.otherPlayer.getName()+ bse.getMessage()); 
                game.endGame();
                displayStatistics();   //current player
                this.game.switchPlayers();
                displayStatistics();   //other player
                passBack = "Q";
            }
        }
        else
        {
            BattleshipError.displayError("You must place your ships before you fire a shot at your opponent!");
        }
        
        return passBack;
    }
    
    
    
    public int fireAShot() throws BattleshipSunkException
    {
        //ShipCodes errCode= ShipCodes.OK;   //Error value for shots
        ShipBoard otherBoatBoard = this.game.otherPlayer.boatBoard;
        ShotBoard thisShotBoard = this.game.currentPlayer.shotBoard;
      
        
        Point location; 
        int flag=0;
        int otherFlag=0;//flag to determine if FireAShot is successful
              
        do
        {  
            //Human or AI get shot location
            if(this.game.currentPlayer.getPlayerType() == PlayerType.HUMAN)
                location = getLocationView.getInput();  //input a coordinate for a shot 
            else
                location = thisShotBoard.fireShotAI();  //AI shot, using random coordinates
            
            //No location was returned, exit the method
            if (location == null) 
            { // no location was entered?    
                return -1;
            }
            
            flag = thisShotBoard.checkLocation(location);       //Set the shot in the grid, 2 = Miss
            
            String printShotLocation =((char) (location.x + 65) + "" + location.y);

            if( flag == 1 || flag == 2)  //Location already used
                BattleshipError.displayLine(this.game.currentPlayer.getName() + " you've already used " +  printShotLocation + " for a shot");  //2/20 Jeffry - Temp print out of location
            else
                BattleshipError.displayLine(this.game.currentPlayer.getName() + " fired a Shot at " +  printShotLocation);  //2/16 Jeffry - Temp print out of location
            

        }while(flag != 0);
        
        /* Katie 2/26/2015
        Added code to establish enhanced firing system for program.
        */

        //Check to see if the other player has a ship here.   Otherflag = 1 (Yes)  = 0 (No)
        otherFlag = otherBoatBoard.checkLocation(location);
        
        if(otherFlag != 0)  //Means there is a boat at this locaiton.
        {
            try {
                   
                
                    Boat hitBoat;  //new local varable to get ship information 

                    thisShotBoard.setHits(thisShotBoard.getHits()+1);
                    thisShotBoard.occupyLocation(location,1); //set to Hit on Shot Board

                    int typeShip = otherBoatBoard.checkLocation(location); //Get ship type location of coordinates
                    hitBoat = otherBoatBoard.getShip(this.game.otherPlayer, typeShip);
                    hitBoat.setHitDamage(hitBoat.getHitDamage()+1);  //Increase damage by one


                    hitBoat.hitOrSunk(hitBoat.getHitDamage(), hitBoat.getMaxDamage()); //calls method in boat.java   
                } 
            catch (BattleshipSunkException btse)
            {
              BattleshipError.displayLine(this.game.currentPlayer.getName() + " you won!"); 
              throw new BattleshipSunkException(btse.getMessage());
            }
            catch (BoatSunkException bse)
            {
              BattleshipError.displayLine(this.game.currentPlayer.getName() + " you sunk "
                      + this.game.otherPlayer.getName()+ "'s " + bse.getMessage()); 
            }
             catch (BoatHitException bse)
            {
              BattleshipError.displayLine(this.game.currentPlayer.getName() + " you hit "
                      + this.game.otherPlayer.getName()+ "'s " + bse.getMessage()); 
            }
            catch(BoatException be)
            {
                BattleshipError.displayLine(be.getMessage());
                
            }
            /*switch(errCode)   //Check if there is an error code need for when we know to end the game.
            {
                case OK:
                    break;
                default:
                    new ShotOutput().displayError(errCode);
            }*/
            
        }
        else{
            thisShotBoard.setMisses(thisShotBoard.getMisses()+1);
            thisShotBoard.occupyLocation(location,2); //set to Miss on Shot board
            BattleshipError.displayLine(this.game.currentPlayer.getName() + " Sorry your shot missed.");
            //this.game.switchPlayers(); //calls swtich player method in game.java

        }           

        return flag;
        
    }
  
    
   
    /*
    Description: Display available grid spaces for shots and calculate
    the total number of shots.
    
    Author(s): John Vehikite
    */
   
     public void displayBoard()
    {
 
        BattleshipError.displayLine("Displaying Your Shot  board");
        this.game.currentPlayer.shotBoard.display();
         BattleshipError.displayLine("\nDisplaying Your Ships ");
        this.game.currentPlayer.boatBoard.display();
    }
     
    public void startNewGame()
    {
         BattleshipError.displayLine("Starting a new game");
    }
   
    public void displayStatistics()
    {
        try{
         BattleshipError.displayLine("Display Statistics");
         //this.game.currentPlayer.sortScores();
         //this.game.currentPlayer.averageScores();
         //this.game.currentPlayer.highScoreNames();
         this.game.currentPlayer.getGameStats(this.game.currentPlayer.shotBoard.getHits(), this.game.currentPlayer.shotBoard.getMisses());
        }
        catch(PlayerException pe)
        {
            BattleshipError.displayLine(pe.getMessage());
        }
                 
    }
    
    public void displayPreferencesMenu()
    {
        //nothing here yet
    }

    public void displayHelpMenu() 
    {
        Help helpMenu = new Help();
        helpMenu.getInput();
    }
    
}
