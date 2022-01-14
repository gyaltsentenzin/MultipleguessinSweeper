import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;

public class HexagonWall{
    private List<Hexagon> ListOfhexagons = new ArrayList<>();
    private static final int h =26, l = 30;

    /**
     * h = 26
     * l = 30
     */
    private Point topLeft;   // (x,y)
    private Point topRight;  // (x + l, y)
    private Point furtherRight; // (x + (3/2)*h , y + h)
    private Point bottomRight;  // (x + l, y + 2*h)
    private Point bottomLeft;  // (x, y + 2*h)
    private Point furtherLeft; // (x - (3/2)*h, y + h)
    private Hexagon hexagon;
    private CanvasWindow canvas;

    /**
     * This constructor takes the canvas where the hexagon wall will be built
     * and calls buildHexagonWall method to do it. 
     * @param canvas the canvaswindow where the game UI is built
     */
    public HexagonWall(CanvasWindow canvas){
        this.canvas = canvas;
        buildHexagonWall();
    }

    /** 
     *  Use the width and height of the canvas and places hexagons on the canvas in 
     * a honeycomb shape.
     */
    private void buildHexagonWall(){        
        int x = (canvas.getWidth()/4)+l, y = 100;
        for(int i = 0; i<4; i+=2*h){
            for(int m = 0; m < 3; m+= 1){
                hexagon = new Hexagon(setCoordinates(x, y));
                ListOfhexagons.add(hexagon);
                canvas.add(hexagon);
                x += 60;
            }

            x=canvas.getWidth()/4;
            y+=h*2;
            for(int j = 0; j < 4; j+= 1){
                hexagon = new Hexagon(setCoordinates(x, y));
                ListOfhexagons.add(hexagon);
                canvas.add(hexagon);
                x += 60;
            }

            x=(canvas.getWidth()/4)-l;
            y+=h*2;
            for(int k = 0; k < 5; k+= 1){
                hexagon = new Hexagon(setCoordinates(x, y));
                ListOfhexagons.add(hexagon);
                canvas.add(hexagon);
                x += 60;
            }

            x=(canvas.getWidth()/4);
            y+=h*2;
            for(int l = 0; l < 4; l+= 1){
                hexagon = new Hexagon(setCoordinates(x, y));
                ListOfhexagons.add(hexagon);
                canvas.add(hexagon);
                x += 60;
            }

            x=canvas.getWidth()/4+l;
            y+=h*2;
            for(int m = 0; m < 3; m+= 1){
                hexagon = new Hexagon(setCoordinates(x, y));
                ListOfhexagons.add(hexagon);
                canvas.add(hexagon);
                x += 60;
            }
        }
    }
    

    /**
     * Sets the coordinates of the corner points for a hexagon given a position on the canvas. Returns 
     * list of corner points of the hexagon.
     * @param x is the x coordinate of the hexagon
     * @param y is the y coordinate of the hexagon
     * @return a list of coordinates of hexagons to build a hexagon wall
     */
    private List<Point> setCoordinates(int x, int y){  
        List<Point> cPoints = new ArrayList<>();
        topLeft = new Point(x,y);
        topRight = new Point(x + l,y); 
        furtherRight = new Point(x + (h/Math.sqrt(3))+l,y + h);
        bottomRight = new Point(x + l,y + 2*h);
        bottomLeft = new Point(x, y + 2*h);
        furtherLeft = new Point (x - (h/Math.sqrt(3)), y + h);
        
        
        cPoints.add(topLeft);
        cPoints.add(topRight);
        cPoints.add(furtherRight);
        cPoints.add(bottomRight);
        cPoints.add(bottomLeft);
        cPoints.add(furtherLeft);
        return cPoints;
    }

    /**
     * 
     * @return list of coordinates of hexagons on the hexagon wall
     */
    public List<Hexagon> getHexagons() {
        return ListOfhexagons;
    } 
}