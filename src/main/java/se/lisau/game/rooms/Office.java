package se.lisau.game.rooms;

import se.lisau.game.Game;
import se.lisau.game.model.Burglar;
import se.lisau.game.util.ScannerUtil;

public class Office implements Room {
    private final Game game; // Game-referens
    private final Burglar burglar; // Burglar-referens
    private boolean running = true;

    public Office(Game game, Burglar burglar) {
        this.game = game; // för att kunna avsluta spelet med hjälp av boolean isGameFinished
        this.burglar = burglar; // för att kunna få tag i en burglar
    }

    @Override
    public void roomDescription() {
        System.out.println("       -- you're in the office --");
        if (!burglar.isConscious()) {
            System.out.println("-- you see the phone charging on the desk --");
            System.out.println("          -- pick it up? y/n --");
            String YN = ScannerUtil.getUserInput();
            if (YN.equalsIgnoreCase("y")) {
                roomTask();
            } else if (YN.equalsIgnoreCase("n")) {
                System.out.println("   -- the burglar is waking up --");
                System.out.println(" -- you need to call the police! --");
                roomTask();
            } else {
                System.out.println("invalid input");
            }
        } else {
            System.out.println(" -- you're too stressed to find the phone! --");
        }


    }

    @Override
    public void roomTask() {
        while (running) {
            System.out.println("            -- call the police: --");
            String policeNumber = ScannerUtil.getUserInput();
            if (policeNumber.equalsIgnoreCase("911") || policeNumber.equalsIgnoreCase("112")) {
                System.out.println("        --calling the police--");
                System.out.println("        YOU WON! CONGRATULATIONS!");
                game.isGameFinished(true);
                running = false;
            } else {
                System.out.println("   -- wrong number! try again --");
            }
        }

    }

}
