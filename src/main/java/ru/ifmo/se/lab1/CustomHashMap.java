package ru.ifmo.se.lab1;

import ru.ifmo.se.lab1.util.hashmap.CustomEntry;
import ru.ifmo.se.lab1.util.hashmap.CustomNode;

import java.util.Arrays;

public class CustomHashMap<K, V> {
    private CustomNode<K, V>[] data;
    private int size;
    private int arraySize;
    private int limit;
    private final double loadFactor;
    private final double multiplier;

    public CustomHashMap(int initialSize, double loadFactor, double multiplier) {
        this.data = new CustomNode[initialSize];
        this.size = 0;
        this.arraySize = initialSize;
        this.limit = (int) (initialSize * loadFactor);
        this.loadFactor = loadFactor;
        this.multiplier = multiplier;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public CustomNode<K, V>[] getData() {
        return data;
    }

    public int getSize() {
        return size;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void put(K key, V value) {
        rawPut(new CustomEntry<K, V>(key, value), this.data);
    }

    public V get(K key) {
        final int hashCode = key.hashCode();
        final int index = hashCode % arraySize;
        V result = null;
        CustomNode<K, V> anchor = data[index];
        if (anchor != null) {
            if (anchor.getValue().getKey() == key) {
                result = anchor.getValue().getValue();
            } else {
                while ((anchor = anchor.getNext()) != null) {
                    if (anchor.getValue().getKey() == key) {
                        result = anchor.getValue().getValue();
                        break;
                    }
                }
            }
        }
        return result;
    }

    public void clear() {
        this.data = new CustomNode[arraySize];
        this.size = 0;
    }

    public void remove(K key) {
        final int hashCode = key.hashCode();
        final int index = hashCode % arraySize;
        CustomNode<K, V> anchor = data[index];
        if (anchor != null) {
            if (anchor.getValue().getKey() == key) {
                data[index] = anchor.getNext();
            } else {
                CustomNode<K, V> prevAnchor = anchor;
                while ((anchor = anchor.getNext()) != null) {
                    if (anchor.getValue().getKey() == key) {
                        prevAnchor.setNext(anchor.getNext());
                        break;
                    }
                    prevAnchor = anchor;
                }
            }
        }
    }

    private void rawPut(CustomEntry<K, V> entry, CustomNode[] data) {
        final int hashCode = entry.getKey().hashCode();
        final int index = hashCode % arraySize;
        if (size < limit) {
            final CustomNode newNode = new CustomNode(entry, null);
            if (data[index] == null) {
                data[index] = newNode;
                size += 1;
            } else {
                CustomNode anchor = data[index];
                while (anchor.getNext() != null) {
                    anchor = anchor.getNext();
                }
                anchor.setNext(newNode);
                size += 1;
            }
        } else {
            rehashUp(entry);
        }
    }

    private void rehashUp(CustomEntry<K, V> entry) {
        int oldArraySize = arraySize;
        size = 0;
        arraySize = (int) (oldArraySize * multiplier);
        limit = (int) (arraySize * loadFactor);
        CustomNode<K, V>[] newData = new CustomNode[arraySize];
        for (int i = 0; i < oldArraySize; i++) {
            CustomNode anchor = data[i];
            if (anchor != null) {
                rawPut(anchor.getValue(), newData);
                while ((anchor = anchor.getNext()) != null) {
                    rawPut(anchor.getValue(), newData);
                }
            }
        }
        data = newData;
        rawPut(entry, newData);
    }

    public String[][] toCube() {
        final String[][] cube = new String[arraySize][size];
        for (int i = 0; i < arraySize; i++) {
            int j = 0;
            CustomNode<K, V> anchor;
            if ((anchor = data[i]) != null) {
                cube[i][j] = anchor.toString();
                CustomNode<K, V> nextAnchor = anchor;
                while ((nextAnchor = nextAnchor.getNext()) != null) {
                    j += 1;
                    cube[i][j] = nextAnchor.toString();
                }
            }
        }

        return cube;
    }

    public String[] toCube1() {
        final String[][] cube2 = toCube();
        final String[] cube1 = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            String str = "[";
            boolean notEmpty = false;
            for (int j = 0; j < cube2[i].length; j++) {
                if (cube2[i][j] != null) {
                    if (notEmpty) {
                        str += ", ";
                    } else {
                        notEmpty = true;
                    }
                    str += cube2[i][j];
                }
            }
            str += "]";
            cube1[i] = str;
        }

        return cube1;
    }

    @Override
    public String toString() {
        final String[][] cube2 = toCube();
        final String[] cube1 = toCube1();
        return Arrays.toString(cube1);
    }
}
