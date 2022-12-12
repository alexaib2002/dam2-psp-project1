public class ConsonantReader extends ReaderProc {
    public static final String CHARSEQ = "bcdfghjklmnpqrstvwxyz";
    public static final String OUTFILE = "consonant.txt";

    public static void main(String[] args) {
        read(args, OUTFILE, CHARSEQ);
    }
}
