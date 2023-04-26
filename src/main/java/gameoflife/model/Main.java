package gameoflife.model;

import java.util.Random;

public class Main {
    
    private int cols;
    private int rows;
    private Random random = new Random();

    private int[][] grid;

    public Main(int gridCols, int gridRows) {
        this.cols = gridCols;
        this.rows = gridRows;
        this.grid = new int[rows][cols];
    }

    public void populateRandom(int percentage){
        if (percentage>100 || percentage<0) {
            throw new IllegalArgumentException();
        }
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[y][x] = random.nextInt(100)<percentage?1:0;
            }
        }
    }


    public void nextGen(){

        int[][] nextGrid = new int[rows][cols];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                int livinNeigboors = countLivingNeigboors(x, y);

                if (grid[y][x]==1) {
                    nextGrid[y][x] = (livinNeigboors<2 || livinNeigboors>3)?0:1;
                }else{
                    nextGrid[y][x] = (livinNeigboors==3?1:0);
                }

            }
        }

        grid = nextGrid;

    }

    public int[][] getGrid(){
        return grid.clone();
    }


    private int countLivingNeigboors(int x, int y){
        int total = 0;

        for (int yOff = -1; yOff <= 1; yOff++) {
            for (int xOff = -1; xOff <= 1; xOff++) {
                if (!(x+xOff>=cols || x+xOff<0 || y+yOff>=rows || y+yOff<0)) {
                    total += grid[y+yOff][x+xOff];
                }
            }
        }
        total -= grid[y][x];

        return total;
    }



}
