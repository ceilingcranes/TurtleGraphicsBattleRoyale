package main;

public class RotateLocationChange extends LocationChange {
    private int rotate;
    public RotateLocationChange(int r){
        this.rotate = r;
    }
    public int getMove(){
        return this.rotate;
    }
    public void setMove(int r){
        this.rotate = r;
    }
}
