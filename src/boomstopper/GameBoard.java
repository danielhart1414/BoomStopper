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
        this.height = height;
        this.width = width;
        this.mineCount = mineCount;
        
        //Add code to protect against bad inputs
        
        Random r = new Random();
        grid = new GameSquare[height][width];
        
        int minesLeft = mineCount; //decreases for each mine
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!grid[i][j].isMine()) {
//                    if (i == 0) {
//                        if (j == 0) {
//                            if (grid[0][0].mine) {
//                                mineCount++;
//                            }
//                            if (grid[0][0].mine) {
//                                mineCount++;
//                            }
//                            if (grid[0][0].mine) {
//                                mineCount++;
//                            }
//                        }
//                    }

//                  general condition  
                    for (int k = i-1; k <= i + 1; k++) {
                        for (int l = j-1; l <= j + 1; l++) {
                            if (grid[k][l].isMine()) {
                                neighborMines++;
                            }
                        }
                    }
                    grid[k][l].setNeighborMines(mineCount);
                    neighborMines = 0;
                    
                    //1st row: look at i & i + 1
                    //middle rows: look at i - 1, i, i + 1
                    //last row: i - 1, i
                    //1st column: look at j & j + 1
                    //middle columns: look at j - 1, j, j + 1
                    //last row: j - 1, j
                }
            }
        }
    }
}
