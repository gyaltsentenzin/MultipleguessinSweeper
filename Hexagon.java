import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
 
public class Hexagon extends Path{

    /**
     * build a hexagon with the coordinates in the parameter of the constructor
     * @param coordinates
     */
    public Hexagon(List<Point> coordinates) {
        super(coordinates);  // super method of path to create the closed polygon (Hexagon in this case)

        setStroked(true);
        setFillColor(Color.YELLOW);
        setStrokeColor(Color.BLACK);
    }
}
