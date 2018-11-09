package main;

import javafx.application.Application;
import javafx.stage.Stage;


// Controls connection between view and backend
public class View extends Application {
    ViewApplication view = new ViewApplication();
    // TODO: work out how to make a board and have it update this view. Maybe make GameController control both?
    // TODO: Add board size and other optional parameters input by user.
//    public View(){
//
//    }

    @Override
    public void start(Stage primaryStage){
        view.startScreenScene(primaryStage);
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        launch(args);

    }
}
