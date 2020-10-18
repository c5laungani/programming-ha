/**
 * 
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * main class
 * 
 * @author Chetan Laungani
 * @author Habeeb Mohammed
 * assignment group: Group 120
 * 
 * assignment copyright Kees Huizing
 */

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
//...

class PrisonersDilemma /* possible extends... */ implements ActionListener, ChangeListener{
    //...
  private final int DEFECTION_MIN = 0;
  private final int DEFECTION_MAX = 300;
  private final int FREQUENCY_MIN = 0;
  private final int FREQUENCY_MAX = 60;
  private final int LENGTH_OF_FIELD = 50;
  PlayingField field;

    void buildGUI() {
        SwingUtilities.invokeLater( () -> {
        //...

        Timer timer = new Timer(1000, this);

        JFrame frame = new JFrame("Prisoners Dilemma");  
        frame.setLayout(new BoxLayout (frame.getContentPane(), BoxLayout.Y_AXIS));  

        field = new PlayingField(LENGTH_OF_FIELD);

        JLabel defection = new JLabel("defection α = ");
        JLabel frequency = new JLabel("frequency = ");

        JSlider defectionSlider = new JSlider(DEFECTION_MIN, DEFECTION_MAX);
        defectionSlider.setMajorTickSpacing(10); 
        defectionSlider.setPaintTicks(true);
        Hashtable<Integer,JLabel> labelTable = new java.util.Hashtable<Integer,JLabel>();
        labelTable.put(0, new JLabel("0.0"));
        labelTable.put(100, new JLabel("1.0"));
        labelTable.put(200, new JLabel("2.0"));
        labelTable.put(300, new JLabel("3.0"));
        defectionSlider.setLabelTable(labelTable);
        defectionSlider.setPaintLabels(true);

        JSlider frequencySlider = new JSlider(FREQUENCY_MIN, FREQUENCY_MAX);
        frequencySlider.setMajorTickSpacing(10);        
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);
        
        //creates panel to store the 2 JSliders with their labels
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(defection);
        sliderPanel.add(defectionSlider);
        sliderPanel.add(frequency);
        sliderPanel.add(frequencySlider);

        //creates panel to store the go and reset button
        JPanel buttonPanel = new JPanel();
        JButton goButton = new JButton("Go");
        JButton resetButton = new JButton("Reset");
        buttonPanel.add(goButton);
        buttonPanel.add(resetButton);

        frame.getContentPane().add(field);
        frame.getContentPane().add(sliderPanel);
        frame.getContentPane().add(buttonPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(1200, 1200); 
        frame.setVisible(true);

        
        
        frequencySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
              if (!frequencySlider.getValueIsAdjusting()) {
                int value = frequencySlider.getValue();
              }
            }
          });

       
        //update alpha value based on defectionSlider
        defectionSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
              if (!defectionSlider.getValueIsAdjusting()) {
                int value = defectionSlider.getValue();
                field.setAlpha(value / 100.0);
              }
            }
          });


        
        //step needs to occur in intervals of 1 second when go button is press 
        goButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
           
            if (goButton.getText().equals("Go")){
              timer.start();
              field.step();
              goButton.setText("Pause");         

            } else if (goButton.getText().equals("Pause")) {
            timer.stop();
            goButton.setText("Go");  
            }
          }
        });
        //still need to implement resetButton 
        resetButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              
          }
      
        });
        
      } );
        
    }

    //...
    
    public static void main( String[] a ) {
        (new PrisonersDilemma()).buildGUI();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
      // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

    }
}
