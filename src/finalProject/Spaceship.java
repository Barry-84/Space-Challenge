package finalProject;

public interface Spaceship {
    // indicates whether launch was successful
    boolean launch(); 
    // indicates whether landing was successful
    boolean land(); 
    /* takes an Item as an argument and returns true if
     * the rocket can carry such item or false if it will exceed
     * the weight limit. */
    boolean canCarry(Item item);
    /* takes an Item object and updates the current weight
     * of the rocket. */
    void carry(Item item);
}
