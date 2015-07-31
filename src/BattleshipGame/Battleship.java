package BattleshipGame;

import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    public void launch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***********BATTLESHIP***********");
        System.out.println("////////////////////////////////");
        System.out.println("1 - Player vs PC.            ///");
        System.out.println("2 - Player1 vs Player2       ///");
        System.out.println("////////////////////////////////");
        System.out.println("Enter game mode: ");
        System.out.print("-->");
        int gameModeChooser = scanner.nextInt();
        if (gameModeChooser == 1) {
            Player firstPlayer = new Player("1");
            Player playerAI = new Player("b");
            Bot bot = new Bot();

            // 1 player placing ships
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("FIRST PLAYER PLACE SHIPS!");
            System.out.println("Place ships randomly? (Y/N)");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String randomChooser = scanner.next();
            if (randomChooser.equals("Y") || randomChooser.equals("y")) {
                randomPlacingProcess(firstPlayer);
            } else {
                placingProcess(firstPlayer);
            }
            System.out.println("***FIRST PLAYER'S SHIPS PLACED***");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            firstPlayer.displaySea();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // 2 player placing ships
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("BOT PLACE SHIPS!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            randomPlacingProcess(playerAI);
            System.out.println("***BOT SHIPS PLACED***");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            playerAI.displaySea();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            //asking for shooting
            System.out.println("Start shooting process? (Y/N): ");
            System.out.print("-->");
            String permission = scanner.next();
            if (permission.equals("Y") || permission.equals("y")) {

                //shooting process
                boolean queueToShoot = true;
                while (true) {
                    if (queueToShoot) {
                        System.out.println("                            FIRST PLAYER");
                        shootingProcess(firstPlayer, playerAI);
                        queueToShoot = false;
                    } else {
                        System.out.println("                                 BOT");
                        shootingProcess(playerAI, firstPlayer, bot);
                        queueToShoot = true;
                    }
                    if (firstPlayer.isWinner(playerAI)) {
                        System.out.println("!!!FIRST PLAYER WON!!!");
                        return;
                    } else if (playerAI.isWinner(playerAI)) {
                        System.out.println("!!!BOT WON!!!");
                        return;
                    }
                }
            }
        } else if (gameModeChooser == 2) {
            Player firstPlayer = new Player("1");
            Player secondPlayer = new Player("2");

            // 1 player placing ships
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("FIRST PLAYER PLACE SHIPS!");
            System.out.println("Place ships randomly? (Y/N)");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String randomChooser = scanner.next();
            if (randomChooser.equals("Y") || randomChooser.equals("y")) {
                randomPlacingProcess(firstPlayer);
            } else {
                placingProcess(firstPlayer);
            }
            System.out.println("***FIRST PLAYER'S SHIPS PLACED***");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            firstPlayer.displaySea();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // 2 player placing ships
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("SECOND PLAYER PLACE SHIPS!");
            System.out.println("Place ships randomly? (Y/N)");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            randomChooser = scanner.next();
            if (randomChooser.equals("Y") || randomChooser.equals("y")) {
                randomPlacingProcess(secondPlayer);
            } else {
                placingProcess(secondPlayer);
            }
            System.out.println("***SECOND PLAYER'S SHIPS PLACED***");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            secondPlayer.displaySea();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            //shooting process
            boolean queueToShoot = true;
            while (true) {
                if (queueToShoot) {
                    System.out.println("                            FIRST PLAYER");
                    shootingProcess(firstPlayer, secondPlayer);
                    queueToShoot = false;
                } else {
                    System.out.println("                            SECOND PLAYER");
                    shootingProcess(secondPlayer, firstPlayer);
                    queueToShoot = true;
                }
                if (firstPlayer.isWinner(secondPlayer)) {
                    System.out.println("!!!FIRST PLAYER WON!!!");
                    return;
                } else if (secondPlayer.isWinner(secondPlayer)) {
                    System.out.println("!!!SECOND PLAYER WON!!!");
                    return;
                }
            }
        } else {
            System.out.println("!!!WRONG INPUT!!!");
        }
    }

    private static void shootingProcess(Player player, Player enemy) {
        Scanner scanner = new Scanner(System.in);
        char[] enteredCoordinatesArrChar;
        do {
            player.displayMyAndMyEnemySeas();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Enter coordinates (e.g. A10, B1, L8): ");
            String enteredCoordinates = scanner.next();
            enteredCoordinatesArrChar = enteredCoordinates.toCharArray();
        } while (!player.shoot(parseInputData(enteredCoordinatesArrChar), enemy));
    }

    private static void shootingProcess(Player player, Player enemy, Bot bot) {
        char[] attackCoordinatesArrChar;
        do {
            player.displayMyAndMyEnemySeas();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String enteredCoordinates = bot.getAttackCoordinates();
            attackCoordinatesArrChar = enteredCoordinates.toCharArray();
        } while (!player.shoot(parseInputData(attackCoordinatesArrChar), enemy, bot));
    }

    private static void randomPlacingProcess(Player player) {
        int amountOfBattleships = 1;
        int amountOfSubmarines = 2;
        int amountOfCruisers = 3;
        int amountOfDestroyers = 4;
        String randomDirection;
        int[] battleshipRandomPlaceCoordinates;
        for (int i = 0; i < 10; i++) {
            if (amountOfBattleships > 0) {
                boolean b = true;
                while (b) {
                    randomDirection = getRandomDirection();
                    battleshipRandomPlaceCoordinates = getRandomCoordinates(4);
                    if (player.emptyPlace(randomDirection, battleshipRandomPlaceCoordinates, 4)) {
                        player.placeShips(randomDirection, battleshipRandomPlaceCoordinates, 4);
                        b = false;
                    }
                }
                amountOfBattleships--;
            }
            if (amountOfSubmarines > 0) {
                boolean b = true;
                while (b) {
                    randomDirection = getRandomDirection();
                    battleshipRandomPlaceCoordinates = getRandomCoordinates(3);
                    if (player.emptyPlace(randomDirection, battleshipRandomPlaceCoordinates, 3)) {
                        player.placeShips(randomDirection, battleshipRandomPlaceCoordinates, 3);
                        b = false;
                    }
                }
                amountOfSubmarines--;
            }
            if (amountOfCruisers > 0) {
                boolean b = true;
                while (b) {
                    randomDirection = getRandomDirection();
                    battleshipRandomPlaceCoordinates = getRandomCoordinates(2);
                    if (player.emptyPlace(randomDirection, battleshipRandomPlaceCoordinates, 2)) {
                        player.placeShips(randomDirection, battleshipRandomPlaceCoordinates, 2);
                        b = false;
                    }
                }
                amountOfCruisers--;
            }
            if (amountOfDestroyers > 0) {
                boolean b = true;
                while (b) {
                    randomDirection = getRandomDirection();
                    battleshipRandomPlaceCoordinates = getRandomCoordinates(1);
                    if (player.emptyPlace(randomDirection, battleshipRandomPlaceCoordinates, 1)) {
                        player.placeShips(randomDirection, battleshipRandomPlaceCoordinates, 1);
                        b = false;
                    }
                }
                amountOfDestroyers--;
            }
        }
    }

    private static int[] getRandomCoordinates(int i) {
        int[] coordinates = new int[2];
        if (i == 4) {
            coordinates[0] = ((int) (Math.random() * 6) + 1);
            coordinates[1] = ((int) (Math.random() * 6) + 1);
        } else if (i == 3) {
            coordinates[0] = ((int) (Math.random() * 7) + 1);
            coordinates[1] = ((int) (Math.random() * 7) + 1);
        } else if (i == 2) {
            coordinates[0] = ((int) (Math.random() * 8) + 1);
            coordinates[1] = ((int) (Math.random() * 8) + 1);
        } else {
            coordinates[0] = ((int) (Math.random() * 9) + 1);
            coordinates[1] = ((int) (Math.random() * 9) + 1);
        }
        return coordinates;
    }

    private static void placingProcess(Player player) {
        int amountOfBattleships = 1;
        int amountOfSubmarines = 2;
        int amountOfCruisers = 3;
        int amountOfDestroyers = 4;
        Scanner scanner2 = new Scanner(System.in);
        player.displaySea();
        System.out.println("There 4 types of ships: ");
        for (int i = 0; i < 10; i++) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            if (amountOfBattleships > 0) {
                System.out.println("1. Battleship (Four-decked)[" + amountOfBattleships + " piece(s)]");
            }
            if (amountOfSubmarines > 0) {
                System.out.println("2. Submarine (Three-decked)[" + amountOfSubmarines + " piece(s)]");
            }
            if (amountOfCruisers > 0) {
                System.out.println("3. Cruiser (Two-decked)[" + amountOfCruisers + " piece(s)");
            }
            if (amountOfDestroyers > 0) {
                System.out.println("4. Destroyer (One-decked)[" + amountOfDestroyers + " piece(s)");
            }
            System.out.println("Choose ship: ");
            int chooserOfShip = scanner2.nextInt();
            if (chooserOfShip >= 1 && chooserOfShip <= 4) {
                System.out.println("Place ship horizontal or vertical? (H/V): ");
                String randomDirection = scanner2.next();
                System.out.println("Enter coordinates (e.g. A1, H8, B10)");
                System.out.println("Starting coordinates: ");
                String startingCoordinates = scanner2.next();
                char[] startingCoordinatesArrChar = startingCoordinates.toCharArray();
                if (chooserOfShip == 1) {
                    sendingDataToPlaceShip(player, randomDirection, startingCoordinatesArrChar, 4);
                    amountOfBattleships--;
                } else if (chooserOfShip == 2) {
                    sendingDataToPlaceShip(player, randomDirection, startingCoordinatesArrChar, 3);
                    amountOfSubmarines--;
                } else if (chooserOfShip == 3) {
                    sendingDataToPlaceShip(player, randomDirection, startingCoordinatesArrChar, 2);
                    amountOfCruisers--;
                } else {
                    sendingDataToPlaceShip(player, randomDirection, startingCoordinatesArrChar, 1);
                    amountOfDestroyers--;
                }
            } else {
                System.out.println("!!!WRONG INPUT!!!");
            }
        }
    }

    private static void sendingDataToPlaceShip(Player player, String placingHorOrVer, char[] startingCoordinatesArrChar, int typeOfShip) {
        player.placeShips(placingHorOrVer, parseInputData(startingCoordinatesArrChar), typeOfShip);
        player.displaySea();
        System.out.println("Ship placed!");
    }

    private static int[] parseInputData(char[] startingCoordinatesArrChar) {
        int[] parsedInputData = new int[2];
        char[] upperCaseLib = "ABCDEFGHIL".toCharArray();
        char[] lowerCaseLib = "abcdefghil".toCharArray();

        if (startingCoordinatesArrChar.length == 2) {
            for (int i = 0; i < upperCaseLib.length; i++) {
                if (startingCoordinatesArrChar[0] == upperCaseLib[i] || startingCoordinatesArrChar[0] == lowerCaseLib[i]) {
                    parsedInputData[0] = (i + 1);
                }
                if (((int) startingCoordinatesArrChar[1] - 48) == (i + 1)) {
                    parsedInputData[1] = (i + 1);
                }
            }
        } else {
            for (int i = 0; i < upperCaseLib.length; i++) {
                if (startingCoordinatesArrChar[0] == upperCaseLib[i] || startingCoordinatesArrChar[0] == lowerCaseLib[i]) {
                    parsedInputData[0] = (i + 1);
                }
                parsedInputData[1] = 10;
            }
        }
        return parsedInputData;
    }

    public static String getRandomDirection() {
        int hRChooser = ((int) (Math.random() * 9) + 1);
        String horOrVer;
        if (hRChooser >= 1 && hRChooser <= 5) {
            horOrVer = "H";
        } else {
            horOrVer = "V";
        }
        return horOrVer;
    }
}
