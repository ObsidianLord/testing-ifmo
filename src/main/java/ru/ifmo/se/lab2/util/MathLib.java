package ru.ifmo.se.lab2.util;

import java.io.IOException;
import java.util.function.BiFunction;

public interface MathLib {
    double sin(double arg, double delta) throws IOException;
    double log(double arg, double delta) throws IOException;
    double log(double base, double arg, double delta) throws IOException;
    double cos(double arg, double delta) throws IOException;
    double tan(double arg, double delta) throws IOException;
    double csc(double arg, double delta) throws IOException;
    double sec(double arg, double delta) throws IOException;
    double calculate(BiFunction<Double, Double, Double> func, double arg, double delta) throws IOException;
}
