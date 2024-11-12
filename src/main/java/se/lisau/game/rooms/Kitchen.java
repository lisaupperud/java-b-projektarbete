package se.lisau.game.rooms;

import se.lisau.game.model.Resident;
import se.lisau.game.util.ScannerUtil;


public class Kitchen implements Room {
    // variabel som håler reda på om stekpannan är funnen
    // behövs för att kunna kontrollera olika utfall ex.
    // spelaren kommer in i köket igen
    private boolean fryingPanFound = false;
    // referens till Resident-objektet
    // behövs för att kunna ändra spelarens skada
    private final Resident resident;

    // konstruktor som tar in ett Resident-objekt
    // i detta fall den aktuella spelaren
    public Kitchen(Resident resident) {
        // sätter objektet från parametern till detta Resident-objekt
        this.resident = resident;
    }

    @Override
    public void roomDescription() {
            // beskriver rummet för spelaren
            // om stekpannan inte är funnen
            if (!fryingPanFound) {
                System.out.println("      -- you're in the kitchen --");
                System.out.println("-- you see the frying pan on the stove! --");
                System.out.println("         -- pick it up? y/n --");
                String answer = ScannerUtil.getUserInput();
                // om svaret är 'y' så anropas roomTask()
                if (answer.equalsIgnoreCase("y")) {
                    roomTask();
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println("okay, going back to the living room");
                } else {
                    System.out.println("invalid answer");
                }
            // om stekpannan är funnen visas detta
            } else System.out.println("-- the stove is empty, the pan has already been taken --");

    }

    @Override
    // metod för rummets uppgift
    // att plocka upp stekpannan
    public void roomTask() {
        if (!fryingPanFound) {
            // anropar metod som ökar skadan
            // och sätta stekpannan till funnen
            pickUpFryingPan();
            // berättar för spelaren att dens skada har ökat
            System.out.println("you now have the frying pan!");
            System.out.println("your damage has increased!");
            System.out.println(" ");
        } else {
            // om spelaren redan har hittat stekpannan
            System.out.println("you already have the frying pan!");
        }
    }
    // metod för att öka spelarens skada
    public void pickUpFryingPan() {
        if (!fryingPanFound) {
            resident.addDamage(resident.getDamage() + 25);
            // sätter variabeln fryingPanFound till true
            fryingPanFound = true;
        }


    }
}
