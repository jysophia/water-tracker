package ui;

import model.DayEntry;
import model.EventLog;
import model.Event;
import model.GraphEntry;
import model.Progress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//This class is a JPanel where the user can input the amount of water they drank that day.
//    This panel follows EntryAddDate.
public class EntryAddAmount extends JPanel implements ActionListener {
    private JFrame frame = new MyFrame();
    private JButton enterAmountButton;
    private JButton back;
    private JLabel label3 = new JLabel("How much water did you drink (in mL)?");
    private JTextField text2 = new JTextField();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private DayEntry water;
    private Progress myProgress;
    private GraphEntry myGraph;

    //REQUIRES: MainPage
    //MODIFIES: this
    //EFFECTS: constructs the panel for user to enter the amount of water they drank that day
    public EntryAddAmount(DayEntry water, Progress myProgress, GraphEntry myGraph) {
        this.water = water;
        this.myProgress = myProgress;
        this.myGraph = myGraph;

        this.setBorder(BorderFactory.createEmptyBorder(200,100,200,100));
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(new Color(0xadd8e6));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(label3);
        text2.setMaximumSize(new Dimension(400, 25));
        this.add(text2);

        enterAmountButton = enterButton();
        back = backButton();
        this.add(enterAmountButton);
        this.add(back);

        this.setVisible(true);
        frame.add(this, BorderLayout.CENTER);
        enterAmountButton.addActionListener(this);
        returnToMainPage(back, enterAmountButton, this);
    }

    //EFFECTS: creates enter button
    public JButton enterButton() {
        JButton enter = new JButton("Enter");
        enter.setBounds(20, 20,100, 50);
        enter.setVisible(true);
        return enter;
    }

    //EFFECTS: creates back button
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

    //REQUIRES: enterAmount
    //MODIFIES: this
    //EFFECTS: action performed when the enter button is pressed, calls the addAmountToProgress method
    @Override
    public void actionPerformed(ActionEvent e) {
        addAmountToProgress();
    }

    //REQUIRES: enterAmount
    //MODIFIES: this
    //EFFECTS: adds the amount of water they drank that day to DayEntry instance
    private void addAmountToProgress() {
        ImageIcon icon = new ImageIcon("./src/waterdrop3.png");
        ImageIcon icon2 = new ImageIcon("./src/waterdropangry.png");
        int a = Integer.parseInt(text2.getText());
        if (a > 0) {
            water.enterAmount(a);
            myProgress.addToProgress(water);
            JOptionPane.showMessageDialog(null, "Keep it up!", "Today's Entry",
                    JOptionPane.PLAIN_MESSAGE, icon);
        } else {
            JOptionPane.showMessageDialog(null,"Go drink more water!", "denied",
                    JOptionPane.PLAIN_MESSAGE, icon2);
        }
        water = new DayEntry("", 0);
        for (JButton currentButton : buttons) {
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }
        }
    }
}
