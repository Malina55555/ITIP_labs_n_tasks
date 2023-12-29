import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("___3.1___");
        System.out.println(replaceVovels("apple"));
        System.out.println(replaceVovels("Even if you did this task not by yourself, you have to understand every single line of code."));
        System.out.println("___3.2___");
        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeeper"));
        System.out.println("___3.3___");
        System.out.println(doesBlockFit(1, 3, 5, 4, 5));
        System.out.println(doesBlockFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockFit(1, 2, 2, 1, 1));
        System.out.println("___3.4___");
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));
        System.out.println("___3.5___");
        int[] arr351 = {1, -3, 2};
        System.out.println(countRoots(arr351));
        int[] arr352 = {2, 5, 2};
        System.out.println(countRoots(arr352));
        int[] arr353 = {1, -6, 9};
        System.out.println(countRoots(arr353));
        System.out.println("___3.6___");
        String[][] arr361 = {
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}
        };
        System.out.println(Arrays.toString(salesData(arr361)));
        String[][] arr362 = {
                {"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}
        };
        System.out.println(Arrays.toString(salesData(arr362)));
        System.out.println("___3.7___");
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish"));
        System.out.println("___3.8___");
        int[] arr381 = {3, 1, 4, 2, 7, 5};
        int[] arr382 = {1, 2, 3, 4, 5};
        int[] arr383 = {1, 2, -6, 10, 3};
        System.out.println(waveForm(arr381));
        System.out.println(waveForm(arr382));
        System.out.println(waveForm(arr383));
        System.out.println("___3.9___");
        System.out.println(commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words."));
        System.out.println("___3.10___");
        int[][] arr3101 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {5, 5, 5, 5, 5},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1},
        };
        print2DArray(dataScience(arr3101));
        int[][] arr3102 = {
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21},
        };
        print2DArray(dataScience(arr3102));
        //System.out.println(Arrays.deepToString(arr3102));
    }
    public static String replaceVovels(String stringIn) {
        String result = stringIn.replace('a','*')
                .replace('e','*')
                .replace('i','*')
                .replace('o','*')
                .replace('u','*')
                .replace('y','*');
        return result;

        //String result = stringIn;
       // char[] vovelArray = {'a','e','i','o','u','y'};
        //for (char i : vovelArray) {
          //  result = result.replace(i, '*');
        //}


    }
    public static String stringTransform(String stringIn) {
        String result = "";
        boolean dobl = false;
        String lBuf = stringIn.substring(0,1);
        for (int i=1; i< stringIn.length(); i++) {
            if (lBuf.equals(stringIn.substring(i, i+1)) && !dobl) {
                result += "Double";
                result += lBuf.toUpperCase();
                dobl = true;
            }
            else if (dobl == true) {
                dobl = false;
            }
            else {
                result += lBuf;
            }
            lBuf = stringIn.substring(i, i+1);
        }
        result+=stringIn.substring(stringIn.length()-1, stringIn.length());
        return result;
    }
    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        /*
        int max1 = (w>h?w:h); //max
        int max2 = (w<h?w:h); //min
        int min2 = (a<b?(a<c?a:c):(b<c?b:c)); //absolutely min
        int m3 = (a>b?(a>c?a:c):(b>c?b:c)); //absolutely max
        int min1 = ((a==min2 || a==m3)?((b==min2 || b==m3)?c:b):a); //middle
         */
        int[] intArrayOfBlock= {a,b,c};
        Arrays.sort(intArrayOfBlock);
        int[] intArrayOfFigure = {w,h};
        Arrays.sort(intArrayOfFigure);
        if (intArrayOfFigure[1]>=intArrayOfBlock[1] && intArrayOfFigure[0]>=intArrayOfBlock[0]) { //средняя сторона блока помещается в максимальну, минимальная - в минимальную
            return true;
        }
        return false;
    }
    public static boolean numCheck(int intIn) {
        int q = intIn;
        int chetnost_ish = intIn % 2;
        int chetnost_kv = 0;
        while (q > 0) {
            int rassm_cifra = q % 10;
            q /= 10;
            chetnost_kv = (chetnost_kv + rassm_cifra) % 2;
        };
        if (chetnost_ish == chetnost_kv) {
            return true;
        } else {
            return false;
        }
    }
    public static int countRoots(int[] intArrayIn) {
        int a = intArrayIn[0];
        int b = intArrayIn[1];
        int c = intArrayIn[2];
        float D = b*b - 4*a*c;
        if (D==0.0 && b%(2*a)==0) {
            return 1;
        } else if (D>0.0 && isSquare(D) && ((b+Math.sqrt(D))%(2*a)==0 || (b-Math.sqrt(D))%(2*a)==0)) {
            if ((b+Math.sqrt(D))%(2*a)==0 && (b-Math.sqrt(D))%(2*a)==0) {
                return 2;
            } else { return 1; }
        } else { return 0; }
    }
    // container
    public static char commonVovel(String stringIn) {
        char[] str0 = stringIn.toLowerCase().toCharArray();
        char[] vovelArray = {'a','e','i','o','u','y'};
        int[] cntVovelArray = {0,0,0,0,0,0};
        for (char c : str0) {
            if (indexOfCharArray(vovelArray, c) != -1) {
                cntVovelArray[indexOfCharArray(vovelArray, c)]++;
            }
        }
        int maxID=0;
        for (int i = 0; i < cntVovelArray.length; i++) {
            if (cntVovelArray[i]>cntVovelArray[maxID]) { maxID = i; }
        }
        return vovelArray[maxID];
    }
    public static String[] salesData(String[][] arrayIn) {
        String[] shopArray = {arrayIn[0][1]};
        for (int i = 0; i < arrayIn.length; i++) {
            for (int j = 1; j < arrayIn[i].length; j++) {
                if (Arrays.asList(shopArray).indexOf(arrayIn[i][j]) == -1) {
                    shopArray = Arrays.copyOf(shopArray, shopArray.length+1);
                    shopArray[shopArray.length-1] = arrayIn[i][j];
                }
            }
        }
        String[] resultArray = {};
        for (int i = 0; i < arrayIn.length; i++) {
            if ((arrayIn[i].length-1)== shopArray.length) {
                resultArray = Arrays.copyOf(resultArray, resultArray.length+1);
                resultArray[resultArray.length-1]=arrayIn[i][0];
            }
        }
        return resultArray;
    }
    public static boolean validSplit(String stringIn) {
        String[] words = stringIn.split(" ");
        for (int i = 0; i < words.length-1;i++) {
            String thatWord = words[i];
            String nextWord = words[i+1];
            if (thatWord.charAt(thatWord.length()-1) != nextWord.charAt(0)) {
                return false;
            }
        }
        return true;
    }
    public static boolean waveForm(int[] intArrayIn) {
        boolean inc = intArrayIn[0] < intArrayIn[1]; // есть возрастание в начале? потом эта переменная зависит от предыдущей ситуации возрастание/убывание
        for (int i = 1; i < intArrayIn.length-1; i++) { //-1 т.к. выходит за индекс
            if (inc && (intArrayIn[i] <= intArrayIn[i+1])) { //возрастание и возрастание - не волна
                return false;
            } else if (!inc && (intArrayIn[i] >= intArrayIn[i+1])) { //убывание и убывание - не волна
                return false;
            }
            inc=!inc; //теперь возрастание - убывание и наоборот
        }
        return true;
    }
    public static int[][] dataScience(int[][] intArrayIn) {
        int[][] result = intArrayIn;
        int[] sumsOfCol = new int[intArrayIn.length];
        Arrays.fill(sumsOfCol, 0);
        for (int i = 0; i< intArrayIn.length; i++) { //i - номер строки; j - номер столбца
            for (int j = 0; j < intArrayIn.length; j++) {
                if (i != j) {
                    sumsOfCol[j] += intArrayIn[i][j];
                }
            }
        }
        for (int i = 0; i< intArrayIn.length; i++) {
            if (sumsOfCol[i]%(intArrayIn.length-1) >= (intArrayIn.length-1)/2) { //проверка на округление в бОльшую сторону
                result[i][i] = (sumsOfCol[i] / (intArrayIn.length - 1)) + 1;
            } else {
                result[i][i] = sumsOfCol[i] / (intArrayIn.length - 1);
            }
        }// Math.ceil() <------- !!!
        return result;
    }
    static void print2DArray(int[][] intArrayIn) {
        System.out.println("[");
        for (int[] row : intArrayIn)
            System.out.println(Arrays.toString(row));
        System.out.println("]");
    }
    static boolean isSquare(float a) {
        int x = (int) Math.sqrt(a);
        if (Math.pow(x,2) == a) {
            return true;
        }
        return false;
    }
    public static int indexOfCharArray(char[] charArray, char charNeed) {
        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] == charNeed)
                return i;
        return -1;
    }
}

