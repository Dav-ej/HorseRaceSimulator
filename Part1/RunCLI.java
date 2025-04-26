/**
 * This class is for running the Horse Racing Simulator CLI
 * 
 * @author Davud Jabiyev
 * @version 1
 */
public class RunCLI {
    public static void main(String[] args) {
        // NOTE: Read the README file in the git repo to ensure you know what you are doing

        // Step 1: Create the horses objects
        // example: Horse h = new Horse('A', "Horse A", 0.5);
        //          fields: symbol, name, confidence (between 1 and 0)
        Horse hA = new Horse('✦', "Horse A", 0.5);
        Horse hB = new Horse('♘', "Horse B", 0.75);
        Horse hC = new Horse('♞', "Horse C", 0.25);

        // Step 2: Create the race object
        int numberOfLanes = 3;   // Adjust this to be the number of horses
        int lengthOfTrack = 15;  // Adjust this to be the desired length of the track
        Race race = new Race(lengthOfTrack, numberOfLanes);

        // Step 3: Add the horses to the race
        // example: race.addHorse(h, 1);
        //          fields: horse object, lane number
        race.addHorse(hA, 1);
        race.addHorse(hB, 2);
        race.addHorse(hC, 3);

        // Step 4: Start the race
        race.startRace();
    }
}