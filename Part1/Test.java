/**
 * A class to run and test the CLI game
 * 
 * @author Davud Jabiyev
 * @version 1.2
 */
public class Test {
    public static void main(String[] args) {
        /*Race race = new Race(5);
        Horse horse1 = new Horse('A', "Horse A", 0.8);
        Horse horse2 = new Horse('B', "Horse B", 0.7);
        Horse horse3 = new Horse('C', "Horse C", 0.9);

        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        race.startRace();*/
        testingHorse();
    }

    public static void print(String... ss) {
        for (String s : ss) {
            System.out.println(s);
        }
    }

    public static void testingHorse() {
        print("---", "Testing Horse Class", "-");
        /*  (helpers) isCharVisible, isStringVisible */
        char isoControlChar = '\u0000';
        char whitespaceChar = ' ';


        // (1/13) Testing setName via (and) Constructor
        //
        print("(1/13) Testing setName via (and) Constructor:");
        
        print("- Null for HorseName Parameter:");
        Horse h1 = new Horse('H', null, 0.5);
        print("- getName result: "+h1.getName(),"");

        print("- ISO Control Char (\\u0000) for HorseName Parameter:");
        h1 = new Horse('H', Character.toString(isoControlChar), 0.5);
        print("- getName result: "+h1.getName(),"");

        print("- Whitespace Char (Space) for HorseName Parameter:");
        h1 = new Horse('H', Character.toString(whitespaceChar), 0.5);
        print("- getName result: "+h1.getName(),"");

        print("- Visible with ISO Control Char for HorseName Parameter:");
        h1 = new Horse('H', "Hors" + Character.toString(isoControlChar) + "ey", 0.5);
        print("- getName result: "+h1.getName(),"");

        print("- Visible with Whitespace Char for HorseName Parameter:");
        h1 = new Horse('H', "Hors" + Character.toString(whitespaceChar) + "ey", 0.5);
        print("- getName result: "+h1.getName(),"");


        // (2/13) Testing getter getConfidence
        //
        print("(2/13) Testing getter getConfidence:");
        print("- getConfidence result: "+h1.getConfidence(), "");


        // (3/13) Testing getter getDistanceTravelled
        //
        print("(3/13) Testing getter getDistanceTravelled:");
        print("- getDistanceTravelled result: "+h1.getDistanceTravelled(), "");


        // (4/13) Testing getter getName
        //
        print("(4/13) Testing getter getName:");
        print("- getName result: "+h1.getName(), "");


        // (5/13) Testing getter getSymbol
        //
        print("(5/13) Testing getter getSymbol:");
        print("- getSymbol result: "+h1.getSymbol(), "");


        // (6/13) Testing getter hasFallen
        //
        print("(6/13) Testing getter hasFallen:");
        print("- hasFallen result: "+h1.hasFallen(), "");


        // (7/13) Testing setter moveForward
        //
        print("(7/13) Testing setter moveForward");
        print("- 1 iteration of moveForward:");
        print("- getDistanceTravelled result before: "+h1.getDistanceTravelled());
        h1.moveForward();
        print("- getDistanceTravelled result after: "+h1.getDistanceTravelled(), "");

        print("- 2,147,483,647 more iterations of moveForward:");
        print("- getDistanceTravelled result before: "+h1.getDistanceTravelled());
        for (int i = 1; i < 2147483647; i++) {
            h1.moveForward();
        }
        print("- getDistanceTravelled result after: "+h1.getDistanceTravelled(), "");

        print("- 1 more iteration of moveForward:");
        print("- getDistanceTravelled result before: "+h1.getDistanceTravelled());
        h1.moveForward();
        print("- getDistanceTravelled result after: "+h1.getDistanceTravelled(), "");


        // (8/13) Testing setConfidence
        //
        print("(8/13) Testing setConfidence:");

        print("- 0.3 for confidence parameter:");
        print("- getConfidence result before: "+h1.getConfidence());
        h1.setConfidence(0.3);
        print("- getConfidence result after: "+h1.getConfidence(), "");

        print("- 0 for confidence parameter:");
        print("- getConfidence result before: "+h1.getConfidence());
        h1.setConfidence(0);
        print("- getConfidence result after: "+h1.getConfidence(), "");

        print("- 1 for confidence parameter:");
        print("- getConfidence result before: "+h1.getConfidence());
        h1.setConfidence(1);
        print("- getConfidence result after: "+h1.getConfidence(), "");

        print("- -0.1 for confidence parameter:");
        print("- getConfidence result before: "+h1.getConfidence());
        try {
            h1.setConfidence(-0.1);
        } catch (Exception e) {
            print("⚠️ Exception Raised");
        }
        print("- getConfidence result after: "+h1.getConfidence(), "");

        print("- 1.1 for confidence parameter:");
        print("- getConfidence result before: "+h1.getConfidence());
        try {
            h1.setConfidence(1.1);
        } catch (Exception e) {
            print("⚠️ Exception Raised");
        }
        print("- getConfidence result after: "+h1.getConfidence(), "");

        print("- 1.00001 for confidence parameter:");
        print("- getConfidence result before: "+h1.getConfidence());
        try {
            h1.setConfidence(1.00001);
        } catch (Exception e) {
            print("⚠️ Exception Raised");
        }
        print("- getConfidence result after: "+h1.getConfidence(), "");


        // (9/13) Testing setter fall
        //
        print("(9/13) Testing setter fall");
        print("- hasFallen result before: "+h1.hasFallen());
        h1.fall();
        print("- hasFallen result after: "+h1.hasFallen(), "");


        // (10/13) Testing setter goBackToStart
        //
        print("(10/13) Testing setter goBackToStart:");
        print("- getDistanceTravelled result before: "+h1.getDistanceTravelled());
        h1.goBackToStart();
        print("- getDistanceTravelled result after: "+h1.getDistanceTravelled(), "");


        // (10.5/13) Testing moveForward after fallen
        //
        print("(10.5/13) Testing moveForward after fallen");
        print("- hasFallen result before: "+h1.hasFallen());
        h1.fall();
        print("- hasFallen result after: "+h1.hasFallen(), "");
        print("- 1 iteration of moveForward:");
        print("- getDistanceTravelled result before: "+h1.getDistanceTravelled());
        h1.moveForward();
        print("- getDistanceTravelled result after: "+h1.getDistanceTravelled(), "");


        // (11/13) Testing setSymbol
        //
        print("(11/13) Testing setSymbol:");

        print("- ISO Control Char (\\u0000) for Symbol Parameter:");
        print("- getSymbol result before: "+h1.getSymbol());
        h1.setSymbol(isoControlChar);
        print("- getSymbol result after: "+h1.getSymbol(), "");

        print("- Whitespace Char (Space) for Symbol Parameter:");
        print("- getSymbol result before: "+h1.getSymbol());
        h1.setSymbol(whitespaceChar);
        print("- getSymbol result after: "+h1.getSymbol(), "");

        print("- Visible Char (♘) for Symbol Parameter:");
        print("- getSymbol result before: "+h1.getSymbol());
        h1.setSymbol('♘');
        print("- getSymbol result after: "+h1.getSymbol(), "");


        // (12/13) Testing isCharVisible directly
        //
        print("(12/13) Testing isCharVisible directly:");

        print("- ISO Control Char (\\u0000) for Char Parameter:");
        print("- isCharVisible: "+h1.isCharVisible(isoControlChar));

        print("- Whitespace Char (Space) for Char Parameter:");
        print("- isCharVisible: "+h1.isCharVisible(whitespaceChar), "");

        print("- Visible Char (♘) for Char Parameter:");
        print("- isCharVisible: "+h1.isCharVisible('♘'), "");


        // (13/13) Testing isStringVisible directly
        //
        print("(13/13) Testing isStringVisible directly:");
        
        print("- Null for String Parameter:");
        print("- isStringVisible: "+h1.isStringVisible(null),"");

        print("- ISO Control Char (\\u0000) for String Parameter:");
        print("- isStringVisible: "+h1.isStringVisible(Character.toString(isoControlChar)),"");

        print("- Whitespace Char (Space) for String Parameter:");
        print("- isStringVisible: "+h1.isStringVisible(Character.toString(whitespaceChar)),"");

        print("- Visible with ISO Control Char for String Parameter:");
        print("- isStringVisible: "+h1.isStringVisible("H"+isoControlChar),"");

        print("- Visible with Whitespace Char for String Parameter:");
        print("- isStringVisible: "+h1.isStringVisible("H"+whitespaceChar),"");

    }
}
