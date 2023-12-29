import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }
    public static void task1() {
        System.out.println("----задание 1----");
        String filePath = "text.txt";
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Integer> map = new HashMap<>();
        for (Scanner it = scanner; it.hasNext(); ) {
            String str = it.next().toLowerCase().replaceAll("[^a-zA-Zа-яА-Я]", "");
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.replace(str, map.get(str)+1);
            }
        }
        scanner.close();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        /*
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
         */

        System.out.println("Топ-10 самых часто встречающихся слов:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (count < 10) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " раз");
                count++;
            } else {
                break;
            }
        }
    }
    public static class Stack<T> {
        private T[] data;
        private int size;
        public Stack(int capacity) {
            data = (T[]) new Object[capacity];
            size = 0;
        }
        public Stack() {    this(15);    }
        public void push(T element) {
            if (this.size == this.data.length) {
                this.data = Arrays.copyOf(this.data, this.data.length+1);
            }
            this.data[this.size]=element;
            this.size++;
        }
        public T pop() {
            if (this.size == 0) {
                throw new EmptyStackException();
            }
            this.size--;
            T popped = this.data[this.size];
            this.data[this.size]=null;
            return popped;
        }
        public T peek() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            return this.data[this.size-1];
        }
    }
    public static void task2() {
        System.out.println("----задание 2----");
        Stack<Integer> stack = new Stack<>(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());
    }
    public static class Item {
        private String name;
        private double price;
        public String getName() {
            return name;
        }
        public double getPrice() {
            return price;
        }
        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
        public String readItem() {
            return "Товар \""+this.name+"\" Цена: "+this.price+" дублей";
        }
    }
    public static class SalesManager {
        private CopyOnWriteArrayList<Item> sales;
        public SalesManager() {
            this.sales = new CopyOnWriteArrayList<>();
        }
        public void addSaleItem(Item item) {
            sales.add(item);
        }
        public void displaySales() {
            for (Item item : sales) {
                System.out.println("Продан товар: " + item.getName() + " по цене: " + item.getPrice());
            }
        }
        public double getTotalSalesAmount() {
            double total = 0;
            for (Item item : sales) {
                total += item.getPrice();
            }
            return total;
        }
        public String getMostPopularItem() {
            Map<String, Integer> itemCounts = new HashMap<>();
            for (Item item : sales) {
                String itemName = item.getName();
                int count = itemCounts.getOrDefault(itemName, 0);
                itemCounts.put(itemName, count + 1);
            }
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                }
            }
                String result = "";
                for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {if (entry.getValue()==maxCount) {result = result + entry.getKey()+ " ";}}
                return result;
        }
    }
    public static void task3() {
        System.out.println("----задание 3----");
        SalesManager manager = new SalesManager();

        manager.addSaleItem(new Item("Арбуз", 20.0));
        manager.addSaleItem(new Item("Бананы", 15.25));
        manager.addSaleItem(new Item("Виноград", 10.0));
        manager.addSaleItem(new Item("Арбуз", 20.0));
        manager.addSaleItem(new Item("Бананы", 10.25));
        manager.displaySales();
        double totalSales = manager.getTotalSalesAmount();
        System.out.println("Общая сумма продаж: " + totalSales);
        String popularItem = manager.getMostPopularItem();
        System.out.println("Самый популярный товар: " + popularItem);

        Item[] newItems = {
                new Item("Груши", 15.0),
                new Item("Дыня", 25.75),
                new Item("Ежевика", 20.10)
        };

        Thread writerThread = new Thread(() -> {
            for (Item item : newItems) {
                manager.addSaleItem(item);
                System.out.println("Added: "+item.readItem());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readerThread = new Thread(() -> {
            for (Item item : manager.sales) {
                System.out.println("Read: " + item.readItem());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        writerThread.start();
        readerThread.start();
    }
}




