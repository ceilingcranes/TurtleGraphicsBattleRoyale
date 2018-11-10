package main;

import java.util.*;
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
    private String commandString;


    // TODO: Add file option, add option for string input with \n or , separations
//    public main.Command(String filename){
//
//    }

    public Command(){this.commandString = "";}

    public Command(String commandInput){
        this.commandString = "";
        addCommands(commandInput);
    }

    public Boolean isEmpty(){
        return commandList.isEmpty();
    }

    public String toString(){
        return commandString;
    }

    public void addCommands(String commandString){
        String lines[] = commandString.split("\\n");
        ArrayList<String> commandInputs = new ArrayList<>(Arrays.asList(lines));

        for(int i=0; i<commandInputs.size(); i++){

            LocationChange next_location = generateLocationChange(commandInputs.get(i));
            System.out.println("Location Change: "+next_location.getMove());
            System.out.println("Location Class: "+ next_location.getClass());
            this.commandList.add(next_location);
        }

        this.commandString += commandString;

    }

    // TODO: Make this system more robust
    public LocationChange generateLocationChange(String input){
        String toMatch = input.trim();
        Pattern movePattern = Pattern.compile("move (\\d*)");
        Matcher moveMatch = movePattern.matcher(toMatch);

        if (moveMatch.matches()){
            return new LocationChange(Integer.parseInt(moveMatch.group(1)),0);
        }

        Pattern turnPattern = Pattern.compile("turn (-?\\d*)");
        Matcher turnMatch = turnPattern.matcher(toMatch);

        if (turnMatch.matches()){
            return new LocationChange(0,Integer.parseInt(turnMatch.group(1)));
        }
        throw new RuntimeException("main.Command Not valid: "+ toMatch);
    }

    public LocationChange getNextLocation(){
        if (commandList.isEmpty()){
            return new LocationChange(0,0);
        }
        else{
            LocationChange nextLoc = commandList.get(index);
            index ++;
            return nextLoc;
        }

    }

    public void resetCommands(){
        commandList.clear();
        commandString = "";
    }

    public static void main(String[] args) {
        String test_vals = "move 2\nturn -20";
        Command command = new Command(test_vals);

    }

}
