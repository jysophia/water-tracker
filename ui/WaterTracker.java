package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;

//Reference: JsonSerializationDemo repo link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//WaterTracker represents the ui of the app, where all the user stories will come to action
//NOTE: code that's been commented out are part of previous versions of the project and
//      will be used for future reference
public class WaterTracker {
    private Progress myProgress = new Progress();
    private GraphEntry myGraph = new GraphEntry();
//    private JFrame frame = new MyFrame();
    private MainPage mainPage = new MainPage(myProgress, myGraph);

    //REQUIRES: string input
    //EFFECT: starts the water tracker
    public WaterTracker() {
        startTracker();
    }

    //EFFECT: allows user to make an input based on the main menu that appears
    private void startTracker() {

//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);

        //String entry = null;

//        init();

//        trackerMenu();

        //entry = scan.next();

//        if (entry.equals("q")) {
//            System.out.println("Alright, see you next time!");
//        } else {
//        startActivity(entry);
//        }
    }

    //REQUIRES: startTracker(), single character string input
    //MODIFIES: this
    //EFFECT: allows user to specify which part of the water tracker they want to use
    //        if user wants to make an entry, then press "e"
    //        if user wants to see their progress on a certain day, then press "p"
    //        otherwise invalid entry
//    private void startActivity(String entry) {
//
//        if (entry.equals("e")) {
//            addDate();
//        } else if (entry.equals("p")) {
//            showProgress();
//        } else if (entry.equals("g")) {
//            makeMyGraph();
//        }  else if (entry.equals("s")) {
//            save();
//        } else if (entry.equals("l")) {
//            load();
//        } else {
//            System.out.println("Invalid entry...");
//            startTracker();
//        }
//    }

//    public void makeMyGraph() {
//        String entry = null;
//
//        System.out.println("Enter the dates you want to graph, one at a time. Press f when you are finished.");
//        entry = scan.next();
//
//        int total = 0;
//
//        if (Pattern.matches(dateFormat, entry)) {
//            DayEntry w = new DayEntry(entry, total);
//            w.enterDate(entry);
//            for (DayEntry dayEntry: myProgress.getProgress()) {
//                if (entry.equals(dayEntry.getDate())) {
//                    total += dayEntry.getAmount();
//                }
//            }
//            w.enterAmount(total);
//            myGraph.addToGraph(w);
//            makeMyGraph();
//        } else if (entry.equals("f")) {
//            System.out.println(myGraph.getGraphEntry());
//            pressToReturn();
//        }
//    }

//    private void addToGraph() {
//        int total = 0;
    //      String date = scan.nextLine();
//
//        if (Pattern.matches(dateFormat, date)) {
//            DayEntry w1 = new DayEntry(date, total);
//            w1.enterDate(date);
//            for (DayEntry dayEntry : myProgress.getProgress()) {
//                if (date.equals(dayEntry.getDate())) {
//                    total += dayEntry.getAmount();
//                }
//            }
//            w1.enterAmount(total);
//            myGraph.addToGraph(w1);
////        } else if (date.equals("f")) {
////            System.out.println(myGraph.getGraphEntry());
//               pressToReturn();
////        }
//    }

//    private void pressToReturn() {
//        String entry = null;
//
//        System.out.println("Press r to return to previous menu.");
//        entry = scan.next();
//
//        if (entry.equals("r")) {
//            startTracker();
//        } else {
//            System.out.println("Invalid entry...");
//            pressToReturn();
//        }
//    }

    //REQUIRES: startActivity(), string input
    //EFFECT: allows user to see their progress on a certain day or return to the main menu
//    public void showProgress() {
//        String entry = null;
//
//        System.out.println("Enter the exact date that you want to view (MM/DD/YYYY) or press r to return "
//                + "to previous menu:");
//        entry = scan.next();
//
//        if (entry.equals("r")) {
//            startTracker();
//        } else if (Pattern.matches(dateFormat, entry)) {
//            printAmount(entry);
//        } else {
//            System.out.println("Please follow the date format.");
//            showProgress();
//        }
//    }


    //REQUIRES: showProgress(), string input of the date that follows the proper regex format
    //MODIFIES: this, amount
    //EFFECT: calculates the total amount inputted with the given date by
    //        searching through the Progress class and adding all the amounts associated with the given date
//    private int printAmount(String date) {
//        int total = 0;
//        for (DayEntry progress: myProgress.getProgress()) {
//            if (date.equals(progress.getDate())) {
//                total += progress.getAmount();
//            }
//        }
//        return total;
////        if (total == 0) {
////            System.out.println("You did not make any entries on " + date + ".");
////        } else {
////            System.out.println("On " + date + ", you drank " + total + "mL out of the recommended 3000 mL/day.");
////        }
////        endProgress();
//    }

