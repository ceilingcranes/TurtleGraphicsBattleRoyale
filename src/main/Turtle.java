package main;

public class Turtle extends GameObject {
    Command turtleCommands;

    public Turtle(Command commands){
        turtleCommands = commands;
    }
    public LocationChange getNextLocation(){
        return turtleCommands.getNextLocation();
    }

}
