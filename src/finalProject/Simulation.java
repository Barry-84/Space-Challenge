package finalProject;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Simulation {

    public ArrayList<Item> loadItems(String fileName) {
        /* this method reads the text file line by line and creates
         * an Item object for each and then adds it to an ArrayList
         * of Items which it then returns. */

        File file = new File(fileName);
        Scanner scanner;
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String itemAsString = scanner.nextLine();
                String[] parts = itemAsString.split("=");
                Item item = new Item(parts[0], Integer.parseInt(parts[1]));
                items.add(item);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return items;
    }
    
    public ArrayList<U1> loadU1(ArrayList<Item> items) {
        /* This method takes the ArrayList of Items
         * returned from loadItems and starts creating U1 rockets.
         * It first tries to fill up 1 rocket with as many items as
         * possible before creating a new rocket object and filling
         * that one until all items are loaded. The method then
         * returns the ArrayList of those U1 rockets that are fully
         * loaded.
         * 
         * This method modifies its formal parameter items.
         * This means that the Phase-1 and Phase-2 items must
         * be loaded again for before loading the fleet of U2 rockets
         * after loading the fleet of U1 rockets. Maybe there's 
         * a better way of doing this? */
        
        ArrayList<U1> loadedU1Rockets = new ArrayList<U1>();
        
        // keep going until all items have been loaded.
        while (!items.isEmpty()) {
            // create new U1 rocket
            U1 u1Rocket = new U1();

            Iterator<Item> itr = items.iterator();
            
            /* If an item is too heavy then move on to the next item.
             * If the end of the ArrayList of items has been reached,
             * then exit to outer loop and create a new rocket (assuming
             * there are more items to load). */
            while (itr.hasNext()) {
                Item item = itr.next();
                // if the rocket can carry the weight of the item...
                if (u1Rocket.canCarry(item)) {
                    // ...add the item to the rocket and...
                    u1Rocket.carry(item);
                    // ...remove last element returned by this iterator. Once the item has been loaded
                    // it is removed from the Arraylist of items. 
                    itr.remove();
                }
            }
            loadedU1Rockets.add(u1Rocket);
        }
        
        return loadedU1Rockets;
    }
    
    public ArrayList<U2> loadU2(ArrayList<Item> items) {
        /* This method takes the ArrayList of Items
         * returned from loadItems and starts creating U2 rockets.
         * It first tries to fill up 1 rocket with as many items as
         * possible before creating a new rocket object and filling
         * that one until all items are loaded. The method then
         * returns the ArrayList of those U2 rockets that are fully
         * loaded. 
         * 
         * This method is almost identical to loadU1. The project 
         * brief is clear in its requirement to create two methods,
         * but would it not be better practice to create one method
         * for loading generic rocket fleets in the superclass Rocket? */
         
        ArrayList<U2> loadedU2Rockets = new ArrayList<U2>();
        
        /* If an item is too heavy then move on to the next item.
         * If the end of the ArrayList of items has been reached,
         * then exit to outer loop and create a new rocket (assuming
         * there are more items to load). */
        while (!items.isEmpty()) {
            // create new U2 rocket
            U2 u2Rocket = new U2();

            Iterator<Item> itr = items.iterator();
            
            while (itr.hasNext()) {
                Item item = itr.next();
                // if the rocket can carry the weight of the item...
                if (u2Rocket.canCarry(item)) {
                    // ...add the item to the rocket and...
                    u2Rocket.carry(item);
                    // ...remove last element returned by this iterator. Once the item has been loaded
                    // it is removed from the Arraylist of items
                    itr.remove();
                }
            }
            // add the loaded rocket to the fleet
            loadedU2Rockets.add(u2Rocket);
        }
        
        return loadedU2Rockets;
    }
    
    public int runSimulation(ArrayList<? extends Rocket> rocketList) {
        
        // declare and initialise budget in millions to zero
        int budget = 0;
        
        // consider each rocket
        for (Rocket rocket : rocketList) {
            // add the cost of the rocket before launch
            budget += rocket.cost;
            /* the || operator short circuits so that the second expression
                is only evaluated when necessary */
            while (!rocket.launch() || !rocket.land()) {
                /* if there's an explosion on launch or on landing,
                    add the cost and try again */
                budget += rocket.cost;
            }
        }
        return budget;
    }
    
    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        // Load items for Phase-1 and Phase-2
        ArrayList<Item> itemsPhase1 = simulation.loadItems("phase-1.txt");
        ArrayList<Item> itemsPhase2 = simulation.loadItems("phase-2.txt");
        
        // load fleet of U1 rockets for Phase-1
        ArrayList<U1> u1RocketFleetPhase1 = simulation.loadU1(itemsPhase1);
        
        // load fleet of U1 rockets for Phase-2
        ArrayList<U1> u1RocketFleetPhase2 = simulation.loadU1(itemsPhase2);
        
        // display the total budget for fleet of U1 rockets
        System.out.println("Total budget using U1 rockets is " +
        (simulation.runSimulation(u1RocketFleetPhase1) +
         simulation.runSimulation(u1RocketFleetPhase2)) + " million USD.");
        
        /* load the items again, because they were deleted from the ArrayList
         * during loading of the fleet of U1 rockets. */
        itemsPhase1 = simulation.loadItems("phase-1.txt");
        itemsPhase2 = simulation.loadItems("phase-2.txt");
        
        // load fleet of U2 rockets for Phase-1
        ArrayList<U2> u2RocketFleetPhase1 = simulation.loadU2(itemsPhase1);
        
        // load fleet of U2 rockets for Phase-2
        ArrayList<U2> u2RocketFleetPhase2 = simulation.loadU2(itemsPhase2);
        
        // display the total budget for fleet of U2 rockets
        System.out.println("Total budget using U2 rockets is " +
        (simulation.runSimulation(u2RocketFleetPhase1) +
         simulation.runSimulation(u2RocketFleetPhase2)) + " million USD.");
    }
}
