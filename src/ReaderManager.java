import java.io.*;

public class ReaderManager {
    public static void main(String[] args) {
        try {
            executeChildProcess("VowelReader", args[0], VowelReader.OUTFILE);
            executeChildProcess("ConsonantReader", args[0], ConsonantReader.OUTFILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeChildProcess(String bin, String in, String out) throws IOException, InterruptedException {
        ProcessBuilder vocProcessBuilder = new ProcessBuilder(
                "java", "-jar", String.format("out/artifacts/%s/%s.jar", bin, bin), in);
        vocProcessBuilder.inheritIO();
        vocProcessBuilder.start().waitFor();
        File outFile = null;
        try {
            outFile = new File(out);
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(out))) {
                System.out.println(String.format("%s", bufferedReader.readLine()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (outFile != null) {
                outFile.delete(); // remove file after used
            }
        }
    }
}