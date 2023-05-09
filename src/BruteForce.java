import java.security.NoSuchAlgorithmException;
import java.util.*;

public class BruteForce {

    static String word;
    static boolean hashed;
    public static ArrayList<String> prepareAllCombinations(int maxLength, String word, boolean hashed, boolean containsSymbols) {

        int lowest, highest;

        if (containsSymbols){
            lowest = 33;
            highest = 127;
        }
        else {
            lowest = 65;
            highest = 123;
        }

        BruteForce.word = word;
        BruteForce.hashed = hashed;

        String[] letters;

        if (containsSymbols){
            letters = new String[highest-lowest];
            for (int i = lowest; i < highest; i++){
                letters[i-lowest] = ((char) i) + "";
            }
        }
        else {
            letters = new String[highest-lowest-6];
            int counter = 0;
            for (int i = lowest; i < 91; i++){
                letters[counter] = ((char) i) + "";
                counter++;
            }
            for (int i = 97; i < highest; i++){
                letters[counter] = ((char) i) + "";
                counter++;
            }

        }


        int wordLength = maxLength;

        ArrayList<String> combinations = getAllCombinationsUntilPasswordFound(letters, wordLength);
        ArrayList<String> result = new ArrayList<>();

        //System.out.println("Alle Kombinationen von Länge " + wordLength + ":");
        for (String combination : combinations) {
            result.add(combination);
        }
        return result;
    }

    public static ArrayList<String> getAllCombinationsUntilPasswordFound(String[] letters, int wordLength) {
        ArrayList<String> combinations = new ArrayList<>();
        int counter = 0;

        // Basisfall: Wenn Wortlänge 0 ist, ist keine weitere Kombination möglich
        if (wordLength == 0) {
            combinations.add("");
            return combinations;
        }

        // Rekursiv alle Kombinationen generieren
        for (String letter : letters) {
            List<String> shorterCombinations = getAllCombinationsUntilPasswordFound(letters, wordLength - 1);
            for (String shorterCombination : shorterCombinations) {
                String stringToTest = letter + shorterCombination;
                combinations.add(stringToTest);
                if (hashed){
                    try {
                        String hashToTest = Hash.hash(stringToTest);
                        System.out.println(stringToTest + " --> Hashwert: " + hashToTest);
                        if (hashToTest.equals(Hash.hash(word))){
                            System.out.println("Ursprünglicher Hashwert: " + Hash.hash(word));
                            return combinations;
                        }
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }

                }
                else {
                    System.out.println(stringToTest);
                    if (stringToTest.equals(word)){
                        return combinations;
                    }
                }

                counter++;
            }
        }

        return combinations;
    }
}