    //REQUIRES: printAmount(), string input
    //EFFECT: returns to the main menu if "r" is pressed, otherwise remains in this method (invalid entry)
//    private void endProgress() {
//        System.out.println("Press r to return to the main menu.");
//        String key = scan.next();
//        if (key.equals("r")) {
//            startTracker();
//        } else {
//            System.out.println("Invalid entry...");
//            endProgress();
//        }
//    }

    //REQUIRES: startActivity(), string input
    //EFFECT: allows user to input the date they want to add an entry for, following the regex format
//    public void addDate() {
//    }

//    public void entryCheckDateFormat() {
//        String date = scan.next();
//        if (Pattern.matches(dateFormat, date)) {
//            water.enterDate(date);
//            addAmount();
////            System.out.println("HERE :)");
////            entry1.setVisible(false);
////            entry2.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null,"Please follow date format", "error",
//                    JOptionPane.ERROR_MESSAGE);
////               JLabel label2 = new JLabel("Please follow date format");
////               label2.setForeground(Color.red);
////               mainPage.add(label2);
//        }
//    }
//
    //REQUIRES: addDate(), integer input
    //EFFECT: allows user to input the amount of water they drank in the specified date
//    private void addAmount() {
//        System.out.println("How much water did you drink (in mL)?");
//        int amount = scan.nextInt();
//
//        if (amount > 0) {
//            water.enterAmount(amount);
//            myProgress.addToProgress(water);
//            System.out.println("Keep it up!");
//        } else {
//            System.out.println("Go drink more water and come back again!");
//        }
//        startTracker();
//    }

//    private void addAmountToProgress() {
//        int a = Integer.parseInt(text2.getText());
//        if (a > 0) {
//            water.enterAmount(a);
//            myProgress.addToProgress(water);
//            JOptionPane.showMessageDialog(null,"Keep it up!", "Today's Entry",
//                    JOptionPane.PLAIN_MESSAGE);
////           JLabel msg = new JLabel("Keep it up!");
////                msg.setForeground(Color.blue);
////                entry2.add(msg);
//        } else {
//            JOptionPane.showMessageDialog(null,"Go drink more water!", "denied",
//                    JOptionPane.PLAIN_MESSAGE);
////                JLabel msg2 = new JLabel("Go drink more water and come back again!");
////                msg2.setForeground(Color.blue);
////                entry2.add(msg2);
//        }
//        water = new DayEntry("", 0);
//        for (JButton currentButton : buttons) {
//            for (ActionListener al : currentButton.getActionListeners()) {
//                currentButton.removeActionListener(al);
//            }
//        }
//    }

    //REQUIRES: startTracker()
    //EFFECT: presents the main menu at the onset of starting water tracker
//    private void trackerMenu() {
//        System.out.println("\nSelect from the following:");
//        System.out.println("\te -> make an entry");
//        System.out.println("\tp -> show progress");
//        System.out.println("\tg -> show graph");
//        System.out.println("\ts -> save data");
//        System.out.println("\tl -> load data");
//        System.out.println("\tq -> quit");
//    }

    //REQUIRES: startTracker()
    //EFFECT: initializes the water tracker by instantiating a new DayEntry class and
    //        using the delimiter function to allow for proper utilization of Scanner and inputs
//    private void init() {
//        water = new DayEntry("", 0);
//        //scan.useDelimiter("\n");
//    }

    // MODIFIES: this
    // EFFECTS: loads graph from file
//    public void load() {
//        try {
//            Map.Entry<Progress, GraphEntry> out = jsonReader.read();
//            myGraph = out.getValue();
//            myProgress = out.getKey();
//            JOptionPane.showMessageDialog(null,"Loaded data from " + JSON_STORE,
//                    "Loading Data", JOptionPane.PLAIN_MESSAGE);
//            //System.out.println("Loaded data from " + JSON_STORE);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE,
//                    "Saving Data",
//                    JOptionPane.ERROR_MESSAGE);
//        }
//        //startTracker();
//    }

    // MODIFIES: this
    // EFFECTS: saves progress to file
//    public void save() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(myProgress, myGraph);
//            jsonWriter.close();
//            JOptionPane.showMessageDialog(null,"Saved!", "Saving Data",
//                    JOptionPane.PLAIN_MESSAGE);
//            //System.out.println("Saved " + myProgress.getProgress() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE,
//                    "Saving Data",
//                    JOptionPane.ERROR_MESSAGE);
//            //System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//        //startTracker();
//    }
}
