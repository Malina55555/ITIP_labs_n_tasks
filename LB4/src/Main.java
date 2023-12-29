import java.io.*;

public class Main {
    public static void main(String[] args) {
        //printArrayAverrage(new String[]{"1","2","3","5","7"});
        //printArrayAverrage(new String[]{"1","2","3",});
        //printArrayAverrage(new String[]{});
        //printArrayAverrage(new String[]{"q","2","3","5","7"});
        //printFileReadWrite("inputFile.txt", "outfile.txt");
        printFileReadWrite("inputFile.txt", "secretfile.txt"); // "secretfile.txt" - доступен только для чтения
        //printFileReadWrite("iamnotexist.txt", "outfile.txt");
        //printParseInt("1234integer");
        //printParseInt("1234");
    }
    public static void printArrayAverrage(String[] stringArrayIn) { //1ое задание
        String[] arr = stringArrayIn;
        int sum = 0;
        try {
            if (arr.length == 0) {
                throw new ArithmeticException("!!!Impossible to calculate average, because length of array is 0!!!");
            }
            for (int i = 0; i < 5; i++) {
                sum += Integer.parseInt(arr[i]);
            }
            System.out.println(sum/arr.length);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("!!!Input index is out of array indexes range!!!");
        } catch (IllegalArgumentException e) {
            System.out.println("!!!Not integer!!!");
        } finally {
            System.out.println("\n***The end of the Program printArrayAverrage***");
        }
    }
    public static void printFileReadWrite(String fileIn, String fileOut) { //2ое задание
        BufferedReader br = null;
        BufferedWriter bw = null;
        String readedText = "";
        try {
            br = new BufferedReader(new FileReader(fileIn)); //поток открыт
            //br.close(); //!!! Smth is wrong with READING( !!!
            readedText = br.readLine(); //действие в потоке, есть разрешение на действие
            System.out.println(readedText);
            br.close(); //можно не писать, рано или поздно он сам закроется. Но это вежливо
        } catch (FileNotFoundException e) {
            System.out.println("!!! I cant find a file to READ( !!!");
        } catch (IOException e) {
            System.out.println("!!! Smth is wrong with READING( !!!");
        }
        try {
            bw = new BufferedWriter(new FileWriter(fileOut, true));
            bw.close(); //!!! Smth is wrong with WRITING( !!!
            bw.write(readedText);
            bw.flush(); //bw close + save buffer
        } catch (FileNotFoundException e) {
            System.out.println("!!! I cant find a file to WRITE( !!!");
        } catch(IOException e){
            System.out.println("!!! Smth is wrong with WRITING( !!!");
        }
    }
    public static void printParseInt(String stringIn) { //3ье задание
        try {
            int integer = Integer.parseInt(stringIn);
        } catch (NumberFormatException e) {
            CustomNumberFormatException.logInFile(e);
            String message = e.getMessage();
            System.out.println("CustomNumberFormatException: Integer.parseInt's argument \" " + message + " \"contains non-parsing elements.\n");
        }
    }
}