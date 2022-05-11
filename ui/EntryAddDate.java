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

//This is a JPanel class that allows user to start entering their entry for the day. This panel is where they
//     add the date of which they are making their entry.
public class EntryAddDate extends JPanel implements ActionListener {
    private JFrame frame = new MyFrame();
    private String dateFormat = "([1][0-2]|[0][1-9])/([0-3][0-1]|[0][1-9]|[1-2][0-9])/([2][0][2-9][2-9])";
    private DayEntry water = new DayEntry("", 0);
    private Progress myProgress;
    private GraphEntry myGraph;
    private JLabel label2 = new JLabel("Enter the date (MM/DD/YYYY):");
    private JTextField text1 = new JTextField();
    private JButton enterDate;
    private JButton back;
    private ArrayList<JButton> buttons = new ArrayList<>();

    //REQUIRES: MainPage
    //MODIFIES: this
    //EFFECTS: constructs the panel for user to enter the date
    public EntryAddDate(Progress myProgress, GraphEntry myGraph) {
        this.myProgress = myProgress;
        this.myGraph = myGraph;

        this.setBorder(BorderFactory.createEmptyBorder(200,100,200,100));
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(new Color(0xadd8e6));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(label2);
        text1.setMaximumSize(new Dimension(400, 25));
        this.add(text1);

        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        enterDate = enterButton();
        back = backButton();

        this.add(enterDate);
        this.add(back);

        frame.add(this, BorderLayout.CENTER);

        enterDate.addActionListener(this);
        returnToMainPage(back, enterDate, this);
    }

    //EFFECTS: creates an enter button
    public JButton enterButton() {
        JButton enter = new JButton("Enter");
        enter.setBounds(20, 20,100, 50);
        enter.setVisible(true);
        return enter;
    }

    //EFFECTS: creates a back button
    public JButton backButton() {
        back = new JButton("Back");
        back.setBounds(20, 20,100, 50);
        back.setVisible(true);
        return back;
    }

    //REQUIRES: back
    //MODIFIES: this, button1, button2
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

    //MODIFIES: this
    //EFFECTS: checks if the user inputted the date in the correct format,
    //         if so, adds the date to DayEntry instance panel changes to EntryAddAmount
    //         if not, prompts user to follow the date format
    public void entryCheckDateFormat() {
        String date = text1.getText();
        ImageIcon icon = new ImageIcon("./src/waterdropangry.png");
        if (Pattern.matches(dateFormat, date)) {
            water.enterDate(date);
            EntryAddAmount entry2 = new EntryAddAmount(water, myProgress, myGraph);
        } else {
            JOptionPane.showMessageDialog(null,"Please follow date format", "error",
                    JOptionPane.ERROR_MESSAGE, icon);
        }
    }

    //REQUIRES: enterDate
    //MODIFIES: this
    //EFFECTS: action performed when the enter button is pressed, takes user to EntryAddAmount
    @Override
    public void actionPerformed(ActionEvent e) {
        entryCheckDateFormat();
    }
}
