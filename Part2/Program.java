import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * The Program class holds the state of the program
 *  and provides an interface to launch the program
 * 
 * @author Davud Jabiyev 
 * @version 1
 */
public class Program {
    private ArrayList<Horse> horses = new ArrayList<Horse>();
    private ArrayList<RaceData> completedRaces = new ArrayList<RaceData>();
    private Race currentRace;
    private Breed[] breeds;

    public static void main(String[] args) {
        Program p = new Program();
        p.startRaceGUI();
    }

    public void startRaceGUI() {
        // Init breeds
        Breed karabakh = new Breed("Karabakh Horse", "Known for endurance and agility", 0.35, 0.25);
        Breed friesian = new Breed("Friesian Horse", "Slower but rarely stumble", 0.20, 0.40);
        Breed akhalteke = new Breed("Akhal-Teke Horse", "Lightning fast, however likely to slip", 0.5, 0.1);
        breeds = new Breed[]{karabakh, friesian, akhalteke};


        // Create random horses
        for (int i = 0; i < 5; i++) {
            String randomName = "Horse" + (i + 1);
            double randomConfidence = Math.random(); // Random confidence between 0 and 1
            Breed randomBreed = breeds[(int) (Math.random() * breeds.length)];
            String randomCoatColour = String.format("%06x", (int) (Math.random() * 0xFFFFFF));
            createHorse(randomName, randomConfidence, randomBreed, randomCoatColour);
        }

        //CreateRaceWindow crw = new CreateRaceWindow();
        //crw.CreateRaceWindow(this);
        ProgramWindow pw = new ProgramWindow();
        pw.ProgramWindow(this);
    }

    /**
     * Checks if a name for a horse is already taken
     * 
     * @param name the name to check for
     * @return true if the horse name is already taken, false otherwise
     */
    public boolean isHorseNameTaken(String name) {
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and adds a new horse
     * 
     * @param horseSymbol the symbol of the horse (printed in their lane)
     * @param horseName the name of the horse
     * @param horseConfidence the confidence of the horse (between 0 and 1 inclusive)
     * @param breed the breed of the horse
     * @param coatColour should be a Hex value without #
     */
    public void createHorse(String horseName, double horseConfidence, Breed breed, String coatColour) {
        if (isHorseNameTaken(horseName)) {
            JOptionPane.showMessageDialog(null, "Horse name is already taken.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        horses.add(new Horse(horseName, horseConfidence, breed, coatColour));
        JOptionPane.showMessageDialog(null, "Horse (" + horseName + ") created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Removes a horse by its name
     * 
     * @param horseName the name of the horse to remove
     */
    public void removeHorse(String horseName) {
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getName().equals(horseName)) {
                horses.remove(i);
                JOptionPane.showMessageDialog(null, "Horse ("+horseName+") removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Horse ("+horseName+") not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Returns an list of horses
     * 
     * @return an ArrayList of Horse objects
     */
    public ArrayList<Horse> getHorses() {
        return horses;
    }
    
    /**
     * Creates a new race with the specified distance and number of lanes, and adds the given horses to the race
     *
     * @param distance the distance of the race
     * @param lanes the number of lanes available in the race
     * @param horsesVarArgs a variable number of Horse objects to be added to the race
     */
    public void createRace(int distance, int lanes, Horse... horsesVarArgs) {
        if (currentRace != null) {
            if (currentRace.isInProgress()) {
                JOptionPane.showMessageDialog(null, "Cannot start new race while one is in progress", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        Race r = new Race(distance, lanes);
        for (int i = 0; i < horsesVarArgs.length; i++) {
            r.addHorse(horsesVarArgs[i], i+1);
        }
        currentRace = r;
    }

    /**
     * Returns the list of available breeds
     * 
     * @return an array of Breed objects
     */
    public Breed[] getBreeds() {
        return breeds;
    }

    /**
     * Returns the current race
     * 
     * @return the current Race object, or null if no race is active
     */
    public Race getCurrentRace() {
        return currentRace;
    }
}