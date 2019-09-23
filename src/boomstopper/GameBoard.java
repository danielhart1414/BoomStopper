//@author Daniel Hart
//The BoomStopper gameboard--made up of GameSquares

package boomstopper;

import java.util.*;

public class GameBoard {
    private int height;
    private int width;
    private int mineCount;
    private GameSquare[][] grid;
    
    public GameBoard() {
        this(8, 8, 10);
    }
    
    public GameBoard(int height, int width, int mineCount) {
        if (height < 1) {
            throw new IllegalArgumentException("height must be greater than 0.");
        } else if (width < 1) {
            throw new IllegalArgumentException("width must be greater than 0.");
        } else if (mineCount < 0) {
            throw new IllegalArgumentException("mineCount cannot be less than 0.");
        }
                
        this.height = height;
        this.width = width;
        this.mineCount = mineCount;
        
        Random r = new Random();
        grid = new GameSquare[height][width];
        
        int minesLeft = mineCount; //will decrease for each mine laid
        int totalSquares = height * width;
        boolean isMine = false;
        
        //Sets the mines
        //probability of mine is mineCount/totalSquares
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (minesLeft != 0) {
                    isMine = r.nextInt(totalSquares) < mineCount;
                    if (isMine) {
                        minesLeft--;
                    }
                }
                grid[i][j] = new GameSquare(i, j, isMine);
                isMine = false;
            }
        }
        
        //Calculates # of neighbor mines
        int neighborMines = 0;
        //Loops through all the GameSquares in the GameBoard
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!grid[i][j].isMine()) {
                    //Loops through the 3 x 3 grid around non-mine squares
                    for (int k = i-1; k <= i + 1; k++) {
                        for (int l = j-1; l <= j + 1; l++) {
                            //Keeps the program from checking spaces that are off the GameBoard
                            if (k >= 0 && l >= 0 && k < height && l < width) {
                                if (grid[k][l].isMine()) {
                                    neighborMines++;
                                }
                            }
                        }
                    }
                    grid[i][j].setNeighborMines(neighborMines);
                    neighborMines = 0;
                }
            }
        }
    }
}
