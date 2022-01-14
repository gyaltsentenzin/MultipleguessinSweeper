import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;

 

public class Game {
    private static final CanvasWindow canvas =  new CanvasWindow("Multiple Guessing Game", 500, 500);
    private HexagonWall hexagonWall; // The hexagon wall object
    private NumberWall numberWall; // number wall object
    private GameLogic logic; // logic object
    private GraphicsText lives; // amount of lives in graphics text
    private GraphicsText dialogueText; // dialogue message on the game
    private GraphicsText instructions; // Instruction graphics text
    private Line line; //  line for design on the canvas
    private ReplayButton replayButton; // replay button object
    private RestartButton restartButton; // restart button object
    private GraphicsText replayButtonText; // replay button graphicstext object
    private GraphicsText restartButtonText; // restart button graphicstext object
    private GraphicsObject hexaPosition; // position of the hexagon

    private int liveCount = 3; // lives you have in the game

    
    /**
     * main method
     * instantiate game class which runs the game
     */
    public static void main(String[] args) {
        Game g = new Game();
    }


    /**
     * constructor which initialised, calls, and adds necessary graphic objects on the window
     * Calls GamePlay method which starts the game
     */
    public Game(){
        hexagonWall = new HexagonWall(canvas);
        this.logic = new GameLogic(hexagonWall);
        numberWall= new NumberWall(canvas,hexagonWall, logic); 

        setBackground();

        lives = new GraphicsText();
        dialogueText = new GraphicsText(null, canvas.getWidth() - 100, canvas.getHeight() - 100);
        dialogueText.setFillColor(Color.WHITE);

        instructions = new GraphicsText("Instructions: Click the hexagons to \nfind 3 numbers with the same divisor. \nFive tries and you're out!", canvas.getWidth() - 350, canvas.getHeight() - 50);
        instructions.setFillColor(Color.WHITE);
       
        canvas.add(instructions);
        canvas.add(dialogueText);

        lifeCounter();

        replayButtonText = new GraphicsText();

        Gameplay();

    }


    /**
     * instantiate and add the replay button
     * function on what it does when it is clicked
     */
    public void replayTheGame(){
        replayButton = new ReplayButton(canvas);
        replayButtonText = replayButton.getGraphicsTextObject();

        canvas.onClick(event -> {
            if(canvas.getElementAt(event.getPosition()) == null){
                return;
            } 

            if(canvas.getElementAt(event.getPosition()).equals(replayButton) || 
                canvas.getElementAt(event.getPosition()).equals(replayButtonText)){
                canvas.pause(10);
                dialogueText.setText("");
                canvas.remove(replayButton);
                resetGame();
            }

        });
    }


    /**
     * It has an onclick method to click the hexagon to reveal the number it has
     * After five hexagon clicked, it will determine whether you lost or won the game
     * changes the amount of lives you have left
     */
    public void Gameplay(){
        canvas.onClick(event -> {
            if(canvas.getElementAt(event.getPosition()) == null){
                return;
            }

            if(canvas.getElementAt(event.getPosition()) instanceof Hexagon ){
                hexaPosition = canvas.getElementAt(event.getPosition());

                if(logic.getMoves() < 6 && (liveCount > 0)){
                    numberWall.changeOnClick((Hexagon) hexaPosition);
                    checkResult();
                }
            }      
        });
    }


    /**
     * Checks the number assigned to the hexagon and checks if the player won or not. If they win, they keep their 
     * lives and can replay. If they lose, they lose a life and either replay or have to restart based on how many lives
     * are left. 
     */
    public void checkResult(){
        
        if (logic.checkNumber((Hexagon) hexaPosition) == true && logic.getMoves() == 5) {
            lifeCounter();
            dialogueText.setText("You won!"); 
            canvas.add(dialogueText);
            replayTheGame();
        } 

        if (logic.checkNumber((Hexagon) hexaPosition) == false && logic.getMoves() == 5){
            liveCount --;
            lifeCounter();
            dialogueText.setText("You Lost!!"); 
            canvas.add(dialogueText);  

            if (liveCount == 0) {
                restartButton = new RestartButton(canvas);
                restartTheGame(); 
            }
            else { 
                replayTheGame();
            }

        } 
    }

    /**
     *  set the background of the canvas and adds a line at the bottom half
     */
    public void setBackground(){
        canvas.setBackground(Color.BLACK);
        line = new Line(0, canvas.getHeight() - 80, canvas.getWidth(), canvas.getHeight() - 80);
        line.setStrokeColor(Color.WHITE);
        canvas.add(line);
    }


    /**
     * counts life you have left and add it to the window
     */
    public void lifeCounter(){
        lives.setText(liveCount + " lives");
        lives.setFillColor(Color.WHITE);
        lives.setCenter(canvas.getWidth() - 60, canvas.getHeight() - 60);
        canvas.add(lives);
    }


    /**
     * reset the game and remove and readd all the graphics object and recall the gameplay method
     */
    public void resetGame(){
        canvas.removeAll();

        hexagonWall = new HexagonWall(canvas);
        this.logic = new GameLogic(hexagonWall);
        numberWall= new NumberWall(canvas , hexagonWall, logic); 
        setBackground();

        lifeCounter();

        instructions = new GraphicsText("Instructions: Click the hexagons to \nfind 3 numbers with the same divisor. \nFive tries and you're out!", canvas.getWidth() - 350, canvas.getHeight() - 50);
        instructions.setFillColor(Color.WHITE);
        canvas.add(instructions);
    }

    /**
     * Restarts the game from the very beginning when the restart button is clicked. 
     */
    public void restartTheGame(){
        restartButtonText = restartButton.getGraphicsTextObject();
         canvas.onClick(event -> {
            if (canvas.getElementAt(event.getPosition()) == null) {
                return;
            }

            if(canvas.getElementAt(event.getPosition()).equals(restartButton) || 
            canvas.getElementAt(event.getPosition()).equals(restartButtonText)){     
                canvas.pause(1000);
                dialogueText.setText("");
                liveCount = 3;
                resetGame();
            }
        });
     }
}
