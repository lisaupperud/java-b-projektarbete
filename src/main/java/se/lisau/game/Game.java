package se.lisau.game;

import se.lisau.game.model.Burglar;
import se.lisau.game.util.ScannerUtil;
import se.lisau.game.model.Resident;
import se.lisau.game.rooms.Bedroom;
import se.lisau.game.rooms.Hallway;
import se.lisau.game.rooms.Kitchen;
import se.lisau.game.rooms.Office;

public class Game {
    // referenser till rums-objekten
    private final Kitchen kitchen;
    private final Office office;
    private final Bedroom bedroom;
    private final Hallway hallway;
    // konstant för att representera vardagsrummet
    private final String LIVING_ROOM = "living room";
    // konstant för att representera startplatsen
    private final String START = "start";
    // variabel för att hålla reda på nuvarande plats i spelet
    private String currentLocation = START;
    // variabel för att hålla reda på den aktuella spelaren
    private Resident currentResident;
    // variabel för att hålla reda på om spelet är vunnet
    private static boolean gameFinished = false;

    public Game() {
        // inledande meddelanden och skapar upp spelkaraktären
        printStartMessage();

        // instantiate Burglar här för att använda i de andra rummen
        Burglar burglar = new Burglar("burglar", 100, 15);

        // instantiate rummen och skickar karaktärerna dit
        kitchen = new Kitchen(currentResident); // tilldela currentResident till Kitchens konstruktor
        office = new Office(burglar);
        bedroom = new Bedroom();
        hallway = new Hallway(currentResident, burglar);

    }

    private void printStartMessage() {
        System.out.println("Please enter your player name: ");
        String playerName = ScannerUtil.getUserInput();
        // initierar spelkaraktären
        this.currentResident = new Resident(playerName, 100, 10);
        System.out.println("Welcome to the game " + currentResident.getRole() + "!");
        wakeUpMessage();
        gameInstructions();

    }

    // skrivs ut efter att spelaren har angett sitt namn
    private void wakeUpMessage() {
        System.out.println("a loud noise wakes you up!");
        System.out.println("you're on the couch in the living room " +
                " and hear a sound coming from the bedroom");
        System.out.println(" there is a burglar in there!");

    }

    // metod för när spelet startats
    public void start() {
        // så länge spelet inte är klart och resident är vid medvetande
        // så fortsätter spelet
        while (!gameFinished && currentResident.isConscious()) {
            System.out.println("where do you go next?");
            livingRoom();
            showRooms();
            String direction = ScannerUtil.getUserInput();
            switch (direction) {
                case "bedroom" -> goToBedroom();
                case "office" -> goToOffice();
                case "kitchen" -> goToKitchen();
                case "hallway" -> goToHallway();
                default -> System.out.println("Invalid input. Try again.");
            }
        }

    }
    // metod för att visa val av rum
    // för att minska mängden kod i start-metoden
    private void showRooms() {
        System.out.println("bedroom");
        System.out.println("office");
        System.out.println("kitchen");
        System.out.println("hallway");
    }
    // metod för att sätta currentLocation till living room
    // så att spelaren kan röra sig till andra rum
    private void livingRoom() {
        if (!currentLocation.equals(LIVING_ROOM)) {
            currentLocation = LIVING_ROOM;
        }
    }
    // om spelaren inte befinner sig i living room och försöker röra sig
    // till ett annat rum så anropas metoden
    private void wrongWay() {
        System.out.println("unable to go there now");
    }
    // metod för att röra sig till sovrummet
    private void goToBedroom() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("          ... you're quietly moving to the bedroom ...");
            bedroom.roomDescription();
        } else {
            wrongWay();
        }
    }
    // metod för att röra sig till köket
    private void goToKitchen() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("      -- moving to the kitchen --");
            kitchen.roomDescription();
        } else {
            wrongWay();
        }

    }
    // metod för att röra sig till kontoret
    private void goToOffice() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("           -- moving to the office --");
            office.roomDescription();
        } else {
            wrongWay();
        }

    }
    // metod för att röra sig till hallen
    private void goToHallway() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("               -- you run towards the hallway --");
            hallway.roomDescription();
        } else {
            wrongWay();
        }

    }
    // metod för spelinstruktioner
    // för att minska mängden kod i start()
    private void gameInstructions() {
        System.out.println("in order to defeat the game and get out you have to do the following:");
        System.out.println(" ");
        System.out.println("1. get a weapon from the kitchen");
        System.out.println(" ");
        System.out.println("2. get the burglar out from the bedroom to the hallway");
        System.out.println(" ");
        System.out.println("3. defeat the burglar");
        System.out.println(" ");
        System.out.println("4. call the police from the office");
    }

    // metod för att avsluta spelet
    // tar in en boolean som sen sätter gameFinished till true
    public static void setGameFinished(boolean done) {
        gameFinished = done;
    }

}
