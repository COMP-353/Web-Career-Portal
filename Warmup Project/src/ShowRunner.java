import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShowRunner {
    private Scriptor scriptGenerator;
    private BufferedReader consoleInput;
    private String input = "";
    private int numOfColumns = 0;

    public ShowRunner() {
        this.scriptGenerator = new Scriptor();
        this.consoleInput = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startTheShow() throws IOException {
        System.out.println("\nHOME");
        System.out.println("Type '-exit-' to stop the program");
        System.out.println("Type '-back-' to go back to the previous step");

        do {
            System.out.println("Start? (y/n)");
            input = consoleInput.readLine();

            if (input.equals("y") || input.equals("Y")) {
                getNameOfTable();
            }

        } while (!input.equals("-exit-"));
    }

    private void getNameOfTable() throws IOException {
        do {
            System.out.println("\nEnter the name of the table:");
            input = consoleInput.readLine();

            if (input.contains("-back-")) startTheShow();
        } while (input.equals(""));

        scriptGenerator.setTableName(input);
        getColumnNames();

    }

    private void getColumnNames() throws IOException {
        System.out.println("\nEnter the names of columns with no space separated by , ");
        input = consoleInput.readLine();

        if (input.contains("-back-")) getNameOfTable();

        String[] columnNames = input.split(",");
        scriptGenerator.setColumnNames(columnNames);
        this.numOfColumns = columnNames.length;
        getColumnDataType();
    }

    private void getColumnDataType() throws IOException {
        String[] dataTypes;
        boolean hasToRepeat = false;
        do {
            hasToRepeat = false;
            System.out.println("\nChoose the type of data for each column by assigning a number with no space separated by ,");
            printDataTypeOption();
            input = consoleInput.readLine();

            if (input.contains("-back-")) getColumnNames();

            dataTypes = input.split(",");

            if (dataTypes.length != numOfColumns) {
                System.out.println("The number of data types does not match the number of columns");
                hasToRepeat = true;
            }

            if (!stringArrayIsNumeric(dataTypes)) {
                System.out.println("Not a recognized number");
                hasToRepeat = true;
            }

        } while (hasToRepeat);

        scriptGenerator.setColumnDataTypes(convertStringToIntArray(dataTypes));
        getNumberOfScriptsToBeGenerated();
    }

    private int [] convertStringToIntArray(String[] input){
       int [] values = new int[input.length];
       for (int i = 0; i < input.length; i++){
           values[i] = Integer.parseInt(input[i]);
       }
       return values;
    }

    private void printDataTypeOption() {
        System.out.println("\nThese are your options:");
        System.out.println("0 - random word");
        System.out.println("1 - random name");
        System.out.println("2 - random city");
        System.out.println("3 - random date");
        System.out.println("4 - random postal code");
        System.out.println("5 - random telephone number");
        System.out.println("6 - random email address");
        System.out.println("7 - random website");
        System.out.println("8 - random isbn");
        System.out.println("9 - null\n");
    }

    private void getNumberOfScriptsToBeGenerated() throws IOException {
        boolean hasToRepeat;
        int number = 0;
        do {
            hasToRepeat = false;
            System.out.println("\nEnter the number of Scripts");
            input = consoleInput.readLine();
            if (input.contains("-back-")) getColumnDataType();
            if (!stringIsNumber(input)){
                System.out.println("Not a recognized Number");
                hasToRepeat = true;
            } else {
                number = Integer.parseInt(input);
                if (number <= 0){
                    System.out.println("Number cannot be smaller than one");
                    hasToRepeat = true;
                }
            }
        } while (hasToRepeat);
        scriptGenerator.setNumberOfScripts(number);
        printTheStatements();
    }

    private boolean stringIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean stringArrayIsNumeric(String[] input) {
        for (String s : input) {
            if (!stringIsNumber(s)) return false;
        }
        return true;
    }

    private void printTheStatements() throws IOException {
        System.out.println("\nYour statements:\n");
        scriptGenerator.printScripts();
        startTheShow();
    }
}
