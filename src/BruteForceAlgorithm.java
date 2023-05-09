import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class BruteForceAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wählen Sie ein Passwort:");
        String passwort = scanner.next();
        System.out.println("Enthält das Passwort auch Zahlen und Sonderzeichen? 0 = Nein, 1 = Ja" );
        int symbols = scanner.nextInt();
        System.out.println("Wollen Sie das Passwort zuerst hashen? 0 = Nein, 1 = Ja");
        int hashOption = scanner.nextInt();

        Instant start = Instant.now();

        ArrayList<String> results = LetterCombination.testAllCombinations(passwort.length(), passwort, hashOption==1, symbols==1);

        System.out.println("Geknacktes Passwort: " + results.get(results.size()-1));
        System.out.println("Anzahl probierte Wörter: " + results.size());
        Instant end = Instant.now();
        System.out.println("Das Passwort wurde geknackt in: " + formatDuration(Duration.between(start, end)));



    }


    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }
}
