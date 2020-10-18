package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;

public class CustomHashMapTest {
    private static final int INITIAL_SIZE = 8;
    private static final double LOAD_FACTOR = 0.75;
    private static final double MULTIPLIER = 2.0;

    @Test
    public void testEmptyHashMap() {
        CustomHashMap <String, String> hashMap = new CustomHashMap<String, String>(
                100, LOAD_FACTOR, MULTIPLIER
        );
        Object[] arr = new Object[100];
        Assert.assertArrayEquals(arr, hashMap.getData());
        Assert.assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testHashMapPut() {
        CustomHashMap <String, String> hashMap = new CustomHashMap<String, String>(
                INITIAL_SIZE, LOAD_FACTOR, MULTIPLIER
        );
        hashMap.put("one","value1");
        hashMap.put("one","value2");
        hashMap.put("two","value3");
        hashMap.put("three","value4");

        String [] cube = hashMap.toCube1();
        Assert.assertArrayEquals(new String[]{
            "[]", "[]", "[]", "[]", "[two:value3]", "[]", "[one:value1, one:value2, three:value4]", "[]"
        }, cube);

        hashMap.clear();
        for (int i = 0; i < 1_000_000; i++) {
            hashMap.put(String.valueOf(i), "DUMMY");
        }

        Assert.assertEquals(1_000_000, hashMap.getSize());
    }

    @Test
    public void testHashMapGet() {
        CustomHashMap <String, String> hashMap = new CustomHashMap<String, String>(
                INITIAL_SIZE, LOAD_FACTOR, MULTIPLIER
        );
        hashMap.put("one","value1");
        hashMap.put("one","value2");
        hashMap.put("two","value3");
        hashMap.put("three","value4");

        Assert.assertEquals("value1", hashMap.get("one"));
        Assert.assertEquals("value3", hashMap.get("two"));
        Assert.assertEquals("value4", hashMap.get("three"));
    }

    @Test
    public void testHashMapRemove() {
        CustomHashMap <String, String> hashMap = new CustomHashMap<String, String>(
                INITIAL_SIZE, LOAD_FACTOR, MULTIPLIER
        );
        hashMap.put("one","value1");
        hashMap.put("one","value2");
        hashMap.put("two","value3");
        hashMap.put("three","value4");
        hashMap.remove("one");
        hashMap.remove("two");
        hashMap.remove("three");

        Assert.assertEquals("value2", hashMap.get("one"));
        Assert.assertEquals(null, hashMap.get("two"));
        Assert.assertEquals(null, hashMap.get("three"));
    }

    @Test
    public void testHashMapRehashUp() {
        CustomHashMap <Integer, String> hashMap = new CustomHashMap<Integer, String>(
                INITIAL_SIZE, LOAD_FACTOR, MULTIPLIER
        );
        for (int i = 0; i < 1_000_000; i++) {
            hashMap.put(i, "DUMMY");
        }

        Assert.assertEquals(4_194_304, hashMap.getArraySize()); // 8 * 2^19
    }
}
