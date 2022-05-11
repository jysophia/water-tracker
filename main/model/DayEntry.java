package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.regex.Pattern;

//Reference: JsonSerializationDemo repo link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//This class represents each entry that is made by the user, which includes the date (String) and
//     the amount of water drank (int) in that particular moment of the day. Users can make more than one
//     DayEntry per day as long as they date it.
public class DayEntry implements Writable {
    private String date;      //date of entry
    private int amount;       //amount of water drank in that day
    
    public DayEntry(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    //EFFECTS: gets the date
    public String getDate() {
        return date;
    }

    //EFFECTS: gets the amount of water drank
    public int getAmount() {
        return amount;
    }

    //REQUIRES: date of the DayEntry of interest
    //MODIFIES: this
    //EFFECT: returns the date if it matches the regex format
    public void enterDate(String date) {
        if (Pattern.matches("([1][0-2]|[0][1-9])/([0-3][0-1]|[0][1-9]|[1-2][0-9])/([2][0][2-9][2-9])",
                date)) {
            this.date = date;
        }
    }

    //REQUIRES: amount of water drank
    //MODIFIES: this
    //EFFECT: adds the inputted amount of water drank
    public void enterAmount(int waterAmount) {
        if (waterAmount > 0) {
            this.amount = waterAmount;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("amount", amount);
        return json;
    }
}
