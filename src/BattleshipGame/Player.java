package BattleshipGame;

public class Player implements IPlayer {
    private static final int SEA_SIZE = 10;
    private String[][] battleSea;
    private String[][] battleSeaOfEnemy;
    private String signOfPlayer;
    private static final String EMPTY_CELL = " ";
    private static final String DESTROYED_SHIP = "X";
    private static final String PAST = "*";

    public Player(String signOfPlayer) {
        this.battleSea = new String[SEA_SIZE][SEA_SIZE];
        this.battleSeaOfEnemy = new String[SEA_SIZE][SEA_SIZE];
        this.emptySea(battleSea);
        this.emptySea(battleSeaOfEnemy);
        this.signOfPlayer = signOfPlayer;
    }

    @Override
    public void placeShips(String placingHorOrVer, int[] startingCoordinatesOfPlacing, int typeOfShip) {
        if (placingHorOrVer.equals("H") || placingHorOrVer.equals("h")) {
            for (int i = startingCoordinatesOfPlacing[0]; i < (startingCoordinatesOfPlacing[0] + typeOfShip); i++) {
                battleSea[startingCoordinatesOfPlacing[1] - 1][i - 1] = signOfPlayer;
            }
        } else if (placingHorOrVer.equals("V") || placingHorOrVer.equals("v")) {
            for (int i = startingCoordinatesOfPlacing[1]; i < (startingCoordinatesOfPlacing[1] + typeOfShip); i++) {
                battleSea[i - 1][startingCoordinatesOfPlacing[0] - 1] = signOfPlayer;
            }
        }
    }

