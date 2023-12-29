import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("___4.1___");
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));
        System.out.println("___4.2___");
        System.out.println(Arrays.toString(generateBrackets(1)));
        System.out.println(Arrays.toString(generateBrackets(2)));
        System.out.println(Arrays.toString(generateBrackets(3)));
        System.out.println("___4.3___");
        System.out.println(Arrays.toString(binarySystem(3)));
        System.out.println(Arrays.toString(binarySystem(4)));
        System.out.println("___4.4___");
        System.out.println(alphabeticRow("aaaaaaaa"));// ➞ "abcd"
        System.out.println(alphabeticRow("klmabzyxw"));// ➞ "zyxw"
        System.out.println("___4.5___");
        System.out.println(countCharsInString("aaabbcdd"));
        System.out.println(countCharsInString("vvvvaajaaaaa"));
        System.out.println("___4.6___");
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));
        System.out.println("___4.7___");
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));
        System.out.println("___4.8___");
        int[][] arr481 = {{1, 3, 1},
                          {1, 5, 1},
                          {4, 2, 1}};
        int[][] arr482 = {{2, 7, 3},
                          {1, 4, 8},
                          {4, 5, 9}};
        System.out.println(shortestWay(arr481));
        System.out.println(shortestWay(arr482));
        System.out.println("___4.9___");
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println("___4.10___");
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
    }
    public static String nonRepeatable(String stringIn) {
        char[] str0 = stringIn.toCharArray();
        String result = "";
        for (char c : str0) {
            if (indexOfCharArray(result.toCharArray(), c)==-1) {
                result += c;
            }
        }
        return result;
    }
    public static String[] generateBrackets(int intIn) {
        LinkedList<String> result = new LinkedList<>();
        changenumber:
        for (int i = 0; i < Math.pow(2, 2*intIn); i++) {
            String str0 = convertToBinaryStr(i, intIn*2);
            if ((str0.length() - str0.replace("0", "").length())!=(str0.length() - str0.replace("1", "").length())) {
                continue changenumber;
            } else {
                int open = 0;
                int close = 0;
                for (int j = 0; j < str0.length(); j++) {
                    if (close>open) continue changenumber;
                    else if ('0' == str0.charAt(j)) { open += 1;
                    } else {close += 1;}
                }
                str0 = str0.replace('0','(');
                str0 = str0.replace('1',')');
                result.add(str0);
            }
        }
        return result.toArray(String[]::new);
    }
    public static String[] binarySystem (int intIn) {
        int q = (int) Math.pow(2, intIn);
        String[] result = {""};
        for (int i = 0; i < q; i++) {
            String str0 = toBinary(i, intIn);
            if (!str0.contains("00")) {
                if (result[0] == "") {
                    result[0] = str0;
                } else {
                    result = Arrays.copyOf(result, result.length+1);
                    result[result.length-1] = str0;
                }
            }
        }
        return result;
    }
    public static String alphabeticRow(String stringIn) {
        String maxStr = "";
        String curStr = String.valueOf(stringIn.charAt(0));
        boolean yes = (stringIn.charAt(0) + 1 == stringIn.charAt(1) || stringIn.charAt(0) == stringIn.charAt(1) + 1);
        boolean isUp = stringIn.charAt(0) + 1 == stringIn.charAt(1);
        for (int i = 1; i < stringIn.length() - 1; i++) {
            //System.out.println(curStr + "   " + maxStr + "    " + yes + "   "+isUp);
            if (yes) {
                curStr += stringIn.charAt(i);
            }
            if (yes && i == stringIn.length() - 2 && isUp == (stringIn.charAt(i) + 1 == stringIn.charAt(i + 1))) {
                curStr += stringIn.charAt(i + 1);
                if (maxStr.length() < curStr.length()) {
                    maxStr = curStr;
                }
            }
            if (yes && isUp == (stringIn.charAt(i) + 1 == stringIn.charAt(i + 1))) {
            } else {
                if (maxStr.length() < curStr.length()) {
                    maxStr = curStr;
                }
                curStr = String.valueOf(stringIn.charAt(i));
            }
            yes = (stringIn.charAt(i) + 1 == stringIn.charAt(i + 1) || stringIn.charAt(i) == stringIn.charAt(i + 1) + 1);
            isUp = stringIn.charAt(i) + 1 == stringIn.charAt(i + 1);
        }
        return maxStr;
    }
    public static String countCharsInString(String stringIn) {
        class CharInt {
            private char charComponent;
            private int intComponent;
            public CharInt(char charIn, int intIn) {
                this.charComponent = charIn;
                this.intComponent = intIn;
            }
            public char getCharComponent() { return charComponent; }
            public int getIntComponent() { return intComponent; }
        }
        List<CharInt> charCounts = new ArrayList<>();
        char curChar = stringIn.charAt(0);
        int cnt = 0;
        for (int i = 0; i < stringIn.length(); i++) {
            if (curChar == stringIn.charAt(i)) {
                cnt++;
            } else {
                charCounts.add(new CharInt(curChar, cnt));
                cnt = 1;
                curChar = stringIn.charAt(i);
            }
        }
        charCounts.add(new CharInt(curChar, cnt));
        Collections.sort(charCounts, Comparator.comparingInt(CharInt::getIntComponent));
        String result = "";
        for (CharInt charint : charCounts) {
            result += (char) charint.getCharComponent();
            result += charint.getIntComponent();
        }
        return  result;
    }
    public static int convertToNum(String stringIn) {
        int result = 0;
        String[] oneArray = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        int[] oneInt = {1,2,3,4,5,6,7,8,9,10};
        String[] tenArray = {"eleven", "twelve", "thirteen", "fourteen", "fifteen",
                "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "thirty",
                "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        int[] tenIn = {11,12,13,14,15,16,17,18,19, 20,30,40,50,60,70,80,90};

        String[] stringArrayIn = stringIn.split(" ");

        if (Arrays.asList(stringArrayIn).contains("hundred")) { //100
            result += oneInt[Arrays.asList(oneArray).indexOf(stringArrayIn[0])]*100;
        }

        for (String str : stringArrayIn) { //11-90
            if (Arrays.asList(tenArray).contains(str)) {
                result += tenIn[Arrays.asList(tenArray).indexOf(str)];
            }
        }

        if (Arrays.asList(oneArray).contains(stringArrayIn[stringArrayIn.length-1])) {
            result += oneInt[Arrays.asList(oneArray).indexOf(stringArrayIn[stringArrayIn.length-1])];
        }

        return result;

    }
    public static String uniqueSubstring(String stringIn) {
        String[] results = {};
        for (int i = 0; i < stringIn.length()-1; i++) {
            for (int j = i+1; j< stringIn.length(); j++) {
                if (unicChars(stringIn.substring(i,j)) && !Arrays.asList(results).contains(stringIn.substring(i,j))) {
                    results = Arrays.copyOf(results, results.length+1);
                    results[results.length-1] = stringIn.substring(i,j);
                }
            }
        }
        Arrays.sort(results);
        if (results.length==1) {
            return results[0];
        }
        //System.out.println(Arrays.toString(results));
        for (int q = 1; q < results.length-1; q++) {
            int q1 = results[q-1].length();
            int q2 = results[q].length();
            int q3 = results[q+1].length();
            if (q1<q2 && q2>q3) {
                return results[q];
            }
        }
        return "";
    }
    public static int shortestWay(int[][] intArrayIn) {
        int n = intArrayIn[0].length;
        int result = 0;
        int curRow = n-1;
        int curCol = n-1;
        for (int i=(2*n-1); i>0; i--) { //номер хода
            if (i==(2*n-1) && result==0) {
            } else if (curCol==0) {
                curRow -= 1;
            } else if (curRow==0) {
                curCol -=1;
            } else {
                int q = Math.min(intArrayIn[curRow-1][curCol], intArrayIn[curRow][curCol-1]);
                if (q==intArrayIn[curRow-1][curCol]) {
                    curRow-=1; //идём вверх
                } else {
                    curCol-=1; //идём влево
                }
            }
            result+=intArrayIn[curRow][curCol];
        }
        return result;
    }
    public static String numericOrder (String stringIn) {
        String[] arrayIn = stringIn.split(" ");
        String[] arrayOut = new String[arrayIn.length];
        for (String str : arrayIn) {
            char[] charArray = str.toCharArray();
            char[] charArrayOut = new char[charArray.length-1];
            int i = 0;
            int q = 0;
            for (char ch : charArray) {
                if (Character.isDigit(ch)) {
                    i = Integer.parseInt(String.valueOf(ch))-1;
                } else {
                    charArrayOut[q] = ch;
                    q++;
                }
            }
            arrayOut[i] = new String(charArrayOut);
        }
        return String.join(" ", arrayOut);
    }
    public static int switchNums(int recours, int intIn) {
        int[] recoursArray = makeGigitArrayFromNumber(recours);
        Arrays.sort(recoursArray);
        int[] intInArray = makeGigitArrayFromNumber(intIn);
        for (int i = 0; i < intInArray.length; i++) {
            if (recoursArray.length==0) {}
            else if (recoursArray[recoursArray.length-1] > intInArray[i]) {
                intInArray[i] = recoursArray[recoursArray.length-1];
                recoursArray = Arrays.copyOf(recoursArray, recoursArray.length-1);
            }
        }
        int result = 0;
        intInArray = reverse(intInArray);
        for (int k = 0; k<intInArray.length; k++) {
            result += (int) intInArray[k]*Math.pow(10,k);
        }
        return result;
    }


    public static int indexOfCharArray(char[] charArray, char charNeed) {
        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] == charNeed)
                return i;
        return -1;
    }
    public static String toBinary(int intIn, int len) {
        String str0 = Integer.toBinaryString(intIn);
        if (str0.length() != len) {
            for (int i = 0; i < (len - str0.length()+1); i++) {
                str0 = "0" + str0;
            }
        }
        return str0;
    }
    public static int[] makeGigitArrayFromNumber (int intIn) {
        int[] result0 = {};
        int q = intIn;
        while (q>0) {
            result0 = Arrays.copyOf(result0, result0.length+1);
            result0[result0.length-1] = q % 10;
            q /= 10;
        }
        return reverse(result0);
    }
    static int[] reverse(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[array.length - 1 - i] = array[i];
        }
        return newArray;
    }
    static boolean unicChars(String stringIn) {
        char[] charArrayIn = stringIn.toCharArray();
        char[] charArrayOut = {};
        for (char ch : charArrayIn) {
            if (indexOfCharArray(charArrayOut, ch)==-1) {
                charArrayOut = Arrays.copyOf(charArrayOut, charArrayOut.length+1);
                charArrayOut[charArrayOut.length-1] = ch;
            }
            else {
                return false;
            }
        }
        return true;
    }
    static String convertToBinaryStr(int intIn, int lenIn) {
        if (lenIn == 0) {return "";};
        int[] result = new int[lenIn];
        int q = intIn;
        for (int i = 0; i < lenIn; i++) {
            result[lenIn-i-1] = q%2;
            q /= 2;
        }
        String res = "";
        for (int w : result) {
            res += w;
        }
        return res;
    }
}

