package org.jasypt.registry;

import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class AlgorithmRegistry {
    public static Set getAllDigestAlgorithms() {
        List algos = new ArrayList(Security.getAlgorithms("MessageDigest"));
        Collections.sort(algos);
        return Collections.unmodifiableSet(new LinkedHashSet(algos));
    }

    public static Set getAllPBEAlgorithms() {
        List<String> algos = new ArrayList(Security.getAlgorithms("Cipher"));
        Collections.sort(algos);
        LinkedHashSet pbeAlgos = new LinkedHashSet();
        for (String algo : algos) {
            if (algo != null && algo.startsWith("PBE")) {
                pbeAlgos.add(algo);
            }
        }
        return Collections.unmodifiableSet(pbeAlgos);
    }

    private AlgorithmRegistry() {
    }
}
