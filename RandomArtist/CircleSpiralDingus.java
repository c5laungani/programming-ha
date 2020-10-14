/**
* @name(s) //Habeeb Mohammed and Chetan Laungani
* @id(s)   // 1582143 and 1562274
* @group   //Group 120
* @date    //7th October 2020
*/

import java.awt.Graphics;
import java.awt.Point;


class CircleSpiralDingus extends Dingus {
    protected int radius;
    protected boolean filled; //true: filled, false: outline
    protected int centerX;
    protected int centerY;


    public CircleSpiralDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        centerX = random.nextInt(maxX/2);
        centerY = random.nextInt(maxY/2);
        filled = random.nextBoolean();
    
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        int numIterations = 5;

        int arcWidth = 10;
        int arcGrowDelta = 30;

        

        for (int i = 0; i < numIterations; i++) {
            g.drawArc(centerX - arcWidth, centerY - arcWidth, 2 * arcWidth, 2 * arcWidth, 0, 180);
            arcWidth += arcGrowDelta;
            g.drawArc(centerX - arcWidth, centerY - arcWidth, 2 * arcWidth - arcGrowDelta, 2 * arcWidth, 180, 180);
        }
        
    }
    
}
