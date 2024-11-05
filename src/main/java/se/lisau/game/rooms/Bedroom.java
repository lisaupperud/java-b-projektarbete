package se.lisau.game.rooms;

import se.lisau.game.util.ScannerUtil;

public class Bedroom implements Room {
    private boolean lightsOn = false;

    @Override
    public void roomDescription() {
        if (!lightsOn) {
            roomTask();
        } else {
            System.out.println("        -- the room is empty! the burglar is in the hallway --");
        }


    }

    @Override
    public void roomTask() {
        if (!lightsOn) {
            System.out.println("                -- turn on the light? y/n --");
            String answer = ScannerUtil.getUserInput();
            if (answer.equalsIgnoreCase("y")) {
                turnOnLights();
                System.out.println("                   -- lights are on --");
                System.out.println("   -- the burglar is searching for something to steal! --");
                System.out.println("  -- the sudden light scares him and he runs towards you --");
                System.out.println(" ");
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("                -- lights are off --");
                System.out.println("  -- the burglar doesn't know you are awake yet --");
            } else {
                System.out.println("-- invalid input --");
            }
        }

    }
    // metod för att tända ljusen
    // om man vill tända ljusen så kallas metoden på och variabeln sätts till true
    // så om man kommer in i Bedroom igen så är ljusen redan tända
    public void turnOnLights() {
        if (!lightsOn) {
            lightsOn = true;
        }
    }
}
