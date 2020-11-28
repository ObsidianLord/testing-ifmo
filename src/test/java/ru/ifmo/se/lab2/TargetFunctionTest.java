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
    private final TargetFunc targetFunc;

    public TargetFunctionTest(MathLib mathLib) {
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
                { mockLib },
                { myLib }
        });
    }

    @Test
    public void testCalculation() throws IOException {
        // x <= 0
        Assert.assertEquals(4.881, targetFunc.calculate(-0.6, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(0, targetFunc.calculate(-2.163, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(0, targetFunc.calculate(-3.989, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(-1.169, targetFunc.calculate(-3.82, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(Double.POSITIVE_INFINITY, targetFunc.calculate(0, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(Double.NaN, targetFunc.calculate(Double.NEGATIVE_INFINITY, DEFAULT_DELTA), DEFAULT_DELTA);

        // x > 0
        Assert.assertEquals(1.556, targetFunc.calculate(0.6, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(0, targetFunc.calculate(0.862, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(-0.046, targetFunc.calculate( 0.929, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(0, targetFunc.calculate(1, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(1.96, targetFunc.calculate(1.514, DEFAULT_DELTA), DEFAULT_DELTA);
        Assert.assertEquals(Double.POSITIVE_INFINITY, targetFunc.calculate(Double.POSITIVE_INFINITY, DEFAULT_DELTA), DEFAULT_DELTA);
    }
}