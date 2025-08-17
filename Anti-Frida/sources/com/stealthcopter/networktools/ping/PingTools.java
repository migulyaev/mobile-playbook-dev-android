package com.stealthcopter.networktools.ping;

import java.io.IOException;
import java.net.InetAddress;
/* loaded from: classes.dex */
public class PingTools {
    private PingTools() {
    }

    public static PingResult doPing(InetAddress ia, PingOptions pingOptions) {
        try {
            return doNativePing(ia, pingOptions);
        } catch (InterruptedException e) {
            PingResult pingResult = new PingResult(ia);
            pingResult.isReachable = false;
            pingResult.error = "Interrupted";
            return pingResult;
        } catch (Exception e2) {
            return doJavaPing(ia, pingOptions);
        }
    }

    public static PingResult doNativePing(InetAddress ia, PingOptions pingOptions) throws IOException, InterruptedException {
        return PingNative.ping(ia, pingOptions);
    }

    public static PingResult doJavaPing(InetAddress ia, PingOptions pingOptions) {
        PingResult pingResult = new PingResult(ia);
        if (ia == null) {
            pingResult.isReachable = false;
            return pingResult;
        }
        try {
            long startTime = System.nanoTime();
            boolean reached = ia.isReachable(null, pingOptions.getTimeToLive(), pingOptions.getTimeoutMillis());
            pingResult.timeTaken = ((float) (System.nanoTime() - startTime)) / 1000000.0f;
            pingResult.isReachable = reached;
            if (!reached) {
                pingResult.error = "Timed Out";
            }
        } catch (IOException e) {
            pingResult.isReachable = false;
            pingResult.error = "IOException: " + e.getMessage();
        }
        return pingResult;
    }
}
