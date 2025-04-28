import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ProgramWindow {
    public void ProgramWindow(Program program) {
        // Create the window
        JFrame frame = new JFrame("Horse Race Simulator - Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // center the frame on the screen


        // Create a root JPanel with BorderLayout
        JPanel rootPanel = new JPanel(new BorderLayout());
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
                createRaceWindow.CreateRaceWindow(program);
            }
        });

        JButton horsesBtn = new JButton("Customise Horses");
        btnPanel.add(horsesBtn);
        // launch CreateRaceWindow on raceConfigBtn press
        horsesBtn.addActionListener(e -> {
            HorsesWindow horsesWindow = new HorsesWindow();
            horsesWindow.HorsesWindow(program);
        });

        rootPanel.add(btnPanel);




        // Finally, Show the window
        frame.setVisible(true);
    }
}