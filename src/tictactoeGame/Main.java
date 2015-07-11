package ticTacToeGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("****Tic-Tac-Toe game begins****");
        System.out.println("Enter \"1\" to play with PC, or \"2\" to play with friend: ");
        int gameChooser = 0;
        //If user enters letters, symbols or numbers >2 or <1, the "try" below will catch it
        try {
            Scanner scanner = new Scanner(System.in);
            gameChooser = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("YOU HAD TO ENTER \"1\" or \"2\"!!!");
        }
        // Player vs PC
        if (gameChooser == 1) {
            TicTacToe ticTacToe = new TicTacToe(gameChooser);
            System.out.println("THE GAME STARTED!!");
            ticTacToe.displayField();
            startGame(ticTacToe);
        }//Player1 vs Player2
        else if (gameChooser == 2) {
            TicTacToe ticTacToe = new TicTacToe();
            System.out.println("THE GAME STARTED!!");
            ticTacToe.displayField();
            System.out.println("Enter two numbers (i.e. coordinates).");
            startGame(ticTacToe);
        }
    }

    private static void startGame(TicTacToe ticTacToe) {
        Scanner scanner;
        while (true) {
            ticTacToe.moveOf();
            int[] moveData = new int[2];
            //If user will try to match the matched cell, the "try" below will handle it
            try {
                //If user enters wrong coordinates (e.g. numbers greater than 3 or less than 1), the "try" below will handle it
                try {
                    //If user enters letters or other symbols, the "try" below will handle it
                    try {
                        scanner = new Scanner(System.in);
                        moveData[0] = scanner.nextInt();
                        moveData[1] = scanner.nextInt();
                        ticTacToe.playerMoved(moveData);
                        ticTacToe.displayField();
                    } catch (InputMismatchException e) {
                        System.out.println("ENTER 2 NUMBERS(i.e. coordinates)!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("EACH ENTERED NUMBER MUST BE >= 1 AND <=3");
                }
            } catch (Exception e) {
                System.out.println("!!!!!!THE CELL YOU WANTED TO MATCH ALREADY MATCHED!!!!!!");
            }
            if (ticTacToe.doesAnyoneWon()) {
                return;
            } else if (ticTacToe.doesAnyoneWon()) {
                return;
            }
        }
    }
}
