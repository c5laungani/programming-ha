/** Main class for HA Random Artist
 * can be used unchanged in most cases
 *
 * @author kees
 * @author huub
 * @name(s) //Habeeb Mohammed and Chetan Laungani
 * @id(s)   // 1582143 and 1562274
 * @group   //Group 120
 * @date    //7th October 2020
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class RandomArtist {
    JFrame frame;
    Painting painting; // panel that provides the random painting
    JButton regenerateButton;
    JButton shotButton;

    RandomArtist() {
        // invokeLater: preferred way to create components
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                painting = new Painting();
                frame = new JFrame("Computer Assisted Random Artist");
                frame.add(painting, BorderLayout.CENTER);
                regenerateButton = new JButton("Regenerate");
                regenerateButton.addActionListener(painting); // painting provides reaction to buttonclick
                frame.add(regenerateButton, BorderLayout.SOUTH);
                shotButton = new JButton("Screenshot");
                shotButton.addActionListener(painting);
                frame.add(shotButton, BorderLayout.NORTH);
                frame.pack();
                painting.regenerate(); // can be done here since painting has a size!
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        } );
    }

    public static void main(String[] arg) {
        new RandomArtist();
    }
}
