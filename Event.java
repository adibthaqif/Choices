//Whatcom Community College - Winter 2019
//CS240 Data Structures and Algorithm Analysis
//Professor Ryan Parsons
//AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller
//
//Constructor class to create an Event object. This object will be used as part of the Choices Game project.

public class Event {

    //Initialize variables
    public String aspect, text;
    public int reward, punishment;
    public boolean played;

    //CONSTRUCTOR. Takes in String text which will describe the specific event, and two int values that determine
    //the percentage added (for the reward) or deducted (for the punishment) from the running total.
    //Throws an IllegalArgumentException if the reward is negative and/or the punishment is positive.
    public Event(String aspect, String text, int reward, int punishment) {
        this.aspect = aspect;
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
        return "Event: " + text +"\nReward: +" + reward + "% \tPunishment: " + punishment + "%";
    }

    //add() method that takes in a String for the text, a positive int for the reward, and a negative value
    //for the punishment. This method allows you to add all three of these fields to an empty Event object.
    //Throws an IllegalStateException if the Event object is not null when trying to add these fields.
    //Throws an IllegalArgumentException if the reward value is negative, or if the punishment value is positive.
    public void add(String data, int newReward, int newPunishment) {
        if (!isEmpty()) {
            throw new IllegalStateException("You can only add to an empty Event object!");
        } else if (newReward < 0) {
            throw new IllegalArgumentException("Rewards must be positive!");
        } else if (newPunishment > 0) {
            throw new IllegalArgumentException("Punishments must be negative!");
        } else {
            this.text = data;
            this.reward = newReward;
            this.punishment = newPunishment;
        }
    }

    //delete() method that deletes the data in the text, reward, and punishment field's and returns an empty Event
    //object.
    public void delete() {
        this.aspect = null;
        this.text = null;
        this.reward = 0;
        this.punishment = 0;
    }

    public String getAspect() {
        return this.aspect;
    }

    public int getReward() {
        return this.reward;
    }

    public int getPunishment() {
        return this.punishment;
    }

    public String getEvent() {
        return this.text;
    }

    public int getEventHashCode() {
        return this.text.hashCode();
    }

    //isEmpty() method to check if the text field is null. If null, return true; else return false.
    public boolean isEmpty() {
        return (this.text == null);
    }


} //End of Class
