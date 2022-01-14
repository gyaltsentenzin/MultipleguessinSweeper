import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class ReplayButton extends Rectangle{
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private GraphicsText text;

    private static final int h =26, l = 30;

    /**
     * adds the replay button on the canvas
     * determine the x and y position of the button appropriately and sets its features
     * @param canvas the canvas window where the game will be played
     */
    public ReplayButton(CanvasWindow canvas) {

        super(canvas.getWidth() / 10, canvas.getHeight() - 55, WIDTH, HEIGHT);
    
        Point bounds = this.getPosition();

        double xpos = bounds.getX() + l/ 2 - 5;
        double ypos = bounds.getY() + h - 3;

        setStroked(true);
        setFillColor(Color.YELLOW);
        setStrokeColor(Color.BLACK);

        text = new GraphicsText("Replay", xpos, ypos);
        text.setFillColor(Color.BLACK);

        canvas.add(this);
        canvas.add(text);
    }

    /**
     * 
     * @return graphicstext on the replay button
     */
    public GraphicsText getGraphicsTextObject(){
        return text;
    }
    
}
