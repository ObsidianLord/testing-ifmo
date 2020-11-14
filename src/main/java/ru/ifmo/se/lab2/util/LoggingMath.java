package ru.ifmo.se.lab2.util;

import java.io.IOException;
import java.util.function.BiFunction;

import static ru.ifmo.se.lab2.util.Logger.*;

public class LoggingMath implements MathLib {
    private CustomMath customMath;
    private Logger logger;

    public LoggingMath(CustomMath customMath, Logger logger) {
        this.customMath = customMath;
        this.logger = logger;
    }

    public double sin(double arg, double delta) throws IOException {
        double result = customMath.sin(arg, delta);
        logger.save(SIN_MODULE, arg, result);
        return result;
    }

    public double log(double arg, double delta) throws IOException {
        double result = customMath.log(arg, delta);
        logger.save(LOG_MODULE, arg, result);
        return result;
    }

    public double log(double base, double arg, double delta) throws IOException {
        double result = customMath.log(base, arg, delta);
        logger.save(LOG_MODULE, base, arg, result);
        return result;
    }

    public double cos(double arg, double delta) throws IOException {
        double result = customMath.cos(arg, delta);
        logger.save(COS_MODULE, arg, result);
        return result;
    }

    public double tan(double arg, double delta) throws IOException {
        double result = customMath.tan(arg, delta);
        logger.save(TAN_MODULE, arg, result);
        return result;
    }

    public double csc(double arg, double delta) throws IOException {
        double result = customMath.csc(arg, delta);
        logger.save(CSC_MODULE, arg, result);
        return result;
    }

    public double sec(double arg, double delta) throws IOException {
        double result = customMath.sec(arg, delta);
        logger.save(SEC_MODULE, arg, result);
        return result;
    }

    public double calculate(BiFunction<Double, Double, Double> func, double arg, double delta) throws IOException {
        double result = customMath.calculate(func, arg, delta);
        logger.save(FUNC_MODULE, arg, result);
        return result;
    }
}
