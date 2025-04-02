
/**
 * Write a description of class Horse here.
 * 
 * @author Davud Jabiyev 
 * @version 1.2
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
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.setSymbol(horseSymbol);
        this.name = horseName;
        this.setConfidence(horseConfidence);

        this.goBackToStart();
    }
    
    
    
    //Other methods of class Horse
    public void fall() //Setter
    {
        this.hasFallen = true;
    }
    
    public double getConfidence() //Getter
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled() //Getter
    {
        return this.distanceTravelled;
    }
    
    public String getName() //Getter
    {
        return this.name;
    }
    
    public char getSymbol() //Getter
    {
        return this.symbol;
    }
    
    public void goBackToStart() //Setter
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    public boolean hasFallen() //Getter
    {
        return this.hasFallen;
    }

    public void moveForward() //Setter
    {
        this.distanceTravelled += 1;
    }

    public void setConfidence(double newConfidence) //Setter
    {
        if (newConfidence < 0.0 || newConfidence > 1.0) {
            throw new IllegalArgumentException("Confidence must be between 0 and 1 inclusive.");
        }
        this.confidence = newConfidence;
    }
    
    public void setSymbol(char newSymbol) //Setter
    {
        this.symbol = newSymbol;
    }
    
}
