/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * part PlayingField
 * 
 * @author  Chetan Laungani
 * @author  Habeeb Mohammed
 * assignment group: Group 120
 * 
 * assignment copyright Kees Huizing
 */

import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.util.ArrayList;


class PlayingField extends JPanel /* possible implements ... */ {

    private Patch[][] grid;

    private double alpha; // defection award factor

    private Timer timer;

    private int colorChosen;

    // random number genrator
    private static final long SEED = 37L; // seed for random number generator; any number goes
    public static final Random random = new Random(SEED);

    // ...
    PlayingField(int length) {
        this.setLayout(new GridLayout(length, length));
        this.grid = new Patch[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                colorChosen = random.nextInt(2);
                if (colorChosen == 0) {
                    Patch p2 = new Patch(Color.red);
                    p2.setCooperating(false);
                    this.grid[i][j] = p2;
                    this.add(p2);
                } else if (colorChosen == 1) {
                    Patch p2 = new Patch(Color.blue);
                    p2.setCooperating(true);
                    this.grid[i][j] = p2;
                    this.add(p2);
                }

            }
        }
    }

    /**
     * calculate and execute one step in the simulation
     */
    public void step() {

        // for all patches get neightbours then calculate score then adjust patch to get
        // best strategy
        // store neighbour patches with highest score (could also be the patch itself)
        // in another arraylist
        // and then randomly adopt the strategy of any of the top scoring patches

        System.out.println("running step");
        for (int x = 0; x < this.getGridLength(); x++) {
            for (int y = 0; y < this.getGridLength(); y++) {
                ArrayList<Patch> neighbours = this.getNeighbours(x, y);
                Patch currentPatch = this.getPatch(x, y);

                double patchScore = this.calculateScore(neighbours, currentPatch.isCooperating());
                currentPatch.setScore(patchScore);
            }
        }

        boolean[][] newStrategies = new boolean[this.getGridLength()][this.getGridLength()];
        for (int x = 0; x < this.getGridLength(); x++) {
            for (int y = 0; y < this.getGridLength(); y++) {
                ArrayList<Patch> patchPlusNeighbours = this.getNeighbours(x, y);
                Patch currentPatch = this.getPatch(x, y);
                patchPlusNeighbours.add(currentPatch);

                boolean bestStrategy = this.getBestStrategy(patchPlusNeighbours);
                newStrategies[x][y] = bestStrategy;

            }
        }

        this.setGrid(newStrategies);

    }

    private boolean getBestStrategy(ArrayList<Patch> patches) {

        ArrayList<Patch> bestStrategyPatches = new ArrayList<Patch>();
        double bestScore = patches.get(0).getScore();

        // loop thorough all patches and find highest score patch and then return the
        // strategy of that patch
        for (Patch patch : patches) {
            double currentPatchScore = patch.getScore();
            if (currentPatchScore > bestScore) {
                bestScore = currentPatchScore;
            }

        }
        for (Patch patch : patches) {
            double currentPatchScore = patch.getScore();
            if (currentPatchScore == bestScore) {
                bestStrategyPatches.add(patch);
            }

        }
        int randomIndex = random.nextInt(bestStrategyPatches.size());
        return bestStrategyPatches.get(randomIndex).isCooperating();
    }

    private double calculateScore(ArrayList<Patch> neighbours, boolean currentStrategy) {

        // If Specific patch isCooperating
        if (currentStrategy) {
            // Get all neighbours that are cooperating and return count
            return calculateNumOfCooperatingNeighbours(neighbours);
            // Defecting
        } else {
            // get all neighbours that are cooperating and multiply by defective rate
            return calculateNumOfCooperatingNeighbours(neighbours) * alpha;
        }

    }

    private int calculateNumOfCooperatingNeighbours(ArrayList<Patch> neighbours) {
        int count = 0;
        for (Patch patch : neighbours) {
            if (patch.isCooperating()) {
                count += 1;
            }
        }
        return count;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
        System.out.println(alpha);
    }

    public double getAlpha() {
        // ...
        return this.alpha;
    }

    // return grid as 2D array of booleans
    // true for cooperators, false for defectors
    // precondition: grid is rectangular, has non-zero size and elements are
    // non-null
    public boolean[][] getGrid() {
        boolean[][] resultGrid = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                resultGrid[x][y] = grid[x][y].isCooperating();
            }
        }

        return resultGrid;
    }

    // sets grid according to parameter inGrid
    // a patch should become cooperating if the corresponding
    // item in inGrid is true
    public void setGrid(boolean[][] inGrid) {
        // ...
        System.out.println("setting grid");
        int length = inGrid[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                boolean strategy = inGrid[i][j];
                Patch patchToUpdate = this.getPatch(i, j);
                patchToUpdate.setCooperating(strategy);
                patchToUpdate.setColor(strategy ? Color.blue : Color.red);
            }
        }

    }

    public void setGrid(Patch[][] inGrid) {
        // ...
        this.grid = inGrid;
        int length = inGrid[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                this.add(inGrid[i][j]);

            }
        }

    }

    public void setPatch(Patch patch, int x, int y) {
        // ...
        this.grid[x][y] = patch;

    }

    public Patch getPatch(int x, int y) {
        // ...
        return this.grid[x][y];
    }

    private ArrayList<Patch> getNeighbours(int col, int row) {
        ArrayList<Patch> neighbours = new ArrayList<>();
        // find all surrounding cells by adding +/- 1 to col and row
        for (int colNum = col - 1; colNum <= (col + 1); colNum += 1) {

            for (int rowNum = row - 1; rowNum <= (row + 1); rowNum += 1) {

                // if not the center cell
                if (!((colNum == col) && (rowNum == row))) {

                    // make sure it is within grid
                    if (withinGrid(colNum, rowNum)) {
                        neighbours.add(this.getPatch(colNum, rowNum));
                    }
                }
            }
        }

        return neighbours;
    }

    private int getGridLength() {

        return this.grid[0].length;
    }

    // define if cell represented by colNum, rowNum is inside grid
    // function used by neighbours()
    private boolean withinGrid(int colNum, int rowNum) {

        int length = this.getGridLength();
        if ((colNum < 0) || (rowNum < 0)) {
            return false; // false if row or col are negative
        }
        if ((colNum >= length) || (rowNum >= length)) {
            return false; // false if row or col are > 75
        }
        return true;
    }

    
}

      





