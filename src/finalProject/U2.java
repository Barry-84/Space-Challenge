package finalProject;

public class U2 extends Rocket {
    
    public U2() {
        // pass the known values to the superclass constructor
        super(11000, 120);
    }
    
    @Override
    public boolean launch() {
        /* returns false if an explosion occurs.
        *  Chance of launch explosion = 4% * (cargo carried / cargo limit) */
        return explosion(0.04);
    }
    
    @Override
    public boolean land() {
        /* returns false if an explosion occurs.
         * Chance of landing explosion = 8% * (cargo carried / cargo limit) */
        return explosion(0.08);
    }
    
    /* this method is not specified in the project brief, but I've taken the liberty
     * to add it to eliminate redundancy. In fact, since both U1 and U2 implement
     * identical methods explosion, the method could be moved to the superclass. */
    private boolean explosion(double factor) {
        /* returns false (explosion occurs) if a random number greater than 0 and
         * less than 1.0 is less than the probability of an explosion */
        return Math.random() >= factor * (this.cargoCarried / this.cargoLimit);
    }
}
