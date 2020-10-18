package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

@RunWith(Parameterized.class)
public class CustomMathTestInfinity {
    private final static double DEFAULT_DELTA = 1e-12;
    private final double arg;
    private final double expected;

    public CustomMathTestInfinity(double arg, double expected) {
        this.arg = arg;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {PI / 2., Math.tan(PI / 2.)},
                {-PI / 2., Math.tan(-PI / 2.)},
                {5 * PI / 2., Math.tan(5 * PI / 2.)},
                {- 5 * PI / 2., Math.tan(- 5 * PI / 2.)},
        });
    }

    @Test
    public void test() {
        Assert.assertTrue(CustomMath.tan(arg, DEFAULT_DELTA) > abs(expected));
    }
}
