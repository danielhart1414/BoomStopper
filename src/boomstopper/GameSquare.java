//@author Daniel Hart
//The gamesquare that makes up the BoomStopper gameboard

package boomstopper;

public class GameSquare {
    private boolean visible;
    private boolean mine;
    private int neighborMines;
    private int row;
    private int column;
    
    public GameSquare(int row, int column, boolean mine) {
        this.row = row;
        this.column = column;
        this.mine = mine;
    }
    
    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }
    
    public int getNeighborMines() {
        return neighborMines;
    }
    
    public boolean isMine() {
        return this.mine;
    }
}