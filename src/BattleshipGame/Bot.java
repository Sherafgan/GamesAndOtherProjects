package BattleshipGame;

public class Bot {
    private String attackCoordinates;
    private String theResultOfAttack = "NOTHING";
    private int[] theCoordinatesOfWoundedShip;
    private static final String[] upperCaseLibStringArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "L"};

    public void setTheResultOfAttack(String theResultOfAttack) {
        this.theResultOfAttack = theResultOfAttack;
    }

    public void setTheCoordinatesOfWoundedShip(int[] theCoordinatesOfWoundedShip) {
        this.theCoordinatesOfWoundedShip = theCoordinatesOfWoundedShip;
    }

    public String getAttackCoordinates() {
        if (theResultOfAttack.equals("WOUNDED")) {
            //attack around
            int chooserOfDirectionOfAttack = ((int) (Math.random() * 99) + 1);
            if (chooserOfDirectionOfAttack <= 25) {
                //up
                attackCoordinates = this.getCoordinatesByDirection(1, theCoordinatesOfWoundedShip);
            } else if (chooserOfDirectionOfAttack > 25 && chooserOfDirectionOfAttack <= 50) {
                //right
                attackCoordinates = this.getCoordinatesByDirection(2, theCoordinatesOfWoundedShip);
            } else if (chooserOfDirectionOfAttack > 50 && chooserOfDirectionOfAttack <= 75) {
                //down
                attackCoordinates = this.getCoordinatesByDirection(3, theCoordinatesOfWoundedShip);
            } else {
                //left
                attackCoordinates = this.getCoordinatesByDirection(4, theCoordinatesOfWoundedShip);
            }
        } else {
            //attack random
            int chooserOfLetter = ((int) (Math.random() * 99) + 1);
            if (chooserOfLetter <= 10) {
                //A letter
                attackCoordinates = "A";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 10 && chooserOfLetter <= 20) {
                //B letter
                attackCoordinates = "B";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 20 && chooserOfLetter <= 30) {
                //C letter
                attackCoordinates = "C";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 30 && chooserOfLetter <= 40) {
                //D letter
                attackCoordinates = "D";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 40 && chooserOfLetter <= 50) {
                //E letter
                attackCoordinates = "E";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 50 && chooserOfLetter <= 60) {
                //F letter
                attackCoordinates = "F";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 60 && chooserOfLetter <= 70) {
                //G letter
                attackCoordinates = "G";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 70 && chooserOfLetter <= 80) {
                //H letter
                attackCoordinates = "H";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else if (chooserOfLetter > 80 && chooserOfLetter <= 90) {
                //I letter
                attackCoordinates = "I";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            } else {
                //L letter
                attackCoordinates = "L";
                attackCoordinates = attackCoordinates + "" + (chooserOfLetter / 10);
            }
        }
        // theResultOfAttack =  e.g. A10, B7, G6...
        return attackCoordinates;
    }

    private String getCoordinatesByDirection(int i, int[] theCoordinatesOfWoundedShip) {
        String attackCoordinates = "";

        if (i == 1) {
            //up
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[0] == (j + 1)) {
                    attackCoordinates = upperCaseLibStringArray[j];
                }
            }
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[1] == (j + 1)) {
                    attackCoordinates = attackCoordinates + "" + (j);
                }
            }
        } else if (i == 2) {
            //right
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[0] == (j + 1)) {
                    attackCoordinates = upperCaseLibStringArray[j + 1];
                }
            }
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[1] == (j + 1)) {
                    attackCoordinates = attackCoordinates + "" + (j + 1);
                }
            }
        } else if (i == 3) {
            //down
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[0] == (j + 1)) {
                    attackCoordinates = upperCaseLibStringArray[j];
                }
            }
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[1] == (j + 1)) {
                    attackCoordinates = attackCoordinates + "" + (j + 2);
                }
            }
        } else {
            //left
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[0] == (j + 1)) {
                    attackCoordinates = upperCaseLibStringArray[j - 1];
                }
            }
            for (int j = 0; j < upperCaseLibStringArray.length; j++) {
                if (theCoordinatesOfWoundedShip[1] == (j + 1)) {
                    attackCoordinates = attackCoordinates + "" + (j + 1);
                }
            }
        }

        return attackCoordinates;
    }
}
