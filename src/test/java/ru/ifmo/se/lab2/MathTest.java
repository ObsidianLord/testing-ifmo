package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.ifmo.se.lab2.util.CustomMath;
import ru.ifmo.se.lab2.util.MathLib;

import java.io.IOException;

public class MathTest {
    private final static double DEFAULT_DELTA = 1e-3;
    private final static double EXTENDED_DELTA = 1e-6;
    private static MathLib mathLib;

    @BeforeClass
    public static void setUp() {
        mathLib = new CustomMath();
    }

    @Test
    public void testSin() throws IOException {
        double[][] params = new double[][]{
                {-3.78, 0.596, DEFAULT_DELTA},
                {10.41, -0.833, DEFAULT_DELTA},
                {0., 0., EXTENDED_DELTA},
                {-7 * Math.PI / 2, 1., EXTENDED_DELTA},
                {3 * Math.PI, 0., EXTENDED_DELTA},
                {Math.PI / 6, 0.5, EXTENDED_DELTA},
                {-5 * Math.PI / 2, -1., EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
        };

        for(double[] param: params) {
            Assert.assertEquals(param[1], mathLib.sin(param[0], param[2]), param[2]);
        }
    }

    @Test
    public void testCos() throws IOException {
        double[][] params = new double[][]{
                {-3.78, -0.803, DEFAULT_DELTA},
                {10.41, -0.553, DEFAULT_DELTA},
                {0., 1., EXTENDED_DELTA},
                {-7 * Math.PI / 2, 0., EXTENDED_DELTA},
                {-3 * Math.PI, -1., EXTENDED_DELTA},
                {Math.PI / 3, 0.5, EXTENDED_DELTA},
                {-5 * Math.PI / 2, -0., EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
        };

        for(double[] param: params) {
            Assert.assertEquals(param[1], mathLib.cos(param[0], param[2]), param[2]);
        }
    }

    @Test
    public void testTan() throws IOException {
        double[][] params = new double[][]{
                {-3.78, -0.742, DEFAULT_DELTA},
                {10.41, 1.508, DEFAULT_DELTA},
                {0., 0., EXTENDED_DELTA},
                {Math.PI / 4, 1., EXTENDED_DELTA},
                {-13 * Math.PI / 4, -1., EXTENDED_DELTA},
                {Math.PI / 2, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
        };

        for(double[] param: params) {
            Assert.assertEquals(param[1], mathLib.tan(param[0], param[2]), param[2]);
        }
    }

    @Test
    public void testCsc() throws IOException {
        double[][] params = new double[][]{
                {-3.78, 1.678, DEFAULT_DELTA},
                {10.41, -1.2, DEFAULT_DELTA},
                {-7 * Math.PI / 2, 1., EXTENDED_DELTA},
                {7 * Math.PI / 2, -1., EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {0., Double.POSITIVE_INFINITY, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
        };

        for(double[] param: params) {
            Assert.assertEquals(param[1], mathLib.csc(param[0], param[2]), param[2]);
        }
    }

    @Test
    public void testSec() throws IOException {
        double[][] params = new double[][]{
                {-3.78, -1.245, DEFAULT_DELTA},
                {10.41, -1.809, DEFAULT_DELTA},
                {0., 1., EXTENDED_DELTA},
                {-3 * Math.PI, -1., EXTENDED_DELTA},
                {5 * Math.PI, -1., EXTENDED_DELTA},
                {Math.PI / 2, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
                {Math.PI / 2, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
        };

        for(double[] param: params) {
            Assert.assertEquals(param[1], mathLib.sec(param[0], param[2]), param[2]);
        }
    }

    @Test
    public void testLog() throws IOException {
        double[][] params = new double[][]{
                {-3.78, Double.NaN, DEFAULT_DELTA},
                {10.41, 2.341, DEFAULT_DELTA},
                {0., Double.NEGATIVE_INFINITY, EXTENDED_DELTA},
                {1., 0, EXTENDED_DELTA},
                {Math.E, 1, EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
        };

        double[][] baseParams = new double[][]{
                {10, -3.78, Double.NaN, DEFAULT_DELTA},
                {10, 0., Double.NEGATIVE_INFINITY, EXTENDED_DELTA},
                {10, 1., 0, EXTENDED_DELTA},
                {10, 10., 1, EXTENDED_DELTA},
                {10, Double.NaN, Double.NaN, EXTENDED_DELTA},
                {10, Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {10, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
        };

        for(double[] param: params) {
            Assert.assertEquals(param[1], mathLib.log(param[0], param[2]), param[2]);
        }

        for(double[] param: baseParams) {
            Assert.assertEquals(param[2], mathLib.log(param[0], param[1], param[3]), param[3]);
        }
    }
}
