package ui;

// sources used:
// - https://www.youtube.com/watch?v=5o3fMLPY7qY
// - https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// - https://stackoverflow.com/questions/10984114/change-jpanel-after-clicking-on-a-button

import model.Activity;
import model.Manager;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class GUI implements ActionListener, ListSelectionListener {

    private static final String JSON_FILE = "./data/saved.json";
    private Manager manager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel homePanel;
    private JPanel upcomingPanel;
    private JPanel registeredPanel;

    private JButton registerButton;

    DefaultListModel upcomingListModel;
    JList upcomingList;
    DefaultListModel registeredListModel;
    JList registeredList;

    // EFFECTS: creates a new instance of application with a manager,
    //          Json reader/writer, and creates GUI
    public GUI() {
        this.manager = new Manager();
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
        setupDisplay();
    }

    // MODIFIES: this
    // EFFECTS: sets up GUI window
    private void setupDisplay() {
        frame = new JFrame();

        setupHomePanel();
        setupUpcomingPanel();
        setupRegisteredPanel();

        frame.add(homePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Communigo");
        frame.pack();
        frame.setVisible(true);
    }


    // HOME PANEL =====================================================================================

    // MODIFIES: this
    // EFFECTS: sets up home page
    private void setupHomePanel() {
        homePanel = new JPanel();
        homePanel.setBorder(BorderFactory.createEmptyBorder(75, 100, 75, 100));
        homePanel.setLayout(new GridLayout(0, 1));

        BufferedImage homePic = null;
        try {
            homePic = ImageIO.read(new File("./images/HomePagePicture.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel homePicLabel = new JLabel(new ImageIcon(homePic));
        homePanel.add(homePicLabel);
        //https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel

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

    // UPCOMING ACTIVITIES PANEL =======================================================================

    // MODIFIES: this
    // EFFECTS: sets up page for viewing upcoming activities
    private void setupUpcomingPanel() {
        upcomingPanel = new JPanel();
        upcomingPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 75, 100));
        upcomingPanel.setLayout(new GridLayout(0, 1));

        JLabel title = new JLabel("Upcoming Activities");
        upcomingPanel.add(title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,40));

        upcomingListModel = new DefaultListModel();
        for (Activity activity : manager.getActivitiesChronological()) {
            String element = activityToString(activity);
            upcomingListModel.addElement(element);
        }
        upcomingList = new JList(upcomingListModel);

        displayActivities(upcomingListModel, upcomingList, upcomingPanel);

        addUpcomingButtons();
    }

    // MODIFIES: this
    // EFFECTS: add buttons to upcoming activities page
    private void addUpcomingButtons() {
        registerButton = new JButton("Register Selected Activity");
        registerButton.setActionCommand("Register Selected Activity");
        registerButton.addActionListener(this);
        registerButton.setFont(new Font(registerButton.getFont().getFontName(),Font.PLAIN,20));
        upcomingPanel.add(registerButton);

        JButton returnToMenuButton = new JButton("Return to Menu");
        returnToMenuButton.setActionCommand("Return to Menu from Upcoming");
        returnToMenuButton.addActionListener(this);
        returnToMenuButton.setFont(new Font(returnToMenuButton.getFont().getFontName(),Font.PLAIN,20));
        upcomingPanel.add(returnToMenuButton);
    }

    // REGISTERED ACTIVITIES PANEL =======================================================================

    // MODIFIES: this
    // EFFECTS: sets up page for viewing registered activities
    private void setupRegisteredPanel() {
        registeredPanel = new JPanel();
        registeredPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 75, 100));
        registeredPanel.setLayout(new GridLayout(0, 1));

        JLabel title = new JLabel("Registered Activities");
        registeredPanel.add(title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,40));

        registeredListModel = new DefaultListModel();
        for (Activity activity : manager.getRegisteredActivities()) {
            String element = activityToString(activity);
            registeredListModel.addElement(element);
        }
        registeredList = new JList(registeredListModel);

        displayActivities(registeredListModel, registeredList, registeredPanel);

        JButton returnToMenuButton = new JButton("Return to Menu");
        returnToMenuButton.setActionCommand("Return to Menu from Registered");
        returnToMenuButton.addActionListener(this);
        returnToMenuButton.setFont(new Font(returnToMenuButton.getFont().getFontName(),Font.PLAIN,20));
        registeredPanel.add(returnToMenuButton);
    }

    // OTHER METHODS ====================================================================================

    // MODIFIES: this
    // EFFECTS: display provided activities to given panel
    private void displayActivities(DefaultListModel listModel, JList list, JPanel panel) {
        // https://docs.oracle.com/javase/tutorial/uiswing/components/list.html

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        panel.add(listScrollPane);
    }

    // EFFECTS: convert activity to "TYPE - AREA - DATE" format, and return it
    private String activityToString(Activity activity) {
        String result = activity.getTypeToPrint() + "  /  " + activity.getAreaToPrint()
                + "  /  " + activity.getDate().toString();
        return result;
    }

    // MODIFIES: this
    // EFFECTS: add given activity string to manager's registered activities
    private void registerActivity(String activityString) {
        manager.registerActivity(createActivity(activityString));
        updateRegisteredActivities();
    }

    // EFFECTS: create and return Activity from activityString
    private Activity createActivity(String activityString) {
        Application.Type type;
        Application.Area area;
        LocalDate date;

        // find type
        if (activityString.contains("Running")) {
            type = Application.Type.RUN;
        } else if (activityString.contains("Biking")) {
            type = Application.Type.BIKE;
        } else {
            type = Application.Type.WALK;
        }

        // find area
        if (activityString.contains("Vancouver")) {
            area = Application.Area.VANCOUVER;
        } else if (activityString.contains("Surrey")) {
            area = Application.Area.SURREY;
        } else {
            area = Application.Area.BURNABY;
        }

        // find date
        int startIndex = activityString.length() - 10;
        date = LocalDate.parse(activityString.substring(startIndex));

        Activity activity = new Activity(type, area, date);
        return activity;
    }

    // MODIFIES: this
    // EFFECTS: update registeredList with current registeredActivities
    private void updateRegisteredActivities() {
        registeredListModel = new DefaultListModel();
        for (Activity a : manager.getRegisteredActivities()) {
            String element = activityToString(a);
            registeredListModel.addElement(element);
        }
        registeredList.setModel(registeredListModel);
    }

    // JSON read/write methods ====================================================================

    // MODIFIES: this
    // EFFECTS: saves application data to Json file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Success! Data saved.");
            // https://stackoverflow.com/questions/9119481/how-to-present-a-simple-alert-message-in-java
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to save to file.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads application data from Json file
    private void loadData() {
        try {
            manager = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Success! Data loaded.");
            // https://stackoverflow.com/questions/9119481/how-to-present-a-simple-alert-message-in-java
        } catch (IOException e) {
            System.out.println("\nUnable to read from file.");
        }
    }

    // MODIFIES: this
    // EFFECTS: handles all user actions (part 1)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Upcoming Activities")) {
            frame.remove(homePanel);
            frame.add(upcomingPanel);
            frame.revalidate();
            frame.repaint();
        } else if (e.getActionCommand().equals("View Registered Activities")) {
            frame.remove(homePanel);
            frame.add(registeredPanel);
            frame.revalidate();
            frame.repaint();
        } else if (e.getActionCommand().equals("Save Data to File")) {
            saveData();
        } else if (e.getActionCommand().equals("Load Data from File")) {
            loadData();
            updateRegisteredActivities();
        }
        actionPerformedPartTwo(e);
    }

    // MODIFIES: this
    // EFFECTS: handles all user actions (part 2)
    public void actionPerformedPartTwo(ActionEvent e) {
        if (e.getActionCommand().equals("Return to Menu from Upcoming")) {
            frame.remove(upcomingPanel);
            frame.add(homePanel);
            frame.revalidate();
            frame.repaint();
        } else if (e.getActionCommand().equals("Return to Menu from Registered")) {
            frame.remove(registeredPanel);
            frame.add(homePanel);
            frame.revalidate();
            frame.repaint();
        } else if (e.getActionCommand().equals("Register Selected Activity")) {
            int index = upcomingList.getSelectedIndex();
            String activity = (String) upcomingListModel.get(index);
            registerActivity(activity);
            JOptionPane.showMessageDialog(null, "Successfully Registered in Activity!");
        }
    }

    // MODIFIES: this
    // EFFECTS: enable registerButton if there is an activity selected,
    //          otherwise, disable registerButton
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        if (e.getValueIsAdjusting() == false) {
            if (upcomingList.getSelectedIndex() == -1) {
                // no selection
                registerButton.setEnabled(false);
            } else {
                // selection
                registerButton.setEnabled(true);
            }
        }
    }
}
