import java.io.*;

public class ReaderManager {
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                argumentExecution(args);
            } else {
                shellExecution();
            }
        } catch (IOException | InterruptedException | IndexOutOfBoundsException e ) {
            System.err.printf("Error at %s", e.getMessage());
        }
    }

    private static void argumentExecution(String[] args) throws IOException, InterruptedException {
        executeChildProcess("VowelReader", args[0], VowelReader.OUTFILE);
        executeChildProcess("ConsonantReader", args[0], ConsonantReader.OUTFILE);
    }

    private static void shellExecution() throws IOException {
        System.out.println("Welcome to the ReaderManager shell\nYou can use the following commands:");
        System.out.println("\tread <file> - reads the file and counts the occurrences of the sequence");
        System.out.println("\texit - exits the shell");
        while (true) {
            System.out.print("ReaderManager> ");
            String[] command = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
            if (command[0].equals("read")) {
                try {
                    executeChildProcess("VowelReader", command[1], VowelReader.OUTFILE);
                    executeChildProcess("ConsonantReader", command[1], ConsonantReader.OUTFILE);
                } catch (IOException | InterruptedException e) {
                    System.out.printf("Error while reading the file: %s\n", e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid syntax. Use read <file>");
                }
            } else if (command[0].equals("exit")) {
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    private static void executeChildProcess(String bin, String in, String out) throws IOException, InterruptedException {
        // first, ensure that the given binary exists
        if (!new File(String.format("out/artifacts/%s/%s.jar", bin, bin)).exists()) {
            throw new IOException(String.format("Child process JAR %s not found", bin));
        }
        if (!new File(in).exists()) {
            throw new IOException(String.format("Input file %s not found", in));
        }
        ProcessBuilder vocProcessBuilder = new ProcessBuilder(
                "java", "-jar", String.format("out/artifacts/%s/%s.jar", bin, bin), in);
        vocProcessBuilder.inheritIO();
        vocProcessBuilder.start().waitFor();
        File outFile = new File(out);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(out))) {
            System.out.printf("%s: %s\n", bin, bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            throw new IOException(String.format("Output file %s not found", out));
        } finally {
            if (outFile != null)
                outFile.delete(); // remove file after used
        }
    }
}