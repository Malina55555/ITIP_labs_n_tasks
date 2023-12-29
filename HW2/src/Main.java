import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("___2.1___");
        System.out.println(dublicateChars("Donald"));
        System.out.println(dublicateChars("orange"));
        System.out.println("___2.2___");
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));
        System.out.println("___2.3___");
        int[] arr231 = {44, 32, 86, 19};
        System.out.println(differenceEvenOdd(arr231));
        int[] arr232 = {22, 50, 16, 63, 31, 55};
        System.out.println(differenceEvenOdd(arr232));
        System.out.println("___2.4___");
        int[] arr241 = {1, 2, 3, 4, 5};
        System.out.println(equalToAvg(arr241));
        int[] arr242 = {1, 2, 3, 4, 6};
        System.out.println(equalToAvg(arr242));
        System.out.println("___2.5___");
        int[] arr251 = {1, 2, 3};
        System.out.println(Arrays.toString(indexMult(arr251)));
        int[] arr252 = {3, 3, -2, 408, 3, 31};
        System.out.println(Arrays.toString(indexMult(arr252)));
        System.out.println("___2.6___");
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println("___2.7___");
        System.out.println(Tribonacci(7));
        System.out.println(Tribonacci(11));
        System.out.println("___2.8___");
        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0));
        System.out.println("___2.9___");
        System.out.println(botHelper("Hello, I’m under the water, please help me"));
        System.out.println(botHelper("Two pepperoni pizzas please"));
        System.out.println("___2.10___");
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
    }
    public static boolean dublicateChars(String stringIn) {
        char[] myArrayChar= stringIn.toLowerCase().toCharArray();
        for (int q = 0; q<stringIn.length()-1; q++) {
            for (int w = q+1; w<stringIn.length(); w++) {
                if (myArrayChar[q] == myArrayChar[w]) {
                        return true;
                }
            }
        }
        return false;
    }

    public static String getInitials(String stringIn) {
        String[] myStringArray = stringIn.split(" ");
        return myStringArray[0].toCharArray()[0] + myStringArray[1].substring(0,1);
    }

    public static int differenceEvenOdd(int[] intArrayIn) {
        int chet = 0;
        int nechet = 0;
        for (int i=0; i< intArrayIn.length; i++) {
            if (intArrayIn[i] %2 ==0) {
                chet += intArrayIn[i];
            }
            else {
                nechet += intArrayIn[i];
            }
        }
        return (chet>nechet?(chet-nechet):(nechet-chet));
    }

    public  static boolean equalToAvg(int[] intArrayIn) {
        int sum = 0;
        for (int i = 0; i< intArrayIn.length; i++) {
            sum += intArrayIn[i];
        }
        int avg = 0;
        if (sum%(intArrayIn.length) ==0) {
            avg = sum/intArrayIn.length;
        }
        else {
            return false;
        }
        int q = Arrays.binarySearch(intArrayIn, avg);
        if (q==-1) {
            return false;
        }
        else {
            return true;
        }
    }

    public static int[] indexMult(int[] intArrayIn) {
        int cnt = intArrayIn.length;;
        int[] intArrayOut= new int[cnt];
        for (int i=0; i< intArrayIn.length; i++) {
            intArrayOut[i] = intArrayIn[i]*i;
        }
        return intArrayOut;
    }

    public static String reverse(String stringIn) {
        String stringOut = "";
        char[] myArrayChar= stringIn.toCharArray();
        for (int i=stringIn.length()-1; i>=0; i--) {
            stringOut += myArrayChar[i];
        }
        return stringOut;
    }

    public static int Tribonacci(int indexIn) {
        int[] intArray = new int[indexIn];
        if (indexIn >= 3) {
            intArray[0] = 0;
            intArray[1] = 0;
            intArray[2] = 1;
        }
        for (int i =3; i< indexIn; i++) {
            intArray[i] = intArray[i-3] + intArray[i-2] + intArray[i-1];
        }
        return intArray[indexIn-1];
    }

    public static String pseudoHash(int intIn) {
        char[] charArray = {'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f'};
        String result = "";
        Random generator = new Random();
        for (int i = 0; i<intIn; i++) {
            result += charArray[generator.nextInt(charArray.length)];
        }
        return result;
    }

    public static String botHelper(String stringIn) {
        int index = stringIn.toLowerCase().indexOf("help");
        if (index == -1) {
            return "Keep waiting";
        }
        else {
            return "Calling for a staff member";
        }
    }

    public static boolean isAnagram(String string1, String string2) {
        char[] charArray1 = string1.toLowerCase().toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = string2.toLowerCase().toCharArray();
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }
}