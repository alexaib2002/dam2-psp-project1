import java.io.FileWriter;
import java.io.IOException;

public class ReaderProc {
    public static void read(String file, String out, String seq) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.write(Integer.toString(new CharSequenceCounter(seq).countOccurrences(file)));
        } catch (IOException e) {
            System.out.println(String.format("File writer error: %s", e.getMessage()));
        }
    }
}
