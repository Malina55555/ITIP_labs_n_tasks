import java.util.LinkedList;
public class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    public HashTable() {
        this(10);
    }
    public HashTable(int sizeIn) {
        table = new LinkedList[sizeIn];
        size = 0;
    }
    public void put(K keyIn, V valueIn) {
        int index = (keyIn.hashCode()%10>0? keyIn.hashCode()%10: -keyIn.hashCode()%10);
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(keyIn)) {
                entry.setValue(valueIn);
                return;
            }
        }
        table[index].add(new Entry<>(keyIn, valueIn));
        size++;
    }
    public V get(K keyIn) {
        int index = (keyIn.hashCode()%10>0? keyIn.hashCode()%10: -keyIn.hashCode()%10);
        LinkedList<Entry<K, V>> list = table[index];
        if (list != null) {
            for (Entry<K, V> entry : list) {
                if (entry.getKey().equals(keyIn)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
    public void remove(K keyIn) {
        int index = (keyIn.hashCode()%10>0? keyIn.hashCode()%10: -keyIn.hashCode()%10);
        LinkedList<Entry<K, V>> list = table[index];
        if (list == null) {return;};
        for (Entry<K, V> entry : list) {
            if (entry.getKey().equals(keyIn)) {
                list.remove(entry);
                size--;
            }
        }
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    private static class Entry<K, V> {
        private final K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
    }
    @Override
    public String toString() {
        String result = "{ ";
        for (int i = 0; i < this.table.length; i++) {
            result += "Index " + i + ": [";
            if (!(this.table[i] ==null)) {
                for (int j = 0; j < this.table[i].size(); j++) {
                    result += this.table[i].get(j).key + " = " + this.table[i].get(j).value + "; ";
                }
            }
                result += "]; \n";
        }
        result += "} \n";
        return result;
    }
}
