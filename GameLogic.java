import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameLogic {
    
    List<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 5, 3, 7, 11, 
                     13, 4, 6, 9, 35, 77, 55, 25, 42, 121, 169, 39, 26));

    List<Integer> chosenNumbers = new ArrayList<Integer>();

    HashMap<Hexagon, Integer> hexagonNumbers = new HashMap<Hexagon, Integer>();

    HashMap<Integer, Integer> divisors = new HashMap<>(); 

    /**
     * Adds a list of divisors to a map, then calls assignNumbers. The map is used to count the 
     * number of hexagons clicked on with that divisor. 
     */
    public GameLogic(HexagonWall hexagons) {
        divisors.put(2, 0);
        divisors.put(3, 0);
        divisors.put(5, 0); 
        divisors.put(7, 0); 
        divisors.put(11, 0); 
        divisors.put(13, 0); 
        assignNumbers(hexagons);
    }

    /**
     * Takes the hexagon wall and assigns each a random number to each hexagon, removing the number 
     * from the number list to choose from. 
     * @param hexagons list of hexagons in a hexagon wall
     */    
    private void assignNumbers(HexagonWall hexagons) {
        for (Hexagon hexagon : hexagons.getHexagons()) { 
            Integer rand = new Random().nextInt(numberList.size()); 
            hexagonNumbers.put(hexagon, numberList.get(rand)); 
            numberList.remove(numberList.get(rand)); 
        }
    }

    /**
     * Adds the chosen number to a list and checks the divisors and adds 1 to that divisor's count if the number has that 
     * divisor. When the chosen numbers hit 3, the count of each divisor is checked and returns a win (true) if it is at least 3. 
     * Finding a "1" is a freebie, and adds 1 to all divisors. 
     * @param hexagon 
     */
    public boolean checkNumber(Hexagon hexagon) {
        if (!chosenNumbers.contains(hexagonNumbers.get(hexagon))) {
            chosenNumbers.add(hexagonNumbers.get(hexagon)); 
            for (Integer divisor : divisors.keySet()) {
                if (hexagonNumbers.get(hexagon) == 1) {
                    Integer value = divisors.get(divisor);                
                    divisors.put(divisor, value + 1);
                }
                if (hexagonNumbers.get(hexagon) % divisor == 0) {
                    Integer value = divisors.get(divisor);                
                    divisors.put(divisor, value + 1);
                }
            }
        }
     
        boolean x = false;
        if (chosenNumbers.size() >= 3) {
            for(Integer divisor : divisors.keySet()) {
                if (divisors.get(divisor) >= 3) {                    
                    x = true;
                }
            }
        }
        return x;
    }

    /**
     * Returns the number assigned randomly to the hexagon chosen 
     */
    public Integer getHexagonNumber(Hexagon hexagon) {
        return hexagonNumbers.get(hexagon);
    }

    /**
     * Returns the number of moves made by the player
     */
    public Integer getMoves() {
        return chosenNumbers.size();
    }

    /**
     * Resets the list of chosen numbers
     */
    public void logicReset(){
        chosenNumbers = null;
    }
}
