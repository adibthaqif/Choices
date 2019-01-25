//Whatcom Community College - Winter 2019
//CS240 Data Structures and Algorithm Analysis
//Professor Ryan Parsons
//AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller
//
//Constructor class to create an Event object. This object will be used as part of the Choices Game project.

public class Event {

    //Initialize variables
    public String text;
    public int reward, punishment;

    //CONSTRUCTOR. Takes in String text which will describe the specific event, and two int values that determine
    //the percentage added (for the reward) or deducted (for the punishment) from the running total.
    //Throws an IllegalArgumentException if the reward is negative and/or the punishment is positive.
    public Event(String text, int reward, int punishment) {
        this.text = text;
        if (reward < 0) {
            throw new IllegalArgumentException("Rewards must be positive!");
        } else {
            this.reward = reward;
        }
        if (punishment > 0) {
            throw new IllegalArgumentException("Punishments must be negative!");
        } else {
            this.punishment = punishment;
        }
    }

    //CONSTRUCTOR. Returns an empty Event object.
    public Event() {
    }

    //toString() method that prints out the Event's text, followed by both the reward and punishment values.
    public String toString() {
        return "Event: " + text +"\nReward: " + reward + "% \tPunishment: " + punishment + "%";
    }

}
