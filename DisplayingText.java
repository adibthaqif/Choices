package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DisplayingText extends Application {
    @Override
    public void start(Stage primarystage) {
        //Creating a Text object
        Text text = new Text();

        //Setting font to the text
        text.setFont(new Font(15));

        //setting the position of the text
        text.setX(150);
        text.setY(130);

        //Setting the text to be added.
        text.setText("Created by Adib, Don, Andrew and Micah");

        //Creating a Group object
        Group root = new Group();

        //Retrieving the observable list object
        ObservableList list = root.getChildren();

        //Setting the text object as a node to the group object
        list.add(text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        primarystage.setTitle("Welcome to the Game");

        //Adding scene to the stage
        primarystage.setScene(scene);

        //Displaying the contents of the stage
        primarystage.show();
    }


    public static void main(String args[]) {
        launch(args);
    }
}
