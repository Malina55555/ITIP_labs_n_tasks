import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {
    public static void main(String[] args) {
        numberFinder("The price of the product is $19.99. And there are some other numbers 13456, 234.7f, 567,8, -67. ");

        passwordChecker("Qert456bjh");
        passwordChecker("Qh1");
        passwordChecker("");
        passwordChecker("ert456bjh");
        passwordChecker("Qertbjhr");

        replaceHyperlink("Просто ссылки в тексте: vk.com/mtuci бла-бла-бла\n " +
                "https://javarush.com/groups/posts/229-reguljarnihe-vihrazhenija-v-java-chastjh-4 Бла");
        String text = "This is a sample text with links like www.example.com and http://www.example.com. Ignore https://www.example.com";
        replaceHyperlink(text);

        checkIP("123.56.784.456");
        checkIP("125.015.125.67");
        checkIP("256.0.0.1");

        String stringIn = "I thought a thought. But the thought I thought wasn't the thought I thought I thought..";
        findLetterWord(stringIn, 'i');
        findLetterWord(stringIn, 'I');
        findLetterWord(stringIn, 'T');
    }
    public static void numberFinder (String stringIn) {
        try {
            Pattern pattern = Pattern.compile("-?\\d+[.,]\\d+[Lfd]?|-?\\d+");
            //Pattern pattern = Pattern.compile("-?\\d+[.,]\\d+[Lfd]?|-?\\d+|(");
            Matcher matcher = pattern.matcher(stringIn);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }
        catch (PatternSyntaxException e) {
            System.err.println("Ошибка регулярного выражения: " + e.getMessage());
            System.err.println("Описание: " + e.getDescription());
            System.err.println("Ошибка возникает с позиции: " + e.getIndex());
            System.err.println("Шаблон, вызывающий ошибку: " + e.getPattern());
        }
    }
    public static void passwordChecker (String stringIn) {
        try {
            Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$");
            Matcher matcher = pattern.matcher(stringIn);
            System.out.println(stringIn + " " + matcher.matches());
        }
        catch (PatternSyntaxException e) {
            System.err.println("Ошибка регулярного выражения: " + e.getMessage());
            System.err.println("Описание: " + e.getDescription());
            System.err.println("Ошибка возникает с позиции: " + e.getIndex());
            System.err.println("Шаблон, вызывающий ошибку: " + e.getPattern());
        }
    }
    public static void replaceHyperlink (String stringIn) {
        try {
            Pattern pattern = Pattern.compile("(?<!https?://)\\b\\S+\\.\\S+\\b");
            Matcher matcher = pattern.matcher(stringIn);
            //String url = matcher.group(); //IllegalStateException
            StringBuilder result = new StringBuilder();
            while (matcher.find()) {
                String url = matcher.group();
                //String url = matcher.group(20); //IndexOutOfBoundsException
                if (!url.matches("https?://.*")) {
                    url = "https://" + url;
                }
                matcher.appendReplacement(result, url);
            }
            matcher.appendTail(result);
            System.out.println(result);
        }
        catch (PatternSyntaxException e) {
            System.err.println("Ошибка регулярного выражения: " + e.getMessage());
            System.err.println("Описание: " + e.getDescription());
            System.err.println("Ошибка возникает с позиции: " + e.getIndex());
            System.err.println("Шаблон, вызывающий ошибку: " + e.getPattern());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Ошибка в вызове несуществующей группы: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Matcher не успел начать поиск / предыдущий поиск был неудачен: " + e.getMessage());
        }
    }
    public static void checkIP (String stringIn) {
        try {
            Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])$");
            Matcher matcher = pattern.matcher(stringIn);
            System.out.println(stringIn + " " + matcher.matches());
        }
        catch (PatternSyntaxException e) {
            System.err.println("Ошибка регулярного выражения: " + e.getMessage());
            System.err.println("Описание: " + e.getDescription());
            System.err.println("Ошибка возникает с позиции: " + e.getIndex());
            System.err.println("Шаблон, вызывающий ошибку: " + e.getPattern());
        }
    }
    public static void findLetterWord (String stringIn, char letter) {
        Pattern pattern = Pattern.compile("\\b" + String.valueOf(letter) + "(\\w+)?\\b", Pattern.CASE_INSENSITIVE);
        //Pattern pattern = Pattern.compile("\\b(?i)" + String.valueOf(letter) + "(\\w+)?\\b"); //(?i) - игнорирование регистра
        Matcher matcher = pattern.matcher(stringIn);
        System.out.println("Слова, начинающиеся с '" + letter + "':");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}