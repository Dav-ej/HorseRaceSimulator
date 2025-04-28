/**
 * The Breed class holds data for a breed and stats it affects
 * 
 * @author Davud Jabiyev 
 * @version 1
 */
public class Breed {
    private double chanceOfExtraMovement;
    private double reductionInChanceOfFalling;
    private String name;
    private String desc;

    /**
     * Constructor for objects of class Breed
     * 
     * @param chanceOfExtraMovement the chance of extra movement for a horse each turn
     * @param reductionInChanceOfFalling the percentage reduction in chance of falling for a horse
     */
    public Breed(String name, String desc, double chanceOfExtraMovement, double reductionInChanceOfFalling) {
        this.name = name;
        this.chanceOfExtraMovement = reductionInChanceOfFalling;
        this.reductionInChanceOfFalling = reductionInChanceOfFalling;
    }
    
    /**
     * Getter for the chanceOfExtraMovement field
     * 
     * @return the chance of extra movement for a horse each turn
     */
    public double getChanceOfExtraMovement()
    {
        return this.chanceOfExtraMovement;
    }
    
    /**
     * Getter for the reductionInChanceOfFalling field
     * 
     * @return the percentage reduction in chance of falling for a horse
     */
    public double getReductionInChanceOfFalling() 
    {
        return this.reductionInChanceOfFalling;
    }
    
    /**
     * Getter for the name field
     * 
     * @return the name of the breed
     */
    public String getName() 
    {
        return this.name;
    }
    
    /**
     * Getter for the desc field
     * 
     * @return the description of the breed
     */
    public String getDesc() 
    {
        return this.desc;
    }
}