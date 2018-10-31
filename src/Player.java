public class Player {
    String playerName;
    String playerColor; // should this be a string?
    Command commands;
    public Player(String name, String color, String commandString){
        this.playerName = name;
        this.commands = new Command(commandString);
        this.playerColor = color;
    }

    public String toString(){
        String retString = playerName + "("+playerColor+"): "+commands.toString();
        return retString;
    }

}
