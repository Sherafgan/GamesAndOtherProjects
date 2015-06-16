package tictactoeGame;

/**
 * @uthor Kandov Sherafgan
 */
public class TicTacToe {

    private static final int FIELD_SIZE = 3;

    private static final int MOVE_DATA_SIZE = 2;

    private static final int FIRST_PLAYER = 1;

    private static final int SECOND_PLAYER = 2;

    private String[][] mainField = new String[FIELD_SIZE][FIELD_SIZE];

    private int[] firstPlayersMove = new int[MOVE_DATA_SIZE];

    private int[] secondPlayersMove = new int[MOVE_DATA_SIZE];

    private int[] moveOfAI = new int[MOVE_DATA_SIZE];

    public TicTacToe() {
        for (int i = 0; i < this.mainField.length; i++) {
            for (int j = 0; j < this.mainField.length; j++) {
                this.mainField[i][j] = " ";
            }
        }
    }

    public void firstPlayerMoved(int[] moveData) {
        this.markTheCell(moveData, FIRST_PLAYER);
    }

    public void secondPlayerMoved(int[] moveData) {
        this.markTheCell(moveData, SECOND_PLAYER);
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
            return true;
        } else if (checkIfXsOrOsWon("O")) {
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
