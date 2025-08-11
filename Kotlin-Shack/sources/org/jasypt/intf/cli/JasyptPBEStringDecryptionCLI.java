package org.jasypt.intf.cli;

import java.util.Properties;
import org.jasypt.intf.service.JasyptStatelessService;

/* loaded from: classes.dex */
public final class JasyptPBEStringDecryptionCLI {
    static /* synthetic */ Class class$org$jasypt$intf$cli$JasyptPBEStringDecryptionCLI;
    private static final String[][] VALID_REQUIRED_ARGUMENTS = {new String[]{"input"}, new String[]{"password"}};
    private static final String[][] VALID_OPTIONAL_ARGUMENTS = {new String[]{"verbose"}, new String[]{"algorithm"}, new String[]{"keyObtentionIterations"}, new String[]{"saltGeneratorClassName"}, new String[]{"providerName"}, new String[]{"providerClassName"}, new String[]{"stringOutputType"}};

    public static void main(String[] args) {
        Class cls;
        String applicationName;
        String[] arguments;
        boolean verbose = CLIUtils.getVerbosity(args);
        try {
            if (args[0] != null && args[0].indexOf("=") == -1) {
                applicationName = args[0];
                arguments = new String[args.length - 1];
                System.arraycopy(args, 1, arguments, 0, args.length - 1);
                Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
                CLIUtils.showEnvironment(verbose);
                JasyptStatelessService service = new JasyptStatelessService();
                String input = argumentValues.getProperty("input");
                CLIUtils.showArgumentDescription(argumentValues, verbose);
                String result = service.decrypt(input, argumentValues.getProperty("password"), null, null, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("keyObtentionIterations"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("stringOutputType"), null, null);
                CLIUtils.showOutput(result, verbose);
            }
            if (class$org$jasypt$intf$cli$JasyptPBEStringDecryptionCLI == null) {
                cls = class$("org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI");
                class$org$jasypt$intf$cli$JasyptPBEStringDecryptionCLI = cls;
            } else {
                cls = class$org$jasypt$intf$cli$JasyptPBEStringDecryptionCLI;
            }
            applicationName = cls.getName();
            arguments = args;
            Properties argumentValues2 = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
            CLIUtils.showEnvironment(verbose);
            JasyptStatelessService service2 = new JasyptStatelessService();
            String input2 = argumentValues2.getProperty("input");
            CLIUtils.showArgumentDescription(argumentValues2, verbose);
            String result2 = service2.decrypt(input2, argumentValues2.getProperty("password"), null, null, argumentValues2.getProperty("algorithm"), null, null, argumentValues2.getProperty("keyObtentionIterations"), null, null, argumentValues2.getProperty("saltGeneratorClassName"), null, null, argumentValues2.getProperty("providerName"), null, null, argumentValues2.getProperty("providerClassName"), null, null, argumentValues2.getProperty("stringOutputType"), null, null);
            CLIUtils.showOutput(result2, verbose);
        } catch (Throwable t) {
            CLIUtils.showError(t, verbose);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError().initCause(x1);
        }
    }

    private JasyptPBEStringDecryptionCLI() {
    }
}
