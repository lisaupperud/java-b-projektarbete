package se.lisau.game.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    Burglar b = new Burglar("Burglar", 10,5 );
    Resident r = new Resident("Resident", 10, 3);

    @Test
    void rPunchB() {
        int health = b.getHealth();
        int punch = 10;
        r.punch(b);
        assertEquals(health - punch, b.getHealth());

    }

    @Test
    void bPunchR() {
        int health = r.getHealth();
        int punch = 15;
        b.punch(r);
        assertEquals(health - punch, r.getHealth());
    }

    @Test
    void bTakeHit() {
        int health = b.getHealth(); // sparar b's health i en variabel
        int damage = 10; // sparar damage i variabel
        b.takeHit(damage); // kallar på metoden
        // förväntar mig 90 (health 100 - damage 10)
        assertEquals(health - damage, b.getHealth());

    }

    @Test
    void rTakeHit() {
        int health = r.getHealth();
        int damage = 15;
        r.takeHit(damage);
        assertEquals(health - damage, r.getHealth());
    }

    @Test
    void isConsciousTrue() {
        r.takeHit(b.getDamage());
        boolean con = r.isConscious();
        assertTrue(con);

    }
    @Test
    void isConsciousFalse() {
        Burglar b = new Burglar("Burglar", 10, 10);
        r.takeHit(b.getDamage());
        boolean con = r.isConscious();
        assertFalse(con);
    }

}