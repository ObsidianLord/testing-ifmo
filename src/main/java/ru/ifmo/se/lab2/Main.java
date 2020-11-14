package ru.ifmo.se.lab2;

import ru.ifmo.se.lab2.util.CustomMath;
import ru.ifmo.se.lab2.util.Logger;
import ru.ifmo.se.lab2.util.LoggingMath;

import java.io.IOException;

import static ru.ifmo.se.lab2.util.Logger.*;

public class Main {
    private final static double DELTA = 1e-3;
    private final static double LOW_ARG = -5.;
    private final static double HIGH_ARG = 5.;
    private final static double STEP = 1e-1;

    public static void main(String[] args) throws IOException {
        Logger logger = new Logger();
        logger.setLoggingAll();

        LoggingMath loggingMath = new LoggingMath(new CustomMath(), logger);
        TargetFunc targetFunc = new TargetFunc(loggingMath);

        for (double i = LOW_ARG; i <= HIGH_ARG; i += STEP) {
            targetFunc.calculate(i, DELTA);
        }

        logger.closeWriters();
    }
}
