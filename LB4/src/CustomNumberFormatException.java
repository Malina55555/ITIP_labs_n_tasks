import java.io.FileWriter;
import java.io.IOException;

public class CustomNumberFormatException extends NumberFormatException {
    public CustomNumberFormatException() {super();}
    public CustomNumberFormatException(String message) {super(message);}
    public static void logInFile(NumberFormatException e) {
        String message = e.getMessage();
        try {
            FileWriter fw = new FileWriter("customlog.txt", true);
            fw.write("CustomNumberFormatException: Integer.parseInt's argument \" " + message + " \" contains non-parsing elements.\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error for write logs in file customlog.txt");
        }
    }
}
