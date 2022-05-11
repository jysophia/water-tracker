package model;

import java.util.Calendar;
import java.util.Date;

//Reference: AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
//The Event class allows application to mark when a user made a new entry each time they do
public class Event {

    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    //MODIFIES: this
    //EFFECTS: constructs an event with the given description and current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: gets the date of the event including time
    public Date getDate() {
        return dateLogged;
    }

    //EFFECTS: gets the description of the event
    public String getDescription() {
        return description;
    }

    //MODIFIES: this
    //EFFECTS: this prevents the same event from getting logged more than once
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));
    }

    //MODIFIES: this
    //EFFECTS: re-formats the date and description logged into hash code
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    //MODIFIES: this
    //EFFECTS: converts the date and description into a string
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
