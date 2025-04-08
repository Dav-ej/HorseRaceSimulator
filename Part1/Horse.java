
/**
 * Write a description of class Horse here.
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
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.setSymbol(horseSymbol);

        this.setName(horseName);

        this.setConfidence(horseConfidence);

        this.goBackToStart();
    }
    
    
    
    //Getter Methods
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled() 
    {
        return this.distanceTravelled;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    public char getSymbol() 
    {
        return this.symbol;
    }
    
    public boolean hasFallen() 
    {
        return this.hasFallen;
    }

    //Setter methods
    public void fall() 
    {
        this.hasFallen = true;
    }
    
    public void goBackToStart() 
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    public void moveForward() 
    {
        if (this.distanceTravelled == Integer.MAX_VALUE) {
            System.out.println("Cannot move horse "+this.name+" forward, overflow has occured with distance!");
            return;
        }
        if (this.hasFallen) {
            System.out.println("Cannot move horse "+this.name+"forward, horse has fallen!");
            return;
        }
        this.distanceTravelled += 1;
    }

    public void setConfidence(double newConfidence) 
    {
        if (newConfidence < 0.0 || newConfidence > 1.0) {
            throw new IllegalArgumentException("Confidence must be between 0 and 1 inclusive.");
        }
        this.confidence = newConfidence;
    }
    
    public void setSymbol(char newSymbol) 
    {
        if (!isCharVisible(newSymbol)) {
            System.out.println("Symbol is invisible, falling back to: H");
            this.symbol = 'H';
        } else {
            this.symbol = newSymbol;
        }
    }

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
    public boolean isCharVisible(char c) 
    {
        if (Character.isWhitespace(c) || Character.isISOControl(c)) {
            return false;
        }
        return true;
    }

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
