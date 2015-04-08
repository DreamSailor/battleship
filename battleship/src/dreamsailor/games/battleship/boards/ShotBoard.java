/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamsailor.games.battleship.boards;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Jeffry Simpson - BYUI CIT260 Section 03
 */


public class ShotBoard extends Board
{
    private int hits;
    private int misses;
    
    public ShotBoard()
    {
        setHits(0);
        setMisses(0);
    }
    
     public int getHits()
    {
       
        return hits;
    }

    public void setHits(int hits)
    {
        this.hits = hits;
    }

    public int getMisses()
    {
        
        return misses;
    }

    public void setMisses(int misses)
    {
        this.misses = misses;
    }
  
        /*-------------------------------------------------------------------
    Description: Calculate hits and misses on shotBoard
    
    Author(s): John Vehikite\
    
    Note: I am not sure if this is the best name for this method.
    --------------------------------------------------------------------*/
    
    private void getHitMiss() {
        
        
        for(int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if(checkLocation(i,j) == 1)
                    hits++;
                if(checkLocation(i,j) == 2)
                    misses++;
            }
        }
    
    }
    
     public void availableShots() {
     
        // String objects can be dynamically added to an array list
        ArrayList<String> availableShots = new ArrayList<String>();
        
        String temp; // temporarily hold coordinate
        int totalSpaces = 0; // keep track of number of available coordinates
        String rowValues[] = {"A","B","C","D","E","F","G","H","I","J"};
        
        for(int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if(checkLocation(i,j) == 0) {
                    // temp will hold the oordinate string - ex: A 3
                    temp = rowValues[i] + ' ' + j; 
                    availableShots.add(temp); // add String to array list
                    totalSpaces++;
                }
            }
        }
        
        //The next block of code prints the array list and prints
        //a new line after every 10 coordinates are printed
        int count = 0; // keep track of how many coordinates there are printed on a line
        for(String temp2 : availableShots) { //for each loop
            System.out.print(temp2);
            System.out.print(", ");
            count++;
            if(count == 10) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println("Total spaces available: " + totalSpaces);
    }
    
      public Point fireShotAI()
    {
        Point location= null;
        int row, column;
      
        row = getRandom(getRows());       //Get Random Startrow
        column = getRandom(getCols());

        location = new Point(row, column);
        
        return location;
    }
    
      
    @Override
    public void display()
    {
        String marker;
        
        System.out.print("  0123456789\n------------\n");   //Print Row header
        for (int i = 0; i < getRows(); i++) 
        {
            System.out.print(getColStr(i) + " ");
            for (int j = 0; j < getCols(); j++) 
            {
                switch(checkLocation(i,j))
                {
                    case 1:
                        marker = "H";
                        break;
                    case 2:
                        marker = "M";
                        break;
                    case 3:
                        marker = "S";
                        break;
                    default:
                        marker = "0";
                                
                }

                System.out.print(marker);
            }
            
            System.out.println();
        }
    }
     
}
