package backwardchaining;

import java.util.ArrayList;

/**
 * This class is for implementing abstraction of implication.
 * @author Dainius
 */
public class Implication {
    private String descriptor;
    private String antecedent;
    private String consequent;
    private static int nr = 0;

    /**
     * This constructor takes as a parameter string and divides it into two
     * parts consequent which is the last character, and antecedent which is
     * rest of the string
     * @param implication must have more than two letters
     */
    public Implication(String implication) {
        this.consequent = Character.
                toString(implication.charAt(implication.length() - 1));
        this.antecedent = implication.substring(0, implication.length() - 1);
        this.descriptor = "R" + ++nr + ":";
    }

    /**
     * Getters
     * @return string representation of parts of implication
     */
    public String getDescriptor() {
        return this.descriptor;
    }
    public String getAntecedent() {
        return this.antecedent;
    }
    public String getConsequent() {
        return this.consequent;
    }

    /**
     * Makes a String representation of the implication
     * @return string. example: "R1: A -> B"
     */
    @Override
    public String toString() {
        return this.descriptor + " " + getAntecedentAsString() + " -> " + this.consequent;
    }

    String getAntecedentAsString() {
        String result = "";
        for (int i = 0; i < this.antecedent.length(); i++) {
            result = result + this.antecedent.charAt(i) + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    /**
     * This method collects and returns all the members of antecedent as a
     * ArrayList
     * @return ArrayList<String> with members of antecedent
     */
    public ArrayList<String> getAntecedentAsList() {
        ArrayList listOfMembersOfAntecedent = new ArrayList();
        for (int i = 0; i < this.antecedent.length(); i++) {
            listOfMembersOfAntecedent.add(Character.toString(this.antecedent.charAt(i)));
        }
        return listOfMembersOfAntecedent;
    }

}
