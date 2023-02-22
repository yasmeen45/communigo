package ui;

import model.Manager;

import java.util.Scanner;

public class Application {

    private enum Source {
        UPCOMING,
        REGISTERED,
        POSTED
    }

    private Manager manager;
    private Scanner input;
    private Source source;

    public Application() {
        this.manager = new Manager();
        this.input = new Scanner(System.in);
        this.input.useDelimiter("\n");
        this.source = null;
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runApp() {
        boolean runApp = true;
        String command = null;

        while (runApp) {
            displayMainMenu();
            command = input.nextLine();

            if (command.equals("q")) {
                runApp = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Goodbye!");
    }


    // EFFECTS: display menu options to user
    private void displayMainMenu() {
        System.out.println("\n====== welcome to communigo! =====");
        System.out.println("\nplease select an option:");
        System.out.println("\t-> (u)pcoming activities (view/post/register)");
        System.out.println("\t-> (r)egistered activities (view/cancel)");
        System.out.println("\t-> (p)osted activities (view/remove)");
        System.out.println("\t-> (q)uit");
    }

    // EFFECTS: get user input
    private void getUserInput() {
        String command = null;
        command = input.nextLine();
        processCommand(command);
    }

    // EFFECTS: process user input to determine next action
    private void processCommand(String command) {
        switch (command) {
            // main menu option:
            case "u":
                displayUpcomingActivitiesMenu();
                break;
            case "r":
                displayRegisteredActivitiesMenu();
                break;
            case "p":
                displayPostedActivitiesMenu();
                break;
            // upcoming activities menu
            case "p":
                displayPostedActivitiesMenu();
                break;
            // other:
            case "f":
                filterActivities();
                break;
            case "m":
                break;
            default:
                System.out.println("invalid selection. please select another option!");
        }
    }

    // EFFECTS: filter activities
    private void filterActivities() {
        System.out.println("\nfilter by:");
        System.out.println("\t-> (t)ype");
        System.out.println("\t-> (d)ate");
        System.out.println("\t-> (a)rea");
        String command = null;
        command = input.nextLine();
        processCommandFilter(command);
    }

    // EFFECTS: process user input for filter type
    private void processCommandFilter(String command) {
        switch (command) {
            case "t":
                // call manager function to filter by type.
                break;
            case "r":
                displayRegisteredActivitiesMenu();
                break;
            case "p":
                displayPostedActivitiesMenu();
                break;
            default:
                System.out.println("invalid selection. please select another option!");
        }
    }

    // EFFECTS: display options for filtering activities
    private void displayFilterOptions() {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: display upcoming activities and menu options to user
    private void displayUpcomingActivitiesMenu() {
        System.out.println("\n----- upcoming activities -----");
        System.out.println("\nplease select an option:");
        System.out.println("\t-> (f)ilter activities");
        System.out.println("\t-> (j)oin an activity");
        System.out.println("\t-> (p)ost an activity");
        System.out.println("\t-> (m)ain menu");
        this.source = Source.UPCOMING;
        getUserInput();
    }

    // MODIFIES: this
    // EFFECTS: display registered activities and menu options to user
    private void displayRegisteredActivitiesMenu() {
        System.out.println("\n----- registered activities -----");
        System.out.println("\nplease select an option:");
        System.out.println("\t-> (f)ilter activities");
        System.out.println("\t-> (c)ancel registration");
        System.out.println("\t-> (m)ain menu");
        this.source = Source.REGISTERED;
        getUserInput();
    }

    // MODIFIES: this
    // EFFECTS: display posted activities and menu options to user
    private void displayPostedActivitiesMenu() {
        System.out.println("\n----- posted activities -----");
        System.out.println("\nplease select an option:");
        System.out.println("\t-> (f)ilter activities");
        System.out.println("\t-> (r)emove posting");
        System.out.println("\t-> (m)ain menu");
        this.source = Source.POSTED;
        getUserInput();
    }

    /*
    private void viewActivitiesJoined() {
        get activitiesJoined from user class
        ask how they would like to filter them -> go to appropriate filter function
        display result after filtering
        list actions (back to main menu, filter again)
    } */

    /*
    private void displayActivities(List<Activity>) {
        display all activities in given list
    } */

}
