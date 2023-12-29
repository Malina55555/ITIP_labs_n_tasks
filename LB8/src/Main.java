import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.loadData("input.txt");
        dataManager.registerDataProcessor(new FilterDataProcessor());
        dataManager.registerDataProcessor(new TransformDataProcessor());
        dataManager.processData();
        dataManager.saveData("output.txt");
    }
}

class FilterDataProcessor {
    @DataProcessor
    public LinkedList<String> sortLexicographically(LinkedList<String> data) {
        return data.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toCollection(LinkedList::new));
    }
    @DataProcessor
    public LinkedList<String> filterByLength(LinkedList<String> data, int minLength, int maxLength) {
        return data.stream()
                .filter(s -> s.length() >= minLength && s.length() <= maxLength)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}

class TransformDataProcessor {
    @DataProcessor
    public LinkedList<String> transformData(LinkedList<String> data) {
        return data.stream()
                .map(s -> new StringBuilder(s).reverse().toString())
                .collect(Collectors.toCollection(LinkedList::new));
    }
}

