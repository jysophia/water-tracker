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

//This class is a JPanel where a user can see their progress on a specified date
public class ProgressPage extends JPanel implements ActionListener {
    private JFrame frame = new MyFrame();
    private String dateFormat = "([1][0-2]|[0][1-9])/([0-3][0-1]|[0][1-9]|[1-2][0-9])/([2][0][2-9][2-9])";
    private JTextField text3 = new JTextField();
    private JLabel label4 = new JLabel("Enter the exact date that you want to view (MM/DD/YYYY)");
    private JButton enterProgress;
    private JButton back;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Progress myProgress;
    private GraphEntry myGraph;

    //REQUIRES: MainPage
    //MODIFIES: this
    //EFFECTS: constructs the ProgressPage panel
    public ProgressPage(Progress myProgress, GraphEntry myGraph) {
        this.myProgress = myProgress;
        this.myGraph = myGraph;

        this.setBorder(BorderFactory.createEmptyBorder(200,100,200,100));
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(new Color(0xadd8e6));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(label4);
        text3.setMaximumSize(new Dimension(400, 25));
        this.add(text3);
        enterProgress = enterButton();
        back = backButton();
        this.add(enterProgress);
        this.add(back);
        this.setVisible(true);
        frame.add(this, BorderLayout.CENTER);
        enterProgress.addActionListener(this);
        returnToMainPage(back, enterProgress, this);
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

    //REQUIRES: enterProgress
    //MODIFIES: this
    //EFFECTS: action performed when enterProgress is clicked, calls the progressShowAmount method
    @Override
    public void actionPerformed(ActionEvent e) {
        progressShowAmount();
    }

    //REQUIRES: enterProgress
    //MODIFIES: this
    //EFFECTS: checks if the date that user specified is provided in the required format,
    //         if so, calls the printAmount method to check if they made any entries for that day
    //                if so, application tells user how much water they drank that day
    //                if not, application tells user that they did not make any entries that day
    //         if not, prompts user to follow date format
    private void progressShowAmount() {
        String date = text3.getText();
        ImageIcon icon = new ImageIcon("./src/waterdrop3.png");
        ImageIcon icon2 = new ImageIcon("./src/waterdropsad.png");
        ImageIcon icon3 = new ImageIcon("./src/waterdropangry.png");
        if (Pattern.matches(dateFormat, date)) {
            int amt = printAmount(date);
            if (amt == 0) {
                JOptionPane.showMessageDialog(null,"You did not make any entries on "
                                + date + ".", "My Progress", JOptionPane.PLAIN_MESSAGE, icon2);
            } else {
                JOptionPane.showMessageDialog(null,"On " + date + ", you drank " + amt
                                + "mL out of the recommended 3000 mL/day.", "My Progress",
                        JOptionPane.PLAIN_MESSAGE, icon);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Please follow date format", "error",
                    JOptionPane.ERROR_MESSAGE, icon3);
        }
    }

    //REQUIRES: progressShowAmount
    //MODIFIES: date
    //EFFECTS: sums up the amount of water drank on the date specified by the user
    private int printAmount(String date) {
        int total = 0;
        for (DayEntry progress: myProgress.getProgress()) {
            if (date.equals(progress.getDate())) {
                total += progress.getAmount();
            }
        }
        return total;
    }
}