    @Override
    public boolean shoot(int[] coordinates, Player enemy) {
        if (enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1].equals(enemy.signOfPlayer)) {

            if (((coordinates[1] - 2) > -1 && (coordinates[1] - 2) < 10) &&
                    (((coordinates[0] - 1) > -1) && ((coordinates[0] - 1) < 10))) {
                if (enemy.battleSea[coordinates[1] - 2][coordinates[0] - 1].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    return false;
                }
            }

            if ((coordinates[1] > -1 && coordinates[1] < 10) &&
                    (((coordinates[0] - 1) > -1) && ((coordinates[0] - 1) < 10))) {
                if (enemy.battleSea[coordinates[1]][coordinates[0] - 1].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    return false;
                }
            }

            if (((coordinates[1] - 1) > -1 && (coordinates[1] - 1) < 10) &&
                    ((coordinates[0] > -1) && (coordinates[0] < 10))) {
                if (enemy.battleSea[coordinates[1] - 1][coordinates[0]].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    return false;
                }
            }

            if (((coordinates[1] - 1) > -1 && (coordinates[1] - 1) < 10) &&
                    (((coordinates[0] - 2) > -1) && ((coordinates[0] - 2) < 10))) {
                if (enemy.battleSea[coordinates[1] - 1][coordinates[0] - 2].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    return false;
                }
            } else {
                this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                System.out.println("KILLED!");
                if (this.isWinner(enemy)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = PAST;
            enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = PAST;
            System.out.println("MISSED!");
            return true;
        }

        return true;
    }


    public boolean shoot(int[] coordinates, Player enemy, Bot bot) {
        if (enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1].equals(enemy.signOfPlayer)) {

            if (((coordinates[1] - 2) > -1 && (coordinates[1] - 2) < 10) &&
                    (((coordinates[0] - 1) > -1) && ((coordinates[0] - 1) < 10))) {
                if (enemy.battleSea[coordinates[1] - 2][coordinates[0] - 1].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    bot.setTheResultOfAttack("WOUNDED");
                    bot.setTheCoordinatesOfWoundedShip(coordinates);
                    return false;
                }
            }

            if ((coordinates[1] > -1 && coordinates[1] < 10) &&
                    (((coordinates[0] - 1) > -1) && ((coordinates[0] - 1) < 10))) {
                if (enemy.battleSea[coordinates[1]][coordinates[0] - 1].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    bot.setTheResultOfAttack("WOUNDED");
                    bot.setTheCoordinatesOfWoundedShip(coordinates);
                    return false;
                }
            }

            if (((coordinates[1] - 1) > -1 && (coordinates[1] - 1) < 10) &&
                    ((coordinates[0] > -1) && (coordinates[0] < 10))) {
                if (enemy.battleSea[coordinates[1] - 1][coordinates[0]].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    bot.setTheResultOfAttack("WOUNDED");
                    bot.setTheCoordinatesOfWoundedShip(coordinates);
                    System.out.println("WOUNDED!");
                    return false;
                }
            }

            if (((coordinates[1] - 1) > -1 && (coordinates[1] - 1) < 10) &&
                    (((coordinates[0] - 2) > -1) && ((coordinates[0] - 2) < 10))) {
                if (enemy.battleSea[coordinates[1] - 1][coordinates[0] - 2].equals(enemy.signOfPlayer)) {
                    this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                    System.out.println("WOUNDED!");
                    bot.setTheResultOfAttack("WOUNDED");
                    bot.setTheCoordinatesOfWoundedShip(coordinates);
                    return false;
                }
            } else {
                this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = DESTROYED_SHIP;
                System.out.println("KILLED!");
                if (this.isWinner(enemy)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            this.battleSeaOfEnemy[coordinates[1] - 1][coordinates[0] - 1] = PAST;
            enemy.battleSea[coordinates[1] - 1][coordinates[0] - 1] = PAST;
            System.out.println("MISSED!");
            return true;
        }

        return true;
    }


    @Override
    public boolean isWinner(Player enemy) {
        for (int i = 0; i < enemy.battleSea.length; i++) {
            for (int j = 0; j < enemy.battleSea.length; j++) {
                if (enemy.battleSea[i][j].equals(enemy.signOfPlayer)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void displaySea() {
        System.out.println("   A  B  C  D  E  F  G  H  I  L");
        for (int i = 0; i < battleSea.length; i++) {
            if ((i + 1) == 10) {
                System.out.print((i + 1) + "|");
            } else {
                System.out.print((i + 1) + " |");
            }
            for (int j = 0; j < battleSea.length; j++) {
                System.out.print(battleSea[i][j] + " |");
            }
            System.out.println();
        }
    }

    public void displayMyAndMyEnemySeas() {
        System.out.println("             MY SEA                              ENEMY'S SEA");
        System.out.println("   A  B  C  D  E  F  G  H  I  L" + "         A  B  C  D  E  F  G  H  I  L");
        for (int i = 0; i < battleSea.length; i++) {
            if ((i + 1) == 10) {
                System.out.print((i + 1) + "|");
            } else {
                System.out.print((i + 1) + " |");
            }
            for (int j = 0; j < battleSea.length; j++) {
                System.out.print(battleSea[i][j] + " |");
            }
            if ((i + 1) == 10) {
                System.out.print("    " + (i + 1) + "|");
            } else {
                System.out.print("    " + (i + 1) + " |");
            }
            for (int j = 0; j < battleSeaOfEnemy.length; j++) {
                System.out.print(battleSeaOfEnemy[i][j] + " |");
            }
            System.out.println();
        }
    }

    @Override
    public void emptySea(String[][] sea) {
        for (int i = 0; i < sea.length; i++) {
            for (int j = 0; j < sea.length; j++) {
                sea[i][j] = EMPTY_CELL;
            }
        }
    }

    public boolean emptyPlace(String direction, int[] ints, int typeOfShip) {
        int[] coordinatesEntered = new int[2];

        coordinatesEntered[0] = ints[1]; //line
        coordinatesEntered[1] = ints[0]; //column

        coordinatesEntered[0]--;
        coordinatesEntered[1]--;

        if (direction.equals("H") || direction.equals("h")) {
            // 2 1
            for (int i = coordinatesEntered[1]; i < coordinatesEntered[1] + typeOfShip; i++) {
                if (!battleSea[coordinatesEntered[0]][i].equals(EMPTY_CELL)) {
                    return false;
                }

                //left
                int leftChecker = i - 1;
                if (leftChecker >= 0 && leftChecker <= 9) {
                    if (!battleSea[coordinatesEntered[0]][leftChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //up
                int upChecker = coordinatesEntered[0] - 1;
                if (upChecker >= 0 && upChecker <= 9) {
                    if (!battleSea[upChecker][i].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //left-up
                if ((leftChecker >= 0 && leftChecker <= 9) && (upChecker >= 0 && upChecker <= 9)) {
                    if (!battleSea[upChecker][leftChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //right
                int rightChecker = i + 1;
                if (rightChecker >= 0 && rightChecker <= 9) {
                    if (!battleSea[coordinatesEntered[0]][rightChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //right-up
                if ((rightChecker >= 0 && rightChecker <= 9) && (upChecker >= 0 && upChecker <= 9)) {
                    if (!battleSea[upChecker][rightChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //down
                int downChecker = coordinatesEntered[0] + 1;
                if (downChecker >= 0 && downChecker <= 9) {
                    if (!battleSea[downChecker][i].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //left-down
                if ((leftChecker >= 0 && leftChecker <= 9) && (downChecker >= 0 && downChecker <= 9)) {
                    if (!battleSea[downChecker][leftChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //right-down
                if ((rightChecker >= 0 && rightChecker <= 9) && (downChecker >= 0 && downChecker <= 9)) {
                    if (!battleSea[downChecker][rightChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

            }

            return true;
        } else if (direction.equals("V") || direction.equals("h")) {

            for (int i = coordinatesEntered[0]; i < coordinatesEntered[0] + typeOfShip; i++) {
                if (!battleSea[i][coordinatesEntered[1]].equals(EMPTY_CELL)) {
                    return false;
                }

                //left
                int leftChecker = i - 1;
                if (leftChecker >= 0 && leftChecker <= 9) {
                    if (!battleSea[coordinatesEntered[0]][leftChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //up
                int upChecker = coordinatesEntered[0] - 1;
                if (upChecker >= 0 && upChecker <= 9) {
                    if (!battleSea[upChecker][i].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //left-up
                if ((leftChecker >= 0 && leftChecker <= 9) && (upChecker >= 0 && upChecker <= 9)) {
                    if (!battleSea[upChecker][leftChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //right
                int rightChecker = i + 1;
                if (rightChecker >= 0 && rightChecker <= 9) {
                    if (!battleSea[coordinatesEntered[0]][rightChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //right-up
                if ((rightChecker >= 0 && rightChecker <= 9) && (upChecker >= 0 && upChecker <= 9)) {
                    if (!battleSea[upChecker][rightChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //down
                int downChecker = coordinatesEntered[0] + 1;
                if (downChecker >= 0 && downChecker <= 9) {
                    if (!battleSea[downChecker][i].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //left-down
                if ((leftChecker >= 0 && leftChecker <= 9) && (downChecker >= 0 && downChecker <= 9)) {
                    if (!battleSea[downChecker][leftChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }

                //right-down
                if ((rightChecker >= 0 && rightChecker <= 9) && (downChecker >= 0 && downChecker <= 9)) {
                    if (!battleSea[downChecker][rightChecker].equals(EMPTY_CELL)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            System.out.println("Damaged emptyPlace method!!!");
            return false;
        }
    }
}
