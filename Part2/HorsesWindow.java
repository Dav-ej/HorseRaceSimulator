import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class HorsesWindow {
    public void HorsesWindow(Program program) {
        // Create the window
        JFrame frame = new JFrame("Horse Race Simulator - Horses");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 400);
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
        JLabel lanesLabel = new JLabel("Horses:");
        lanesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lanesLabel);


        /// Scrollable Checkable Horses:
        ArrayList<Horse> horses = program.getHorses(); // get horses

        String[][] horseData = new String[horses.size()][4];
        for (int i = 0; i < horses.size(); i++) { // extract horses data
            horseData[i][0] = horses.get(i).getName();
            horseData[i][1] = "Breed: " + horses.get(i).getBreed().getName();
            horseData[i][2] = "Confidence: " + horses.get(i).getConfidence();
            horseData[i][3] = horses.get(i).getCoatColour();
        }

        JPanel horseListPanel = new JPanel(); // create list
        horseListPanel.setLayout(new BoxLayout(horseListPanel, BoxLayout.Y_AXIS));

        for (String[] horse : horseData) { // create rows of horses with checkbox
            JPanel horseIcon = new JPanel();
            horseIcon.setPreferredSize(new Dimension(20, 20));
            try {
                horseIcon.setBackground(Color.decode("#" + horse[3]));
            } catch (NumberFormatException e) {
                horseIcon.setBackground(Color.BLACK);
            }

            JPanel horseRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel horseNameLabel = new JLabel(horse[0]);
            JLabel horseSpeedLabel = new JLabel(horse[1]);
            JLabel horseStaminaLabel = new JLabel(horse[2]);

            JButton deleteBtn = new JButton("Delete");
            deleteBtn.addActionListener(e -> { // logic for delete button
                int confirmation = JOptionPane.showConfirmDialog( // confirm popup
                    null,
                    "Are you sure you want to delete this horse?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirmation == JOptionPane.YES_OPTION) {
                    program.removeHorse(horse[0]); // remove by name

                    // rerender horses
                    horseListPanel.remove(horseRow);
                    horseListPanel.revalidate();
                    horseListPanel.repaint();
                }
            });

            JButton historyBtn = new JButton("History and Stats");
            historyBtn.addActionListener(e -> { // logic for history button
                // launch history window

            });

            horseRow.add(horseIcon);
            horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            horseRow.add(horseNameLabel);
            horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            horseRow.add(horseSpeedLabel);
            horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            horseRow.add(horseStaminaLabel);
            horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            horseRow.add(deleteBtn);
            //horseRow.add(Box.createHorizontalStrut(10)); // Add spacing
            //horseRow.add(historyBtn);

            horseListPanel.add(horseRow);
        }

        JScrollPane scrollPane = new JScrollPane(horseListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(scrollPane);




        // Create the bottom buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("Add Horse");
        btnPanel.add(addBtn);
        // cancel button logic (just closes window)
        addBtn.addActionListener(e -> {
            AddHorseWindow addHorseWindow = new AddHorseWindow();
            addHorseWindow.AddHorseWindow(program, frame);
        });

        JButton doneBtn = new JButton("Done");
        btnPanel.add(doneBtn);
        // done button logic (calls program.createRace)
        doneBtn.addActionListener(e -> frame.dispose());

        rootPanel.add(btnPanel, BorderLayout.SOUTH);




        // Finally, Show the window
        frame.setVisible(true);
    }
}