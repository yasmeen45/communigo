package ui;

// sources used:
// - https://www.youtube.com/watch?v=5o3fMLPY7qY
// - https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// - https://stackoverflow.com/questions/10984114/change-jpanel-after-clicking-on-a-button

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel homePanel;
    private JPanel upcomingPanel;
    private JPanel registeredPanel;

    // EFFECTS: constructs a new instance of GUI to run Communigo
    public GUI() {
        setupDisplay();
    }

    // MODIFIES: this
    // EFFECTS: sets up GUI window
    private void setupDisplay() {
        frame = new JFrame();

        setupHomePanel();
        setupUpcomingActivitiesPanel();
        setupRegisteredActivitiesPanel();

        frame.add(homePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Communigo");
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up home page
    private void setupHomePanel() {
        homePanel = new JPanel();
        homePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        homePanel.setLayout(new GridLayout(0, 1));

        JLabel title = new JLabel("Welcome to Communigo!");
        homePanel.add(title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,40));
        // https://stackoverflow.com/questions/2715118/
        // how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size

        addHomeButtons(title);
    }

    // MODIFIES: this
    // EFFECTS: add buttons to home page
    private void addHomeButtons(JLabel title) {
        JButton viewUpcomingButton = new JButton("View Upcoming Activities");
        viewUpcomingButton.setActionCommand("View Upcoming Activities");
        viewUpcomingButton.addActionListener(this);
        viewUpcomingButton.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,20));
        homePanel.add(viewUpcomingButton);

        JButton viewRegisteredButton = new JButton("View Registered Activities");
        viewRegisteredButton.setActionCommand("View Registered Activities");
        viewRegisteredButton.addActionListener(this);
        viewRegisteredButton.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,20));
        homePanel.add(viewRegisteredButton);

        JButton saveDataButton = new JButton("Save Data to File");
        saveDataButton.setActionCommand("Save Data to File");
        saveDataButton.addActionListener(this);
        saveDataButton.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,20));
        homePanel.add(saveDataButton);

        JButton loadDataButton = new JButton("Load Data from File");
        loadDataButton.setActionCommand("Load Data from File");
        loadDataButton.addActionListener(this);
        loadDataButton.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,20));
        homePanel.add(loadDataButton);
    }


    // MODIFIES: this
    // EFFECTS: sets up page for viewing upcoming activities
    private void setupUpcomingActivitiesPanel() {
        upcomingPanel = new JPanel();
        upcomingPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        upcomingPanel.setLayout(new GridLayout(0, 1));
        upcomingPanel.setBackground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: sets up page for viewing registered activities
    private void setupRegisteredActivitiesPanel() {
        registeredPanel = new JPanel();
        registeredPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        registeredPanel.setLayout(new GridLayout(0, 1));
        registeredPanel.setBackground(Color.CYAN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Upcoming Activities")) {
            frame.remove(homePanel);
            frame.add(upcomingPanel);
            frame.validate();
        } else if (e.getActionCommand().equals("View Registered Activities")) {
            frame.remove(homePanel);
            frame.add(registeredPanel);
            frame.validate();
        } else if (e.getActionCommand().equals("Save Data to File")) {
            // TODO: logic to save data
        } else if (e.getActionCommand().equals("Load Data from File")) {
            // TODO: logic to load data
        }
    }
}
