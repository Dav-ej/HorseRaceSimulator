import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AddHorseWindow {
    private String selectedColour;
    public void AddHorseWindow(Program program, JFrame horsesWindowFrame) {
        //public void createHorse(String horseName, double horseConfidence, Breed breed, String coatColour) {

        // Create the window
        JFrame frame = new JFrame("Horse Race Simulator - Add Horse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null); // center the frame on the screen


        // Create a root JPanel with BorderLayout
        JPanel rootPanel = new JPanel(new BorderLayout());
        frame.add(rootPanel);





        // Create the form inputs panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        rootPanel.add(formPanel, BorderLayout.CENTER);

        // Add form elements
        // Horse Name
        formPanel.add(new JLabel("Horse Name:"));
        JTextField horseNameField = new JTextField(20);
        horseNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(horseNameField);

        // Breed
        JLabel breedLabel = new JLabel("Breed:");
        breedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(breedLabel);
        // get breeds from the program
        Breed[] breeds = program.getBreeds();
        String[] breedNames = new String[breeds.length];
        for (int i = 0; i < breeds.length; i++) {
            breedNames[i] = breeds[i].getName();
        }
        JComboBox<String> breedComboBox = new JComboBox<>(breedNames);
        breedComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(breedComboBox);

        // Breed Information Panel
        JPanel breedInfoPanel = new JPanel();
        breedInfoPanel.setLayout(new BoxLayout(breedInfoPanel, BoxLayout.Y_AXIS));
        breedInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(breedInfoPanel);

        // Add a title for the breed information
        JLabel breedInfoTitle = new JLabel("Breed Information:");
        breedInfoPanel.add(breedInfoTitle);

        // Loop through breeds and display their information
        for (Breed breed : breeds) {
            String breedInfo = String.format(
                "<html><b><u>%s</u></b><br><b>Description:</b> %s<br><b>Chance of Extra Movement:</b> %.2f%%<br><b>Reduction in Chance of Falling:</b> %.2f%%</html>",
                breed.getName(),
                breed.getDesc(),
                breed.getChanceOfExtraMovement() * 100,
                breed.getReductionInChanceOfFalling() * 100
            );
            JLabel breedInfoLabel = new JLabel(breedInfo);
            breedInfoLabel.setBorder(new EmptyBorder(5, 5, 5, 5)); // padding 5
            breedInfoPanel.add(breedInfoLabel);
        }

        // Coat Colour
        JPanel coatColourPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coatColourPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(coatColourPanel);
        coatColourPanel.add(new JLabel("Coat Colour:"));

        // Colour Icon
        JPanel colourIcon = new JPanel();
        colourIcon.setPreferredSize(new Dimension(20, 20));
        colourIcon.setBackground(Color.BLACK);
        coatColourPanel.add(colourIcon);

        // Add a button to open a colour picker
        JButton colorPickerButton = new JButton("Pick Colour");
        coatColourPanel.add(colorPickerButton);


        // Open colour picker on button press
        this.selectedColour = "000000";
        colorPickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Choose Coat Colour", Color.BLACK);
            if (selectedColor != null) {
                // convert to string, remove the '#'
                this.selectedColour = Integer.toHexString(selectedColor.getRGB()).substring(2).toUpperCase();
                // update the colour icon
                try {
                    colourIcon.setBackground(Color.decode("#" + this.selectedColour));
                } catch (NumberFormatException exception) {
                    colourIcon.setBackground(Color.BLACK); // fallback to black
                }
            }
        });






        // Create the bottom buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rootPanel.add(btnPanel, BorderLayout.SOUTH);

        JButton cancelBtn = new JButton("Cancel");
        btnPanel.add(cancelBtn);
        // cancel button logic (just closes window)
        cancelBtn.addActionListener(e -> frame.dispose());

        JButton addBtn = new JButton("Add Horse");
        btnPanel.add(addBtn);
        // add horse button logic
        addBtn.addActionListener(e -> {
            // check name is visible
            if (!isStringVisible(horseNameField.getText())) {
                JOptionPane.showMessageDialog(frame, "Horse name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // check name is not taken
            if (program.isHorseNameTaken(horseNameField.getText())) {
                JOptionPane.showMessageDialog(frame, "Horse with that name already.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // get breed with name
            /// get index via breedNames
            int selectedBreedIndex = -1;
            for (int i = 0; i < breedNames.length; i++) {
                if (breedNames[i].equals(breedComboBox.getSelectedItem().toString())) {
                    selectedBreedIndex = i;
                    break;
                }
            }
            /// check index exists
            if (selectedBreedIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Invalid breed selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            /// get breed via index and breeds
            Breed selectedBreed = breeds[selectedBreedIndex];

            // create horse
            program.createHorse(horseNameField.getText(), 0.75, selectedBreed, this.selectedColour);
            
            // close frame
            frame.dispose();

            // Close the current horses window and reopen it
            horsesWindowFrame.dispose();
            HorsesWindow newHorsesWindow = new HorsesWindow();
            newHorsesWindow.HorsesWindow(program);
        });



        // Finally, Show the window
        frame.setVisible(true);
    }

        /**
     * Checks if a given character is visible
     * 
     * @param c the character that is checked for being visible
     * @return true if the character is visible, false otherwise
     */
    public boolean isCharVisible(char c) 
    {
        if (Character.isWhitespace(c) || Character.isISOControl(c)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a given string has any visible characters
     * 
     * @param s the string that is checked for having any visible characters
     * @return true if the string has any visible characters, false otherwise
     */
    public boolean isStringVisible(String s) 
    {
        if (s == null) { return false; }
        for (int i = 0; i < s.length(); i++) { // return true if any visible character
            if (isCharVisible(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}