package se.lisau.game;

import se.lisau.game.model.Burglar;
import se.lisau.game.util.ScannerUtil;
import se.lisau.game.model.Resident;
import se.lisau.game.rooms.Bedroom;
import se.lisau.game.rooms.Hallway;
import se.lisau.game.rooms.Kitchen;
import se.lisau.game.rooms.Office;

public class Game {
    private final Kitchen kitchen;
    private final Office office;
    private final Bedroom bedroom;
    private final Hallway hallway;
    private final String LIVING_ROOM = "living room";
    private final String START = "start";
    private String currentLocation = START;
    private Resident currentResident;
    private boolean gameFinished = false; // variabel för att hålla reda på om spelet är vunnet

    public Game() {
        // instantiate Burglar här för att använda i de andra klasserna
        Burglar burglar = new Burglar("burglar", 100, 15);
        printStartMessage(); // sätter metoden där Resident instansieras här för att kunna ->
        kitchen = new Kitchen(currentResident); // tilldela currentResident till Kitchens konstruktor
        office = new Office(this, burglar);
        bedroom = new Bedroom();
        hallway = new Hallway(currentResident, burglar);

    }

    private void printStartMessage() {
        System.out.println("Please enter your player name: ");
        String playerName = ScannerUtil.getUserInput();
        this.currentResident = new Resident(playerName, 100, 10);
        System.out.println("Welcome to the game " + currentResident.getRole() + "!");
        wakeUpMessage();
        gameInstructions();

    }

    private void wakeUpMessage() {
        System.out.println("a loud noise wakes you up!");
        System.out.println("you're on the couch in the living room " +
                " and hear a sound coming from the Bedroom");
        System.out.println(" there is a burglar in there!");

    }

    public void start() {

        while (!gameFinished && currentResident.isConscious()) {
            System.out.println("where do you go next?");
            livingRoom();
            System.out.println("bedroom");
            System.out.println("office");
            System.out.println("kitchen");
            System.out.println("hallway");
            String direction = ScannerUtil.getUserInput();
            switch (direction) {
                case "bedroom" -> Bedroom();
                case "office" -> Office();
                case "kitchen" -> Kitchen();
                case "hallway" -> Hallway();
                default -> System.out.println("Invalid input. Try again.");
            }
        }


    }

    private void livingRoom() {
        if (!currentLocation.equals(LIVING_ROOM)) {
            currentLocation = LIVING_ROOM;
        }
    }

    private void wrongWay() {
        System.out.println("unable to go there now");
    }

    private void Bedroom() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("          ... you're quietly moving to the Bedroom ...");
            bedroom.roomDescription();
        } else {
            wrongWay();
        }
    }

    private void Kitchen() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("      -- moving to the kitchen --");
            kitchen.roomDescription();
        } else {
            wrongWay();
        }

    }

    private void Office() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("           -- moving to the office --");
            office.roomDescription();
        } else {
            wrongWay();
        }

    }

    private void Hallway() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("               -- you run towards the hallway --");
            hallway.roomDescription();
        } else {
            wrongWay();
        }

    }

    private void gameInstructions() {
        System.out.println("in order to defeat the game and get out you have to do the following:");
        System.out.println(" ");
        System.out.println("1. get a weapon from the kitchen");
        System.out.println(" ");
        System.out.println("2. get the burglar out from the Bedroom to the hallway");
        System.out.println(" ");
        System.out.println("3. defeat the burglar");
        System.out.println(" ");
        System.out.println("4. call the police from the office");
    }

    // metod för att avsluta spelet
    public void isGameFinished(boolean done) {
        this.gameFinished = done;
    }

}
