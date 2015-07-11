package ticTacToeGame;

/**
 * @uthor Kandov Sherafgan
 */
public class TicTacToe {

    private static final int FIELD_SIZE = 3;

    private static final int FIRST_PLAYER = 1;

    private static final int SECOND_PLAYER = 2;

    private static final String X_MARK = "X";

    private static final String O_MARK = "O";

    private static final String EMPTY_CELL = " ";

    private static boolean FIRST_PLAYERS_MOVE = true;

    private static boolean AI_PLAYS;

    private String[][] mainField = new String[FIELD_SIZE][FIELD_SIZE];

    public TicTacToe() {
        for (int i = 0; i < this.mainField.length; i++) {
            for (int j = 0; j < this.mainField.length; j++) {
                this.mainField[i][j] = EMPTY_CELL;
            }
        }
    }

    public TicTacToe(int gameChooser) {
        this();
        if (gameChooser == 1) {
            AI_PLAYS = true;
        } else {
            AI_PLAYS = false;
        }
    }

    public int[] movesAI() {

        int[] moveOfAIData = new int[2];

        for (int i = 0; i < this.mainField.length; i++) {
            if (this.mainField[i][0].equals(X_MARK) && this.mainField[i][1].equals(X_MARK)) {
                if (this.mainField[i][2].equals(EMPTY_CELL)) {
                    moveOfAIData[0] = i + 1;
                    moveOfAIData[1] = 3;
                }
            } else if (this.mainField[0][i].equals(X_MARK) && this.mainField[1][i].equals(X_MARK)) {
                if (this.mainField[2][i].equals(EMPTY_CELL)) {
                    moveOfAIData[0] = 3;
                    moveOfAIData[1] = i + 1;
                }
            } else if (this.mainField[i][1].equals(X_MARK) && this.mainField[i][2].equals(X_MARK)) {
                if (this.mainField[i][0].equals(EMPTY_CELL)) {
                    moveOfAIData[0] = i + 1;
                    moveOfAIData[1] = 1;
                }
            } else if (this.mainField[1][i].equals(X_MARK) && this.mainField[2][i].equals(X_MARK)) {
                if (this.mainField[0][i].equals(EMPTY_CELL)) {
                    moveOfAIData[0] = 1;
                    moveOfAIData[1] = i + 1;
                }
            }
        }
        if (this.mainField[0][0].equals(X_MARK) && this.mainField[1][1].equals(X_MARK)) {
            if (this.mainField[2][2].equals(EMPTY_CELL)) {
                moveOfAIData[0] = 3;
                moveOfAIData[1] = 3;
            }
        } else if (this.mainField[1][1].equals(X_MARK) && this.mainField[2][2].equals(X_MARK)) {
            if (this.mainField[0][0].equals(EMPTY_CELL)) {
                moveOfAIData[0] = 1;
                moveOfAIData[1] = 1;
            }
        } else if (this.mainField[0][2].equals(X_MARK) && this.mainField[1][1].equals(X_MARK)) {
            if (this.mainField[2][0].equals(EMPTY_CELL)) {
                moveOfAIData[0] = 3;
                moveOfAIData[1] = 1;
            }
        } else if (this.mainField[1][1].equals(X_MARK) && this.mainField[2][0].equals(X_MARK)) {
            if (this.mainField[0][2].equals(EMPTY_CELL)) {
                moveOfAIData[0] = 1;
                moveOfAIData[1] = 3;
            }
        } else {
            for (int i = 0; i < this.mainField.length; i++) {
                for (int j = 0; j < this.mainField.length; j++) {
                    if (this.mainField[i][j].equals(EMPTY_CELL)) {
                        moveOfAIData[0] = i + 1;
                        moveOfAIData[1] = j + 1;
                        return moveOfAIData;
                    }
                }
            }
        }
        return moveOfAIData;
    }

    public void playerMoved(int[] moveData) throws Exception {
        if (FIRST_PLAYERS_MOVE) {
            this.markTheCell(moveData, FIRST_PLAYER);
            FIRST_PLAYERS_MOVE = false;
            if (AI_PLAYS) {
                this.markTheCell(this.movesAI(), SECOND_PLAYER);
                FIRST_PLAYERS_MOVE = true;
                //System.out.println("PC matched.");
            }
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

    private void markTheCell(int[] moveData, int player) throws Exception {
        if (player == FIRST_PLAYER) {
            if (isCellMatched(moveData)) {
                throw new Exception();
            } else {
                this.mainField[moveData[0] - 1][moveData[1] - 1] = X_MARK;
            }
        } else {
            if (isCellMatched(moveData)) {
                throw new Exception();
            } else {
                this.mainField[moveData[0] - 1][moveData[1] - 1] = O_MARK;
            }
        }
    }

    private boolean isCellMatched(int[] moveData) {
        if (mainField[moveData[0] - 1][moveData[1] - 1].equals(X_MARK) || mainField[moveData[0] - 1][moveData[1] - 1].equals(O_MARK)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doesAnyoneWon() {
        if (checkIfXsOrOsWon(X_MARK)) {
            System.out.println("FIRST PLAYER WON!!!");
            this.displayField();
            return true;
        } else if (checkIfXsOrOsWon(O_MARK)) {
            if (AI_PLAYS) {
                System.out.println("PC WON!!!");
            } else {
                System.out.println("SECOND PLAYER WON!!!");
            }
            this.displayField();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfXsOrOsWon(String xOrO) {
        for (int i = 0; i < this.mainField.length; i++) {
            if (this.mainField[i][0].equals(xOrO) && this.mainField[i][1].equals(xOrO) && this.mainField[i][2].equals(xOrO)) {
                return true;
            } else if (this.mainField[0][i].equals(xOrO) && this.mainField[1][i].equals(xOrO) && this.mainField[2][i].equals(xOrO)) {
                return true;
            }
        }
        if (this.mainField[0][0].equals(xOrO) && this.mainField[1][1].equals(xOrO) && this.mainField[2][2].equals(xOrO)) {
            return true;
        } else if (this.mainField[0][2].equals(xOrO) && this.mainField[1][1].equals(xOrO) && this.mainField[2][0].equals(xOrO)) {
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
