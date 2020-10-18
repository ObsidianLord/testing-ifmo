package ru.ifmo.se.lab1.util.math;

import ru.ifmo.se.lab1.CustomHashMap;
import static ru.ifmo.se.lab1.util.math.CachedFactorial.factorial;

public class Bernoulli {
    private final static CustomHashMap<Integer, Double> cache = new CustomHashMap<>(100, 0.75, 2.0);

    public static double bernoulli(int n) {
        if (n == 0) {
            return 1.;
        }
        if (n == 1)
            return -0.5;
        if (n % 2 == 1) {
            return 0.;
        }


        Double result = cache.get(n);
        if (result != null) {
            return result;
        }

        result = 0.;

        for (int i = 1; i <= n; i++) {
            result += bernoulli(n - i) * binom(n + 1, i + 1);
        }

        result /= -n - 1;

        cache.put(n, result);
        return result;
    }

    private static double binom(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}
