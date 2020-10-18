/**
 *  
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * part Patch
 * 
 * @author  Chetan Laungani
 * @author  Habeeb Mohammed
 * assignment group: Group 120
 * 
 * assignment copyright Kees Huizing
 */
import javax.swing.*;
import java.awt.*;

class Patch extends JPanel{
    //...
    private boolean strategy;

    private double score;

    
    Patch(Color color) {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setColor(color);
    }

    public void setColor(Color color){
        this.setBackground(color);
    }


    // returns true if and only if patch is cooperating
    boolean isCooperating() {
        //...
      return strategy; 
    }
    
    // set strategy to C if isC is true and to D if false
    void setCooperating(boolean isC) {
        //...
        strategy = isC;
    }
    
    // change strategy from C to D and vice versa
    void toggleStrategy() {
        // ...
        strategy = !strategy;
    }

    // sets the score of this patch in current round
    void setScore(double score){
        this.score = score;
    }
    
    // return score of this patch in current round
    double getScore() {
        //...
        return score; 
    }

   
}


