import java.sql.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Scriptor {

    private final Random randomNumberGenerator;
    private final int MAX_NUM = 10;
    private final String[] cities = {"Montreal", "Laval", "Brossard", "Longueuil", "Quebec", "Toronto", "Gatineau", "Ottawa", "Toronto", "New York", "Edmonton", "Calgary", "Waterloo", "Vancouver", "London", "Saskatoon", "Windsor"};

    private String[] columnNames;
    private String tableName;
    private int[] columnDataTypes;
    private int numberOfScripts;

    public void setNumberOfScripts(int numberOfScripts) {
        this.numberOfScripts = numberOfScripts;
    }

    public void setColumnDataTypes(int[] columnDataTypes) {
        this.columnDataTypes = columnDataTypes;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public Scriptor() {
        this.randomNumberGenerator = new Random();
    }


    private String getRandomCity() {
        int index = randomNumberGenerator.nextInt(cities.length);
        return cities[index];
    }

    private String getRandomDate() {
        return new Date(ThreadLocalRandom.current().nextInt() * 1000L).toString();
    }

    private String getRandomWord() {
        String randomStrings;
        char[] word = new char[randomNumberGenerator.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + randomNumberGenerator.nextInt(26));
        }
        randomStrings = new String(word);
        return randomStrings;
    }

    private String getRandomName() {
        String randomStrings;
        char[] word = new char[randomNumberGenerator.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        word[0] = getRandomUpperLetter();
        for (int j = 1; j < word.length; j++) {
            word[j] = (char) ('a' + randomNumberGenerator.nextInt(26));
        }
        randomStrings = new String(word);
        return randomStrings;
    }

    private char getRandomUpperLetter() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWVXYZ";
        return upperCaseLetters.charAt(randomNumberGenerator.nextInt(upperCaseLetters.length()));
    }

    private String getRandomEmailAddress() {
        return getRandomWord() + "@" + getRandomWord() + ".com";
    }

    private String getRandomPostalCode() {
        return new String(getRandomUpperLetter() + String.valueOf(randomNumberGenerator.nextInt(10)) + getRandomUpperLetter() + String.valueOf(randomNumberGenerator.nextInt(10)) + getRandomUpperLetter() + String.valueOf(randomNumberGenerator.nextInt(10)));
    }

    private String getRandomWebSite() {
        return "www." + getRandomWord() + ".com";
    }

    private String getRandomTelephoneNumber() {
        StringBuilder phoneNumber = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            if (i == 3 || i == 7) {
                phoneNumber.append("-");
            } else {
                phoneNumber.append(String.valueOf((randomNumberGenerator.nextInt(10))));
            }
        }
        return phoneNumber.toString();
    }

    public String testAllGenerations() {
        return "Random Name: " + getRandomName() + "\n" + "Random word: " + getRandomWord() + "\nRandom Date: " + getRandomDate() + "\nRandom Email:" + getRandomEmailAddress() + "\nRandom Postal Code:" + getRandomPostalCode() + "\nTelephone number: " + getRandomTelephoneNumber() + "\nWebsite: " + getRandomWebSite() + "\nRandom city: " + getRandomCity();
    }


    private String getRandomISBN() {
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 17; i++) {
            if (i == 3 || i == 5 || i == 8 || i == 15) {
                isbn.append("-");
            } else {
                isbn.append(String.valueOf((randomNumberGenerator.nextInt(10))));
            }
        }

        return isbn.toString();
    }



    public void printScripts() {
        for (int scriptNumber = 1; scriptNumber <= numberOfScripts; scriptNumber++) {
           System.out.println(getInsertStatement());
        }
    }

    private String getInsertStatement() {
        return  "INSERT INTO " + tableName + " " + getColumnNames() + " VALUES " + getValues();
    }

    private String getColumnNames() {
        StringBuilder names = new StringBuilder("(");
        int theEnd = columnNames.length - 1;

        for (int i = 0; i < columnNames.length; i++) {
            names.append(columnNames[i]);
            if (i == theEnd) {
                names.append(")");
            } else {
                names.append(", ");
            }
        }

        return names.toString();
    }

    private String getValues(){
        StringBuilder values = new StringBuilder("(");
        int theEnd = columnDataTypes.length - 1;

        for (int i = 0; i < columnDataTypes.length; i++) {
            values.append(getRandomTypeById(columnDataTypes[i]));
            if (i == theEnd) {
                values.append(");");
            } else {
                values.append(", ");
            }
        }
        return values.toString();
    }

    private String getRandomTypeById(int id) {
        switch (id) {
            case 0:
                return getRandomWord();
            case 1:
                return getRandomName();
            case 2:
                return getRandomCity();
            case 3:
                return getRandomDate();
            case 4:
                return getRandomPostalCode();
            case 5:
                return getRandomTelephoneNumber();
            case 6:
                return getRandomEmailAddress();
            case 7:
                return getRandomWebSite();
            case 8:
                return getRandomISBN();
            default:
                return "null";
        }
    }
}
