import java.io.FileWriter;
import java.io.IOException;

public class ReaderProc {

    public static void read(String file, String out, String seq) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.write(Integer.toString(new CharSequenceCounter(seq).countOcurrences(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
