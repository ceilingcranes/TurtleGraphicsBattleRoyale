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
    private ArrayList<Integer> invalidCommandLineNumber = new ArrayList<>();
    private ArrayList<String> commandInputs = new ArrayList<>();

    int index = 0; // Make this not terrible
    private String commandString;
    private final String MOVE_REGEX = "move (\\d*)";
    private final String TURN_REGEX = "turn (-?\\d*)";

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
        if (commandString.equals("")){
            System.out.println("Command input string empty");
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
                System.out.println("Location Change: "+next_location.getMove());
                System.out.println("Location Class: "+ next_location.getClass());
                this.commandList.add(next_location);
            }
        }
        this.commandString += commandString;
    }

    public ArrayList<Integer> getInvalidCommandLineNumber(){
        return invalidCommandLineNumber;
    }

    public boolean validateCommand(String input){
        String toMatch = input.trim();
        Pattern commandPattern = Pattern.compile(MOVE_REGEX + "|" + TURN_REGEX);
        Matcher commandMatch = commandPattern.matcher(toMatch);
        return commandMatch.matches();
    }

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

    public void resetCommands(){
        commandList.clear();
        commandString = "";
    }

    public ArrayList<String> getCommandInputs(){
        return this.commandInputs;
    }

    public static void main(String[] args) {
        String test_vals = "move 2\nturn -20";
        Command command = new Command(test_vals);

    }

}
