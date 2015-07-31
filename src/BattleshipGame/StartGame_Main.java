package BattleshipGame;

import java.io.*;

public class StartGame_Main implements Serializable {
    public static void main(String[] args) throws IOException {
        Battleship battleship = new Battleship();
        battleship.launch();
    }
}
