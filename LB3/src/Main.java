import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("---2 задание---");
        HashTable<Integer, Order> orders = new HashTable<>();
        String[] dishes1=  {"Салат Цезарь", "Борщ"};
        String[] dishes2=  {"Стейк Говядина Медиум"};
        orders.put(1, new Order(dishes1, 320, "18:56:23 07/09/2023"));
        orders.put(2, new Order(dishes1, 320, "10:24:09 08/09/2023"));
        orders.put(3, new Order(dishes2, 560, "11:09:45 09/09/2023"));
        System.out.println("Инициализируем новый HashTable:");
        System.out.println(orders);
        System.out.println("Элемент HashTable с ключом 2: " + orders.get(2));
        System.out.println("Удаляем элемент из HashTable с ключом 2:");
        orders.remove(2);
        System.out.println(orders);
        System.out.println("Текущий размер HashTable: " + orders.size());
        System.out.println("Пуст ли сейчас HashTable? " + orders.isEmpty());
        orders.remove(1);
        orders.remove(3);
        System.out.println("Пуст ли сейчас HashTable? " + orders.isEmpty());
        System.out.println("Текущий размер HashTable: " + orders.size());
    }
}
class Order {
    public String[] dishes;
    public int cost;
    public String timeOfOrder;
    public Order(String[] dishes, int cost, String timeOfOrder) {
        this.dishes=dishes;
        this.cost = cost;
        this.timeOfOrder = timeOfOrder;
    }
    @Override
    public String toString() {
        return "Order{dishes=" + Arrays.toString(dishes) + ", cost=" + cost + ", timeOfOrder='" + timeOfOrder + "'}";
    }
}