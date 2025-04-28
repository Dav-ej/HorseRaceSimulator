
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
     * @return the confidence level of the horse
     */
    public double getConfidence()
    {
        return this.confidence;
    }
    
    /**
     * Getter for the horse's distanceTravelled field
     * 
     * @return the distance travelled by the horse
     */
    public int getDistanceTravelled() 
    {
        return this.distanceTravelled;
    }
    
    /**
     * Getter for the horse's name field
     * 
     * @return the name of the horse
     */
    public String getName() 
    {
        return this.name;
    }
    
    /**
     * Getter for the horse's symbol field
     * 
     * @return the symbol representing the horse
     */
    public char getSymbol() 
    {
        return this.symbol;
    }
    
    /**
     * Getter for the horse's hasFallen field
     * 
     * @return true if the horse has fallen, false otherwise
     */
    public boolean hasFallen() 
    {
        return this.hasFallen;
    }

    /**
     * Checks if a given character is visible
     * 
     * @param c the character that is checked for being visible
     * @return true if the character is visible, false otherwise
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
     * @return true if the string contains any visible characters, false otherwise
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
