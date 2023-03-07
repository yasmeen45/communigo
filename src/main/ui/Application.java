package ui;

import model.Activity;
import model.Manager;
import org.json.JSONObject;
import persistance.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// citation: modelled after Json Demo provided in P2 description on EdX

// Runs applications: displays menu options and handles all user input
public class Application {

    public enum Type {
        WALK,
        RUN,
        BIKE
    }

    public enum Area {
        VANCOUVER,
        BURNABY,
        SURREY
    }

    private static final String JSON_FILE = "./data/saved.json";
    private Manager manager;
    private Scanner input;
    private JsonWriter jsonWriter;

    // EFFECTS: creates a new instance of application with a manager, Scanner (with a delimiter),
    //          Json reader/writes, and runs application
    public Application() {
        this.manager = new Manager();
        this.input = new Scanner(System.in);
        this.input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_FILE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: begins user display and processes input for exit
    private void runApp() {
        boolean runApp = true;
        String command = null;

        while (runApp) {
            displayMainMenu();
            command = input.nextLine();

            if (command.equals("7")) {
                runApp = false;
            } else {
                processCommandMainMenu(command);
            }
        }

        System.out.println("Goodbye!");
    }


    // EFFECTS: display menu options to user
    private void displayMainMenu() {
        System.out.println("\n\nWelcome to Communigo!");
        System.out.println(" =====================");

        System.out.println("\n(1) View upcoming activities");
        System.out.println("(2) Post an activity");
        System.out.println("(3) View my posted activities");
        System.out.println("(4) View my registered activities");
        System.out.println("(5) Save data to file");
        System.out.println("(6) Load data from file");
        System.out.println("(7) Quit");

        System.out.print("\nEnter the number of your choice: ");
    }


    // MODIFIES: this
    // EFFECTS: process user input to determine next action from main menu
    private void processCommandMainMenu(String command) {
        switch (command) {
            case "1":
                viewUpcomingActivities();
                break;
            case "2":
                createPosting();
                break;
            case "3":
                displayPostedActivities();
                break;
            case "4":
                displayRegisteredActivities();
                break;
            case "5":
                saveData();
                break;
            case "6":
                loadData();
                break;
            default:
                break;
            //default:
            //    System.out.print("Please enter a valid choice: ");
        }
    }

    // MODIFIES: this
    // EFFECTS: display posted activities or error message if none. get user input to delete posting
    public void displayPostedActivities() {
        System.out.println("\n\nPosted Activities\n-----------------");
        List<Activity> postedActivities = manager.getPostedActivities();
        if (postedActivities.isEmpty()) {
            System.out.println("\nSorry, there are no activities!\nReturning to main menu...");
        } else {
            displayActivities(postedActivities);
            deletePosting(postedActivities);
        }
    }

    // MODIFIES: this
    // EFFECTS: display registered activities or error message if none. get user input to cancel registration
    public void displayRegisteredActivities() {
        System.out.println("\n\nRegistered Activities\n---------------------");
        List<Activity> registeredActivities = manager.getRegisteredActivities();
        if (registeredActivities.isEmpty()) {
            System.out.println("\nSorry, there are no activities!\nReturning to main menu...");
        } else {
            displayActivities(registeredActivities);
            cancelRegistration(registeredActivities);
        }
    }

    // DISPLAYING ACTIVITIES =======================================================================================

    // EFFECTS: display menu to filter and list activities
    private void viewUpcomingActivities() {
        System.out.println("\n\nUpcoming Activities");
        System.out.println("-------------------");

        System.out.println("\nView activities by:");
        System.out.println("\t(1) Type");
        System.out.println("\t(2) Date");
        System.out.println("\t(3) Area");
        System.out.println("\t(4) View all");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();
        processCommandViewActivity(command);
    }

    // EFFECTS: process user input to determine next action for filtering activities
    private void processCommandViewActivity(String command) {
        switch (command) {
            case "1":
                filterType();
                break;
            case "2":
                filterDate();
                break;
            case "3":
                filterArea();
                break;
            case "4":
                displayActivities(manager.getActivitiesChronological());
                registerActivity(manager.getActivitiesChronological());
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    // EFFECTS: display options to filter by activity type and process user input
    private void filterType() {
        displayFilterTypeOptions();
        String command = null;
        command = input.nextLine();
        List<Activity> display = new ArrayList<>();

        switch (command) {
            case "1":
                display = manager.getActivitiesWithType(Type.WALK);
                break;
            case "2":
                display = manager.getActivitiesWithType(Type.RUN);
                break;
            case "3":
                display = manager.getActivitiesWithType(Type.BIKE);
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }

        if (display.isEmpty()) {
            System.out.println("\nSorry, there are no activities!\nReturning to main menu...");
        } else {
            displayActivities(display);
            registerActivity(display);
        }
    }

    // EFFECTS: display options for filtering by activity type
    private void displayFilterTypeOptions() {
        System.out.println("\nSelect activity type:");
        System.out.println("\t(1) Walking");
        System.out.println("\t(2) Running");
        System.out.println("\t(3) Biking");

        System.out.print("\nEnter the number of your choice: ");
    }

    // EFFECTS: process user input to display activities of a specific date
    private void filterDate() {
        System.out.print("\nEnter date (yyyy-mm-dd): ");

        String command = null;
        command = input.nextLine();
        LocalDate date = LocalDate.parse(command);
        List<Activity> display = manager.getActivitiesWithDate(date);

        if (display.isEmpty()) {
            System.out.println("\nSorry, there are no activities!\nReturning to main menu...");
        } else {
            displayActivities(display);
            registerActivity(display);
        }
    }

    // EFFECTS: display options to filter by activity area and process user input
    private void filterArea() {
        displayFilterAreaOptions();
        String command = null;
        command = input.nextLine();
        List<Activity> display = new ArrayList<>();

        switch (command) {
            case "1":
                display = manager.getActivitiesWithArea(Area.VANCOUVER);
                break;
            case "2":
                display = manager.getActivitiesWithArea(Area.BURNABY);
                break;
            case "3":
                display = manager.getActivitiesWithArea(Area.SURREY);
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }

        if (display.isEmpty()) {
            System.out.println("\nSorry, there are no activities!\nReturning to main menu...");
        } else {
            displayActivities(display);
            registerActivity(display);
        }
    }

    // EFFECTS: display options for filtering by activity area
    private void displayFilterAreaOptions() {
        System.out.println("\nSelect activity Area:");
        System.out.println("\t(1) Vancouver");
        System.out.println("\t(2) Burnaby");
        System.out.println("\t(3) Surrey");

        System.out.print("\nEnter the number of your choice: ");
    }

    // EFFECTS: display provided list to user
    private void displayActivities(List<Activity> activities) {

        int index = 1;
        for (Activity a : activities) {
            System.out.println("\n(" + index + ") " + a.getTypeToPrint());
            System.out.println("\tArea: " + a.getAreaToPrint());
            System.out.println("\tDate: " + a.getDate().toString());
            index++;
        }
    }

    // REGISTER/REMOVE/CANCEL ========================================================================================

    // MODIFIES: this
    // EFFECTS: Display option to register in an activity and process user input for activity selection
    private void registerActivity(List<Activity> displayed) {
        displayRegisterActivityOptions();

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                System.out.print("\nEnter the number of the activity to register in: ");

                String index = null;
                index = input.nextLine();
                int i = Integer.parseInt(index) - 1;
                // https://codegym.cc/groups/posts/string-to-int-java

                Activity activity = displayed.get(i);
                if (manager.alreadyRegistered(activity)) {
                    System.out.println("\nYou are already registered in this activity!");
                } else {
                    manager.registerActivity(activity);
                    System.out.println("\nSuccess! You are now registered in the activity.");
                    System.out.println("Returning to main menu...");
                }
            case "2":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    public void displayRegisterActivityOptions() {
        System.out.println("\nSelect an option:");
        System.out.println("\t(1) Register in an activity");
        System.out.println("\t(2) Main menu");

        System.out.print("\nEnter the number of your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: Display option to delete a posting and process user input for posting selection
    private void deletePosting(List<Activity> displayed) {
        System.out.println("\nSelect an option:");
        System.out.println("\t(1) Delete a posting");
        System.out.println("\t(2) Main menu");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                System.out.print("\nEnter the number of the posting to delete: ");
                String index = null;
                index = input.nextLine();

                int i = Integer.parseInt(index) - 1;

                Activity posting = displayed.get(i);
                manager.deletePosting(posting);

                System.out.println("\nSuccess! Your posting is deleted.");
                System.out.println("Returning to main menu...");
            case "2":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    // MODIFIES: this
    // EFFECTS: Display option to cancel registration in an activity and process user input for activity selection
    private void cancelRegistration(List<Activity> displayed) {
        System.out.println("\nSelect an option:");
        System.out.println("\t(1) Cancel Registration for an activity");
        System.out.println("\t(2) Main menu");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                System.out.print("\nEnter the number of the activity to cancel: ");
                String index = null;
                index = input.nextLine();

                int i = Integer.parseInt(index) - 1;
                Activity posting = displayed.get(i);
                manager.cancelRegistration(posting);

                System.out.println("\nSuccess! Your registration is cancelled.");
                System.out.println("Returning to main menu...");
            case "2":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }


    // MODIFIES: this
    // EFFECTS: obtain and process input to create a new posting
    private void createPosting() {
        Type type = null;
        Area area = null;
        LocalDate date = null;

        String command = null;

        System.out.println("\n\nPost an activity");
        System.out.println("----------------");

        type = getActivityType();
        area = getActivityArea();

        // date selection
        System.out.print("\nEnter activity date (yyyy-mm-dd): ");

        command = input.nextLine();

        date = LocalDate.parse(command);

        manager.postActivity(type, area, date);

        System.out.println("\nSuccess! Your activity is posted.");
        System.out.println("Returning to main menu...");
    }

    // EFFECTS: obtain and return user input for activity type
    private Type getActivityType() {
        System.out.println("\nSelect activity type:");
        System.out.println("\t(1) Walking");
        System.out.println("\t(2) Running");
        System.out.println("\t(3) Biking");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                return Type.WALK;
            case "2":
                return Type.RUN;
            case "3":
                return Type.BIKE;
            default:
                System.out.print("Please enter a valid choice: ");
        }
        return null;
    }

    // EFFECTS: obtain and return user input for activity area
    private Area getActivityArea() {
        System.out.println("\nSelect activity area:");
        System.out.println("\t(1) Vancouver");
        System.out.println("\t(2) Burnaby");
        System.out.println("\t(3) Surrey");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                return Area.VANCOUVER;
            case "2":
                return Area.BURNABY;
            case "3":
                return Area.SURREY;
            default:
                System.out.print("Please enter a valid choice: ");
        }
        return null;
    }


    // JSON read/write methods ====================================================================
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            System.out.println("\nSuccess! Data saved.");
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to save to file.");
        }
    }

    private void loadData() {
        /*
        try {
            int x = 1;
        } catch (IOException e) {
            System.out.println("\nUnable to read from file.");
        }
         */
    }

}











