package se.lisau.game.model;

public class Burglar extends Entity {
    public Burglar(String role, int health, int damage) {
        super(role, health, damage);
    }
    @Override
    public String getRole(){
        return super.getRole();
    }
    @Override
    public int getHealth(){
        return super.getHealth();
    }
    @Override
    public int getDamage(){
        return super.getDamage();
    }
    @Override
    public void takeHit(int damage){
        super.takeHit(damage);
    }
    @Override
    public void punch(Entity toPunch){
        toPunch.takeHit(this.getDamage());
    }
    @Override
    public boolean isConscious(){
        return super.isConscious();
    }
}
