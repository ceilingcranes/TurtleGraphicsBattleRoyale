package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The main.Command class is used to track the commands used to control a given turtle. The initalization requires
 * either an ArrayList of inputs, a file name containing valid commands, with one command on each line, or a string
 * with valid commands seperated with newlines. This keeps track of the current command, and allows a system for getting
 * the next LocationChange object for movement.
 */
public class Command { // TODO: Implements Iterable
    private LinkedList<LocationChange> commandList = new LinkedList<>();
    int index = 0; // Make this not terrible
    // TODO: Add file option, add option for string input with \n or , separations
//    public main.Command(String filename){
//
//    }
    private String commandString;

    public Command(String commandString){
        ArrayList<String> commandInputs = new ArrayList<>();
        String lines[] = commandString.split("\\n");
        for (String line: lines){
            commandInputs.add(line); // I hate this
        }

        for(int i=0; i<commandInputs.size(); i++){

            LocationChange next_location = generateLocationChange(commandInputs.get(i));
            System.out.println("Location Change: "+next_location.getMove());
            System.out.println("Location Class: "+ next_location.getClass());
            this.commandList.add(next_location);
        }
        this.commandString = commandString;
    }

    public String toString(){
        return commandString;
    }
    // TODO: Make this system more robust
    private LocationChange generateLocationChange(String input){
        Pattern movePattern = Pattern.compile("move (\\d*)");
        Matcher moveMatch = movePattern.matcher(input);

        if (moveMatch.matches()){
            return new MovementLocationChange(Integer.parseInt(moveMatch.group(1)));
        }

        Pattern turnPattern = Pattern.compile("turn (-+\\d*)");
        Matcher turnMatch = turnPattern.matcher(input);

        if (turnMatch.matches()){
            return new RotateLocationChange(Integer.parseInt(turnMatch.group(1)));
        }
        throw new RuntimeException("main.Command Not valid: "+ input);
    }

    public LocationChange getNextLocation(){
        if (commandList.isEmpty()){
            return new MovementLocationChange(0);
        }
        else{
            LocationChange nextLoc = commandList.get(index);
            index ++;
            return nextLoc;
        }

    }

    public static void main(String[] args) {
        String test_vals = "move 2\nturn -20";

        Command command = new Command(test_vals);

    }

}
