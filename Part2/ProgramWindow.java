import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ProgramWindow {
    private JPanel rootPanel;
    private Program program;
    private JPanel racePanel;
    private ArrayList<JPanel> horseObjects = new ArrayList<>();

    public void ProgramWindow(Program program) {
        //init program var
        this.program = program;

        // Create the window
        JFrame frame = new JFrame("Horse Race Simulator - Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // center the frame on the screen
        frame.setResizable(false);


        // Create a root JPanel
        JPanel rootPanel = new JPanel();
        this.rootPanel = rootPanel;
        rootPanel.setLayout(null);
        frame.add(rootPanel);


        // Create a JPanel with buttons for race setup and horses customisation
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton raceConfigBtn = new JButton("Config Race");
        btnPanel.add(raceConfigBtn);
        // launch CreateRaceWindow on raceConfigBtn press
        raceConfigBtn.addActionListener(e -> {
            if (program.getCurrentRace() != null && program.getCurrentRace().isInProgress()) {
                JOptionPane.showMessageDialog(frame, "Cannot configure race while a race is in progress.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                CreateRaceWindow createRaceWindow = new CreateRaceWindow();
                createRaceWindow.CreateRaceWindow(program, this);
            }
        });

        JButton horsesBtn = new JButton("Customise Horses");
        btnPanel.add(horsesBtn);
        // launch CreateRaceWindow on raceConfigBtn press
        horsesBtn.addActionListener(e -> {
            if (program.getCurrentRace() != null && program.getCurrentRace().isInProgress()) {
                JOptionPane.showMessageDialog(frame, "Cannot configure horses while a race is in progress.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                HorsesWindow horsesWindow = new HorsesWindow();
                horsesWindow.HorsesWindow(program);
            }
        });

        btnPanel.setBounds(0, 0, 1000, 50);
        rootPanel.add(btnPanel);

        JButton startRaceBtn = new JButton("Start Race");
        btnPanel.add(startRaceBtn);
        // Start the race on startRaceBtn press
        startRaceBtn.addActionListener(e -> {
            if (program.getCurrentRace() == null) {
                JOptionPane.showMessageDialog(frame, "No race configured to start.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (program.getCurrentRace().isInProgress()) {
                JOptionPane.showMessageDialog(frame, "Race is already in progress.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new Thread(() -> {
                    reloadScrollPane();
                    program.getCurrentRace().startRace();
                }).start();
            }
        });




        // Create and add the actual racePanel
        JPanel racePanel = new JPanel(); // this sits inside scrollPane
        racePanel.setLayout(null);
        racePanel.setPreferredSize(new Dimension(995, 300));
        racePanel.setBackground(Color.WHITE);
        this.racePanel = racePanel;

        JScrollPane scrollPane = new JScrollPane(racePanel);
        scrollPane.setBounds(0, 50, 1000, 600);

        rootPanel.add(scrollPane);

        reloadScrollPane();


        // Finally, Show the window
        frame.setVisible(true);
    }

    public void reloadScrollPane() {
        program = this.program;
        racePanel = this.racePanel;

        // First delete all children of racepanel
        racePanel.removeAll();
        racePanel.revalidate();
        racePanel.repaint();

        // Get the horses, if none then output no race set
        if (program.getCurrentRace() == null) {
            JLabel noRaceLabel = new JLabel("No race set");
            noRaceLabel.setBounds(50, 50, 200, 30);
            racePanel.add(noRaceLabel);
        } else {
            // set race program window for allowing redraw on horse move
            program.getCurrentRace().setProgramWindow(this);

            Horse[] horses = program.getCurrentRace().getHorses();

            // set the height of racePanel based on number of horses + 20 for header
            racePanel.setPreferredSize(new Dimension(995, (100*horses.length)+20));

            // add header
            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            headerPanel.setBounds(0, 0, 995, 25);
            headerPanel.setBackground(Color.GRAY);

            JLabel headerLabel = new JLabel("Race (Length of " + program.getCurrentRace().getRaceLength() + "):");
            headerPanel.add(headerLabel);

            racePanel.add(headerPanel);

            // add each lane
            for (int i = 0; i < horses.length; i++) {
                int yPos = 20 + (i*100);

                // Horse Info
                JPanel horseInfoPanel = new JPanel();
                horseInfoPanel.setLayout(null);
                horseInfoPanel.setBounds(0, yPos, 995, 100);

                Horse horse = horses[i];
                
                JTextArea horseDetails = new JTextArea();
                horseDetails.setText("("+(i+1)+") "+horse.getName() + ":\n" +
                        "Breed: " + horse.getBreed().getName() + "\n" +
                        "Confidence: " + horse.getConfidence()); /*+ "\n" +
                        "Distance Travelled: " + horse.getDistanceTravelled() + "\n" +
                        (horse.hasFallen() ? "Status: Fallen" : "Status: Running"));*/
                horseDetails.setLineWrap(true);
                horseDetails.setWrapStyleWord(true);
                horseDetails.setFont(new Font("Arial", Font.PLAIN, 12));
                horseDetails.setEditable(false);
                horseDetails.setFocusable(false);
                horseDetails.setBounds(10, 10, 300, 80);
                horseInfoPanel.add(horseDetails);

                racePanel.add(horseInfoPanel);

                // Horse label
                JLabel horseLabel = new JLabel();
                horseLabel.setBounds(320, yPos, 50, 50);
                horseLabel.setIcon(new ImageIcon(new ImageIcon(horses[i].getBreed().getImage()+".png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                racePanel.add(horseLabel);

                // Horse coat colour
                JPanel coatColourPanel = new JPanel();
                coatColourPanel.setBackground(Color.decode("#" + horse.getCoatColour()));
                coatColourPanel.setBounds(320 + 15 - 5, yPos + 25-5, 10, 10);
                racePanel.add(coatColourPanel);

                // Make sure coatColourPanel is above horseLabel
                racePanel.setComponentZOrder(coatColourPanel, 1);
                racePanel.setComponentZOrder(horseLabel, 2);
            }

            // Create finishing line
            JPanel finishLine = new JPanel();
            finishLine.setBackground(Color.RED);
            finishLine.setBounds(320+25+((600/program.getCurrentRace().getRaceLength())*program.getCurrentRace().getRaceLength()), 25, 3, (100 * horses.length));
            racePanel.add(finishLine);
            racePanel.setComponentZOrder(finishLine, 0);
        }
    }

    public void redrawHorses() {
        program = this.program;
        racePanel = this.racePanel;

        // First delete all horses
        for (Component component : racePanel.getComponents()) {
            if (component instanceof JLabel || component instanceof JPanel) {
                if (component.getBounds().width == 50 && component.getBounds().height == 50) { // if horseLabel delete
                    racePanel.remove(component);
                } else if (component.getBounds().width == 10 && component.getBounds().height == 10) { // if coat delete
                    racePanel.remove(component);
                }
            }
        }
        racePanel.revalidate();
        racePanel.repaint();

        // Get the horses, if none then output no race set
        Horse[] horses = program.getCurrentRace().getHorses();

        // set the height of racePanel based on number of horses + 20 for header
        racePanel.setPreferredSize(new Dimension(995, (100*horses.length)+20));

        // add header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBounds(0, 0, 995, 25);
        headerPanel.setBackground(Color.GRAY);

        JLabel headerLabel = new JLabel("Race (Length of " + program.getCurrentRace().getRaceLength() + "):");
        headerPanel.add(headerLabel);

        racePanel.add(headerPanel);

        // add each lane horse
        for (int i = 0; i < horses.length; i++) {
            Horse horse = horses[i];
            int yPos = 20 + (i*100);
            int xPos = 320+(600/program.getCurrentRace().getRaceLength()*horses[i].getDistanceTravelled());

            // Horse label
            JLabel horseLabel = new JLabel();
            horseLabel.setBounds(xPos, yPos, 50, 50);
            horseLabel.setIcon(new ImageIcon(new ImageIcon(horses[i].getBreed().getImage()+".png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            racePanel.add(horseLabel);

            // Horse coat colour
            if (!horses[i].hasFallen()) {
                JPanel coatColourPanel = new JPanel();
                coatColourPanel.setBackground(Color.decode("#" + horse.getCoatColour()));
                coatColourPanel.setBounds(xPos + 15 - 5, yPos + 25-5, 10, 10);
                racePanel.add(coatColourPanel);
                // Make sure coatColourPanel is above horseLabel
                racePanel.setComponentZOrder(coatColourPanel, 0);
            }
            racePanel.setComponentZOrder(horseLabel, 1);

            if (horses[i].hasFallen()) {
                horseLabel.setIcon(new ImageIcon(new ImageIcon(horses[i].getBreed().getImage()+"_fallen.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            }
        }
    }
}