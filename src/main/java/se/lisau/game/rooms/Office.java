package se.lisau.game.rooms;

import se.lisau.game.Game;
import se.lisau.game.model.Burglar;
import se.lisau.game.util.ScannerUtil;

public class Office implements Room {
    // referens till Game-objektet för att kunna avsluta spelet om spelaren vunnit
    private final Game game;
    // referens till Burglar-objektet för att kunna kontrollera dess tillstånd
    private final Burglar burglar;
    // kontrollerar om spelaren kan försöka ringa polisen
    private boolean running = true;

    // konstruktor som tar in två parametrar
    public Office(Game game, Burglar burglar) {
        this.game = game; // för att kunna avsluta spelet med hjälp av boolean setGameFinished
        this.burglar = burglar; // för att kunna kontrollera om tjuven är vid medvetande
    }

    @Override
    public void roomDescription() {
        System.out.println("       -- you're in the office --");
        // man kan bara ringa polisen om tjuven är medvetslös
        // skrivs bara ut om villkoret stämmer
        if (!burglar.isConscious()) {
            System.out.println("-- you see the phone charging on the desk --");
            System.out.println("          -- pick it up? y/n --");
            String YN = ScannerUtil.getUserInput();
            // om spelaren väljer 'y' så anropar roomTask()
            if (YN.equalsIgnoreCase("y")) {
                roomTask();
            } else if (YN.equalsIgnoreCase("n")) {
                System.out.println("   -- the burglar is waking up --");
                System.out.println(" -- you need to call the police! --");
                // spelaren måste ringa polisen
                roomTask();
            } else {
                System.out.println("invalid input");
            }
        // här hamnar spelaren om tjuven inte är medvetslös
        } else {
            System.out.println(" -- you're too stressed to find the phone! --");
        }


    }

    @Override
    public void roomTask() {
        // loop där spelaren måste ange rätt nummer för att ringa polisen
        while (running) {
            System.out.println("            -- call the police: --");
            String policeNumber = ScannerUtil.getUserInput();
            // om spelaren ringer 911 eller 112
            if (policeNumber.equalsIgnoreCase("911") || policeNumber.equalsIgnoreCase("112")) {
                System.out.println("        --calling the police--");
                System.out.println("        YOU WON! CONGRATULATIONS!");
                // så avslutas spelet eftersom variabeln gameFinished nu är true
                game.setGameFinished(true);
                // loopen avslutas
                running = false;
            } else {
                // om man skriver fel nummer
                System.out.println("   -- wrong number! try again --");
            }
        }

    }

}
