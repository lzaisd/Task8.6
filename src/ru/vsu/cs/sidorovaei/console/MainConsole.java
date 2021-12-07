package ru.vsu.cs.sidorovaei.console;

import ru.vsu.cs.sidorovaei.logics.Logic;
import ru.vsu.cs.sidorovaei.util.ArrayUtils;

import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;

public class MainConsole {
        public static void main(String[] args) throws FileNotFoundException {
            Locale.setDefault(Locale.ROOT);

            runInCommandLineMode(args);
            }

    static class ArgsParser {
        private final Dictionary<String, String> argsDict = new Hashtable<>();

        public ArgsParser(String[] args) {
            parse(args);
        }

        private void parse(String[] args) {
            String argName = null;

            for (String arg : args) {
                if (arg.startsWith("-") && arg.length() == 2 || arg.startsWith("--")) {
                    argName = arg;
                } else {
                    if (argName != null) {
                        argsDict.put(argName, arg);
                    }
                }
            }
        }

        public String getArgumentValue(String shortName, String longName) {
            String value = argsDict.get(shortName);

            if (value == null) {
                value = argsDict.get(longName);
            }

            return value;
        }
    }

    private static void runInCommandLineMode(String[] args) throws FileNotFoundException {
        ArgsParser argsParser = new ArgsParser(args);

        String inputFile = argsParser.getArgumentValue("-i", "--input-file=");
        String outputFile = argsParser.getArgumentValue("-o", "--output-file=");

        int[][] arr = ArrayUtils.readIntArray2FromFile(inputFile);
        if (Logic.checkIfArrIsNull(arr) || Logic.checkIfArrIsEmpty(arr) || !Logic.checkIfArrIsRectangle(arr)) {
            System.out.println("Массив пустой или не прямоугольный");
            System.err.println(1);
        }
        ArrayUtils.writeArrayToFile(outputFile, Logic.deleteEqualRows(arr));
    }
}