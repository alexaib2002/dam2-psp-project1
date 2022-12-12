public class VowelReader extends ReaderProc {
    public static final String CHARSEQ = "aeiou";
    public static final String OUTFILE = "vowel.txt";

    public static void main(String[] args) {
        read(args, OUTFILE, CHARSEQ);
    }
}
