import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BackspacesInString {
    public static String cleanString(String s) {
        CapturingPrinter cp = new CapturingPrinter();
        PrintStream ps = cp.getPrintStream();
        ps.print(s.replaceAll("#", "\b"));
        return cp.getPrintedText();
    }

    public static void main(String[] args) {
        System.out.println(BackspacesInString.cleanString("abc##d#"));
    }
}

class CapturingPrinter {
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final PrintStream printer = new PrintStream(buffer);

    public PrintStream getPrintStream() {
        return printer;
    }

    public String getPrintedText() {
        printer.flush();
        return buffer.toString();
    }
}
