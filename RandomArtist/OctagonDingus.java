import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
/** 
* @name(s) //Habeeb Mohammed and Chetan Laungani
* @id(s)   // 1582143 and 1562274
* @group   //Group 120
* @date    //7th October 2020
*/

public class OctagonDingus extends Dingus {
    protected int radius;
    protected Point center;
    protected boolean filled; //true: filled, false: outline

    public OctagonDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        radius = random.nextInt(maxX/4);
        center = new Point(random.nextInt(maxX),random.nextInt(maxY));
        filled = random.nextBoolean();
    }

    private Polygon createOctagon() {
        Polygon polygon = new Polygon();

        for (int i = 0; i < 8; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 8D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 8D));
            polygon.addPoint(xval, yval);
        }

        return polygon;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled)
            g.fillPolygon(createOctagon());
        else
            g.drawPolygon(createOctagon());
    
    }
}
