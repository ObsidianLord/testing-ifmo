package ru.ifmo.se.lab2;

import ru.ifmo.se.lab2.util.MathLib;

import java.io.IOException;
import java.util.function.BiFunction;

public class TargetFunc {
    private MathLib mathLib;

    public TargetFunc(MathLib mathLib) {
        this.mathLib = mathLib;
    }

    public BiFunction<Double, Double, Double> getFunc() {
        return (Double arg, Double delta) -> {
            double result = Double.NaN, extendedDelta = delta * delta;

            try {
                if (arg <= 0) {
                    result = Math.pow(mathLib.cos(arg, extendedDelta), 2) / mathLib.cos(arg, extendedDelta) / mathLib.cos(arg, extendedDelta)
                            + (Math.pow(mathLib.sin(arg, extendedDelta), 2))
                            - (
                            mathLib.sin(arg, extendedDelta) / mathLib.csc(arg, extendedDelta) * mathLib.cos(arg, extendedDelta) * mathLib.sin(arg, extendedDelta)
                                    - (mathLib.csc(arg, extendedDelta) + mathLib.sin(arg, extendedDelta)) / mathLib.tan(arg, extendedDelta)
                                    + (mathLib.tan(arg, extendedDelta) - mathLib.tan(arg, extendedDelta)) / (mathLib.csc(arg, extendedDelta) - mathLib.sec(arg, extendedDelta))
                    );
                } else {
                    result = (mathLib.log(3, arg, extendedDelta) + mathLib.log(arg, extendedDelta) + mathLib.log(arg, extendedDelta))
                            * (mathLib.log(2, arg, extendedDelta) + mathLib.log(2, arg, extendedDelta))
                            + mathLib.log(5, arg, extendedDelta)
                            + mathLib.log(5, arg, extendedDelta);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        };
    }

    public double calculate(double arg, double delta) throws IOException {
        return mathLib.calculate(getFunc(), arg, delta);
    }
}
