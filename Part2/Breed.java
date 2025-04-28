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
    private String image;

    /**
     * Constructor for objects of class Breed
     * 
     * @param name the name of the breed
     * @param desc the description of the breed
     * @param image the name of the file for the image of the horse breed (minus .png; must work with _fallen.png also)
     * @param chanceOfExtraMovement the chance of extra movement for a horse each turn
     * @param reductionInChanceOfFalling the percentage reduction in chance of falling for a horse
     */
    public Breed(String name, String desc, String image, double chanceOfExtraMovement, double reductionInChanceOfFalling) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        if (chanceOfExtraMovement < 0) {
            chanceOfExtraMovement = 0;
        }
        if (chanceOfExtraMovement > 1) {
            chanceOfExtraMovement = 1;
        }
        this.chanceOfExtraMovement = reductionInChanceOfFalling;
        if (reductionInChanceOfFalling < 0) {
            reductionInChanceOfFalling = 0;
        }
        if (reductionInChanceOfFalling > 1) {
            reductionInChanceOfFalling = 1;
        }
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

    /**
     * Getter for the image field
     * 
     * @return the image of the breed
     */
    public String getImage() 
    {
        return this.image;
    }
}