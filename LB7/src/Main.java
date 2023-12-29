import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int NUM_THREADS = 4;
    private static int[][] matrix = {
            {1, 2, 7, 8, 9},
            {56,57,58,59,50},
            {23,89,56,78,10},
            {34,98,78,56,13},
            {99,34,55,88,101}
    };

    public static void main(String[] args) {
//        task1();
//        task2();
        task3();
    }
    public static void task1() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS); //создаст нам ExecutorService с фиксированным количеством потоков

        int chunkSize = array.length / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int finish = (i == NUM_THREADS - 1) ? array.length : (i + 1) * chunkSize;
            int[] chunk = new int[finish - start];
            System.arraycopy(array, start, chunk, 0, finish - start);
            executor.execute(new ArraySumTask(chunk)); //начать выполнять
        }

        executor.shutdown(); //остановка Executor
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Ждем завершения всех потоков long timeout, TimeUnit unit
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); //иначе прервать поток
        }

        System.out.println("Итоговая сумма массива: " + ArraySumTask.getTotalSum());
    }
    public static class ArraySumTask implements Runnable {
        private int[] arrayChunk;
        private static int totalSum = 0;
        private static final Object lock = new Object();
        //Когда мы используем synchronized (lock) {...}, мы гарантируем, что операции, которые выполняются в блоке кода,
        // берут "блокировку" объекта lock. Таким образом, только один поток сможет получить доступ к этому блоку кода в данное время.
        // Это обеспечивает безопасную многопоточность при выполнении операций сложения totalSum в классе ArraySumTask.

        public ArraySumTask(int[] arrayChunk) {
            this.arrayChunk = arrayChunk;
        }

        @Override
        public void run() {
            int localSum = 0;
            for (int number : arrayChunk) {
                localSum += number;
            }
            synchronized (lock) {
                totalSum += localSum; //сложение происходит в единственном потоке (главном)
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static int getTotalSum() {
            return totalSum;
        }
    }
    public static void task2() {
        ExecutorService executor = Executors.newFixedThreadPool(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            int[] chunk = matrix[i];
            executor.execute(new ArrayMaxTask(chunk)); //начать выполнять
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Наибольший элемент матрицы: " + ArrayMaxTask.getTotalMax());
    }
    public static class ArrayMaxTask implements Runnable {
        private int[] arrayChunk;
        private static int maxInArray = 0;
        private static final Object lock = new Object();
        public ArrayMaxTask(int[] arrayChunk) {
            this.arrayChunk = arrayChunk;
        }
        @Override
        public void run() {
            int max = -1000000000;
            for (int number : arrayChunk) {
                max = Math.max(max, number);
            }
            synchronized (lock) {
                maxInArray = Math.max(max, maxInArray);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public static int getTotalMax() {
            return maxInArray;
        }
    }
    public static class Storage{
        public static final Object lock = new Object();
        static AtomicInteger totalWeight = new AtomicInteger(0); // текущий вес товаров в грузовике
        public static int[] weights = {2, 3, 3, 5, 7, 14, 45, 57, 63, 68, 71, 39, 96, 69, 5, 73, 44, 11, 45, 92, 59,
                82, 26, 56, 49, 67, 11, 49, 69, 1, 49, 11, 30, 16, 22, 97, 34, 61, 11, 86};// вес товаров на складе
    }
    public static void task3() {

        int numLoaders = 3; // количество грузчиков

        ExecutorService executorService = Executors.newFixedThreadPool(numLoaders); // управляет потоками
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        int chunk = Storage.weights.length / numLoaders; // для разделения областей склада между грузчиками

        for (int i = 0; i < numLoaders; i++) { // распределяем области между грузчиками
            int startIndex = i * chunk;
            int endIndex = (i == numLoaders - 1) ? Storage.weights.length - 1 : (i + 1) * chunk - 1;
            completionService.submit(new Loader(Storage.weights, startIndex, endIndex, "Грузчик " + (i+1)));
            // инициализируем грузчиков
        }

        for (int i = 0; i < numLoaders; i++) {
            try {
                Future<Integer> future = completionService.take(); //получает состояние грузчиков
                System.out.println("Состояние Грузчика: " + (i + 1) + " --- " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Вес товаров в грузовике на момент последнего рейса: " + Storage.totalWeight.get() + " кг");
        executorService.shutdown();
    }
    static class Loader implements Callable<Integer> {
        private int[] weights;
        private int startIndex;
        private int endIndex;
        private String name;

        public Loader(int[] weights, int startIndex, int endIndex, String name){
            this.weights = weights;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.name = name;
        }
        @Override
        public Integer call() {
            for (int i = startIndex; i <= endIndex; i++) { // для каждого груза из его части склада
                synchronized (Storage.lock) {
                    if (Storage.totalWeight.get() + weights[i] <= 150) { // если в грузовик товар вмещается
                        Storage.totalWeight.addAndGet(weights[i]); // увеличиваем вес в грузовике
                        System.out.println(name + " загружает в машину " + weights[i] + " кг");
                    } else {
                        System.out.println("---Вес товаров в грузовике на момент текущего рейса "+ Storage.totalWeight.get() + " кг---");
                        System.out.println(name + " видит, что его товар в грузовик не влезет, поэтому товар весом "+ weights[i] + " кг оставляет на следующую загрузку");
                        Storage.totalWeight.set(weights[i]);
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return 1; //когда грузчик всё закончит, пусть возвращает 1
        }
    }
}