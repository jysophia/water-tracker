package ui;

import model.EventLog;
import model.Event;
import model.GraphEntry;
import model.Progress;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

//This class is a JPanel that displays the first page when a user opens the application
public class MainPage extends JPanel implements ActionListener {
    private JFrame frame = new MyFrame();
    private static final String JSON_STORE = "./data/waterTracker.json";
    private JLabel label1 = new JLabel("Select from the following:");
    private JButton makeEntryButton;
    private JButton showProgressButton;
    private JButton showGraphButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Progress myProgress;
    private GraphEntry myGraph;

    //MODIFIES: this
    //EFFECTS: constructs the main page of the application
    public MainPage(Progress myProgress, GraphEntry myGraph) {
        this.myProgress = myProgress;
        this.myGraph = myGraph;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.setBorder(BorderFactory.createEmptyBorder(200,100,200,100));
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(new Color(0xadd8e6));
        this.setVisible(true);
        this.add(label1);

        mainPageButtons();

        frame.add(this, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECT: displays the main page with all options to explore the application
    //        "Make an entry" takes user to the panel where they can make their entry for the day
    //        "Show progress" takes user to the panel where they can see their progress for a certain day
    //        "Show my history or compare progress" allows user to compare multiple entries and see their history
    //        "Save my data" allows user to save their data
    //        "Load my data" allows user to load from their previously saved data
    //        "Quit" allows user to quit application, giving them an alert with the option to save before quitting
    private void mainPageButtons() {
        makeEntryButton = new JButton("Make an entry");
        this.add(makeEntryButton);
        makeEntryButton.addActionListener(this);

        showProgressButton = new JButton("Show progress");
        this.add(showProgressButton);
        showProgressButton.addActionListener(this);

        showGraphButton = new JButton("Show my history or compare progress");
        this.add(showGraphButton);
        showGraphButton.addActionListener(this);

        saveButton = new JButton("Save my data");
        this.add(saveButton);
        saveButton.addActionListener(this);

        loadButton = new JButton("Load my data");
        this.add(loadButton);
        loadButton.addActionListener(this);

        quitButton = new JButton("Quit");
        this.add(quitButton);
        quitButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECT: action performed after a button is pressed,
    //        takes user to the ensuing panel upon pressing the following buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == makeEntryButton) {
            this.setVisible(false);
            EntryAddDate entry1 = new EntryAddDate(myProgress, myGraph);
        } else if (e.getSource() == showProgressButton) {
            this.setVisible(false);
            ProgressPage progress = new ProgressPage(myProgress, myGraph);
        } else if (e.getSource() == showGraphButton) {
            this.setVisible(false);
            DisplayMultipleEntries graph = new DisplayMultipleEntries(myProgress, myGraph);
        } else if (e.getSource() == saveButton) {
            save();
        } else if (e.getSource() == loadButton) {
            load();
        } else if (e.getSource() == quitButton) {
            quit();
        }
    }

    // MODIFIES: this
    // EFFECTS: saves progress to file
    public void save() {
        ImageIcon icon = new ImageIcon("./src/waterdrop3.png");
        ImageIcon icon2 = new ImageIcon("./src/waterdropsad.png");
        try {
            jsonWriter.open();
            jsonWriter.write(myProgress, myGraph);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,"Saved!", "Saving Data",
                    JOptionPane.PLAIN_MESSAGE, icon);
            //System.out.println("Saved " + myProgress.getProgress() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE,
                    "Saving Data", JOptionPane.ERROR_MESSAGE, icon2);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads graph from file
    public void load() {
        ImageIcon icon = new ImageIcon("./src/waterdrop3.png");
        ImageIcon icon2 = new ImageIcon("./src/waterdropsad.png");
        try {
            Map.Entry<Progress, GraphEntry> out = jsonReader.read();
            myGraph = out.getValue();
            myProgress = out.getKey();
            JOptionPane.showMessageDialog(null,"Loaded data from " + JSON_STORE,
                    "Loading Data", JOptionPane.PLAIN_MESSAGE, icon);
            //System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE,
                    "Saving Data", JOptionPane.ERROR_MESSAGE, icon2);
        }
    }

    public void quit() {
        ImageIcon icon = new ImageIcon("./src/waterdropsurprised.png");
        int answer = JOptionPane.showOptionDialog(null, "Do you want to save?", "Quit",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, null,
                0);
        if (answer == 0) {
            save();
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.getDate());
                System.out.println(event.getDescription());
            }
            System.exit(0);
        } else if (answer == 1) {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.getDate());
                System.out.println(event.getDescription());
            }
            System.exit(0);
        }
    }
}
