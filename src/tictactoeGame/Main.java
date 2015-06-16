package tictactoeGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println("THE GAME STARTED!!");
        ticTacToe.displayField();
        while (true) {
            ticTacToe.moveOf();
            int[] moveData = new int[2];
            try {
                try {
                    scanner = new Scanner(System.in);
                    moveData[0] = scanner.nextInt();
                    moveData[1] = scanner.nextInt();
                    ticTacToe.playerMoved(moveData);
                    ticTacToe.displayField();
                } catch (InputMismatchException e) {
                    System.out.println("ENTER 2 NUMBERS(e.g. coordinates)!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("EACH ENTERED NUMBER MUST BE >= 1 AND <=3");
            }
            if (ticTacToe.doesAnyoneWon()) {
                return;
            } else if (ticTacToe.doesAnyoneWon()) {
                return;
            }
        }
    }
}
