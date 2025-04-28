# Horse Race Simulator
This application simulates horse races. It has two versions, a CLI version (in Part1 directory) and a GUI version (in Part2 directory).

## Dependencies
This project was programmed with `jdk-24.0.1.jdk`, for the best experience ensure you have the same version running on your computer.

## Downloading
You can download the code from github either through your browser or terminal.

## How to run the app
(Read Usage of CLI before this section)
1. Launch the terminal on your computer
2. `cd` into the right directory (Part1 for CLI; Part2 for GUI)
3. Compile the code: `javac RunCLI.java` or `javac Program.java`
4. Execute the code: `java RunCLI` or `java Program`

## Usage of CLI
Inside the main function in Part1/RunCLI.java, you'll find some code to assist setup (configuration and calling of `race.startRace();`):
```
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
```

Familiarise yourself with this code and simply follow the comments to adjust as you wish before you compile and run.

## Usage of GUI
The GUI version of this app automatically creates 5 random horses at launch. The GUI launches a window from which you can config races, customise horses, and start races. There aren't any complicated menus and usage should be intuitive.

The main Function in Program automatically calls `startRaceGUI()` which runs the application.

## Contact
If you have any questions, don't hesitate to reach me at `jabiyev@icloud.com`