/*
1.	Напишите рекурсивную функцию, которая принимает строку и удаляет из неё повторяющиеся символы. Функция должна вернуть строку, в которой каждый символ встречается только один раз.
Пример:
nonRepeatable("abracadabra") ➞ " abrcd"
nonRepeatable("paparazzi") ➞ " parzi"

2.	Напишите функцию, которая генерирует все возможные правильные комбинации пар скобок для заданного числа n.
Пример:
generateBrackets(1) ➞ ["()"]
generateBrackets(2) ➞ ["(())", "()()"]
generateBrackets(3) ➞ ["((()))", "(()())", "(())()", "()(())", "()()()"]

3.	Напишите функцию, которая генерирует все возможные бинарные комбинации длины n, в которых не может быть соседствующих нулей или единиц.
Пример:
binarySystem(3) ➞ ["010", "011", "101", "110", "111"]
binarySystem(4) ➞ ["0101", "0110", "0111", "1010", "1011", "1101", "1110", "1111"]

4.	Реализуйте функцию, которая принимает строку и возвращает длину самого длинного последовательного ряда в этом массиве. Последовательный ряд – это список соседних элементов, идущих подряд в алфавитном порядке, который может быть как увеличивающимся, так и уменьшающимся.
Пример:
alphabeticRow("abcdjuwx") ➞ "abcd"
// два последовательных ряда: "abcd", "uwx"; самый длинный: "abcd"
alphabeticRow("klmabzyxw") ➞ "zyxw"


5.	Напишите функцию, которая принимает строку и подсчитывает количество идущих подряд символов, заменяя соответствующим числом повторяющиеся символы. Отсортируйте строку по возрастанию длины буквенного паттерна.
Пример:
("aaabbcdd") ➞ "c1b2d2a3"
("vvvvaajaaaaa") ➞ "j1a2v4a5"

6.	Напишите функцию, принимающую положительное целое число в строковом формате, не превышающее 1000, и возвращающую его целочисленное представление.
Пример:
convertToNum("eight") ➞ 8
convertToNum("five hundred sixty seven") ➞ 567
convertToNum("thirty one") ➞ 31

7.	Напишите функцию, принимающую строку цифр, выполняющую поиск подстроки максимальной длины с уникальными элементами. Если найдено несколько подстрок одинаковой длины, верните первую.
Пример:
uniqueSubstring("123412324") ➞ "1234"
uniqueSubstring("111111") ➞ "1"
uniqueSubstring("77897898") ➞ "789"


8.	Напишите функцию поисковик наименьшего матричного пути. На вход поступает двумерный массив, размера n x n, ваша задача найти путь с минимальной суммой чисел, передвигаясь только вправо или вниз.
Пример:
shortestWay(
[[1, 3, 1],
 [1, 5, 1],
 [4, 2, 1]]) ➞ 7
// 1+3+1+1+1=7
shortestWay(
[[2, 7, 3],
 [1, 4, 8],
 [4, 5, 9]]) ➞ 21

9.	Создайте функцию, принимающую строку, содержащую числа внутри слов. Эти числа представляют расположение слова для новой строящейся строки.
Пример:
numericOrder("t3o the5m 1One all6 r4ule ri2ng") ➞ " One ring to rule them all"
numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat") ➞ " With great power comes great responsibility"

10.	Напишите функцию, принимающую два числа, которая делает второе число максимально возможным за счет замены своих элементов элементами первого числа. Брать цифру можно только один раз.
Пример:
switchNums(519, 723) ➞ 953
switchNums(491, 3912) ➞ 9942
switchNums(6274, 71259) ➞ 77659
 */