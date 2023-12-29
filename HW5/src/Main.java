import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("___5.1___");
        System.out.println(sameLetterPattern("ABAB", "CDCD")); //➞ true
        System.out.println(sameLetterPattern("ABCBA", "BCDCB")); //t
        System.out.println(sameLetterPattern("FFGG", "CDCD"));//f
        System.out.println(sameLetterPattern("FFFF", "ABCD"));//f
        System.out.println("___5.2___");
        System.out.println(spiderVsFly("H3", "E2")); // ➞ "H3-H2-H1-A0-E1-E2"
        System.out.println(spiderVsFly("A4", "B2")); // ➞ "A4-A3-A2-B2"
        System.out.println(spiderVsFly("A4", "C2")); // ➞ "A4-A3-A2-B2-C2"
        System.out.println("___5.3___");
        System.out.println(digitsCount(4666)); //  ➞ 4
        System.out.println(digitsCount(544)); //  ➞ 3
        System.out.println(digitsCount(121317)); //  ➞ 6
        System.out.println(digitsCount(0)); //  ➞ 1
        System.out.println(digitsCount(12345)); //  ➞ 5
        System.out.println(digitsCount(1289396387328L)); // ➞ 13
        System.out.println("___5.4___");
        String[] arr541 = {"cat", "create", "sat"};
        System.out.println(totalPoints(arr541, "caster")); //➞ 2
        String[] arr542 = {"recant", "trance"};
        System.out.println(totalPoints(arr542, "recant")); //➞ 108
        String[] arr543 = {"dote", "dotes", "toes", "set", "dot", "dots", "sted"};
        System.out.println(totalPoints(arr543, "tossed")); //➞ 13
        System.out.println("___5.5___");
        printArrayListIntArray(sumsUp(new int[]{1, 2, 3, 4, 5})); //➞ [[3, 5]]
        printArrayListIntArray(sumsUp(new int[]{1, 2, 3, 7, 9})); //➞ [[1, 7]]
        printArrayListIntArray(sumsUp(new int[]{10, 9, 7, 2, 8})); //➞ []
        printArrayListIntArray(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})); //➞ [[2, 6], [3, 5], [1, 7]]
        System.out.println("___5.6___");
        System.out.println(takeDownAverage(new String[] {"95%", "83%", "90%", "87%", "88%", "93%"})); //➞ "54%"
        System.out.println(takeDownAverage(new String[] {"10%"})); //➞ "0%"
        System.out.println(takeDownAverage(new String[] {"53%", "79%"})); //➞ "51%"
        System.out.println("___5.7___");
        System.out.println(caesarCipher("encode", "hello world", 3)); //" KHOOR ZRUOG"
        //System.out.println(caesarCipher("decode", "almost last task!", 4)); //" EPQSWX PEWX XEWO!"
        System.out.println(caesarCipher("decode", "EPQSWX PEWX XEWO!", 4)); //"almost last task!"
        System.out.println("___5.8___");
        System.out.println(setSetup(5,3)); //60
        System.out.println(setSetup(7,3)); //210
        System.out.println("___5.9___");
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println("___5.10___");
        System.out.println(isNew(3));// isNew(3) ➞ true
        System.out.println(isNew(30));//isNew(30) ➞ true
        System.out.println(isNew(321));//isNew(321) ➞ false
        System.out.println(isNew(130));//isNew(123) ➞ true
    }

    public static boolean sameLetterPattern(String stringIn1, String stringIn2) {
        int n = stringIn1.length();
        HashMap<Character, Character> hashMap1 = new HashMap<>();
        HashMap<Character, Character> hashMap2 = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c1 = stringIn1.charAt(i);
            char c2 = stringIn2.charAt(i);

            if (!hashMap1.containsKey(c1)) {
                hashMap1.put(c1, c2);
            } else if (hashMap1.get(c1) != c2) {
                return false;
            }

            if (!hashMap2.containsKey(c2)) {
                hashMap2.put(c2, c1);
            } else if (hashMap2.get(c2) != c1) {
                return false;
            }
        }
        return true;
    }
    static double wayBetweenBranches(int level) {
        return level * Math.sqrt(2 - Math.sqrt(2));
    }
    public static String spiderVsFly(String spider, String fly) {
        class Coord {
            int branch; //{"A","B","C","D","E","F","G","H"}
            int level;
            public Coord(String stringIn) {
                this.branch = stringIn.charAt(0) - 'A';
                this.level = stringIn.charAt(1) - '0';
            }
            public String strCoord() {
                String result = "";
                result += (char) ('A' + this.branch);
                result += (char) ('0' + this.level);
                return result;
            }
        }
        Coord spiderC = new Coord(spider);
        Coord flyC = new Coord(fly);
        StringBuilder result = new StringBuilder();
        result.append(spiderC.strCoord()); //начальные координаты
        result.append("-");
        while (! (spiderC.branch==flyC.branch && spiderC.level==flyC.level)) {
            if (spiderC.level==0) { //из центра
                spiderC.level++;
                spiderC.branch = flyC.branch;
            } else if (spiderC.branch==flyC.branch) { //одна ветка
                if (spiderC.level > flyC.level) {
                    spiderC.level--;
                } else {
                    spiderC.level++;
                }
            } else if (Math.abs(spiderC.branch - flyC.branch) == 4) { // противоположная ветвь
                spiderC.level--;
                if (spiderC.level==0) {
                    spiderC.branch = 0;
                }
            } else if (spiderC.level > flyC.level) { //снижение до уровня мухи
                spiderC.level--;
                if (spiderC.level==0) {
                    spiderC.branch = 0;
                }
            } else if (Math.abs(spiderC.level - flyC.level) + wayBetweenBranches(spiderC.level) * (Math.abs(spiderC.branch - flyC.branch) % 4) < (spiderC.level + flyC.level)) { //паук по дугам потом по уровням быстрее, чем через центр
                spiderC.branch += (int) Math.signum(flyC.branch - spiderC.branch);
            } else {
                spiderC.level--;
                if (spiderC.level==0) {
                    spiderC.branch = 0;
                }
            }
            result.append(spiderC.strCoord());
            if (! (spiderC.branch==flyC.branch && spiderC.level==flyC.level)) {
                result.append("-");
            }
        }
        return result.toString();
    }
    public static int digitsCount(int intIn) {
        if (intIn != 0) {
            return recouseF(intIn, 0);
        }
        return 1;
    }
    public static int digitsCount(Long intIn) {
        if (intIn != 0) {
            return recouseF(intIn, 0);
        }
        return 1;
    }
    static int recouseF (int intIn, int curLen) {
        if (intIn>0) {
            return recouseF(intIn/10, curLen+1);
        }
        return curLen;
    }
    static int recouseF (Long intIn, int curLen) {
        if (intIn>0) {
            return recouseF(intIn/10, curLen+1);
        }
        return curLen;
    }
    public static int totalPoints(String[] arrayStringIn, String stringIn) {
        HashMap <Character, Integer> hashMapStingIn = new HashMap<>();
        for (Character chIn : stringIn.toCharArray()) {
            if (!hashMapStingIn.containsKey(chIn)) {
                hashMapStingIn.put(chIn, 1);
            } else {
                hashMapStingIn.put(chIn, hashMapStingIn.get(chIn)+1);
            }
        }
        int result = 0;
        forStringInArrayIn:
        for (String strAr : arrayStringIn) {
            HashMap <Character, Integer> hashMap0 = new HashMap<>();
            for (Character chIn : strAr.toCharArray()) {
                if (!hashMapStingIn.containsKey(chIn)) {
                    continue forStringInArrayIn;
                }
                if (!hashMap0.containsKey(chIn)) {
                    hashMap0.put(chIn, 1);
                } else {
                    hashMap0.put(chIn, hashMap0.get(chIn)+1);
                }
                if (hashMapStingIn.get(chIn) < hashMap0.get(chIn)) {
                    continue  forStringInArrayIn;
                }
            }
            if (strAr.length()==3) {
                result += 1;
            } else if (strAr.length()==4) {
                result += 2;
            } else if (strAr.length()==5) {
                result += 3;
            //} else if (strAr.length() == 6 && Arrays.asList(arrayStringIn).contains(stringIn)) { //если за каждое 6-буквенное при разгаданном даётся +50
              //  result += 54;
            } else if (strAr.length() == 6) {
                result += 4;
            }
            if (Arrays.asList(arrayStringIn).contains(stringIn))
             {result+=50;} //если за каждое при разгаданном 6-буквенном
        }
        return result;
    }
    public static ArrayList<int[]> sumsUp(int[] intArrayIn) {
    ArrayList<int[]> results = new ArrayList<>();
    int n = intArrayIn.length;
    for (int k = 3; k <= n; k++) {
        int[] workingArray = Arrays.copyOf(intArrayIn, k);
        for (int i = 0; i < workingArray.length - 1; i++) {
            for (int j = i + 1; j < workingArray.length; j++) {
                int a = workingArray[i];
                int b = workingArray[j];
                if (a + b == 8) {
                    int[] ab = {Math.min(a, b), Math.max(a, b)};
                    if (!containsMy(results, ab)) {
                        results.add(ab);
                    }
                }
            }
        }
    }
    return results;
}
    public static boolean containsMy(ArrayList<int[]> list, int[] checkArray) {
        for (int[] arr : list) {
            if (arr.length == checkArray.length && containsAll(arr, checkArray)) {
                return true;
            }
        }
        return false;
    }
    public static boolean containsAll(int[] array, int[] subArray) {
        for (int num : subArray) {
            boolean found = false;
            for (int arrNum : array) {
                if (num == arrNum) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
    static void printArrayListIntArray(ArrayList<int[]> arrIn) {
        System.out.print("[");
        for (int[] result : arrIn) {
            System.out.print(Arrays.toString(result));
            if (result != arrIn.get(arrIn.size() - 1)) {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }
    public static String takeDownAverage(String[] stringArrayIn) {
        int[] intArrayIn = new int[stringArrayIn.length];
        int sum = 0;
        int len = intArrayIn.length;
        for (int i = 0; i < stringArrayIn.length; i++) {
            intArrayIn[i] = Integer.parseInt(stringArrayIn[i].substring(0,stringArrayIn[i].length()-1));
            sum += intArrayIn[i];
        }
        int x = (sum*(len+1)-len*(sum+5*(len+1)))/len;
        String result = x + "%";
        return result;
    }
    public static String caesarCipher(String strMode, String stringIn, int intIn) {
        String chrInAcceptable = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
        String result = "";
        for (char ch0 : stringIn.toCharArray()) {
            if (!chrInAcceptable.contains(String.valueOf(ch0))) {
                result += String.valueOf(ch0);
            } else if (strMode == "encode") {
                result += (Character.toString(ch0 + intIn)).toUpperCase();
            } else if (strMode == "decode") {
                result += (Character.toString(ch0 - intIn)).toLowerCase();
            }
        }
        return result;
    }
    public static int setSetup(int nIn, int kIn) {
        return factorial(nIn)/factorial(nIn-kIn);
    }
    static int factorial(int intIn) {
        return (intIn<=1?1:factorial(intIn-1)*intIn);
    }
    static ArrayList<String> results10;
    static int needLen10;
    public static String timeDifference(String cityA, String stringDate, String cityB) {
        HashMap<String, TimeZone> timeZones = new HashMap<>();
        timeZones.put("Los Angeles", SimpleTimeZone.getTimeZone("GMT-8"));
        timeZones.put("New York", SimpleTimeZone.getTimeZone("GMT-5"));
        timeZones.put("Caracas", SimpleTimeZone.getTimeZone("GMT-4:30"));
        timeZones.put("Buenos Aires", SimpleTimeZone.getTimeZone("GMT-3"));
        timeZones.put("London", SimpleTimeZone.getTimeZone("GMT"));
        timeZones.put("Rome", SimpleTimeZone.getTimeZone("GMT+1"));
        timeZones.put("Moscow", SimpleTimeZone.getTimeZone("GMT+3"));
        timeZones.put("Tehran", SimpleTimeZone.getTimeZone("GMT+3:30"));
        timeZones.put("New Delhi", SimpleTimeZone.getTimeZone("GMT+5:30"));
        timeZones.put("Beijing", SimpleTimeZone.getTimeZone("GMT+8"));
        timeZones.put("Canberra", SimpleTimeZone.getTimeZone("GMT+10"));

        SimpleDateFormat formatIn = new SimpleDateFormat("MMMM d, yyyy H:m", Locale.US);
        SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-M-d HH:mm", Locale.US);

        formatIn.setTimeZone(timeZones.get(cityA));
        formatOut.setTimeZone(timeZones.get(cityB));

        try {
            return formatOut.format(formatIn.parse(stringDate));
        } catch (ParseException ignored) {
            return "error";
        }
    }
    public static boolean isNew(int intIn) {
        results10 = new ArrayList<>();
        needLen10 = digitsCount(intIn);
        printPermutn(String.valueOf(intIn), "");
        for (String q : results10) {
            if (Integer.parseInt(q)<intIn) {
                return false;
            }
        }
        return true;
    }
    static void printPermutn(String stringIn, String stringOut) {
        if (stringIn.isEmpty()) { //все символы взяты
            if (stringOut.charAt(0) != '0' && !results10.contains(stringOut)) { //проверка на начинание с 0 и повторяемость результатов
                results10.add(stringOut);
            }
            return;
        }
        for (int i = 0; i < stringIn.length(); i++) {
            char ch = stringIn.charAt(i);
            String withoutI = stringIn.substring(0, i) + stringIn.substring(i + 1); //убираем iтый символ
            printPermutn(withoutI, stringOut + ch);
        }
    }
}


/*
1.	Создайте функцию, которая возвращает true, если две строки имеют один и тот же буквенный шаблон, и false в противном случае.

Пример:
sameLetterPattern("ABAB", "CDCD") ➞ true

sameLetterPattern("ABCBA", "BCDCB") ➞ true

sameLetterPattern("FFGG", "CDCD") ➞ false

sameLetterPattern("FFFF", "ABCD") ➞ false

2.	Паутина определяется кольцами, пронумерованными от 0 до 4 от центра, и радиалами, помеченными по часовой стрелке сверху как A-H.

Создайте функцию, которая принимает координаты паука и мухи и возвращает кратчайший путь для паука, чтобы добраться до мухи.

Стоит отметить, что кратчайший путь должен быть рассчитан "геометрически", а не путем подсчета количества точек, через которые проходит этот путь.
•	Угол между каждой парой радиалов одинаков (45 градусов).
•	Расстояние между каждой парой колец всегда одинаково (скажем, "x").

На приведенном выше рисунке координаты паука - "H3", а координаты мухи - "E2". Паук будет следовать по кратчайшему пути "H3-H2-H1-A0-E1-E2", чтобы добраться до мухи.

Пример:
spiderVsFly("H3", "E2") ➞ "H3-H2-H1-A0-E1-E2"

spiderVsFly("A4", "B2") ➞ "A4-A3-A2-B2"

spiderVsFly("A4", "C2") ➞ "A4-A3-A2-B2-C2"
3.	Создайте функцию, которая будет рекурсивно подсчитывать количество цифр числа. Преобразование числа в строку не допускается, поэтому подход является рекурсивным.

digitsCount(4666) ➞ 4

digitsCount(544) ➞ 3

digitsCount(121317) ➞ 6

digitsCount(0) ➞ 1

digitsCount(12345) ➞ 5

digitsCount(1289396387328L) ➞ 13

4.	Игроки пытаются набрать очки, формируя слова, используя буквы из 6-буквенного скремблированного слова. Они выигрывают раунд, если им удается успешно расшифровать слово из 6 букв.

Создайте функцию, которая принимает в массив уже угаданных слов расшифрованное 6-буквенное слово и возвращает общее количество очков, набранных игроком в определенном раунде, используя следующую рубрику:

3-буквенные слова – это 1 очко
4-буквенные слова – это 2 очка
5-буквенные слова – это 3 очка
6-буквенные слова – это 4 очка + 50 пт бонуса (за расшифровку слова)
Помните, что недопустимые слова (слова, которые не могут быть сформированы из 6-буквенных расшифрованных слов) считаются 0 очками.

Пример:
totalPoints(["cat", "create", "sat"], "caster") ➞ 2
// Since "create" is an invalid word.

totalPoints(["trance", "recant"], "recant") ➞ 108
// Since "trance" and "recant" score 54 pts each.

totalPoints(["dote", "dotes", "toes", "set", "dot", "dots", "sted"], "tossed") ➞ 13
// Since 2 + 3 + 2 + 1 + 1 + 2 + 2 = 13

Примечание:
- Если 6-буквенное слово имеет несколько анаграмм, считайте каждую 6-буквенную расшифровку дополнительными 54 очками. Например, если слово arches, а игрок угадал arches и chaser, добавьте 108 очков для обоих слов.
- Вы можете играть в Текстовый Твист здесь: http://text-twist2.com

5.	Создайте функцию, которая получает каждую пару чисел из массива, который суммирует до восьми, и возвращает его как массив пар (отсортированный по возрастанию).

Пример:
sumsUp([1, 2, 3, 4, 5]) ➞ [[3, 5]]

sumsUp([1, 2, 3, 7, 9]) ➞ [[1, 7]]

sumsUp([10, 9, 7, 2, 8]) ➞ []

sumsUp([1, 6, 5, 4, 8, 2, 3, 7]) ➞ [[2, 6], [3, 5], [1, 7]]
// [6, 2] first to complete the cycle (to sum up to 8)
// [5, 3] follows
// [1, 7] lastly
// the pair that completes the cycle is always found on the left
// [2, 6], [3, 5], [1, 7] sorted according to cycle completeness, then pair-wise.

6.	Какой процент вы можете набрать на тесте, который в одиночку снижает средний балл по классу на 5%? Учитывая массив оценок ваших одноклассников, создайте функцию, которая возвращает ответ. Округлите до ближайшего процента.

Пример:
takeDownAverage(["95%", "83%", "90%", "87%", "88%", "93%"]) ➞ "54%"

takeDownAverage(["10%"]) ➞ "0%"

takeDownAverage(["53%", "79%"]) ➞ "51%"

7.	Создайте функцию, которая будет шифровать и дешифровать сообщения с использованием шифра Цезаря. Шифр Цезаря – это метод шифрования, в котором каждая буква в сообщении сдвигается на фиксированное количество позиций в алфавите. Например, если сдвиг составляет 3 позиции, то буква 'A' будет зашифрована как 'D', 'B' как 'E' и так далее.

Функция должна выполнять следующие действия:

1. Определять режим работы: шифрование или дешифрование сообщения.
2. Если пользователь хочет зашифровать сообщение, программа должна запросить само сообщение и сдвиг, на который нужно зашифровать текст.
3. Если пользователь хочет дешифровать сообщение, программа должна запросить зашифрованное сообщение и сдвиг, чтобы расшифровать его.
4. Обрабатывать сообщения только в верхнем регистре и оставлять другие символы (пробелы, цифры и специальные символы) без изменений.

Пример:
caesarCipher("encode", "hello world", 3) ➞ " KHOOR ZRUOG"

caesarCipher(["decode", "almost last task!", 4]) ➞ "EPQSWX PEWX XEWO!"

8.	Создайте метод для рекурсивного вычисления количества различных способов как можно разместить k элементов из множества из n элементов без повторений. Это задача комбинаторики, и она часто используется в комбинаторных оптимизациях, таких как размещение задач на стеллажах или распределение ресурсов.

Метод принимает два параметра, где n - количество элементов в множестве, а k - количество элементов, которые нужно разместить (n >= k) и рассчитывает количество размещений по формуле размещений без повторений: n! / (n - k)!

Пример:
setSetup(5, 3) ➞ 60

setSetup(7, 3) ➞ 210
9.	В этой задаче цель состоит в том, чтобы вычислить, сколько времени сейчас в двух разных городах. Вам дается строка cityA и связанная с ней строка timestamp (time in cityA) с датой, отформатированной в полной нотации США, как в этом примере:

"July 21, 1983 23:01"

Вы должны вернуть новую метку времени с датой и соответствующим временем в cityB, отформатированную как в этом примере:

"1983-7-22 23:01"

Список данных городов и их смещения по Гринвичу (среднее время по Гринвичу) приведены в таблице ниже.

GMT	City
- 08:00	Los Angeles
- 05:00	New York
- 04:30	Caracas
- 03:00	Buenos Aires
00:00	London
+ 01:00	Rome
+ 03:00	Moscow
+ 03:30	Tehran
+ 05:30	New Delhi
+ 08:00	Beijing
+ 10:00	Canberra

Пример:
timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra") ➞ "2011-4-2 17:23"
// Can be a new day.

timeDifference("London", "July 31, 1983 23:01", "Rome") ➞ "1983-8-1 00:01"
// Can be a new month.

timeDifference("New York", "December 31, 1970 13:40", "Beijing") ➞ "1971-1-1 02:40"
// Can be a new year.

Примечание:
- Обратите внимание на часы и минуты, ведущий 0 необходим в возвращаемой метке времени, когда они представляют собой одну цифру.
- Обратите внимание на города с получасовыми смещениями.

10.	Новое число – это число, которое не является перестановкой любого меньшего числа. 869 – это не новое число, потому что это просто перестановка меньших чисел, 689 и 698. 509 – это новое число, потому что оно не может быть образовано перестановкой любого меньшего числа (ведущие нули не допускаются).

Напишите функцию, которая принимает неотрицательное целое число и возвращает true, если целое число является новым числом, и false, если это не так.

Пример:
isNew(3) ➞ true

isNew(30) ➞ true

isNew(321) ➞ false

isNew(123) ➞ true
 */
