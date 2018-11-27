package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewApplication extends Application {
    private final IntegerProperty numPlayers = new SimpleIntegerProperty(1);
    public final static int BOARDSIZE = 600;
    public final static int IMAGE_SIZE = 20;
//    private GameBoard board = new GameBoard();
    private GameController gameController = new GameController(BOARDSIZE, BOARDSIZE);

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

    class PlayerTextFields{
        private TextField name;
        private TextArea commands;
        private PlayerTextFields(TextField playerName, TextArea playerCommands){
            this.name = playerName;
            this.commands = playerCommands;
        }
        private TextField getName(){
            return name;
        }
        private TextArea getCommands(){
            return commands;
        }
    }

    /**
     * Creates the starting scene graph for the start screen.
     * @param primaryStage : the Stage object for the view
     * @return the Pane object containing the start screen
     */
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
        // When submit button is pressed, change the scene to the player creation scene graph
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

    public void startScreenScene(Stage primaryStage){
        GridPane start = (GridPane) startScreenSceneGraph(primaryStage);
        updateStage(primaryStage, start, "Start");
    }

    /**
     * Return a Pane object containing the scene graph for the player creation scene
     * @param primaryStage: Stage containing the scene
     * @return Pane object with scene graph
     */
    private Pane playerCreationSceneGraph(Stage primaryStage){
        Button backButton = new Button("Back");
        backButton.setOnAction((event)->{
           startScreenScene(primaryStage);
        });

        Text text = new Text();

        GridPane playerPane = new GridPane();
        PlayerTextFields[] playerFields = new PlayerTextFields[numPlayers.get()];


        for(int i = 0; i< numPlayers.get(); i++) {
            TextField nameField = new TextField();
            GridPane.setConstraints(nameField, 0, i);

            TextArea commands = new TextArea();
            GridPane.setConstraints(commands, 1, i);

            playerPane.getChildren().addAll(nameField, commands);
            playerFields[i] = new PlayerTextFields(nameField, commands);
        }

        playerPane.setAlignment(Pos.CENTER);

        // Update board with entered information when submit button is pressed
        Button submitButton = new Button("Submit");
        ArrayList<Player> players = new ArrayList<>();
         //TODO: Fix this - will error out. Need to not double-add players when button is clicked more than once.
        // can add a list of players at the end
        // validate first, when no errors add all players
        // Move it all to another function? Or to GameBoard?
        submitButton.setOnAction((event)->{
            String playerNameString = "";
            String[] playerNames = new String[numPlayers.get()];
            Command[] playerCommands = new Command[numPlayers.get()];
            int index = 0;
            boolean error = false;

            for (PlayerTextFields playerData:playerFields
                 ) {
                Command commands = new Command(playerData.getCommands().getText());
                playerNames[index] = playerData.getName().getText();
                playerCommands[index] = commands;
                playerNameString += playerData.getName().getText();
                index++;

                if (commands.getInvalidCommandLineNumber().size() != 0){
                    String errorMessage = "Error in following lines: "+commands.getInvalidCommandLineNumber().toString();
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
                    alert.show();
                    error = true;
                }
//                    gameController.addPlayer(playerData.getName().getText(), commands);
            }
            gameController.getPlayers().addPlayers(playerNames, playerCommands);
            text.setText(playerNameString);
            if (!error) playGameScene(primaryStage);
            // TODO: Add game screen transition
        });

        GridPane.setConstraints(submitButton, 1,2);

        GridPane root = new GridPane();
        GridPane.setConstraints(text,1,0);
        GridPane.setConstraints(backButton, 0,0);
        GridPane.setConstraints(playerPane, 0,1);
        root.getChildren().addAll(text,backButton,playerPane,submitButton);

        return root;
    }

    /**
     * Update stage to the player creation screen.
     * @param primaryStage: Stage to update
     */
    private void playerCreationScene(Stage primaryStage){
        Pane sceneGraph = playerCreationSceneGraph(primaryStage);
        updateStage(primaryStage, sceneGraph, "Player Update");
    }

    private Pane playGameSceneGraph(Stage primaryStage){
        GridPane root = new GridPane();
        GridPane gameBoard = gameBoardSceneGraph(primaryStage);
        root.getChildren().add(gameBoard);
        return root;
    }

    /**
     * Create the Scene Graph for the actual game board screen, with players, commands, and turtle running.
     * @param primaryStage The stage associated with the scene
     * @return GridPane object containing the root of the scene graph.
     */
    private GridPane gameBoardSceneGraph(Stage primaryStage){
        // https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835

        GridPane root = new GridPane();
        // Set up even distributions
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);

        root.getColumnConstraints().addAll(column1, column2, column3);
        Canvas gameBoard = new Canvas(BOARDSIZE, BOARDSIZE);

        PlayerList players = gameController.getPlayers();
        System.out.println(players.getNumPlayers());
//        GridPane[] playerPanes = new GridPane[players.getNumPlayers()];
        for(int i = 0; i<players.getNumPlayers(); i++){
            GridPane playerPane = new GridPane();

            StackPane namePane = new StackPane();
            Text playerName = new Text(players.getPlayer(i).getPlayerName());
            playerName.setFont(new Font(22));
            playerName.setWrappingWidth(100);
            Paint color = Paint.valueOf(players.getPlayer(i).getPlayerColor());

            Rectangle playerBg = new Rectangle(playerName.getWrappingWidth(), 22, color); // TODO: Change this to use a label
            namePane.getChildren().addAll(playerBg, playerName);


            playerPane.getChildren().add(namePane);

            GridPane.setConstraints(namePane, 0, 0);
            playerPane.setAlignment(Pos.CENTER);

            ArrayList<String> commands = players.getPlayer(i)
                    .getPlayerTurtle()
                    .getTurtleCommands()
                    .getCommandInputs();

            for (int j = 0; j<commands.size(); j++){
                Text commandString = new Text(commands.get(j));
                playerPane.getChildren().add(commandString);
                GridPane.setConstraints(commandString, 0, j+1);
            }

            switch(i){
                case 0: GridPane.setConstraints(playerPane, 0, 0);
                break;
                case 1: GridPane.setConstraints(playerPane, 0, 1);
                break;
                case 2: GridPane.setConstraints(playerPane,2, 0);
                break;
                case 3: GridPane.setConstraints(playerPane, 2, 1);
                break;
            }
            root.getChildren().add(playerPane);
        }
        root.getChildren().add(gameBoard);
        GridPane.setRowSpan(gameBoard, 2);
        GridPane.setConstraints(gameBoard,1,0);
        GraphicsContext gc = gameBoard.getGraphicsContext2D();
        // TODO: Add border using css
//        gc.setFill(Paint.valueOf("white"));

        Image redTurtle = new Image(getClass().getResource("images/redturtle.png").toExternalForm(),
                IMAGE_SIZE,IMAGE_SIZE, true, false);
        Image blueTurtle = new Image(getClass().getResource("images/blueturtle.png").toExternalForm(),
                IMAGE_SIZE,IMAGE_SIZE, true, false);
        Image greenTurtle = new Image(getClass().getResource("images/greenturtle.png").toExternalForm(),
                IMAGE_SIZE,IMAGE_SIZE, true, false);
        Image yellowTurtle = new Image(getClass().getResource("images/yellowturtle.png").toExternalForm(),
                IMAGE_SIZE,IMAGE_SIZE, true, false);

        Map<String, ImageView> turtleMap = new HashMap<String, ImageView>();
        turtleMap.put("0xff8f80", new ImageView(redTurtle));
        turtleMap.put("0xa3d977", new ImageView(greenTurtle));
        turtleMap.put("0x5abaa7", new ImageView(blueTurtle));
        turtleMap.put("0xffdf71", new ImageView(yellowTurtle));

        AnimationTimer timer = new AnimationTimer(){
            private SnapshotParameters params = new SnapshotParameters();
            private int timeCounter = 0;
            private long startNanoTime = 0;

            @Override
            public void start(){
                startNanoTime = System.nanoTime();
                super.start();
            }

            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                if (t > timeCounter) { // Move every 1 second
                    timeCounter++;
                    gc.setFill(Paint.valueOf("white"));
                    gc.fillRect(0, 0, BOARDSIZE, BOARDSIZE);
                    ArrayList<GameObject> objects = gameController.getObjects();

                    for (GameObject obj : objects) {
                        if (obj instanceof Turtle) {
                            String color = obj.getObjectColor();
                            Location turtleLoc = obj.getCurrentLocation();
                            ImageView turtle = turtleMap.get(color);
                            turtle.setRotate(turtleLoc.getOrientation());
                            gc.drawImage(turtle.snapshot(params, null),
                                    turtleLoc.getXLocation(), turtleLoc.getYLocation());
                        }
                        if (obj instanceof Line){
                            Line lineObj = (Line) obj;
                            gc.setStroke(Paint.valueOf(lineObj.getObjectColor()));

                            gc.strokeLine(lineObj.getStartLocation().getXLocation(),
                                    lineObj.getStartLocation().getYLocation(),
                                    lineObj.getEndLocation().getXLocation(),
                                    lineObj.getEndLocation().getYLocation());
                        }
                    }
                    Player winningPlayer = gameController.checkLose();
                    if (winningPlayer != null){
                        Alert winAlert = new Alert(Alert.AlertType.INFORMATION, winningPlayer.getPlayerName()+" " +
                                "is the loser of this round!"
                        );

                        Platform.runLater(winAlert::showAndWait);
                        gameController.resetGame();
                        this.stop();
//                                .ifPresent(response ->{
//                            if(response == ButtonType.NO){
//                                gameController.resetGame();
//                            }
//                            else if (response == ButtonType.YES){
//                                playerCreationScene(primaryStage);
//                            }
//                        }));
                    }
                }
            }
        };

        Button startButton = new Button("Start");
        startButton.setOnAction((event)->{
            timer.start();
        });
        root.getChildren().add(startButton);
        GridPane.setConstraints(startButton, 2,2);
        return root;
    }

    private void playGameScene(Stage primaryStage){
        Pane sceneGraph = playGameSceneGraph(primaryStage);
        updateStage(primaryStage, sceneGraph, "Gameplay");
    }

    private void updateStage(Stage primaryStage, Pane root, String title){
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    /**
     * Required method to start the application
     */
    public void start(Stage primaryStage) {
//        startScreenScene(primaryStage);
        Command[] addCommands = {new Command("turn -90\nmove 2\nturn 30\nmove 4"),
                new Command("turn 45\nmove 100")
        };
        String[] playerNames = {"player1", "player2"};
        gameController.getPlayers().addPlayers(playerNames, addCommands);
        playGameScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
