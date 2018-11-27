package main;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The main.Command class is used to track the commands used to control a given turtle. The initalization requires
 * either an ArrayList of inputs or a string with valid commands seperated with newlines.
 * This keeps track of the current command, and has a method for getting the next LocationChange object for movement.
 */
public class Command { // TODO: Implements Iterable
    /** The list of LocationChange that tracks what the commands do. */
    private LinkedList<LocationChange> commandList = new LinkedList<>();
    /** The list of the line numbers of invalid commands */
    private ArrayList<Integer> invalidCommandLineNumber = new ArrayList<>();
    /** The list of the actual input strings */
    private ArrayList<String> commandInputs = new ArrayList<>();
    /** The index of the next LocationChange inside commandList to return.*/
    private int index = 0;
    /** The full input */
    private String commandString;
    /** The regular expression for evaluating move commands */
    private final String MOVE_REGEX = "move (\\d*)";
    /** The regular expression for evaluating turn commands */
    private final String TURN_REGEX = "turn (-?\\d*)";

    /**
     * Create a new object out of a string input.
     * @param commandInput A String with move and turn commands, seperated by a newline.
     */
    public Command(String commandInput){
        this.commandString = "";
        addCommands(commandInput);
    }

    /**
     * Return a string containing all the commmands
     * @return String containing all commands
     */
    public String toString(){
        return commandString;
    }

    /**
     * Add commands from a String which has commands separated by newlines. If given an empty string, it will
     * default to not moving. If one or more command is invalid, its line number will be added to
     * the invalidCommandLineNumber array for display of errors.
     * @param commandString A String of valid commands, separated by newlines, which will be added on to the Command iteration.
     */
    public void addCommands(String commandString){
        if (commandString.equals("")){
            this.commandList.add(new LocationChange(0,0));
            return;
        }

        String lines[] = commandString.split("\\n");
        this.commandInputs = new ArrayList<>(Arrays.asList(lines));

        for(int i=0; i<commandInputs.size(); i++){

            LocationChange next_location = generateLocationChange(commandInputs.get(i));

            if (next_location == null)
                this.invalidCommandLineNumber.add(i);
            else {
                this.commandList.add(next_location);
            }
        }
        this.commandString += commandString;
    }

    /**
     * Return the invalid command line number, which will be an empty ArrayList if all the entered commands are valid.
     * @return ArrayList of integers corresponding to the line numbers of invalid commands.
     */
    public ArrayList<Integer> getInvalidCommandLineNumber(){
        return invalidCommandLineNumber;
    }

    /**
     * Will check to see if a single command input is valid or not.
     * @param input the single command to be validated
     * @return true if the command is valid, false if it isn't.
     */
    public boolean validateCommand(String input){
        String toMatch = input.trim();
        Pattern commandPattern = Pattern.compile(MOVE_REGEX + "|" + TURN_REGEX);
        Matcher commandMatch = commandPattern.matcher(toMatch);
        return commandMatch.matches();
    }

    /**
     * Given a single input, will create a new LocationChange object with either a move or a turn.
     * @param input A single valid input, either move or turn
     * @return a LocationChange object translating the command into a number of degrees of turning or a number
     * of steps of movement.
     */
    public LocationChange generateLocationChange(String input){
        String toMatch = input.trim();
        Pattern movePattern = Pattern.compile(MOVE_REGEX);
        Matcher moveMatch = movePattern.matcher(toMatch);

        if (moveMatch.matches()){
            return new LocationChange(Integer.parseInt(moveMatch.group(1)),0);
        }

        Pattern turnPattern = Pattern.compile(TURN_REGEX);
        Matcher turnMatch = turnPattern.matcher(toMatch);

        if (turnMatch.matches()){
            return new LocationChange(0,Integer.parseInt(turnMatch.group(1)));
        }

        return null;
//        throw new RuntimeException("main.Command Not valid: "+ toMatch);
    }

    /**
     * Go through the Commands in order, and return the next LocationChange object in the sequence. Will loop back
     * to the beginning upon reaching the end of the Commands.
     * @return LocationChange object corresponding to the next change.
     */
    public LocationChange getNextLocation(){
        if (commandList.isEmpty()){
            return new LocationChange(0,0);
        }
        else{
            if (index == commandList.size())
                index = 0;
            LocationChange nextLoc = commandList.get(index);
            index ++;
            return nextLoc;
        }
    }

    /**
     * Reset the Command object.
     */
    public void resetCommands(){
        commandList.clear();
        commandString = "";
    }

    /**
     * Get the ArrayList of String inputs.
     * @return ArrayList of Strings corresponding to the command inputs
     */
    public ArrayList<String> getCommandInputs(){
        return this.commandInputs;
    }
}
