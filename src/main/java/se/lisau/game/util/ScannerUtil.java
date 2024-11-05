package se.lisau.game.util;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner sc = new Scanner(System.in);

    private ScannerUtil() {

    }

    public static String getUserInput() {
        return sc.nextLine().toLowerCase();
    }

    public static void closeScanner() {
        sc.close();
    }
}
