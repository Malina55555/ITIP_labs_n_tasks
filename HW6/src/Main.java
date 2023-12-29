import java.util.*;
import java.util.Arrays.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("___6.1___");
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived")); //➞ "worldevolvesin"
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood")); //➞ "noldwestactio"
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison")); //➞ "mrmojorisin"
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM")); //➞ "anamarg"
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth")); //"brightmoon"
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirthz")); //"notfound"
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit")); // ➞ "debitcard"
        System.out.println("___6.2___");
        System.out.println(collect("strengths", 3)); //➞ ["eng", "str", "ths"]
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15)); //➞ ["croscopicsilico", "pneumonoultrami", "volcanoconiosis"]
        System.out.println(collect("intercontinentalisationalism", 6)); //➞ ["ationa", "interc", "ntalis", "ontine"]
        System.out.println(collect("", 6)); //➞ []
        System.out.println("___6.3___");
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));// ➞ "yowmledrovlvsnieesrh"
        System.out.println(nicoCipher("andiloveherso", "tesha") );//➞ "lnidaevheo s or"
        System.out.println(nicoCipher("mubashirhassan", "crazy"));//➞ "bmusarhiahass n"
        System.out.println(nicoCipher("edabitisamazing", "matt"));// ➞ "deabtiismaaznig "
        System.out.println(nicoCipher("iloveher", "612345") );// ➞ "lovehir    e"
        System.out.println("___6.4___");
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45))); // ➞ [9, 5]
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45))); // ➞ [3, 15]
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, -1, 4, 5, 6, 10, 7}, 20))); // ➞ [4, 5]
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10))); // ➞ [2, 5]
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15))); // ➞ []
        System.out.println("___6.5___");
        System.out.println(Arrays.toString(isExact(6))); // ➞ [6, 3]
        System.out.println(Arrays.toString(isExact(24))); //  ➞ [24, 4]
        System.out.println(Arrays.toString(isExact(125))); //   ➞ []
        System.out.println(Arrays.toString(isExact(720))); // ➞ [720, 6]
        System.out.println(Arrays.toString(isExact(1024))); // ➞ []
        System.out.println(Arrays.toString(isExact(40320))); // ➞ [40320, 8]
        System.out.println("___6.6___"); ////? неверные ответы
        System.out.println(fractions("0.(6)")); //         ➞ "2/3"
        System.out.println(fractions("1.(1)")); //          ➞ "10/9"
        System.out.println(fractions("3.(142857)")); //         ➞ "22/7"
        System.out.println(fractions("0.19(2367)")); //         ➞ "5343/27775"
        System.out.println(fractions("0.1097(3)")); //         ➞ "823/7500"
        System.out.println("___6.7___");
        System.out.println(pilish_string("33314444")); //➞ "333 1 4444"
        System.out.println(pilish_string("TOP")); //➞ "TOP"
        System.out.println(pilish_string("X")); //➞ "XXX"
        System.out.println(pilish_string("")); //➞ ""
        System.out.println("___6.8___");
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));           //➞ -17
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)"));          //➞ 0
        System.out.println("___6.9___");
        System.out.println(isValid("aabbcd")); //         ➞ "NO"
        System.out.println(isValid("aabbccddeefghi")); //         ➞ "NO"
        System.out.println(isValid("abcdefghhgfedecba")); //        ➞ "YES"
        System.out.println("___6.10___");
        System.out.println(findLCS("abcd", "bd")); // ➞ "bd"
        System.out.println(findLCS("aggtab", "gxtxamb")); // ➞ "gtab"
    }

    public static String hiddenAnagram(String stringIn, String stringHide) {
        String stringInCopy = stringIn.toLowerCase().replaceAll("[^a-zA-Z]", "");
        String stringHideCopy = stringHide.toLowerCase().replaceAll("[^a-zA-Z]", "");
        String stringOut = "";
        HashMap<Integer, Character> hashMap = new HashMap<>(); //индекс символ
        for (char c : stringHideCopy.toCharArray()) {
            if (stringInCopy.matches(".*" + (char) c + ".*")) {
                hashMap.put(indexOfCharArray(stringInCopy.toCharArray(), c), c);
                stringInCopy = stringInCopy.replaceFirst(String.valueOf((char) c), "*"); //заменяем на символ, точно не содержащийся в строке, чтобы не сбивать индексы
            } else {
                return "notfound";
            }
        }
        for (int i = 0; i < stringIn.length() - 1; i++) {
            if (hashMap.containsKey(i)) {
                stringOut += (char) hashMap.get(i);
            }
        }
        return stringOut;
    }
    public static List<String> collect(String stringIn, int n) {
        List<String> result = new ArrayList<>();
        recourseColect(stringIn, n, result);
        Collections.sort(result);
        return result;
    }
    static void recourseColect(String stringIn, int n, List<String> result) {
        if (stringIn.length() < n) { return; }
        String slice = stringIn.substring(0, n);
        result.add(slice);
        recourseColect(stringIn.substring(n), n, result);
    }
    static String nicoMover(String stringIn, int[] indexes) { //перестановка символов в строке в соответствии с входящим набором индексов
        char[] strOut = new char[stringIn.length()];
        for (int i = 0; i < indexes.length; i++) {
            strOut[i] = stringIn.charAt(indexes[i]);
        }
        return String.valueOf(strOut);
    }
    public static String nicoCipher(String message, String key) {
        //ключ как лист из символов
        char[] keyArr = key.toCharArray();
        ArrayList<Character> keyList = new ArrayList<>();
        for (char c : keyArr) {
            keyList.add(c);
        }
        Collections.sort(keyList); // - сортируем по алфавиту/возрастанию
        //формирование исходной матрицы
        List<String> rows = new ArrayList<>();
        recourseColect(message, key.length(), rows); // - метод из предыдущего упражнения
        if (key.length() * rows.size() < message.length()) { // - проверка для недостающей строки (самый "низ" матрицы)
            String lastRow = message.substring(key.length() * rows.size());
            while (lastRow.length() != key.length()) {
                lastRow += " ";
            }
            rows.add(lastRow);
        }
        // строим отсортированный ключ из листа
        String keyCopy = key;
        StringBuilder builder = new StringBuilder(keyList.size());
        for(Character ch: keyList) {
                builder.append(ch);
        }
        String keyOut = builder.toString();
        // строим массив для перстановки символов в строке "матрицы"
        int[] indexOut = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            indexOut[i]=indexOfCharArray(keyCopy.toCharArray(), keyOut.charAt(i));
            keyCopy = keyCopy.replaceFirst(String.valueOf((char) keyOut.charAt(i)), " ");
        }
        String result = "";
        // из "матрицы" формируем строку на выходе
        for (String row : rows) {
            result += nicoMover(row, indexOut);
        }
        return result;
    }
    static int indexOfCharArray(char[] charArray, char charNeed) {
        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] == charNeed)
                return i;
        return -1;
    }
    public static String pilish_string(String stringIn) { //задание с числом пи
        if (stringIn.length() == 0) {
            return "";
        }
        //Делаем из ПИ лист из длин слов 3 1 4 ...
        ArrayList<Integer> pi = new ArrayList<>();
        for (char ch : String.valueOf(Math.PI).toCharArray()) {
            if (ch != '.') {
                pi.add(Integer.parseInt(String.valueOf((char) ch)));
            }
        }
        //работаем чисто с первым элементом листа, уменьшаем его и удаляем
        String result = "";
        for (char ch : stringIn.toCharArray()) {
            if (!pi.isEmpty()) { //пока все длины слов не использованы
                result += (char) ch;
                pi.set(0, pi.get(0) - 1); //уменьши длину необходимую на 1
                if (pi.get(0) == 0) { //если длина слова закончилась
                    if (stringIn.charAt(stringIn.length() - 1) != ch) { //если не последний элемент входящего слова
                        pi.remove(0);
                    }
                    result += " ";
                }
            }
            if (pi.isEmpty()) { //если ПИ закончилось, а длина >=0 - возвращай что есть
                return result;
            }
        }
        if (pi.get(0) == 0) { //если взяли длину слова (1ый элемент) полностью, то возвращай
            return result;
        } else {
            while (pi.get(0) != 0) { //или же повторяй последний символ, пока не станет нормально
                result += stringIn.charAt(stringIn.length() - 1);
                pi.set(0, pi.get(0) - 1);
            }
            return result;
        }
    }
    public static String findLCS(String string1, String string2) {
        int[][] dp = new int[string1.length() + 1][string2.length() + 1];
        // Находим длину наибольшей общей подпоследовательности
        //делаем с помощью матрицы
        //заполняем матрицу см https://habr.com/ru/articles/142825/
        for (int i = 1; i <= string1.length(); i++) {
            for (int j = 1; j <= string2.length(); j++) {
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // Восстанавливаем подпоследовательность из матрицы
        StringBuilder result = new StringBuilder();
        int i = string1.length(), j = string2.length();
        while (i > 0 && j > 0) {
            //начинаем с конца
            if (string1.charAt(i - 1) == string2.charAt(j - 1)) { //если символы совпадают, то на первый символ результата ставим их
                result.insert(0, string1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) { //иначе сдвигаемся вверх/влево по матрице в сторону большего значения с меньшими индексами
                i--;
            } else {
                j--;
            }
        }
        return result.toString();
    }


    public static double generateNonconsecutive(String stringIn) { //задание с калькулятором, решается через обратную польскую запись
        String str = stringIn.replaceAll(" ", "");
        if (str.matches("[^0-9.\\-+*/()]")) {
            throw new IllegalArgumentException("Неверно введённое выражение, присутствуют некорректные символы");
        }
        LinkedList<String> reversePolishNotation = makeReversePolishNotation(str);
        return calculateExpression(reversePolishNotation);
    }
    static LinkedList<String> makeReversePolishNotation(String stringIn) { //обратная польская запись
        String curNum = ""; //для хранения чисел, длина которых 1 и более символов
        LinkedList<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (char c : stringIn.toCharArray()) {
            if (Character.isDigit(c)) { //заполнение числа
                curNum += c;
            } else if ((char) c == '.') {
                if (curNum.isEmpty()) { //точка также идёт в заполение числа
                    throw new IllegalArgumentException("Неверно введённое выражение, неверный формат ввода дробного числа вида: .0 ");
                }
                curNum += c;
            } else {
                if (stringIn.charAt(stringIn.length()-1)==c && !"()".contains(String.valueOf((char) c))) {
                    throw new IllegalArgumentException("Неверно введённое выражение, операнд не имеет второго аргумента или неверный формат дробного числа вида: 0. ");
                }
                if (curNum.isEmpty() && c == '-') { //проверка на то, что минус показывает отрицательность числа, а не действие
                    curNum += c;
                    continue;
                }
                if (!curNum.isEmpty()) {  //заполненное число идёт автоматически в queue (очередь)
                    if (curNum.charAt(curNum.length()-1) == '.') {
                        throw new IllegalArgumentException("Неверно введенное выражение, неверный формат дробного числа вида: 0. ");
                    }
                    queue.add(curNum);
                    curNum = "";
                }
                if (stack.isEmpty() || stack.peek().equals('(')) { // если стэк пуст или на верху стэка (, то операнд идёт в стэк
                    stack.push(String.valueOf((char) c));
                } else if ("+-".contains(String.valueOf(stack.peek())) && "*/".contains(String.valueOf((char) c))) {  //если операнд входящий приоритетней операнда на верху стэка, то в стэк
                    stack.push(String.valueOf((char) c));
                } else if ("+-*/".contains(String.valueOf((char) c))) { //опустошение стэка до тех пор, пока не встретится ( или входящий симол не будет приоритетнее верха стэка, потом в стэк
                    while (true) { //до тех пор
                        if (stack.isEmpty() || stack.peek().equals("(") || ("*/".contains(String.valueOf((char) c))) && ("+-".contains(String.valueOf(stack.peek())))) {
                            //stack.isEmpty()   на случай если +- на входе, а стек опустошается до состояния начала выражения
                            break;
                        }
                        queue.add(stack.pop());
                    }
                    stack.push(String.valueOf((char) c));
                } else if (c == '(') { //просто добавляем в стэк
                    stack.push("(");
                    if (stringIn.charAt(stringIn.length()-1)==c) {
                        throw new IllegalArgumentException("Неверно введенное выражение, лишняя скобка: (");
                    }
                } else if (c == ')') { //опустошаем стэк до скобки (
                    while (true) {
                        if (stack.isEmpty()) {
                            throw new IllegalArgumentException("Неверно введенное выражение, отсутствует скобка: (");
                        }
                        if (stack.peek().equals("(")) {break;}
                        queue.add(stack.pop());
                    }
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Неверно введенное выражение, отсутствует скобка: )???");
                    }
                    stack.pop();
                } else {
                    throw new IllegalArgumentException("Неверно введенное выражение, посторонний символ или состояние выражения"); //никогда не выскочит
                }
            }
        }
        while (!stack.isEmpty()) { //когда выражение закончилось опустошаем остаток стэка
            queue.add(stack.pop());
        }
        return queue;
    }
    static double calculateExpression(LinkedList<String> queueIn) {
        Stack<Double> result = new Stack<>(); //это не тот стэк, что в обратной польской записи!
        LinkedList<String> queue = queueIn;
        while (!queue.isEmpty()) { //пока не используем всё из очереди queue
            String elem = queue.removeFirst();
            try { //если на входе число - просто в стэк
                double num = Double.parseDouble(elem);
                result.push(num);
            } catch (NumberFormatException e) { //если не число, то выполняем вычисление
                switch (elem) {
                    case "+": { result.push(result.pop() + result.pop());  break; }
                    case "-": { result.push(-result.pop() + result.pop());  break; }
                    case "*": { result.push(result.pop() * result.pop());  break; }
                    case "/": {
                        double num1 = result.pop();
                        double num2 = result.pop();
                        if (num1 == 0) {
                            throw new ArithmeticException("Нельзя делить на 0");
                        }
                        result.push(num2 / num1); //и результат обратно в стэк
                    }
                }
            }
        }
        return result.pop();
    }

    public static int[] twoProduct(int[] intArrayIn, int intIn) {
        int[] result = new int[2]; //нам нужна пара или её отсутствие
        for (int q = 3; q < intArrayIn.length; q++) { //"расширяем кругозор"
            for (int i = 0; i < q - 1; i++) { //перебираем первый элемент
                if (intIn % intArrayIn[i] != 0) {
                    continue;
                }
                for (int j = i; j < q; j++) { //затем второй из оставшейся области видимости
                    if (intIn % intArrayIn[i] != 0) {
                        continue;
                    }
                    if (intArrayIn[i] * intArrayIn[j] == intIn) {
                        return new int[]{intArrayIn[i], intArrayIn[j]}; //выводим пару
                    }
                }
            }
        }
        return new int[]{};
    }

    public static int[] isExact(int intIn) { //задание, что число - факториал
        int i = 0;
        while (i <= intIn) {
            if (factorial(i) == intIn) {
                return new int[]{intIn, i};
            }
            i++;
        }
        return new int[]{};
    }

    static int factorial(int intIn) {
        if (intIn < 2) {
            return 1;
        }
        return intIn * factorial(intIn - 1);
    }

    public static String fractions(String stringIn) { //про перевод дроби из десятичной в обыкновенную
        String wholePart = stringIn.substring(0, stringIn.indexOf('.')); //целая
        String nonPeriod = stringIn.substring(stringIn.indexOf('.') + 1, stringIn.indexOf('(')); //дробная
        String period = stringIn.substring(stringIn.indexOf('(') + 1, stringIn.indexOf(')')); //дробная в скобках

        int numerator = Integer.parseInt(wholePart + nonPeriod + period) - Integer.parseInt(wholePart + nonPeriod); //числитель

        char[] ch9 = new char[period.length()];
        char[] ch0 = new char[nonPeriod.length()];
        Arrays.fill(ch9, '9');
        Arrays.fill(ch0, '0');
        String denomin = new String(ch9); //знаменатель
        denomin += new String(ch0);
        int denominator = Integer.parseInt(denomin);

        String result = String.valueOf((int) numerator / euclideanAlgorithm(numerator, denominator)); //сокращаем дробь на НОД
        result += "/";
        result += String.valueOf((int) denominator / euclideanAlgorithm(numerator, denominator)); //сокращаем дробь НОД

        return result;
    }

    static int euclideanAlgorithm(int a, int b) { //НОД
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static String isValid(String stringIn) { //про Шерлока и действительную строку
        HashMap<Character, Integer> inside = new HashMap<>(); //к-во симмволлов
        HashMap<Integer, Integer> outside = new HashMap<>(); // к-во к-ва символов
        //к-во символов
        for (char ch : stringIn.toCharArray()) {
            if (!inside.containsKey(ch)) {
                inside.put(ch, 1);
            } else {
                inside.replace(ch, inside.get(ch) + 1);
            }
        }
        //к-во к-ва
        for (char c : inside.keySet()) {
            if (!outside.containsKey(inside.get(c))) { //если к-во не занесено
                outside.put(inside.get(c), 1);
            } else {
                outside.replace(inside.get(c), outside.get(inside.get(c)) + 1);   //увеличиваем к-во к-ва
            }
            if (outside.keySet().size() > 2) { //если ситуация н-р аббввв, т.е. 1=,2=,3= - уже не сделать строку действительной
                return "NO";
            } //проверка на случай когда 2 набора символов вываливаются из общей колеи: аббвввггдд а-1 б-2 в-3 г-2 д-2
        }

        if (outside.keySet().size() == 1) { //если всё заполнено одним символом
            return "YES";
        }
        int maxCountOfCountKey = outside.keySet().stream().max(Integer::compare).get(); //максимальный ключ из 2ого хэшмапа
        int secondKey = outside.keySet().stream().min(Integer::compare).get(); //оставшийся

//        if (maxCountOfCountKey == secondKey) { //
//            return "YES";
//        }

        if (outside.get(secondKey) == 1 && (secondKey == 1)) { //ситуация ааабббвввг
            return "YES";
        }
        if (outside.get(maxCountOfCountKey) == 1 && maxCountOfCountKey - secondKey == 1) { //ситуация абвггд
            return "YES";
        }
        return "NO";
    }

}
/*
1.	Создайте функцию, которая принимает две строки. Первая строка содержит предложение, содержащее буквы второй строки в последовательной последовательности, но в другом порядке. Скрытая анаграмма должна содержать все буквы, включая дубликаты, из второй строки в любом порядке и не должна содержать никаких других букв алфавита.
Напишите функцию, чтобы найти анаграмму второй строки, вложенную где-то в первую строку. Вы должны игнорировать регистр символов, любые пробелы и знаки препинания и возвращать анаграмму в виде строчной строки без пробелов или знаков препинания.

Пример:
hiddenAnagram(["My world evolves in a beautiful space called Tesh.", "sworn love lived"]) ➞ "worldevolvesin"
// The sequence "world evolves in" is a perfect anagram of "sworn love lived".

hiddenAnagram("An old west action hero actor", "Clint Eastwood") ➞ "noldwestactio"
// The sequence "n old west actio" is a perfect anagram of "Clint Eastwood".

hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison") ➞ "mrmojorisin"
// The sequence "Mr. Mojo Risin" ignoring the full stop, is a perfect
// anagram of "Jim Morrison".

hiddenAnagram("Banana? margaritas", "ANAGRAM") ➞ "anamarg"
// The sequence "ana? marg" ignoring "?" is a perfect anagram of "Anagram".

hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit") ➞ "debitcard"
// When all spaces, numbers and puntuation marks are removed
// from the whole phrase, the remaining characters form the sequence
// "Debitcard" which is a perfect anagram of "bad credit".

hiddenAnagram("Bright is the moon", "Bongo mirth") ➞ "notfound"
// The words "Bright moon" are an anagram of "bongo mirth" but they are
// not a continuous alphabetical sequence because the words "is the" are in
// between. Hence the negative result, "notfound" is returned.

Примечание:

- Совершенная анаграмма содержит все буквы оригинала в любом порядке, ни больше, ни меньше.
- Если в предложении нет скрытой анаграммы, верните "notfound".
- Как и в приведенных выше примерах, скрытая анаграмма может начинаться или заканчиваться частично через слово и/или охватывать несколько слов и может содержать знаки препинания и другие не-альфа-символы.
- Игнорируйте регистр символов и любые встроенные не-альфа-символы.
- Если в предложении больше 1 результата, верните ближайший к началу.

2.	Напишите функцию, которая возвращает массив строк, заполненных из срезов символов n-длины данного слова (срез за другим, в то время как n-длина применяется к слову).

Пример:
collect("intercontinentalisationalism", 6)
➞ ["ationa", "interc", "ntalis", "ontine"]

collect("strengths", 3)
➞ ["eng", "str", "ths"]

collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15)
➞ ["croscopicsilico", "pneumonoultrami", "volcanoconiosis"]

Примечания:

- Убедитесь, что результирующий массив лексикографически упорядочен.
- Возвращает пустой массив, если заданная строка меньше n.
- Ожидается, что вы решите эту задачу с помощью рекурсивного подхода.

3.	В шифре Nico кодирование осуществляется путем создания цифрового ключа и присвоения каждой буквенной позиции сообщения с помощью предоставленного ключа.

Создайте функцию, которая принимает два аргумента, message и key, и возвращает закодированное сообщение.

Существуют некоторые вариации правил шифрования. Одна из версий правил шифрования изложена ниже:

message = "mubashirhassan"
key = "crazy"

nicoCipher(message, key) ➞ "bmusarhiahass n"

Шаг 1: Назначьте числа отсортированным буквам из ключа:
"crazy" = 23154

Шаг 2: Назначьте номера буквам данного сообщения:
2 3 1 5 4
---------
m u b a s
h i r h a
s s a n

Шаг 3: Сортировка столбцов по назначенным номерам:
1 2 3 4 5
---------
b m u s a
r h i a h
a s s   n

Шаг 4: Верните закодированное сообщение по строкам:
eMessage = "bmusarhiahass n"

Пример:
nicoCipher("myworldevolvesinhers", "tesh") ➞ "yowmledrovlvsnieesrh"

nicoCipher("andiloveherso", "tesha") ➞ "lnidaevheo s or"

nicoCipher("mubashirhassan", "crazy") ➞ "bmusarhiahass n"

nicoCipher("edabitisamazing", "matt") ➞ "deabtiismaaznig "

nicoCipher("iloveher", "612345") ➞ "lovehir    e"


4.	Создайте метод, который принимает массив arr и число n и возвращает массив из двух целых чисел из arr, произведение которых равно числу n следующего вида:

[value_at_lower_index, value_at_higher_index]

Убедитесь, что вы возвращаете массив из двух целых чисел, который точно делит n (произведение n) и что индексы совпадают с указанным выше форматом. Таким образом, сортировка значений основана на восходящих индексах.

Пример:
twoProduct([1, 2, 3, 9, 4, 5, 15], 45) ➞ [9, 5]
// at index 5 which has the value 5 is a full match // to the value at index 3 which is 9
// the closest gap between indices that equates // to the product which is 45

twoProduct([1, 2, 3, 9, 4, 15, 3, 5], 45) ➞ [3, 15]
// at index 5 which has the value 15 is a full match // to the value at index 2 which is 3
// the closest gap between indices that equates // to the product which is 45

twoProduct([1,  2, -1,  4,  5,  6,  10, 7], 20) ➞ [4, 5]
// at index 4 which has the value 5 is a full match // to the value at index 3 which is 4
// a full match can only be achieved if you've found the multiplier at an // index lower than the current index, thus, 5 (@ index 4) has a pair lower
// than its index which is the value 4 (@ index 3), so, 5*4 = 20, in which 5 // has a higher index (4) than 4 which has an index value of 3

twoProduct([1, 2, 3, 4, 5,  6, 7, 8, 9, 10], 10) ➞ [2, 5]

twoProduct([100, 12, 4, 1, 2], 15) ➞ []

Примечание:
- Дубликатов не будет.
- Возвращает пустой массив, если совпадение не найдено.
- Всегда считайте, что пара рассматриваемого элемента (текущий элемент, на который указывали во время поиска) находится слева от него.
- Массив может иметь несколько решений (произведений n), поэтому возвращайте первое найденное полное совпадение (как описано выше).

5.	Создайте рекурсивную функцию, которая проверяет, является ли число точной верхней границей факториала n. Если это так, верните массив точной факториальной границы и n, или иначе, пустой массив.

Пример:
isExact(6) ➞ [6, 3]

isExact(24) ➞ [24, 4]

isExact(125) ➞ []

isExact(720) ➞ [720, 6]

isExact(1024) ➞ []

isExact(40320) ➞ [40320, 8]


6.	Деление на дробь часто приводит к бесконечно повторяющейся десятичной дроби.

1/3=.3333333...  1/7=.142857142857...

Создайте функцию, которая принимает десятичную дробь в строковой форме с повторяющейся частью в круглых скобках и возвращает эквивалентную дробь в строковой форме и в наименьших членах.

Пример:
fractions("0.(6)") ➞ "2/3"

fractions("1.(1)") ➞ "10/9"

fractions("3.(142857)") ➞ "22/7"

fractions("0.19(2367)") ➞ "5343/27775"

fractions("0.1097(3)") ➞ "823/7500"

7.	В этой задаче преобразуйте строку в серию слов (или последовательности символов), разделенных одним пробелом, причем каждое слово имеет одинаковую длину, заданную первыми 15 цифрами десятичного представления числа Пи:
3.14159265358979
Если строка содержит больше символов, чем общее количество, заданное суммой цифр Пи, неиспользуемые символы отбрасываются, и вы будете использовать только те, которые необходимы для формирования 15 слов.

String = "HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"

Pi String = "HOW I NEED A DRINK ALCOHOLIC IN NATURE AFTER THE HEAVY LECTURES INVOLVING QUANTUM MECHANICS"

// Every word has the same length of the digit of Pi found at the same index ?
// "HOW" = 3, "I" = 1, "NEED" = 4, "A" = 1, "DRINK" = 5
// "ALCOHOLIC" = 9, "IN" = 2, "NATURE" = 6, "AFTER" = 5
// "THE" = 3, "HEAVY" = 5, "LECTURES" = 8, "INVOLVING" = 9
// "QUANTUM" = 7, "MECHANICS" = 9
// 3.14159265358979

Также, если строка содержит меньше символов, чем общее количество, заданное суммой цифр Пи, в любом случае вы должны соблюдать последовательность цифр для получения слов.

String = "FORALOOP"

Pi String = "FOR A LOOP"

// Every word has the same length of the digit of Pi found at the same index ?
// "FOR" = 3, "A" = 1, "LOOP" = 4
// 3.14

Если буквы, содержащиеся в строке, не совпадают в точности с цифрами, в этом случае вы будете повторять последнюю букву, пока слово не будет иметь правильную длину.

String = "CANIMAKEAGUESSNOW"

Pi String = "CAN I MAKE A GUESS NOWWWWWWW"

// Every word has the same length of the digit of Pi found at the same index ?
// "CAN" = 3, "I" = 1, "MAKE" = 4, "A" = 1, "GUESS" = 5, "NOW" = 3
// 3.14153 (Wrong!)
// The length of the sixth word "NOW" (3)...
// ...doesn't match the sixth digit of Pi (9)
// The last letter "W" will be repeated...
// ...until the length of the word will match the digit

// "CAN" = 3, "I" = 1, "MAKE" = 4, "A" = 1, "GUESS" = 5, "NOWWWWWWW" = 9
// 3.14159 (Correct!)

Если данная строка пуста, должна быть возвращена пустая строка.

Учитывая строку txt, реализуйте функцию, которая возвращает ту же строку, отформатированную в соответствии с приведенными выше инструкциями.

Пример:
pilish_string("33314444") ➞ "333 1 4444"
// 3.14

pilish_string("TOP") ➞ "TOP"
// 3

pilish_string("X")➞ "XXX"
// "X" has to match the same length of the first digit (3)
// The last letter of the word is repeated

pilish_string("")➞ ""

Примечание:
- Эта задача представляет собой упрощенную концепцию, вдохновленную Пилишем, своеобразным типом ограниченного письма, в котором используются все известные возможные цифры Пи. Потенциально бесконечный текст может быть написан с использованием знаков препинания и набора дополнительных правил, которые позволяют избежать длинных последовательностей маленьких цифр, заменяя их словами больше 9 букв и делая так, чтобы композиция выглядела более похожей на стихотворение вольным стихом.
- Точка, отделяющая целую часть числа Пи от десятичной, не должна учитываться в функции: она присутствует в инструкциях и примерах только для удобства чтения.

8.	Создайте функцию, которая будет вычислять результат математических выражений, предоставленных в виде строки.

Реализуйте алгоритм, который разбирает строку и вычисляет результат выражения, учитывая приоритет операций, скобки и т. д. Математические операции, которые нужно поддерживать, включают в себя сложение, вычитание, умножение, деление и скобки. Обработайте ошибки, такие как деление на ноль или неправильно введенное выражение, и верните соответствующее сообщение об ошибке.

Пример:
generateNonconsecutive("3 + 5 * (2 - 6)") ➞ -17

generateNonconsecutive("6 – 18 / (-1 + 4)") ➞ 0


9.	Шерлок считает строку действительной, если все символы строки встречаются одинаковое количество раз. Также допустимо, если он может удалить только 1 символ из 1 индекса в строке, а остальные символы будут встречаться одинаковое количество раз. Для данной строки str определите, действительна ли она. Если да, верните «ДА», в противном случае верните «НЕТ».

Например, если str = "abc", строка действительна, потому что частота символов у всех одинакова. Если str = "abcc", строка также действительна, потому что мы можем удалить 1 "c" и оставить по одному символу каждого символа в строке. Однако, если str = "abccc", строка недействительна, поскольку удаление одного символа не приводит к одинаковой частоте символов.

Пример:
isValid("aabbcd") ➞ "NO"
// We would need to remove two characters, both c and d  -> aabb or a and b -> abcd, to make it valid.
// We are limited to removing only one character, so it is invalid.

isValid("aabbccddeefghi") ➞ "NO"
// Frequency counts for the letters are as follows:
// {"a": 2, "b": 2, "c": 2, "d": 2, "e": 2, "f": 1, "g": 1, "h": 1, "i": 1}
// There are two ways to make the valid string:
// Remove 4 characters with a frequency of 1: {f, g, h, i}.
// Remove 5 characters of frequency 2: {a, b, c, d, e}.
// Neither of these is an option.

isValid("abcdefghhgfedecba") ➞ "YES"
// All characters occur twice except for e which occurs 3 times.
// We can delete one instance of e to have a valid string.

10.	Создайте функцию, которая будет находить наибольшую общую подпоследовательность (LCS) для двух строк. LCS – это самая длинная последовательность символов, которая встречается как подпоследовательность в обеих строках. Эта задача требует понимания алгоритма динамического программирования для нахождения наибольшей общей подпоследовательности и его эффективной реализации.

Пример:
findLCS("abcd", "bd") ➞ "bd"

findLCS("aggtab", "gxtxamb") ➞ "gtab"



 */