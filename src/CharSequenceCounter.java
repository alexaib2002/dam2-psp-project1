import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CharSequenceCounter {
    private String sequence;

    public CharSequenceCounter(String sequence) {
        this.sequence = sequence;
    }

    public int countOccurrences(String file) throws IOException {
        int counter = 0;
        for (String line : Files.readAllLines(Path.of(file))
        ) {
            for (char c : line.toCharArray()) {
                if (sequence.indexOf(c) != -1) {
                    counter++;
                }
            }
            for (char c : line.toCharArray()) {
                if (sequence.toUpperCase().indexOf(c) != -1) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
