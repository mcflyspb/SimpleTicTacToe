package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int [][] grid = new int[][] {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        boolean playerXTurn = true;
        while (Objects.equals(checkGameState(grid), "")) {
            printGrid(grid);
            grid = inputFirstMoveX(grid, playerXTurn);
            playerXTurn = !playerXTurn;
        }
        printGrid(grid);
        System.out.println(checkGameState(grid));
    }

    private static int[][] inputFirstMoveX(int[][] grid, boolean playerXTurn) {
        Scanner input = new Scanner(System.in);
        for (;;) {
            String inputText = input.nextLine().trim();
            String[] inputChar = inputText.split(" ");
            if ((Objects.equals(inputChar[0],"1") || Objects.equals(inputChar[0],"2") || Objects.equals(inputChar[0],"3")) &&
            Objects.equals(inputChar[1], "1") || Objects.equals(inputChar[1], "2") || Objects.equals(inputChar[1], "3"))  {
                int x = Integer.parseInt(inputChar[0]);
                int y = Integer.parseInt(inputChar[1]);
                if (x >= 1 && x <= 3 && y >= 1 && y <=3) {
                    if (grid[x - 1][y - 1] < 0) {
                        if (playerXTurn) {
                            grid[x - 1][y - 1] = 1;
                        } else {
                            grid[x - 1][y - 1] = 0;
                        }
                        return grid;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }

                } else {
                    System.out.println("You should enter numbers!");
                    continue;
                }

            }
            else {
                System.out.println("You should enter numbers!");
                continue;
            }
        }
    }

    private static String checkGameState(int[][] grid) {
        final String X_WINS = "X wins";
        final String O_WINS = "O wins";
        final String DRAW = "Draw";
        final String IMPOSSIBLE = "Impossible";
        boolean oWins;
        boolean xWins;

        xWins = checkPlayerWin (1,grid);
        oWins = checkPlayerWin (0,grid);

        if (Math.abs(checkBusyCell (1, grid) - checkBusyCell (0, grid)) > 1) {
            return IMPOSSIBLE;
        }

        if (xWins && oWins) {
            return IMPOSSIBLE;
        }

        if (xWins) return X_WINS;
        if (oWins) return O_WINS;

        if (checkEmptyCell (grid)) {
            return "";
        }

        return DRAW;
    }

    private static int checkBusyCell(int x, int[][] grid) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == x) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean checkEmptyCell(int[][] grid) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] < 0 || grid[i][1] < 0 || grid[i][2] < 0 ) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkPlayerWin(int x, int[][] grid) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == x && grid[i][1] == x && grid[i][2] == x) {
                return true;
            }
            if (grid[0][i] == x && grid[1][i] == x && grid[2][i] == x) {
                return true;
            }
        }
        if (grid[0][0] == x && grid[1][1] == x && grid[2][2] == x) {
            return true;
        }
        return grid[2][0] == x && grid[1][1] == x && grid[0][2] == x;
    }

    private static int[][] inputGrid(int[][] grid) {
        int [][] inputGrid = new int[3][3];
        Scanner input = new Scanner(System.in);
        String inputLine = input.nextLine().substring(0,9);
        String[] inputChar = inputLine.split("");


        int i;
        int j;
        for (int x = 0; x < 9; x++) {
            i = x / 3;
            j = x - 3 * i;
            if (Objects.equals(inputChar[x],"X")) {
                inputGrid[i][j] = 1;
            } else if (Objects.equals(inputChar[x],"O")) {
                inputGrid[i][j] = 0;
            } else if (Objects.equals(inputChar[x],"_")) {
                inputGrid[i][j] = -1;
            }
        }
        return inputGrid;

    }

    private static void printGrid(int[][] grid) {
        System.out.println("-------");
        for (int i = 0; i < 3; i++) {
            StringBuilder line = new StringBuilder("| ");
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 1) {
                    line.append("X ");
                } else if (grid[i][j] == 0) {
                    line.append("O ");
                } else {
                    line.append("_ ");
                }
            }
            line.append("|");
            System.out.println(line);
        }
        System.out.println("-------");
    }
}
