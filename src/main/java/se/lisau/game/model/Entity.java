package se.lisau.game.model;

public abstract class Entity {
    private final String role;
    private int health;
    private int damage;

    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void addDamage(int damage) {
        this.damage = damage;
    }

    public void takeHit(int damage) {
        this.health -= damage;
    }

    public void punch(Entity toPunch) {
        toPunch.takeHit(damage);
    }

    public boolean isConscious() {
        return health > 0;
    }

}
