package se.lisau.game.rooms;

import se.lisau.game.model.Resident;
import se.lisau.game.util.ScannerUtil;


public class Kitchen implements Room {
    private boolean fryingPanFound = false;
    private final Resident resident;

    public Kitchen(Resident resident) {
        this.resident = resident;
    }

    @Override
    public void roomDescription() {
            if (!fryingPanFound) {
                System.out.println("      -- you're in the kitchen --");
                System.out.println("-- you see the frying pan on the stove! --");
                System.out.println("         -- pick it up? y/n --");
                String answer = ScannerUtil.getUserInput();
                if (answer.equalsIgnoreCase("y")) {
                    roomTask();
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println("then gtfo of the house");
                } else {
                    System.out.println("invalid answer");
                }
            } else System.out.println("-- the stove is empty, the pan has already been taken --");

    }

    @Override
    public void roomTask() {
        if (!fryingPanFound) {
            System.out.println("you now have the frying pan!");
            System.out.println("your damage has increased!");
            System.out.println(" ");
            pickUpFryingPan();
        } else {
            System.out.println("you already have the frying pan!");
        }
    }

    public void pickUpFryingPan() {
        if (!fryingPanFound) {
            resident.addDamage(resident.getDamage() + 25);
            fryingPanFound = true;
        }


    }
}
