package ru.ifmo.se.lab1.util.math;

import ru.ifmo.se.lab1.CustomHashMap;

public class CachedFactorial {
    private final static CustomHashMap<Integer, Double> cache = new CustomHashMap<>(100, 0.75, 2.0);

    public static double factorial(int n) {
        Double result;

        if (n == 0) {
            return 1.;
        }

        result = cache.get(n);
        if (result != null) {
            return cache.get(n);
        }

        result = (double) n * factorial(n - 1);
        cache.put(n, result);
        return result;
    }
}