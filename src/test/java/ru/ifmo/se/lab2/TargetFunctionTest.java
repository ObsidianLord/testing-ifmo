package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.ifmo.se.lab2.util.CustomMath;
import ru.ifmo.se.lab2.util.MathLib;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;

@RunWith(Parameterized.class)
public class TargetFunctionTest {
    private final static double DEFAULT_DELTA = 1e-3;

    private final double arg;
    private final double expected;
    private final double delta;
    private final TargetFunc targetFunc;

    public TargetFunctionTest(MathLib mathLib, double arg, double expected, double delta) {
        this.arg = arg;
        this.expected = expected;
        this.delta = delta;
        this.targetFunc = new TargetFunc(mathLib);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        MathLib mockLib = new MathLib() {
            @Override
            public double sin(double arg, double delta) throws IOException {
                return Math.sin(arg);
            }

            @Override
            public double log(double arg, double delta) throws IOException {
                return Math.log(arg);
            }

            @Override
            public double log(double base, double arg, double delta) throws IOException {
                return Math.log(arg) / Math.log(base);
            }

            @Override
            public double cos(double arg, double delta) throws IOException {
                return Math.cos(arg);
            }

            @Override
            public double tan(double arg, double delta) throws IOException {
                return Math.tan(arg);
            }

            @Override
            public double csc(double arg, double delta) throws IOException {
                return 1 / Math.sin(arg);
            }

            @Override
            public double sec(double arg, double delta) throws IOException {
                return 1 / Math.cos(arg);
            }

            @Override
            public double calculate(BiFunction<Double, Double, Double> func, double arg, double delta) throws IOException {
                return func.apply(arg, delta * delta);
            }
        };
        MathLib myLib = new CustomMath();
        return Arrays.asList(new Object[][]{
                // Mock x <= 0
                {mockLib, -0.6, 4.881, DEFAULT_DELTA},
                {mockLib, -2.163, 0, DEFAULT_DELTA},
                {mockLib, -3.989, 0, DEFAULT_DELTA},
                {mockLib, -3.82, -1.169, DEFAULT_DELTA},
                {mockLib, 0, Double.POSITIVE_INFINITY, DEFAULT_DELTA},
                {mockLib, Double.NEGATIVE_INFINITY, Double.NaN, DEFAULT_DELTA},

                // Mock x > 0
                {mockLib, 0.6, 1.556, DEFAULT_DELTA},
                {mockLib, 0.862, 0, DEFAULT_DELTA},
                {mockLib, 0.929, -0.046, DEFAULT_DELTA},
                {mockLib, 1, 0, DEFAULT_DELTA},
                {mockLib, 1.514, 1.96, DEFAULT_DELTA},
                {mockLib, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, DEFAULT_DELTA},

                // Custom x <= 0
                {myLib, -0.6, 4.881, DEFAULT_DELTA},
                {myLib, -2.163, 0, DEFAULT_DELTA},
                {myLib, -3.989, 0, DEFAULT_DELTA},
                {myLib, -3.82, -1.169, DEFAULT_DELTA},
                {myLib, 0, Double.POSITIVE_INFINITY, DEFAULT_DELTA},
                {myLib, Double.NEGATIVE_INFINITY, Double.NaN, DEFAULT_DELTA},

                // Custom x > 0
                {myLib, 0.6, 1.556, DEFAULT_DELTA},
                {myLib, 0.862, 0, DEFAULT_DELTA},
                {myLib, 0.929, -0.046, DEFAULT_DELTA},
                {myLib, 1, 0, DEFAULT_DELTA},
                {myLib, 1.514, 1.96, DEFAULT_DELTA},
                {myLib, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, DEFAULT_DELTA},
        });
    }

    @Test
    public void testCalculation() throws IOException {
        Assert.assertEquals(expected, targetFunc.calculate(arg, delta), delta);
    }
}