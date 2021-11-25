package finalProject;

public class Rocket implements Spaceship {
    
    public int cargoLimit;
    public int cargoCarried;
    public int cost;
    
    public Rocket(int cargoLimit, int cost) {
        this.cargoLimit = cargoLimit;
        this.cost = cost;
    }
    
    @Override
    public boolean launch() {
        return true;
    }
    
    @Override
    public boolean land() {
        return true;
    }
    
    @Override
    public boolean canCarry(Item item) {
        return cargoCarried + item.weight <= cargoLimit;
    }
    
    @Override
    public void carry(Item item) {
        cargoCarried += item.weight;
    }
}
