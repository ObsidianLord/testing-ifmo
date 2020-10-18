package ru.ifmo.se.lab1;

import static java.lang.Math.*;
import static ru.ifmo.se.lab1.util.math.Bernoulli.bernoulli;
import static ru.ifmo.se.lab1.util.math.CachedFactorial.factorial;

public class CustomMath {
    public static double tan(double x, double delta) {
        if (Double.isNaN(x) || Double.isInfinite(x))
            return Double.NaN;
        x %= PI;
        if (abs(x) > PI / 2 && x < 0) {
            x += PI;
        }
        if (abs(x) > PI / 2 && x > 0) {
            x -= PI;
        }

        double result = 0;
        double step = 0;
        int i = 1;
        do {
            step = bernoulli(2 * i) * pow(-4, i) * (1 - pow(4, i)) / factorial(2 * i) * pow(x, 2 * i - 1);
            result += step;
            if (abs(result) > 1.6e16) {
                result = Double.POSITIVE_INFINITY;
                break;
            }
            i += 1;
        } while (abs(step) > delta);
        return result;
    }
}