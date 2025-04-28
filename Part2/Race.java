import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;

/**
 * A variable-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @author Davud Jabiyev
 * @version 1.3
 */
public class Race
{
    private int raceLength;
    private Horse[] horses;
    private boolean isInProgress;
    private ProgramWindow programWindow;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     * @param lanes the number of lanes in the racetrack (1 or more)
     */
    public Race(int distance, int lanes)
    {
        // initialise instance variables
        if (distance <= 0) {
            System.out.println("Cannot be less than 1 distance! Falling back to 1.");
            distance = 1;
        }
        raceLength = distance;
        if (lanes <= 0) { 
            System.out.println("Cannot be less than 1 lane! Falling back to 1 lane.");
            lanes = 1;
        }
        horses = new Horse[lanes];
        isInProgress = false;
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if ( laneNumber <= 0 || laneNumber > horses.length) {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
            return;
        }
        horses[laneNumber-1] = theHorse;
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        // start race active
        isInProgress = true;

        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        
        //reset all the lanes (all horses not fallen and back to 0). 
        for ( int i = 0; i<horses.length; i++ ) {
            if ( horses[i] != null ) {
                horses[i].goBackToStart();
            }
        }

        //declare a local variable arraylist of winners
        ArrayList<Horse> winners = new ArrayList<Horse>();
                      
        while (!finished)
        {
            //move each horse
            for ( int i = 0; i<horses.length; i++ ) {
                if ( horses[i] != null ) {
                    moveHorse(horses[i]);
                }
            }

            // redraw horses in GUI
            javax.swing.SwingUtilities.invokeLater(() -> {
                programWindow.redrawHorses();
            });

            //if all horses have fallen, the race is finshed
            boolean allFallen = true;
            for ( int i = 0; i<horses.length; i++ ) {
                if ( horses[i] != null ) {
                    if ( !horses[i].hasFallen() ) {
                        allFallen = false;
                        break;
                    }
                }
            }
            if ( allFallen ) {
                finished = true;
                javax.swing.JOptionPane.showMessageDialog(null, "All horses have fallen!! There are no winners.", "Race Over", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            
            //if any of the horses has won the race is finished
            for ( int i = 0; i<horses.length; i++ ) {
                if ( horses[i] != null ) {
                    if ( raceWonBy(horses[i])) {
                        winners.add(horses[i]);
                    }
                }
            }
            if ( winners.size() > 0 ) {
                finished = true;
                String message = "And the winner" + (winners.size() == 1 ? " is... " : "s are... ");
                for (int i = 0; i < winners.size(); i++) {
                    if (i == 0) {
                        message += winners.get(i).getName();
                    } else {
                        message += ", " + winners.get(i).getName();
                    }
                }
                message += "!";

                javax.swing.JOptionPane.showMessageDialog(null, message, "Race Over", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){
                isInProgress = false;
            }
        }
        //update isInProgress
        isInProgress = false;
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        double random;
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            random = Math.random();
            if (random < 0.05 || random < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < ((0.1*theHorse.getConfidence()*(theHorse.getConfidence()- theHorse.getBreed().getReductionInChanceOfFalling()))))
            {
                theHorse.fall();
                theHorse.setConfidence(Math.max(0, theHorse.getConfidence()-0.025));
            }

            // roll a chance to move again
            if (!raceWonBy(theHorse) && Math.random() < theHorse.getBreed().getChanceOfExtraMovement()) {
                System.out.println("MOVED AGAIN");
                theHorse.moveForward();
            }
        }
    }

    /**
     * Set the program window for the race
     * 
     * @param programWindow the ProgramWindow instance to be set
     */
    public void setProgramWindow(ProgramWindow programWindow) {
        this.programWindow = programWindow;
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() >= raceLength)
        {
            theHorse.setConfidence(Math.min(1,theHorse.getConfidence()+0.025));
            return true;
        }
        else
        {
            return false;
        }
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    /**
     * Check if the race is currently in progress
     * 
     * @return true if the race is in progress, false otherwise
     */
    public boolean isInProgress()
    {
        return isInProgress;
    }

    /**
     * Get the array of horses in the race
     * 
     * @return the array of horses
     */
    public Horse[] getHorses()
    {
        return horses;
    }

    /**
     * Get the length of the race
     * 
     * @return the length of the race
     */
    public int getRaceLength()
    {
        return raceLength;
    }
}
