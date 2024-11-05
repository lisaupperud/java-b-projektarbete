package se.lisau.game.rooms;

import se.lisau.game.model.Burglar;
import se.lisau.game.model.Entity;
import se.lisau.game.model.Resident;
import se.lisau.game.util.ScannerUtil;

public class Hallway implements Room {
    private final Resident resident;
    Entity burglar = new Burglar("burglar", 100, 15);

    public Hallway(Resident resident) {
        this.resident = resident;
    }

    @Override
    public void roomDescription() {
        System.out.println(" -- in the hallway a fight breaks out between you and the burglar! --");
        System.out.println("       -- hit the burglar with the frying pan by pressing H --");
        String H = ScannerUtil.getUserInput();
        if(H.equalsIgnoreCase("H")) {
            roomTask();
        }
    }

    @Override
    public void roomTask() {
        fightOneRound(resident, burglar);

    }
    public void executeAttack(Entity attacker, Entity defender) {

        try {
            System.out.println(attacker.getRole() + " attacks " + defender.getRole());
            attacker.punch(defender); // attacker slår defender
                                      // skada appliceras genom takeHit()

            Thread.sleep(1000);

            if (defender.isConscious()) {
                System.out.println(defender.getRole() + "'s health: " + defender.getHealth());
            }
        } catch (InterruptedException e) {
            System.out.println("The fight was interrupted");
            Thread.currentThread().interrupt();
        }

    }

    private void fightOneRound(Entity resident, Entity burglar) {
        // resident börjar alltid med att attackera
        while(resident.isConscious() && burglar.isConscious()) {
            executeAttack(resident, burglar);
            if (!burglar.isConscious()) {
                System.out.println(burglar.getRole() + " is unconscious!");
                System.out.println(" ");
                break;
            }
            // om burglar är vid liv så attackerar denna
            executeAttack(burglar, resident);
            if(!resident.isConscious()){
                System.out.println(resident.getRole() + " is unconscious! Game over!");
                break;
            }
        }


    }
}
