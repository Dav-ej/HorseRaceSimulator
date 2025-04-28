import java.time.LocalDateTime;
/**
 * The RaceData class holds data for a race that has happened
 * 
 * @author Davud Jabiyev 
 * @version 1
 */
public class RaceData {
    // all the horses that participated
    private Horse[] horses; 

    // the length of the race
    private int raceLength;

    // the distance of each horse (linked by index)
    private int[] distances;

    // the fallen state of each horse (linked by index)
    private boolean[] fallenStates; 

    // the winners of the race (linked by index)
    private int[] winners;

    // length of the race
    private LocalDateTime length;

    // time of race start
    private LocalDateTime startTime;

    /**
     * Constructor for objects of class RaceData
     * 
     * @param horses all the horses that participated
     * @param raceLength the length of the race
     * @param distances the distance of each horse (linked by index)
     * @param fallenStates the fallen state of each horse (linked by index)
     * @param winners the winners of the race (linked by index)
     * @param length the length of the race
     * @param startTime the time of race start
     */
    public RaceData(Horse[] horses, int raceLength, int[] distances, boolean[] fallenStates, int[] winners, LocalDateTime length, LocalDateTime startTime) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.distances = distances;
        this.fallenStates = fallenStates;
        this.winners = winners;
        this.length = length;
        this.startTime = startTime;
    }

    /**
     * Getter for the horses field
     * 
     * @return all the horses that participated
     */
    public Horse[] getHorses()
    {
        return this.horses;
    }

    /**
     * Getter for the raceLength field
     * 
     * @return the length of the race
     */
    public int getRaceLength() {
        return this.raceLength;
    }

    /**
     * Getter for the distances field
     * 
     * @return the distance of each horse (linked by index)
     */
    public int[] getDistances() {
        return this.distances;
    }

    /**
     * Getter for the fallenStates field
     * 
     * @return the fallen state of each horse (linked by index)
     */
    public boolean[] getFallenStates() {
        return this.fallenStates;
    }

    /**
     * Getter for the winners field
     * 
     * @return the winners of the race (linked by index)
     */
    public int[] getWinners() {
        return this.winners;
    }

    /**
     * Getter for the length field
     * 
     * @return the length of the race
     */
    public LocalDateTime getLength() {
        return this.length;
    }

    /**
     * Getter for the startTime field
     * 
     * @return the time of race start
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }
}