package collections;

public class MyMap<K, V> {
    private Entry<K, V>[] entries;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyMap() {
        entries = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > entries.length) {
            int newCapacity = Math.max(entries.length * 2, minCapacity);
            @SuppressWarnings("unchecked")
            Entry<K, V>[] newEntries = new Entry[newCapacity];
            for (int i = 0; i < size; i++) {
                newEntries[i] = entries[i];
            }
            entries = newEntries;
        }
    }

    public void put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                entries[i].value = value;
                return;
            }
        }
        ensureCapacity(size + 1);
        entries[size++] = new Entry<>(key, value);
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                return entries[i].value;
            }
        }
        return null;
    }

    public V remove(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                V val = entries[i].value;
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    for (int j = i; j < size - 1; j++) {
                        entries[j] = entries[j + 1];
                    }
                }
                entries[--size] = null;
                return val;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public MyList<K> keys() {
        MyList<K> list = new MyList<>();
        for (int i = 0; i < size; i++) {
            list.add(entries[i].key);
        }
        return list;
    }

    static class Entry<K, V> {
        K key;
        V value;
        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }
}

