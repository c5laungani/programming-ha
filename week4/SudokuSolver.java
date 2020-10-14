/**
 * Class that solves the Asterisk Sudoku.
 * Prints the number of solutions of a Sudoku if there are multiple and prints single if only one.
 *
 * by <<Chetan Laungani, 1562274>>
 * and <<Habeeb Mohammed, 1582143>>
 * as group <<120>>
 */
import java.util.ArrayList;
class SudokuSolver {

    int SUDOKU_SIZE = 9;          // Size of the grid.
    int SUDOKU_MIN_NUMBER = 1;    // Minimum digit to be filled in.
    int SUDOKU_MAX_NUMBER = 9;    // Maximum digit to be filled in.
    int SUDOKU_BOX_DIMENSION = 3; // Dimension of the boxes (sub-grids that should contain all digits).
    int foundSolutions=0;
    int rEmpty;
    int cEmpty;
    ArrayList<Integer> astList = new ArrayList<Integer>();
    
    int[][] grid = new int[][] {  // The puzzle grid; 0 represents empty.
        { 0, 9, 0,   7, 3, 0,    4, 0, 0 },    // One solution.
        { 0, 0, 0,   0, 0, 0,    5, 0, 0 },
        { 3, 0, 0,   0, 0, 6,    0, 0, 0 },

        { 0, 0, 0,   0, 0, 2,    6, 4, 0 },
        { 0, 0, 0,   6, 5, 1,    0, 0, 0 },
        { 0, 0, 6,   9, 0, 7,    0, 0, 0 },

        { 5, 8, 0,   0, 0, 0,    0, 0, 0 },
        { 9, 0, 0,   0, 0, 3,    0, 2, 5 },
        { 6, 0, 3,   0, 0, 0,    8, 0, 0 },
    };
    
    int solutionCounter = 0; // Solution counter

    // Is there a conflict when we fill in d at position (r, c)?
    boolean givesConflict(int r, int c, int n) {
        // TODO
        if (rowConflict(r, n)||columnConflict(c, n)||boxConflict(r, c, n)) {
            return true;
        } else {
            return false;
        }
    }

    // Is there a conflict when we fill in d in row r?
    boolean rowConflict(int r, int n) {
        // TODO
      int t = 0;
        for (int x = 0; x < grid[r].length;x++) {
            if (grid[r][x] == n) {
                t++;
            } 
        }
        
      if (t > 0) {
            return true;
        }
            
		return false;
    }

    // Is there a conflict in column c when we fill in d?
    boolean columnConflict(int c, int n) {
        // TODO
        
        int t = 0;
        for (int x=0;x<grid.length;x++) {
            if(grid[x][c] == n) {
                t++;
            } 
        }
        if (t > 0) {
            
            return true;
        }
            
		return false;
        
    }

    // Is there a conflict in the box at (r, c) when we fill in d?
    boolean boxConflict(int r, int c, int n) {
        // TODO
        int row = r - r % 3;
        int column = c - c % 3;
        
        for (int a = row; a < row + 3; a++) {
            for (int b = column; b < (column + 3); b++) {
                if (grid[a][b]== n) {
                    return true;
                  
                }
            }
        }

        return false;
    }
	// Is there a conflict in the asterisk when we fill in d?
    boolean asteriskConflict(int n) {
        if ((astList.contains(n))) {
            return true;
        } else {
            return false;
        }
    }
	
	// Finds the next empty square (in "reading order").
    int[] findEmptySquare() {
        // TODO
        // Two for loops to loop through the grid and search for empty value 
        for (int d = 0; d < grid.length; d++) {
            for (int e = 0; e < grid[d].length; e++) {
                if (grid[d][e]== 0){
                    rEmpty = d;
                    cEmpty = e;
                    return new int[] {d,e};
                }
            }
        }
        
        return new int[]{-1, -1};
    }

    // Find all solutions for the grid, and stores the final solution.
    void solve() {
        // TODO

        //call findEmpty method to get location of empty spaces
        if(findEmptySquare()==null){
            foundSolutions++; 

        }

        //loop numbers 1 to 9 for empty spaces
        for (int l = SUDOKU_MIN_NUMBER; l <= SUDOKU_MAX_NUMBER; l++) {
            if (givesConflict(rEmpty, cEmpty, l) == false) {
                grid[rEmpty][cEmpty] = l;
                solve();
            } else{
            grid[rEmpty][cEmpty] = 0;
            }
            
        }

        
        
        
                


    }

    // Print the sudoku grid.
    void print() {
        // TODO
        
        printPlus();
        System.out.println();

        for (int i = 0; i < grid.length; i++) {

            System.out.print("|");

            for (int j=0; j < grid[i].length; j++) {
                                              
                if (grid[i][j] == 0){
                    System.out.print("  ");
                    
                    
                } else {
                    System.out.print(grid[i][j]);
                    System.out.print(" ");
                }
                if ((j + 1) % 3 == 0 && (j + 1) != 9) {
                    
                    System.out.print("\b|");
                    
                }
               
            } 

            System.out.print("\b|");
                        
            if ((i + 1) % 3 == 0){
                System.out.println();
                printPlus();
                                
            }

            System.out.println();
        }        

    }
    //prints the separators between each row of boxes
    void printPlus() {
        System.out.print("+");
        for (int a = 0; a < 17; a++){
            System.out.print("-");
        }
        System.out.print("+");
        
    }





    // Run the actual solver.
    void solveIt() {
        // TODO
    }

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
        s.solve();
        s.print();

        
        
        
        
    }
}
