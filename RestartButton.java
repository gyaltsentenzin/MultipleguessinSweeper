import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class RestartButton extends Rectangle{
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private GraphicsText text;

    private static final int h =26, l = 20;

    /**
     * adds the start button on the canvas
     * @param canvas
     */
    public RestartButton(CanvasWindow canvas) {

        super(canvas.getWidth() / 10, canvas.getHeight() / 90, WIDTH, HEIGHT);

        Point bounds = this.getPosition();

        double xpos = bounds.getX() + l/ 2 - 5;
        double ypos = bounds.getY() + h - 3;

        setStroked(true);
        setFillColor(Color.YELLOW);
        setStrokeColor(Color.BLACK);

        text = new GraphicsText("Start Over?", xpos, ypos);
        text.setFillColor(Color.BLACK);

        canvas.add(this);
        canvas.add(text);
    }

    public GraphicsText getGraphicsTextObject(){
        return text;
    }
    
}
