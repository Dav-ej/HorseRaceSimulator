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
                        
            //print the race positions
            printRace();

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
                System.out.println();
                System.out.println("All horses have fallen!! There are no winners.");
                System.out.println();
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
                System.out.println();
                System.out.print("And the winner");
                System.out.print((winners.size() == 1) ? " is... " :  "s are... ");
                for (int i = 0; i < winners.size(); i++) {
                    if (i == 0) {
                        System.out.print(winners.get(i).getName());
                    } else {
                        System.out.print(", "+winners.get(i).getName());
                    }
                }
                System.out.println("!");
                System.out.println();
            }
           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
        }
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
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
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
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        
        //each horse
        for ( int i = 0; i<horses.length; i++ ) {
            if ( horses[i] != null ) {
                printLane(horses[i]);
                System.out.println();
            }
        }
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');

        //print the horse name and confidence
        System.out.print(" "+theHorse.getName()+" (Current confidence: "+theHorse.getConfidence()+")");
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
}
