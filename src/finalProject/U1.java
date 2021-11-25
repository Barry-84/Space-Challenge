package finalProject;

public class U1 extends Rocket {
    
    public U1() {
        // pass the known values to the superclass constructor
        super(8000, 100);
    }
    
    @Override
    public boolean launch() {
        /* returns false if an explosion occurs.
        *  Chance of launch explosion = 5% * (cargo carried / cargo limit) */
        return explosion(0.05);
    }
    
    @Override
    public boolean land() {
        /* returns false if an explosion occurs.
         * Chance of landing explosion = 1% * (cargo carried / cargo limit) */
        return explosion(0.01);
    }
    
    private boolean explosion(double factor) {
        return Math.random() >= factor * (this.cargoCarried / this.cargoLimit);
    }
}
