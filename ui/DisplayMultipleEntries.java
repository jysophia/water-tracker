package ui;

import model.DayEntry;
import model.GraphEntry;
import model.Progress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

//This class is a JPanel where users can compare multiple entries they already made or see their entire history
//    of entries
public class DisplayMultipleEntries extends JPanel implements ActionListener {
    private JFrame frame = new MyFrame();
    private String dateFormat = "([1][0-2]|[0][1-9])/([0-3][0-1]|[0][1-9]|[1-2][0-9])/([2][0][2-9][2-9])";
    private JTextField text4 = new JTextField();
    private JLabel label5 = new JLabel("Enter the dates you want to compare (MM/DD/YYYY) and press Enter.");
    private JButton enterGraph;
    private JButton back;
    private JButton showHistory = new JButton("Click to see everything in my history");
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Progress myProgress;
    private GraphEntry myGraph;

    //REQUIRES: MainPage
    //MODIFIES: this
    //EFFECTS: constructs the DisplayMultipleEntries panel
    public DisplayMultipleEntries(Progress myProgress, GraphEntry myGraph) {
        this.myProgress = myProgress;
        this.myGraph = myGraph;

        this.setBorder(BorderFactory.createEmptyBorder(200,100,200,100));
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(new Color(0xadd8e6));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(label5);

        text4.setMaximumSize(new Dimension(400, 25));
        this.add(text4);

        enterGraph = enterButton();
        this.add(enterGraph);

        this.add(showHistory);

        back = backButton();
        this.add(back);
        this.setVisible(true);
        frame.add(this, BorderLayout.CENTER);
        enterGraph.addActionListener(this);
        showHistory.addActionListener(this);
        returnToMainPage(back, enterGraph, this);
    }

    //EFFECTS: creates the enter button
    public JButton enterButton() {
        JButton enter = new JButton("Enter");
        enter.setBounds(20, 20,100, 50);
        enter.setVisible(true);
        return enter;
    }

    //EFFECTS: creates the back button
    public JButton backButton() {
        back = new JButton("Back");
        back.setBounds(20, 20,100, 50);
        back.setVisible(true);
        return back;
    }

    //REQUIRES: back
    //MODIFIES: this, button1, button2, button3
    //EFFECTS: action performed when the back button is pressed, takes user back to the MainPage
    //         and removes all action listeners associated with the panel
    public void returnToMainPage(JButton button1, JButton button2, JPanel panel) {
        button1.addActionListener(e -> {
            panel.setVisible(false);
            MainPage mainPage = new MainPage(myProgress, myGraph);
            buttons.add(button2);
            for (JButton currentEnterButton : buttons) {
                for (ActionListener al : currentEnterButton.getActionListeners()) {
                    currentEnterButton.removeActionListener(al);
                }
            }
        });
    }

    //REQUIRES: enterGraph, showHistory
    //MODIFIES: this
    //EFFECTS: action performed when enterGraph and showHistory are clicked,
    //         calls displaySpecificEntries and displayAllEntries methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterGraph) {
            displaySpecificEntries();
        } else if (e.getSource() == showHistory) {
            displayAllEntries(myProgress);
        }
    }

    //REQUIRES: showHistory
    //MODIFIES: this
    //EFFECTS: displays all entries held in the Progress class on the panel
    private void displayAllEntries(Progress myProgress) {
        for (DayEntry e : myProgress.getProgress()) {
            String date = e.getDate();
            String amt = Integer.toString(e.getAmount());
            JLabel allEntries = new JLabel();
            allEntries.setText(date + ": " + amt + "mL");
            this.add(allEntries);
            this.revalidate();
            this.repaint();
        }
    }

    //REQUIRES: enterGraph
    //MODIFIES: this
    //EFFECTS: displays entries from all the dates that the user specified
    private void displaySpecificEntries() {
        int total = 0;
        String date = text4.getText();
        JLabel listOfDates = new JLabel();

        if (Pattern.matches(dateFormat, date)) {
            DayEntry w1 = new DayEntry(date, total);
            w1.enterDate(date);
            for (DayEntry dayEntry : myProgress.getProgress()) {
                if (date.equals(dayEntry.getDate())) {
                    total += dayEntry.getAmount();
                }
            }
            w1.enterAmount(total);
            myGraph.addToGraph(w1);

            listOfDates.setText(date + ": " + total + "mL");
            listOfDates.setForeground(Color.blue);
            this.add(listOfDates);
            this.revalidate();
            this.repaint();
        }
    }
}
