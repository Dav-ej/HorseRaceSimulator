import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CreateRaceWindow {
    private ArrayList<Horse> selectedHorses;
    private int trackLength;
    public void CreateRaceWindow(Program program) {
        // init vars
        selectedHorses = new ArrayList<Horse>();
        trackLength = 15;


        // Create the window
        JFrame frame = new JFrame("Horse Race Simulator - Create Race");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); // center the frame on the screen


        // Create a root JPanel with BorderLayout
        JPanel rootPanel = new JPanel(new BorderLayout());
        frame.add(rootPanel);





        // Create the form inputs panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        rootPanel.add(formPanel, BorderLayout.CENTER);
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add form elements
        /// Horses Title
        JLabel lanesLabel = new JLabel("Select Horses (0 Lanes):");
        lanesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lanesLabel);


        /// Scrollable Checkable Horses:
        ArrayList<Horse> horses = program.getHorses(); // get horses

        String[][] horseData = new String[horses.size()][3];
        for (int i = 0; i < horses.size(); i++) { // extract horses data
            horseData[i][0] = horses.get(i).getName();
            horseData[i][1] = "Breed: " + horses.get(i).getBreed().getName();
            horseData[i][2] = "Confidence: " + horses.get(i).getConfidence();
        }

        JPanel horseListPanel = new JPanel(); // create list
        horseListPanel.setLayout(new BoxLayout(horseListPanel, BoxLayout.Y_AXIS));

        for (String[] horse : horseData) { // create rows of horses with checkbox
            JPanel horseRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox();
            JLabel horseNameLabel = new JLabel(horse[0]);
            JLabel horseSpeedLabel = new JLabel(horse[1]);
            JLabel horseStaminaLabel = new JLabel(horse[2]);

            // checkbox logic
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) { // add if getting checked
                    selectedHorses.add(horses.get(horseListPanel.getComponentZOrder(horseRow)));
                } else { // remove if getting unchecked
                    selectedHorses.remove(horses.get(horseListPanel.getComponentZOrder(horseRow)));
                }
                lanesLabel.setText("Select Horses (" + selectedHorses.size() + " Lane"+(selectedHorses.size() == 1 ? "" : "s")+"):");
            });



            horseRow.add(checkBox);
            horseRow.add(horseNameLabel);
            horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            horseRow.add(horseSpeedLabel);
            horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            horseRow.add(horseStaminaLabel);

            horseListPanel.add(horseRow);
        }

        JScrollPane scrollPane = new JScrollPane(horseListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(scrollPane);

        /// Length title
        JLabel trackLengthLabel = new JLabel("Length of track (15):");
        trackLengthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(trackLengthLabel);

        /// Length slider
        JSlider trackLengthSlider = new JSlider(0, 100, 15);
        trackLengthSlider.setPaintTrack(true);
        trackLengthSlider.setPaintTicks(true);
        trackLengthSlider.setPaintLabels(true);
        trackLengthSlider.setMajorTickSpacing(10);
        trackLengthSlider.setMinorTickSpacing(5);
        trackLengthSlider.setAlignmentX(Component.LEFT_ALIGNMENT);

        // slider logic
        trackLengthSlider.addChangeListener(e -> {
            trackLength = trackLengthSlider.getValue();
            trackLengthLabel.setText("Length of track (" + trackLength + "):");
        });

        formPanel.add(trackLengthSlider);




        // Create the bottom buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancelBtn = new JButton("Cancel");
        btnPanel.add(cancelBtn);
        // cancel button logic (just closes window)
        cancelBtn.addActionListener(e -> frame.dispose());

        JButton doneBtn = new JButton("Done");
        btnPanel.add(doneBtn);
        // done button logic (calls program.createRace)
        doneBtn.addActionListener(e -> {
            if (selectedHorses.size() < 2) {
                JOptionPane.showMessageDialog(frame, "You need to select at least 2 horses to create a race.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (program.getCurrentRace() != null && program.getCurrentRace().isInProgress()) {
                JOptionPane.showMessageDialog(frame, "Cannot create a new race while one is active.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            program.createRace(trackLength, selectedHorses.size(), selectedHorses.toArray(new Horse[0]));
            frame.dispose();
        });

        rootPanel.add(btnPanel, BorderLayout.SOUTH);




        // Finally, Show the window
        frame.setVisible(true);
    }
}