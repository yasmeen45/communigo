package ui;

import model.Activity;
import model.Manager;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Scanner;

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

    private Manager manager;
    private Scanner input;

    // EFFECTS: creates a new instance of application with a manager, Scanner (with a delimiter), and runs application
    public Application() {
        this.manager = new Manager();
        this.input = new Scanner(System.in);
        this.input.useDelimiter("\n");
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

            if (command.equals("5")) {
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
        System.out.println("(5) Quit");

        System.out.print("\nEnter the number of your choice: ");
    }


    // MODIFIES: this
    // EFFECTS: process user input to determine next action from main menu
    private void processCommandMainMenu(String command) {
        switch (command) {
            case "1":
                viewActivities("upcoming");
                registerActivity();
                break;
            case "2":
                createPosting();
                break;
            case "3":
                viewActivities("posted");
                deletePosting();
                break;
            case "4":
                viewActivities("registered");
                cancelRegistration();
                break;
            case "5":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }


    // DISPLAYING ACTIVITIES =======================================================================================

    // MODIFIES: this
    // EFFECTS: display menu to filter and list activities
    private void viewActivities(String type) {
        String firstLetterCap = type.substring(0, 1).toUpperCase() + type.substring(1);
        //https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
        System.out.println("\n\n" + firstLetterCap + " Activities");
        System.out.println("-------------------");

        System.out.println("\nView " + type + " activities by:");
        System.out.println("\t(1) Type");
        System.out.println("\t(2) Date");
        System.out.println("\t(3) Area");
        System.out.println("\t(4) View all");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();
        processCommandViewActivity(command, type);
    }

    // MODIFIES: this
    // EFFECTS: process user input to determine next action
    private void processCommandViewActivity(String command, String type) {
        switch (command) {
            case "1":
                filterType(type);
                break;
            case "2":
                filterDate(type);
                break;
            case "3":
                filterArea(type);
                break;
            case "4":
                displayActivities(manager.getActivitiesChronological());
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    // MODIFIES: this
    // EFFECTS: display options to filter by activity type and process user input
    private void filterType(String type) {
        System.out.println("\nSelect activity type:");
        System.out.println("\t(1) Walking");
        System.out.println("\t(2) Running");
        System.out.println("\t(3) Biking");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                // displayActivities(manager.filterByType("WALK", type);
                // TODO - set up manager methods to get appropriate activity lists
            case "2":
                break;
            case "3":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    // EFFECTS: process user input to display activities of a specific date
    private void filterDate(String type) {
        System.out.print("\nEnter date (yyyy-mm-dd): ");

        String command = null;
        command = input.nextLine();
        LocalDate date = LocalDate.parse(command);

        // displayActivities(manager.filterByDate(date, type)
        // TODO - make manager method to return activities with appropriate date
    }

    // EFFECTS: display options to filter by activity area and process user input
    private void filterArea(String type) {
        System.out.println("\nSelect activity Area:");
        System.out.println("\t(1) Vancouver");
        System.out.println("\t(2) Burnaby");
        System.out.println("\t(3) Surrey");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                // displayActivities(manager.filterByArea("VANCOUVER", type);
                // TODO - set up manager methods to get appropriate activity lists
            case "2":
                break;
            case "3":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
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

    // EFFECTS: Display option to register in an activity and process user input for activity selection
    private void registerActivity() {
        System.out.println("\nSelect an option:");
        System.out.println("\t(1) Register in an activity");
        System.out.println("\t(2) Main menu");

        System.out.print("\nEnter the number of your choice: ");

        String command = null;
        command = input.nextLine();

        switch (command) {
            case "1":
                System.out.print("\nEnter the number of the activity to register in: ");
                String index = null;
                index = input.nextLine();
                // manager.registerActivity(index);
                // TODO - registerActivity method in manager
                System.out.println("\nSuccess! You are now registered in the activity.");
                System.out.println("Returning to main menu...");
            case "2":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    // EFFECTS: Display option to delete a posting and process user input for posting selection
    private void deletePosting() {
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
                // manager.deletePosting(index);
                // TODO - deletePosting method in manager
                System.out.println("\nSuccess! Your posting is deleted.");
                System.out.println("Returning to main menu...");
            case "2":
                break;
            default:
                System.out.print("Please enter a valid choice: ");
        }
    }

    // EFFECTS: Display option to cancel registration in an activity and process user input for activity selection
    private void cancelRegistration() {
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
                // manager.cancelRegistration(index);
                // TODO - cancelRegistration method in manager
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

        // TODO - manager.createActivity(type, date, area);

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

}











