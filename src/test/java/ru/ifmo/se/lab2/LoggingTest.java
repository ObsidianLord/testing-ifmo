package ru.ifmo.se.lab2;

import org.junit.Test;
import org.mockito.Mockito;
import ru.ifmo.se.lab2.util.CustomMath;
import ru.ifmo.se.lab2.util.Logger;
import ru.ifmo.se.lab2.util.LoggingMath;
import ru.ifmo.se.lab2.util.MathLib;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.function.BiFunction;

import static ru.ifmo.se.lab2.util.Logger.FUNC_MODULE;

public class LoggingTest {
    @Test
    public void testLogging() throws IOException {
        BiFunction<Double, Double, Double> func = (arg, delta) -> 1984.0;
        double arg = 2020.0;
        double delta = 0.001;

        MathLib mathLib = Mockito.mock(MathLib.class);
        Mockito.when(mathLib.calculate(func, arg, delta)).thenReturn(42.0);

        Logger logger = new Logger();
        HashMap<String, Writer> writers = new HashMap<>();

        Writer writer = Mockito.mock(Writer.class);
        writers.put(FUNC_MODULE, writer);

        logger.setWriters(writers);
        logger.setLogging(FUNC_MODULE);

        LoggingMath loggingMath = new LoggingMath(new CustomMath(), logger);

        loggingMath.calculate(func, arg, delta);
        Mockito.verify(writer, Mockito.times(1)).write("2020.000,1984.000\n");
    }
}