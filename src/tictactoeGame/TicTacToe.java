package tictactoeGame;

/**
 * @uthor Kandov Sherafgan
 */
public class TicTacToe {

    private static final int FIELD_SIZE = 3;

    private static final int MOVE_DATA_SIZE = 2;

    private static final int FIRST_PLAYER = 1;

    private static final int SECOND_PLAYER = 2;

    private static boolean FIRST_PLAYERS_MOVE = true;

    private String[][] mainField = new String[FIELD_SIZE][FIELD_SIZE];

    private int[] moveOfAI = new int[MOVE_DATA_SIZE];

    public TicTacToe() {
        for (int i = 0; i < this.mainField.length; i++) {
            for (int j = 0; j < this.mainField.length; j++) {
                this.mainField[i][j] = " ";
            }
        }
    }

    public void playerMoved(int[] moveData) {
        if (FIRST_PLAYERS_MOVE) {
            this.markTheCell(moveData, FIRST_PLAYER);
            FIRST_PLAYERS_MOVE = false;
        } else {
            this.markTheCell(moveData, SECOND_PLAYER);
            FIRST_PLAYERS_MOVE = true;
        }
    }

    public void moveOf() {
        if (FIRST_PLAYERS_MOVE) {
            System.out.println("First player: ");
        } else {
            System.out.println("Second player: ");
        }
    }

    private void markTheCell(int[] moveData, int player) {
        if (player == FIRST_PLAYER) {
            this.mainField[moveData[0] - 1][moveData[1] - 1] = "X";
        } else {
            this.mainField[moveData[0] - 1][moveData[1] - 1] = "O";
        }
    }

    public boolean doesAnyoneWon() {
        if (checkIfXsOrOsWon("X")) {
            System.out.println("FIRST PLAYER WON!!!");
            this.displayField();
            return true;
        } else if (checkIfXsOrOsWon("O")) {
            System.out.println("SECOND PLAYER WON!!!");
            this.displayField();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfXsOrOsWon(String xOrO) {
        for (int i = 0; i < this.mainField.length; i++) {
            if (this.mainField[i][0] == xOrO && this.mainField[i][1] == xOrO && this.mainField[i][2] == xOrO) {
                return true;
            } else if (this.mainField[0][i] == xOrO && this.mainField[1][i] == xOrO && this.mainField[2][i] == xOrO) {
                return true;
            }
        }
        if (this.mainField[0][0] == xOrO && this.mainField[1][1] == xOrO && this.mainField[2][2] == xOrO) {
            return true;
        } else if (this.mainField[0][2] == xOrO && this.mainField[1][1] == xOrO && this.mainField[2][0] == xOrO) {
            return true;
        } else {
            return false;
        }
    }

    public void displayField() {
        for (int i = 0; i < this.mainField.length; i++) {
            for (int j = 0; j < this.mainField.length; j++) {
                System.out.print("[" + this.mainField[i][j] + "]");
            }
            System.out.println();
        }
    }
}
