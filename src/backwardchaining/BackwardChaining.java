package backwardchaining;

import java.util.ArrayList;

/**
 * This class is implementation of the Backward-Chaining
 * @author Dainius Jocas
 */
public class BackwardChaining {
    private String facts;
    private ArrayList<Implication> implications;
    private String goal;
    private ArrayList<String> productions = new ArrayList();
    private String path = "";

    /**
     * Constructor
     * @param goal of the chaining
     * @param facts facts for the chaining
     * @param implications which will be used for derivation of the new facts
     */
    public BackwardChaining(String goal, String facts, ArrayList<String> implications) {
        this.goal = goal;
        this.facts = facts;
        this.implications = new ArrayList();
        for (String implication : implications) {
            this.implications.add(new Implication(implication));
        }
    }

    /**
     * Method that checks if goal is derivable in production system.
     * @param goal
     * @return return true if goal is derivable, otherwise - false
     */
    public boolean doBackwardChaining(String goal) {
        System.out.println("Searching for: " + goal);
        if (isGoalInFacts(goal)) {
            System.out.println(goal + " is in facts.");
            //removeLastStep();
            return true;
        }
        if (!isGoalSomewhereAsConsequent(goal)) {
            System.out.println("DEADEND!");
            removeLastStep();
            return false;
        }
        if (this.path.contains(goal)) {
            System.out.println("LOOP!!!");
            //removeLastStep();
            return false;
        }
        boolean result = true;
        for (Implication implication : this.implications) {
            if (implication.getConsequent().contains(goal)) {
                addNewGoal(goal);
                for (String newGoal : implication.getAntecedentAsList()) {
                    System.out.println(this.path);
                    result = result && this.doBackwardChaining(newGoal);
                }
                if (result) {
                    removeLastStep();
                    System.out.println(goal + " found!");
                    this.addNewFact(goal);
                    this.productions.add(implication.getDescriptor());
                    //showState();
                    return true;
                } else {
                    //removeLastStep();
                    result = true;
                }
            }
        }
        return false;
    }

    void addNewGoal(String newGoal) {
        if (!this.path.contains(newGoal)) {
            this.path = this.path + newGoal;
        }
    }

    /**
     * Method that removes the goal that was searched last.
     */
    void removeLastStep() {
        if (this.path.length() > 0) {
            this.path = this.path.substring(0, this.path.length() - 1);
        }
    }

    /**
     * Method that prints current state of the program to terminal
     */
    void showState() {
        System.out.println("Current list of facts: " + getFacts());
        System.out.println("Current production system: " + getProductionSystem());
    }

    /**
     * Method that prints implications with indentation
     */
    void printImplications() {
        for (Implication implication : this.implications) {
            System.out.println("  " + implication);
        }
    }

    /**
     * Method that gets String representation of facts
     * @return
     */
    String getFacts() {
        String representationOfFacts = "{";
        for (int i = 0; i < this.facts.length(); i++) {
            representationOfFacts = representationOfFacts + this.facts.charAt(i) + ", ";
        }
        return representationOfFacts.substring(0, representationOfFacts.length() - 2) + "}";
    }

    /**
     * Getter of goal
     * @return
     */
    String getGoal() {
        return this.goal;
    }

    /**
     * Method that gets String representation of production system
     * @return
     */
    String getProductionSystem() {
        String productionSystem = "{";
        if (this.productions.size() > 0) {
            for (String production : this.productions) {
                productionSystem = productionSystem + production.substring(0, 2) + ", ";
            }
            return productionSystem.substring(0, productionSystem.length() - 2) + "}";
        }
        return "{NO PRODUCTIONS USED. Goal was in facts.}";
    }

    /**
     * Method that checks if the goal is in facts
     * @param goal that is being searched
     * @return true if goal is in facts, otherwise - false
     */
    public boolean isGoalInFacts(String goal) {
        if (this.facts.contains(goal)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that ads a fact to the list of facts
     * @param fact
     */
    private void addNewFact(String fact) {
        this.facts = this.facts + fact;
    }

    /**
     * Method that checks if there is a implications which has such a consequent
     * @param consequent
     * @return true if there is such an implication with a specific consequent,
     * otherwise - false
     */
    boolean isGoalSomewhereAsConsequent(String consequent) {
        for (Implication implication : this.implications) {
            if (implication.getConsequent().contains(consequent)) {
                return true;
            }
        }
        return false;
    }

}
