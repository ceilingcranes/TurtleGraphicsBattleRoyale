import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewApplication extends Application {

    private final IntegerProperty numPlayers = new SimpleIntegerProperty(1);

    class NumPlayersButton implements EventHandler<ActionEvent> {
        private final int number;
        private NumPlayersButton(int np){
            number = np;
        }
        @Override
        public void handle(ActionEvent e){
            numPlayers.set(number);
        }
    }

    private Pane startScreenSceneGraph(Stage primaryStage){
        int max_players  = 4;

        Text title = new Text();

        title.setText("Welcome to Turtle Graphics Battle Royale!");
        title.setTextAlignment(TextAlignment.CENTER);

        GridPane.setConstraints(title, 0, 0);
        GridPane.setMargin(title, new Insets(0,0, 200, 40));

        GridPane root = new GridPane();
        root.getChildren().add(title);
        root.setAlignment(Pos.CENTER);

        GridPane buttonGroup = new GridPane();
        buttonGroup.setHgap(20);
        for(int i=1; i<=max_players; i++){
            String text = Integer.toString(i)+" Player";
            Button button = new Button(text);
            button.setOnAction(new NumPlayersButton(i));
            GridPane.setConstraints(button, (i-1), 0);

            buttonGroup.getChildren().add(button);
        }

        GridPane.setConstraints(buttonGroup, 0, 1);

        root.getChildren().add(buttonGroup);

        Text number = new Text();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((event)-> {
            number.setText(numPlayers.toString());
            playerCreationScene(primaryStage);
        });

        submitButton.setAlignment(Pos.CENTER);
        root.getChildren().addAll(submitButton, number);
        GridPane.setConstraints(number, 0, 2);
        GridPane.setConstraints(submitButton, 0, 3);

        return root;
    }

    private void startScreenScene(Stage primaryStage){
        GridPane start = (GridPane) startScreenSceneGraph(primaryStage);
        updateStage(primaryStage, start, "Start");
    }

    private Pane playerCreationSceneGraph(Stage primaryStage){
        Button backButton = new Button("Back");
        backButton.setOnAction((event)->{
           startScreenScene(primaryStage);
        });

        Text text = new Text();

        GridPane playerPane = new GridPane();
        TextField nameField = new TextField();
        GridPane.setConstraints(nameField,0,0);
        playerPane.getChildren().add(nameField);

        Button addPlayer = new Button("Add Player");
        GridPane.setConstraints(addPlayer, 0, 1);
        addPlayer.setOnAction((event)-> {
            text.setText(nameField.getText());
        });

        playerPane.getChildren().add(addPlayer);
        playerPane.setAlignment(Pos.CENTER);

        GridPane root = new GridPane();
        GridPane.setConstraints(text,0,0);
        GridPane.setConstraints(backButton, 1,0);
        GridPane.setConstraints(playerPane, 0,1);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(text,backButton,playerPane);

        return root;
    }

    private void playerCreationScene(Stage primaryStage){
        Pane sceneGraph = playerCreationSceneGraph(primaryStage);
        updateStage(primaryStage, sceneGraph, "Player Update");
    }

    private void updateStage(Stage primaryStage, Pane root, String title){
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        startScreenScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}