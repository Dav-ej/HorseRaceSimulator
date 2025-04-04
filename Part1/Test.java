/**
 * A class to run and test the CLI game
 * 
 * @author Davud Jabiyev
 * @version 1
 */
public class Test {
    public static void main(String[] args) {
        Race race = new Race(5);
        Horse horse1 = new Horse('A', "Horse A", 0.8);
        Horse horse2 = new Horse('B', "Horse B", 0.7);
        Horse horse3 = new Horse('C', "Horse C", 0.9);

        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        race.startRace();
    }
}
