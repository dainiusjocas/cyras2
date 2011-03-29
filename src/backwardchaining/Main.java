package backwardchaining;

/**
 * Client class for using backward chaining
 * @author Dainius
 */
public class Main {

    /**
     * Main method which is needed to run a program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Program that demonstrates the way that backward " +
                "chaining works starts to work:\n");
        DataCollector dc = new DataCollector("src/backwardchaining/longer_9.txt");
        BackwardChaining bc = new BackwardChaining(dc.getGoal(), dc.getFacts(), dc.getImplications());
        System.out.println("List of implications that was read from the data file: ");
        bc.printImplications();
        System.out.println("Initial list of facts: " + bc.getFacts());
        System.out.println("The goal for the program: " + bc.getGoal() + "\n");
        if (bc.doBackwardChaining(dc.getGoal())) {
            System.out.println("\nThe goal is reached!\nThe answer is: " + bc.getProductionSystem());
        } else {
            System.out.println("\nGoal is not derivable in the production system");
        }
        System.out.println("\nEnd of the program!");
    }

}
