package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.PI;

@RunWith(Parameterized.class)
public class CustomMathTest {
    private final static double DEFAULT_DELTA = 1e-12;
    private final double arg;

    public CustomMathTest(double arg) {
        this.arg = arg;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {Double.MIN_VALUE},
                {-Double.MIN_VALUE},
                {Double.NEGATIVE_INFINITY},
                {Double.POSITIVE_INFINITY},
                {Double.NaN},
                {-PI / 4},
                {PI / 4},
                {-5.5555},
                {5.5555},
        });
    }

    @Test
    public void test() {
        Assert.assertEquals(Math.tan(arg), CustomMath.tan(arg, DEFAULT_DELTA), DEFAULT_DELTA);
    }
}
