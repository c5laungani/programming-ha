/*
 * part of HA Random artist
 * TO BE COMPLETED
 *
 * @author huub
 * @author kees
 * @name(s) //Habeeb Mohammed and Chetan Laungani
 * @id(s)   // 1582143 and 1562274
 * @group   //Group 120
 * @date    //7th October 2020
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Painting extends JPanel implements ActionListener {

   /*---- Randomness ----*/
    /** you can change the variable SEED if you like
     *  the same program with the same SEED will generate exactly the same sequence of pictures
     */
    final static long SEED = 37; // seed for the random number generator; any number works

    /** do not change the following two lines **/
    final static Random random = new Random( SEED ); // random generator to be used by all classes
    int numberOfRegenerates = 0;

   /*---- Screenshots ----
    * do not change
    */
    char current = '0';
    String filename = "randomshot_"; // prefix
    
   /*---- Dinguses ----*/
    ArrayList<Dingus> shapes = new ArrayList<Dingus>();
   
    //...

    public Painting() {
        setPreferredSize(new Dimension(800, 450)); // make panel 800 by 450 pixels.
        //...
        setBackground(Color.PINK);
    }

    @Override
    protected void paintComponent(Graphics g) { // draw all your shapes
        super.paintComponent(g);     // clears the panel
        // draw all shapes
        // ...
        for (int x = 0; x < shapes.size(); x++){
            shapes.get(x).draw(g);
        }
    }

    /**
     * reaction to button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( "Regenerate".equals(e.getActionCommand()) ) {
            regenerate();
            repaint();
        } else { // screenshot
            saveScreenshot( this, filename+current++ ); // ++ to show of compact code :-)
        }
    }

    void regenerate() {
        numberOfRegenerates++; // do not change
        
        // clear the shapes list
        // ...
        shapes.clear();

        // create random shapes
        // ...
        int randomShapeAmount = random.nextInt(4 - 2) + 2; //determines how many shapes will be created
        for (int a = 0; a <= randomShapeAmount; a++){
        shapes.add(new CircleDingus(800,450));
        shapes.add(new TreeDingus(800,450));
        shapes.add(new HexagonDingus(800,450));
        shapes.add(new OctagonDingus(800, 450));
        shapes.add(new DecagonDingus(800, 450));
        }

        shapes.add(new CircleSpiralDingus(800,450));
        

    }

    /** 
     * saves a screenshot of a Component on disk
     *  overides existing files
     *
     * @param component - Component to be saved
     * @param name - filename of the screenshot, followed by a sequence number
     * 
     * do not change
     */
    void saveScreenshot(Component component, String name) {
        String randomInfo = ""+SEED+"+"+ (numberOfRegenerates-1); //minus 1 because the initial picture should not count
        System.out.println( SwingUtilities.isEventDispatchThread() );
        BufferedImage image =
            new BufferedImage(
                              component.getWidth(),
                              component.getHeight(),
                              BufferedImage.TYPE_INT_RGB
                             );
        // call the Component's paint method, using
        // the Graphics object of the image.
        Graphics graphics = image.getGraphics();
        component.paint( graphics ); // alternately use .printAll(..)
        graphics.drawString(randomInfo, 0, component.getHeight());
        
        try {
            ImageIO.write(image, "PNG", new File(name+".png"));
            System.out.println( "Saved screenshot as "+ name );
        } catch( IOException e ) {
            System.out.println( "Saving screenshot failed: "+e );
        }
    }
    
}