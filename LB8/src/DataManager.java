import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.reflect.Method;
import java.util.concurrent.*;

public class DataManager {
    LinkedList<Object> processors = new LinkedList<>();
    private LinkedList<String> fileWords = new LinkedList<>();
    private static final Object lock = new Object();
    HashMap<String, LinkedList<String>> hashMapOfResults = new HashMap<>();
    public DataManager() {}
    public void loadData(String source) {
        LinkedList<String> wordsList = new LinkedList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(source));
            String line;
            while ((line = br.readLine()) != null) { //технически, это итератор для чтения файла по строчкам
                String[] words = line.split("\\s+");  // Разделение строки на слова по пробельным символам
                for (String word : words) {
                    // Фильтрация слов, чтобы оставить только буквы (кириллица и латиница любого регистра)
                    String filteredWord = word.replaceAll("[^\\p{IsAlphabetic}]", "");
                    if (!filteredWord.isEmpty()) {
                        wordsList.add(filteredWord);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Ошибка чтения из файла: " + e.getMessage());
        }
        this.fileWords = wordsList;
    }
    public void saveData(String destination) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destination, true));
            for (String str : hashMapOfResults.keySet()) {
                bufferedWriter.write(str + ": " + Arrays.toString(hashMapOfResults.get(str).toArray()) + "\n\n");  // Запись обработанных данных в файл
            }
            bufferedWriter.write("\n\n\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Ошибка сохранения файла: " + e.getMessage());
        }
    }
    public void registerDataProcessor(Object processor) {
        for(Method method : processor.getClass().getDeclaredMethods()) {
            if(method.isAnnotationPresent(DataProcessor.class)) {
                processors.add(method);
            }
        }
    }
    public void processData() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<LinkedList<String>> completionService = new ExecutorCompletionService<>(executor);
        FilterDataProcessor filterDataProcessor = new FilterDataProcessor();
        TransformDataProcessor transformDataProcessor = new TransformDataProcessor();
        //переворачиваем слова, длиной меньше или равно 5 символам
        completionService.submit(() -> {
            LinkedList<String> filteredData1 = filterDataProcessor.filterByLength(this.fileWords, 1, 5);
            LinkedList<String> transformedData1 = transformDataProcessor.transformData(filteredData1);
            synchronized (lock) {
                this.hashMapOfResults.put("reverseLowThen5", transformedData1);
            }
            return transformedData1;
        });
        //фильтруем слова длиннее 7 букв
        completionService.submit(() -> {
            LinkedList<String> filteredData2 = filterDataProcessor.filterByLength(this.fileWords, 7, Integer.MAX_VALUE);
            synchronized (lock) {
                this.hashMapOfResults.put("filterHighThen7", filteredData2);
            }
            return filteredData2;
        });
        //сортировка лексикографически
        completionService.submit(() -> {
            LinkedList<String> sortedData = filterDataProcessor.sortLexicographically(this.fileWords);
            synchronized (lock) {
                this.hashMapOfResults.put("sortLexicographically", sortedData);
            }
            return sortedData;
        });

        for (int i = 0; i < 3; i++) {
            try {
                Future<LinkedList<String>> result = completionService.take();
            } catch (InterruptedException e) {
                System.err.println("Проблема обработки результата: " + e.getMessage());
            }
        }
        executor.shutdown();
    }
}
