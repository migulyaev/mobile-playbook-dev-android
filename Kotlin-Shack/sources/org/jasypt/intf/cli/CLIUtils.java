package org.jasypt.intf.cli;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.jasypt.commons.CommonUtils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

/* loaded from: classes.dex */
final class CLIUtils {
    static void showEnvironment(boolean verbose) {
        if (verbose) {
            System.out.println("\n----ENVIRONMENT-----------------\n");
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Runtime: ");
            stringBuffer.append(System.getProperty("java.vm.vendor"));
            stringBuffer.append(" ");
            stringBuffer.append(System.getProperty("java.vm.name"));
            stringBuffer.append(" ");
            stringBuffer.append(System.getProperty("java.vm.version"));
            stringBuffer.append(" ");
            printStream.println(stringBuffer.toString());
            System.out.println("\n");
        }
    }

    static void showArgumentDescription(Properties argumentValues, boolean verbose) {
        if (verbose) {
            System.out.println("\n----ARGUMENTS-------------------\n");
            for (Map.Entry entry : argumentValues.entrySet()) {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(entry.getKey());
                stringBuffer.append(": ");
                stringBuffer.append(entry.getValue());
                printStream.println(stringBuffer.toString());
            }
            System.out.println("\n");
        }
    }

    static void showOutput(String output, boolean verbose) {
        if (verbose) {
            System.out.println("\n----OUTPUT----------------------\n");
            System.out.println(output);
            System.out.println("\n");
            return;
        }
        System.out.println(output);
    }

    static void showError(Throwable t, boolean verbose) {
        if (verbose) {
            System.err.println("\n----ERROR-----------------------\n");
            if (t instanceof EncryptionOperationNotPossibleException) {
                System.err.println("Operation not possible (Bad input or parameters)");
            } else if (t.getMessage() != null) {
                System.err.println(t.getMessage());
            } else {
                System.err.println(t.getClass().getName());
            }
            System.err.println("\n");
            return;
        }
        System.err.print("ERROR: ");
        if (t instanceof EncryptionOperationNotPossibleException) {
            System.err.println("Operation not possible (Bad input or parameters)");
        } else if (t.getMessage() != null) {
            System.err.println(t.getMessage());
        } else {
            System.err.println(t.getClass().getName());
        }
    }

    static boolean getVerbosity(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String key = CommonUtils.substringBefore(args[i], "=");
            String value = CommonUtils.substringAfter(args[i], "=");
            if (!CommonUtils.isEmpty(key) && !CommonUtils.isEmpty(value) && "verbose".equals(key)) {
                Boolean verbosity = CommonUtils.getStandardBooleanValue(value);
                if (verbosity != null) {
                    return verbosity.booleanValue();
                }
                return false;
            }
        }
        return true;
    }

    static Properties getArgumentValues(String appName, String[] args, String[][] requiredArgNames, String[][] optionalArgNames) {
        Set argNames = new HashSet();
        for (String[] strArr : requiredArgNames) {
            argNames.addAll(Arrays.asList(strArr));
        }
        for (String[] strArr2 : optionalArgNames) {
            argNames.addAll(Arrays.asList(strArr2));
        }
        Properties argumentValues = new Properties();
        for (int i = 0; i < args.length; i++) {
            String key = CommonUtils.substringBefore(args[i], "=");
            String value = CommonUtils.substringAfter(args[i], "=");
            if (CommonUtils.isEmpty(key) || CommonUtils.isEmpty(value)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Bad argument: ");
                stringBuffer.append(args[i]);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
            if (argNames.contains(key)) {
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    argumentValues.setProperty(key, value.substring(1, value.length() - 1));
                } else {
                    argumentValues.setProperty(key, value);
                }
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Bad argument: ");
                stringBuffer2.append(args[i]);
                throw new IllegalArgumentException(stringBuffer2.toString());
            }
        }
        for (int i2 = 0; i2 < requiredArgNames.length; i2++) {
            boolean found = false;
            for (int j = 0; j < requiredArgNames[i2].length; j++) {
                if (argumentValues.containsKey(requiredArgNames[i2][j])) {
                    found = true;
                }
            }
            if (!found) {
                showUsageAndExit(appName, requiredArgNames, optionalArgNames);
            }
        }
        return argumentValues;
    }

    static void showUsageAndExit(String appName, String[][] requiredArgNames, String[][] optionalArgNames) {
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\nUSAGE: ");
        stringBuffer.append(appName);
        stringBuffer.append(" [ARGUMENTS]\n");
        printStream.println(stringBuffer.toString());
        System.err.println("  * Arguments must apply to format:\n");
        System.err.println("      \"arg1=value1 arg2=value2 arg3=value3 ...\"");
        System.err.println();
        System.err.println("  * Required arguments:\n");
        for (int i = 0; i < requiredArgNames.length; i++) {
            System.err.print("      ");
            if (requiredArgNames[i].length == 1) {
                System.err.print(requiredArgNames[i][0]);
            } else {
                System.err.print("(");
                for (int j = 0; j < requiredArgNames[i].length; j++) {
                    if (j > 0) {
                        System.err.print(" | ");
                    }
                    System.err.print(requiredArgNames[i][j]);
                }
                System.err.print(")");
            }
            System.err.println();
        }
        System.err.println();
        System.err.println("  * Optional arguments:\n");
        for (int i2 = 0; i2 < optionalArgNames.length; i2++) {
            System.err.print("      ");
            if (optionalArgNames[i2].length == 1) {
                System.err.print(optionalArgNames[i2][0]);
            } else {
                System.err.print("(");
                for (int j2 = 0; j2 < optionalArgNames[i2].length; j2++) {
                    if (j2 > 0) {
                        System.err.print(" | ");
                    }
                    System.err.print(optionalArgNames[i2][j2]);
                }
                System.err.print(")");
            }
            System.err.println();
        }
        System.exit(1);
    }

    private CLIUtils() {
    }
}
