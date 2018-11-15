package main;

public class Turtle extends GameObject {
    private Command turtleCommands;
    private Location currentLocation;

    public Turtle(Command commands){
        turtleCommands = commands;
    }

    public Turtle(Command commands, Location loc){
        turtleCommands = commands;
        currentLocation = loc;
        System.out.println("Created new turtle at location "+loc.toString());
    }

//    public Location getNextLocation(){
//        LocationChange locChange =  turtleCommands.getNextLocation();
//
//    }



    public Command getTurtleCommands(){
        return turtleCommands;
    }

    public void setTurtleCommands(Command c){
        turtleCommands = c;
    }
}
