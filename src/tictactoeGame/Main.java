package tictactoeGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println("THE GAME STARTED!!");
        ticTacToe.displayField();
        boolean queue = true;
        while (true) {
            if (queue) {
                System.out.println("First player: ");
                int[] moveData = new int[2];
                moveData[0] = scanner.nextInt();
                moveData[1] = scanner.nextInt();
                ticTacToe.firstPlayerMoved(moveData);
                ticTacToe.displayField();
                queue = false;
            } else {
                System.out.println("Second player: ");
                int[] moveData = new int[2];
                moveData[0] = scanner.nextInt();
                moveData[1] = scanner.nextInt();
                ticTacToe.secondPlayerMoved(moveData);
                ticTacToe.displayField();
                queue = true;
            }

            if (ticTacToe.doesAnyoneWon()) {
                System.out.println("FIRST PLAYER WON!!!");
                ticTacToe.displayField();
                return;
            } else if (ticTacToe.doesAnyoneWon()) {
                System.out.println("SECOND PLAYER WON!!!");
                ticTacToe.displayField();
                return;
            }
        }
    }
}
