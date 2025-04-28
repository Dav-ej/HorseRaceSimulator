
/**
 * The Horse class represents a horse in a race simulation. 
 * Each horse has a name, a symbol, a confidence level, 
 * and tracks its distance travelled during the race. 
 * The class also includes functionality to handle scenarios 
 * such as the horse falling, or the horse moving or
 * resetting to the start of the race.
 * 
 * @author Davud Jabiyev 
 * @version 1.4
 */
public class Horse
{
    //Fields of class Horse
    private String name; //Name of the horse
    private char symbol; //A single Unicode character representing the horse
    private int distanceTravelled; //Distance travelled by the horse
    private boolean hasFallen; //A flag indicating if the horse has fallen during the race
    private double confidence; //Confidence level of the horse, between 0 and 1

    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     * 
     * @param horseSymbol the symbol of the horse (printed in their lane)
     * @param horseName the name of the horse
     * @param horseConfidence the confidence of the horse (between 0 and 1 inclusive)
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.setSymbol(horseSymbol);

        this.setName(horseName);

        this.setConfidence(horseConfidence);

        this.goBackToStart();
    }
    
    
    
    //Getter Methods
    /**
     * Getter for the horse's confidence field
     * 
     */
    public double getConfidence()
    {
        return this.confidence;
    }
    
    /**
     * Getter for the horse's distanceTravelled field
     * 
     */
    public int getDistanceTravelled() 
    {
        return this.distanceTravelled;
    }
    
    /**
     * Getter for the horse's name field
     * 
     */
    public String getName() 
    {
        return this.name;
    }
    
    /**
     * Getter for the horse's symbol field
     * 
     */
    public char getSymbol() 
    {
        return this.symbol;
    }
    
    /**
     * Getter for the horse's hasFallen field
     * 
     */
    public boolean hasFallen() 
    {
        return this.hasFallen;
    }

    //Setter methods
    /**
     * Sets the horses hasFallen value to true
     * 
     */
    public void fall() 
    {
        this.hasFallen = true;
    }
    
    /**
     * Sets the horses distanceTravelled to 0 and hasFallen to false
     * 
     */
    public void goBackToStart() 
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    /**
     * Increments the horses distanceTravelled by 1
     * 
     */
    public void moveForward() 
    {
        if (this.distanceTravelled == Integer.MAX_VALUE) {
            System.out.println("Cannot move horse "+this.name+" forward, overflow will occur with distance!");
            return;
        }
        if (this.hasFallen) {
            System.out.println("Cannot move horse "+this.name+" forward, horse has fallen!");
            return;
        }
        this.distanceTravelled += 1;
    }

    /**
     * Sets the horses confidence field (between 0 and 1 inclusive)
     * 
     * @param newConfidence the new confidence to set the horse to
     */
    public void setConfidence(double newConfidence) 
    {
        if (newConfidence < 0.0 || newConfidence > 1.0) {
            throw new IllegalArgumentException("Confidence must be between 0 and 1 inclusive.");
        }
        this.confidence = newConfidence;
    }
    
    /**
     * Sets the horses symbol (must be visible)
     * 
     * @param newSymbol the new symbol to set the horse to
     */
    public void setSymbol(char newSymbol) 
    {
        if (!isCharVisible(newSymbol)) {
            System.out.println("Symbol is invisible, falling back to: H");
            this.symbol = 'H';
        } else {
            this.symbol = newSymbol;
        }
    }

    /**
     * Sets the horses name
     * 
     * @param newName the new name to set the horse to
     */
    private void setName(String newName)
    {
        if (isStringVisible(newName)) {
            this.name = newName;
        } else {
            System.out.println("Horse name is not visible, falling back to: Horse");
            this.name = "Horse";
        }
    }

    //Helper methods
    /**
     * Checks if a given character is visible
     * 
     * @param c the character that is checked for being visible
     */
    public boolean isCharVisible(char c) 
    {
        if (Character.isWhitespace(c) || Character.isISOControl(c)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a given string has any visible characters
     * 
     * @param s the string that is checked for having any visible characters
     */
    public boolean isStringVisible(String s) 
    {
        if (s == null) { return false; }
        for (int i = 0; i < s.length(); i++) { // return true if any visible character
            if (isCharVisible(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}