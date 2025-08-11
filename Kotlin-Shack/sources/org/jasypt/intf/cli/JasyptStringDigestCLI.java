package org.jasypt.intf.cli;

import java.util.Properties;
import org.jasypt.intf.service.JasyptStatelessService;

/* loaded from: classes.dex */
public final class JasyptStringDigestCLI {
    static /* synthetic */ Class class$org$jasypt$intf$cli$JasyptStringDigestCLI;
    private static final String[][] VALID_REQUIRED_ARGUMENTS = {new String[]{"input"}};
    private static final String[][] VALID_OPTIONAL_ARGUMENTS = {new String[]{"verbose"}, new String[]{"algorithm"}, new String[]{"iterations"}, new String[]{"saltSizeBytes"}, new String[]{"saltGeneratorClassName"}, new String[]{"providerName"}, new String[]{"providerClassName"}, new String[]{"invertPositionOfSaltInMessageBeforeDigesting"}, new String[]{"invertPositionOfPlainSaltInEncryptionResults"}, new String[]{"useLenientSaltSizeCheck"}, new String[]{"unicodeNormalizationIgnored"}, new String[]{"stringOutputType"}, new String[]{"prefix"}, new String[]{"suffix"}};

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
                String result = service.digest(input, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("iterations"), null, null, argumentValues.getProperty("saltSizeBytes"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("invertPositionOfSaltInMessageBeforeDigesting"), null, null, argumentValues.getProperty("invertPositionOfPlainSaltInEncryptionResults"), null, null, argumentValues.getProperty("useLenientSaltSizeCheck"), null, null, argumentValues.getProperty("unicodeNormalizationIgnored"), null, null, argumentValues.getProperty("stringOutputType"), null, null, argumentValues.getProperty("prefix"), null, null, argumentValues.getProperty("suffix"), null, null);
                CLIUtils.showOutput(result, verbose);
            }
            if (class$org$jasypt$intf$cli$JasyptStringDigestCLI == null) {
                cls = class$("org.jasypt.intf.cli.JasyptStringDigestCLI");
                class$org$jasypt$intf$cli$JasyptStringDigestCLI = cls;
            } else {
                cls = class$org$jasypt$intf$cli$JasyptStringDigestCLI;
            }
            applicationName = cls.getName();
            arguments = args;
            Properties argumentValues2 = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
            CLIUtils.showEnvironment(verbose);
            JasyptStatelessService service2 = new JasyptStatelessService();
            String input2 = argumentValues2.getProperty("input");
            CLIUtils.showArgumentDescription(argumentValues2, verbose);
            String result2 = service2.digest(input2, argumentValues2.getProperty("algorithm"), null, null, argumentValues2.getProperty("iterations"), null, null, argumentValues2.getProperty("saltSizeBytes"), null, null, argumentValues2.getProperty("saltGeneratorClassName"), null, null, argumentValues2.getProperty("providerName"), null, null, argumentValues2.getProperty("providerClassName"), null, null, argumentValues2.getProperty("invertPositionOfSaltInMessageBeforeDigesting"), null, null, argumentValues2.getProperty("invertPositionOfPlainSaltInEncryptionResults"), null, null, argumentValues2.getProperty("useLenientSaltSizeCheck"), null, null, argumentValues2.getProperty("unicodeNormalizationIgnored"), null, null, argumentValues2.getProperty("stringOutputType"), null, null, argumentValues2.getProperty("prefix"), null, null, argumentValues2.getProperty("suffix"), null, null);
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

    private JasyptStringDigestCLI() {
    }
}
