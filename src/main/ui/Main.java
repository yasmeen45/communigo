package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main implements WindowListener {
    public static void main(String[] args) {
        // run GUI:
        new GUI();

        // run console-based application:
        // new Application();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        EventLog log = EventLog.getInstance();
        System.out.println("Event Log");
        System.out.println("=========\n");

        for (Event event : log) {
            System.out.println(e.toString());
        }

        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
