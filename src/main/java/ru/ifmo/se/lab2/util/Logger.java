package ru.ifmo.se.lab2.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Logger {
    public final static String EXTENSION = "csv";

    public final static String SIN_MODULE = "sin";
    public final static String COS_MODULE = "cos";
    public final static String CSC_MODULE = "csc";
    public final static String SEC_MODULE = "sec";
    public final static String TAN_MODULE = "tan";
    public final static String LOG_MODULE = "log";
    public final static String FUNC_MODULE = "func";

    private final HashSet<String> loggingModules = new HashSet<>();
    private HashMap<String, Writer> writers = new HashMap<>();

    public void setLogging(String... modules) {
        loggingModules.clear();
        loggingModules.addAll(Arrays.asList(modules));
    }

    public void setLoggingAll() {
        loggingModules.clear();
        loggingModules.add(SIN_MODULE);
        loggingModules.add(COS_MODULE);
        loggingModules.add(CSC_MODULE);
        loggingModules.add(SEC_MODULE);
        loggingModules.add(TAN_MODULE);
        loggingModules.add(LOG_MODULE);
        loggingModules.add(FUNC_MODULE);
    }

    public void setWriters(HashMap<String, Writer> writers) {
        this.writers = writers;
    }

    public boolean save(String module, double arg, double result) throws IOException {
        if (loggingModules.contains(module)) {
            String line = String.format(Locale.ROOT, "%.3f,%.3f\n", arg, result);
            writeLine(module, line);
            return true;
        }
        return false;
    }

    public boolean save(String module, double arg1, double arg2, double result) throws IOException {
        if (loggingModules.contains(module)) {
            String line = String.format(Locale.ROOT, "%.3f,%.3f,%.3f\n", arg1, arg2, result);
            writeLine(module, line);
            return true;
        }
        return false;
    }

    private void writeLine(String module, String line) throws IOException {
        Writer writer;
        if (writers.containsKey(module)) {
            writer = writers.get(module);
        } else {
            writer = new FileWriter(module + "." + EXTENSION);
            writers.put(module, writer);
        }
        writer.write(line);
    }

    public void closeWriters() throws IOException {
        for (Writer writer: writers.values()) {
            writer.close();
        }
    }
}
