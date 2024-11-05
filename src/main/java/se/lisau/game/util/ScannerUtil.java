package se.lisau.game;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner sc = new Scanner(System.in);

    private ScannerUtil() {

    }

    public static String getUserInput(){
        return sc.nextLine();
    }
}
