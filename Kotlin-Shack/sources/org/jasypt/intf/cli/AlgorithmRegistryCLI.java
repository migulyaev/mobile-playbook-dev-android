package org.jasypt.intf.cli;

import java.io.PrintStream;
import java.util.Set;
import org.jasypt.registry.AlgorithmRegistry;

/* loaded from: classes.dex */
public final class AlgorithmRegistryCLI {
    public static void main(String[] args) {
        try {
            Set digestAlgos = AlgorithmRegistry.getAllDigestAlgorithms();
            Set pbeAlgos = AlgorithmRegistry.getAllPBEAlgorithms();
            System.out.println();
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("DIGEST ALGORITHMS:   ");
            stringBuffer.append(digestAlgos);
            printStream.println(stringBuffer.toString());
            System.out.println();
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("PBE ALGORITHMS:      ");
            stringBuffer2.append(pbeAlgos);
            printStream2.println(stringBuffer2.toString());
            System.out.println();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    private AlgorithmRegistryCLI() {
    }
}
