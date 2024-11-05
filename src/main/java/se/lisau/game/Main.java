package se.lisau.game;

import se.lisau.game.util.ScannerUtil;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        ScannerUtil.closeScanner();

    }
}
