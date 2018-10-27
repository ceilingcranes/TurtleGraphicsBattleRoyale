public class MovementLocationChange extends LocationChange {
    private int steps;
    public MovementLocationChange(int s){
        this.steps=s;
    }
    public void setMove(int s){
        this.steps=s;
    }
    public int getMove(){
        return this.steps;
    }
}
