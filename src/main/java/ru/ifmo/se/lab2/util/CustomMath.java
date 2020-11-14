package ru.ifmo.se.lab2.util;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CustomMath implements MathLib {
    private final static HashMap<Integer, Double> cache = new HashMap<>();

    private static double factorial(int n) {
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

    private static double sum(Function<Integer, Double> func, double delta) {
        double result = 0.0, prevResult;

        int i = 0;
        do {
            prevResult = result;
            result += func.apply(i++);
        } while (Math.abs(prevResult - result) > delta);

        return result;
    }

    public double sin(double arg, double delta) {
        return sum(n -> Math.pow(-1, n) * Math.pow(arg, 2. * n + 1.) / factorial(2 * n + 1), delta);
    }

    public double log(double arg, double delta) {
        if (Double.isNaN(arg) || arg < 0) {
            return Double.NaN;
        }

        if (arg == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        if (arg == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        return 2. * (arg - 1.) / (arg + 1.) *
                sum(n -> Math.pow(Math.pow(arg - 1, 2) / Math.pow(arg + 1, 2), n) / (2. * n + 1.), delta);
    }

    public double log(double base, double arg, double delta) {
        return log(arg, delta) / log(base, delta);
    }

    public double cos(double arg, double delta) {
        return sin(Math.PI / 2. - arg, delta);
    }

    public double tan(double arg, double delta) {
        return sin(arg, delta) / cos(arg, delta);
    }

    public double csc(double arg, double delta) {
        return 1. / sin(arg, delta);
    }

    public double sec(double arg, double delta) {
        return 1. / cos(arg, delta);
    }

    public double calculate(BiFunction<Double, Double, Double> func, double arg, double delta) {
        return func.apply(arg, delta * delta);
    }
}