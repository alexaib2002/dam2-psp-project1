import java.io.FileWriter;
import java.io.IOException;

public class ReaderProc {
    public static void read(String[] args, String out, String seq) {
        try (FileWriter fileWriter = new FileWriter(out)) {
            String file = args[0];
            fileWriter.write(Integer.toString(new CharSequenceCounter(seq).countOccurrences(file)));
        } catch (IOException e) {
            System.out.printf("File writer error: %s%n", e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No file specified on arguments");
        }
    }
}
