package se.lisau.game.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    private Burglar b;
    private Resident r;
    @BeforeEach
    void setUp() {
        b = new Burglar("Burglar", 10,5 );
        r = new Resident("Resident", 10, 3);
    }


    @Test
    void punch() {
        int health = b.getHealth();
        int punch = r.getDamage();
        r.punch(b);
        assertEquals(health - punch, b.getHealth());

    }


    @Test
    void takeHit() {
        int health = r.getHealth();
        int damage = b.getDamage();
        r.takeHit(damage);
        assertEquals(health - damage, r.getHealth());

    }

    @Test
    void isConsciousTrue() {
        // förväntar mig att det är true eftersom
        // b har 7 i health efter takeHit
        b.takeHit(r.getDamage());
        boolean conscious = b.isConscious();
        assertTrue(conscious);

    }
    @Test
    void isConsciousFalse() {
        b.addDamage(10);
        // returnera false eftersom b's damage är 10 nu
        // så r's hälsa blir 0 efter en hit
        r.takeHit(b.getDamage());
        boolean conscious = r.isConscious();
        assertFalse(conscious);
    }

}