package backwardchaining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class is for collecting data from plain text files. This class is
 * important because with a usage of this class we separate data collection
 * from other parts of the program.
 * @author Dainius Jocas
 */
public class DataCollector {
    public static int EMPTY_LINES_TILL_IMPLICATIONS = 1;
    public static int EMPTY_LINES_TILL_FACTS = 2;
    public static int EMPTY_LINES_TILL_GOAL = 3;
    /* In this variable data input file descriptor is loaded */
    private BufferedReader bufferedReader;

    /**
     * Constructor which loads file with input data.
     * @param fileURI
     */
    public DataCollector(String fileURI) {
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(fileURI)));
            bufferedReader.mark(1);
        } catch (Exception e) {
            System.out.println("Some general error occurred.");
        }
    }

    /**
     * Method which collects  goal.
     * @return string representation of goal
     */
    public String getGoal() {
        goToTheFirstLine();
        String line = "";
        int counterOfEmptyLines = 0;
        while (counterOfEmptyLines < EMPTY_LINES_TILL_GOAL) {
            line = line = getLine();
            if ((line.contains("#")) || (line.isEmpty())) {
                counterOfEmptyLines++;
            }
        }
        return line = getLine();
    }

    /**
     * Method which collects facts
     * @return string representation of facts
     */
    public String getFacts() {
        goToTheFirstLine();
        String line = "";
        int counterOfEmptyLines = 0;
        while (counterOfEmptyLines < EMPTY_LINES_TILL_FACTS) {
            line = line = getLine();
            if ((line.contains("#")) || (line.isEmpty())) {
                counterOfEmptyLines++;
            }
        }
        return line = getLine();
    }

    /**
     * Method that gets list of implications from file.
     * @return
     */
    public ArrayList<String> getImplications() {
        goToTheFirstLine();
        ArrayList<String> implications = new ArrayList();
        String line = "";
        int counterOfEmptyLines = 0;
        while (counterOfEmptyLines < EMPTY_LINES_TILL_IMPLICATIONS) {
            line = getLine();
            if ((line.contains("#")) || (line.isEmpty())) {
                counterOfEmptyLines++;
            }
        }
        do {
            line = getLine();
            implications.add(line);
        } while (!line.isEmpty());
        implications.remove(implications.size() - 1);
        return implications;
    }

    /**
     * This methods reads line and tries to solve problems faced;
     * @return String
     */
    private String getLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return null;
    }

    /**
     * Method to reset file cursor;
     */
    private void goToTheFirstLine() {
        try {
            this.bufferedReader.reset();
        } catch (Exception e) {
            System.out.println("Error while reseting file cursor");
        }
    }
}
