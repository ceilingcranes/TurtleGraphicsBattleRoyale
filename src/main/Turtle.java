package main;

import java.util.ArrayList;

public class Turtle extends GameObject {
    private Command turtleCommands;
    private int stepsToTake = 0;

    public Turtle(Command commands, String color){
        objectColor = color;
        turtleCommands = commands;
        currentLocation = new Location(0,0);
    }

    public Turtle(Command commands, Location loc, String color){
        objectColor = color;
        turtleCommands = commands;
        currentLocation = loc;
        System.out.println("Created new turtle at location "+loc.toString());
    }

//    public Location getNextLocation(){
//        LocationChange locChange =  turtleCommands.getNextLocation();
//
//    }

    /**
     * Update the turtle's location by either taking a step within the current MOVE command, or by going to the next
     * command and updating the orientation/steps left to take.
     */
    public void update(){
        if (stepsToTake == 0) {
            LocationChange nextLoc = turtleCommands.getNextLocation();
            stepsToTake = nextLoc.getMove();
            currentLocation.updateOrientation(nextLoc.getTurn());
        }
        if (stepsToTake>0){
            currentLocation.takeStep(GameController.STEPSIZE);
            stepsToTake--;
        }
    }


    public Command getTurtleCommands(){
        return turtleCommands;
    }

    public void setTurtleCommands(Command c){
        turtleCommands = c;
        stepsToTake = 0;
    }
}
