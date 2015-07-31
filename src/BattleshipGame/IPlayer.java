package BattleshipGame;

public interface IPlayer {
    void placeShips(String placingHorOrVer, int[] startingCoordinatesOfPlacing, int typeOfShip);

    boolean shoot(int[] coordinates, Player enemy);

    boolean isWinner(Player enemy);

    void displaySea();

    void emptySea(String[][] battleSea);
}
