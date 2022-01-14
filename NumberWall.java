
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.*;

public class NumberWall {
    private Map<Hexagon, GraphicsText> dispNumbers = new HashMap<>();
    private GraphicsText dispnumber;
    private CanvasWindow canvas;
    private static final int h = 26, l = 30;

    public NumberWall(CanvasWindow canvas, HexagonWall hexagonwall, GameLogic gameLogic){
        this.canvas = canvas;
        addNumbers(hexagonwall, gameLogic);
    }
    /**
     * Given a hexagonwall, takes the number for each hexagon from the game logic, adds each number into 
     * the corresponding hexagon on the canvas. 
     * 
     * @param hexagonwall
     * @param gameLogic
     */
    public void addNumbers(HexagonWall hexagonwall, GameLogic gameLogic) {
        for (Hexagon hexagon : hexagonwall.getHexagons()) { 
            Point bounds = hexagon.getPosition();
            Integer number = gameLogic.getHexagonNumber(hexagon);
            String string = String.valueOf(number);

            dispnumber = new GraphicsText();
            dispnumber.setText(string);

            dispNumbers.put(hexagon, dispnumber);
           
            double xpos = bounds.getX() + l/ 2 + 5;
            double ypos = bounds.getY() + h + 5;
            dispnumber.setPosition(xpos,ypos);
            dispnumber.setFillColor(Color.BLACK); 

        }
    }

    /**
     * Finds the graphicstext number for the hexagon and changes its color to black 
     * so it appears on screen.
     * @param hexagon
     */
    public void changeOnClick(Hexagon hexagon) {
        canvas.add(getdispNo(hexagon));
        hexagon.setStrokeColor(Color.GRAY);
    }

    /**
     * Used to find the number assigned on the hexagon
     * @param hexagon 
     * @return the number in Graphics Text
     */
    public GraphicsText getdispNo(Hexagon hexagon){
        return dispNumbers.get(hexagon);
    }

 

}
