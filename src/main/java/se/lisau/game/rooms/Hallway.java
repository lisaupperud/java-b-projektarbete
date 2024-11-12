package se.lisau.game.rooms;

import se.lisau.game.model.Burglar;
import se.lisau.game.model.Entity;
import se.lisau.game.model.Resident;
import se.lisau.game.util.ScannerUtil;

public class Hallway implements Room {
    // referens till Resident-objekten som är spelaren
    private final Resident resident;
    // referens till Burglar-objektet som är tjuven
    private final Burglar burglar;
    // kontrollerar om kampen i hallen fortfarande pågår
    private boolean running = true;

    // konstruktorn tar in Resident- och Burglar-objekt
    public Hallway(Resident resident, Burglar burglar) {
        // sätter referenserna till objekten
        this.resident = resident;
        this.burglar = burglar;
    }

    @Override
    public void roomDescription() {
        // om tjuven är vid medvetande så startar fighten
        if (burglar.isConscious()) {
            System.out.println(" -- in the hallway a fight breaks out between you and the burglar! --");
            System.out.println("       -- hit the burglar with the frying pan by pressing H --");

            // loop för att vänta på korrekt input
            while(running) {
                String H = ScannerUtil.getUserInput();
                if (H.equalsIgnoreCase("H")) {
                    // om spelaren har angivit 'H' så anropas roomTask()
                    roomTask();
                    running = false;
                } else {
                    System.out.println(" -- invalid input. try again! --");
                }
            }
        // om tjuven redan är medvetslös
        } else {
            System.out.println("-- the burglar is already unconscious! call the police before he wakes up! --");
        }


    }

    @Override
    public void roomTask() {
        // startar en omgång av fight
        // spelaren är attacker och utför första slaget
        fightOneRound(resident, burglar);

    }
    // metod för att genomföra attack mellan två Entities
    // använder polymorfism för att inte begränsa vem som är attacker eller defender
    public void executeAttack(Entity attacker, Entity defender) {
        // try-catch eftersom jag använder Thread.sleep()
        // hanterar ev. avbrott i väntetiden mellan attacker
        try {
            System.out.println(attacker.getRole() + " attacks " + defender.getRole());
            attacker.punch(defender); // attacker slår defender
            // skada appliceras genom takeHit() i punch()

            // simulerar tid mellan attacker
            Thread.sleep(1000);

            // om defender fortfarande är vid liv så skrivs dens hälsa ut
            if (defender.isConscious()) {
                System.out.println(defender.getRole() + "'s health: " + defender.getHealth());
            }
        } catch (InterruptedException e) {
            System.out.println("The fight was interrupted");
            Thread.currentThread().interrupt();
        }

    }
    // metod för en omgång av fighten
    private void fightOneRound(Entity attacker, Entity defender) {
        // loopen körs så länge spelare och tjuv är vid medvetande
        while (attacker.isConscious() && defender.isConscious()) {
            // attacker börjar att attackera
            executeAttack(attacker, defender);
            // kontrollerar om defender är medvetslös
            if (!defender.isConscious()) {
                System.out.println(defender.getRole() + " is unconscious!");
                System.out.println(" ");
                break;
            }
            // om defender är vid medvetande så attackerar denna
            executeAttack(defender, attacker);
            // kontrollerar om attacker (spelaren) är medvetslös
            if (!attacker.isConscious()) {
                // om detta stämmer så avslutas spelet eftersom spelaren inte kan fortsätta fighten
                System.out.println(attacker.getRole() + " is unconscious! Game over!");
                break;
            }
        }


    }
}
