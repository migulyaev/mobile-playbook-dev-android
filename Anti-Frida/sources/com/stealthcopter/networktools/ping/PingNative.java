package com.stealthcopter.networktools.ping;

import com.stealthcopter.networktools.IPTools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
/* loaded from: classes.dex */
public class PingNative {
    private PingNative() {
    }

    public static PingResult ping(InetAddress host, PingOptions pingOptions) throws IOException, InterruptedException {
        String pingError;
        PingResult pingResult = new PingResult(host);
        if (host == null) {
            pingResult.isReachable = false;
            return pingResult;
        }
        StringBuilder echo = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        int timeoutSeconds = Math.max(pingOptions.getTimeoutMillis() / 1000, 1);
        int ttl = Math.max(pingOptions.getTimeToLive(), 1);
        String address = host.getHostAddress();
        String pingCommand = "ping";
        if (address == null) {
            address = host.getHostName();
        } else if (IPTools.isIPv6Address(address)) {
            pingCommand = "ping6";
        } else {
            IPTools.isIPv4Address(address);
        }
        Process proc = runtime.exec(pingCommand + " -c 1 -W " + timeoutSeconds + " -t " + ttl + " " + address);
        proc.waitFor();
        switch (proc.exitValue()) {
            case 0:
                BufferedReader buffer = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                while (true) {
                    String line = buffer.readLine();
                    if (line == null) {
                        return getPingStats(pingResult, echo.toString());
                    }
                    echo.append(line);
                    echo.append("\n");
                }
            case 1:
                pingError = "failed, exit = 1";
                break;
            default:
                pingError = "error, exit = 2";
                break;
        }
        pingResult.error = pingError;
        return pingResult;
    }

    public static PingResult getPingStats(PingResult pingResult, String s) {
        String pingError;
        if (s.contains("0% packet loss")) {
            int start = s.indexOf("/mdev = ");
            int end = s.indexOf(" ms\n", start);
            pingResult.fullString = s;
            if (start == -1 || end == -1) {
                pingError = "Error: " + s;
            } else {
                String s2 = s.substring(start + 8, end);
                String[] stats = s2.split("/");
                pingResult.isReachable = true;
                pingResult.result = s2;
                pingResult.timeTaken = Float.parseFloat(stats[1]);
                return pingResult;
            }
        } else if (s.contains("100% packet loss")) {
            pingError = "100% packet loss";
        } else if (s.contains("% packet loss")) {
            pingError = "partial packet loss";
        } else if (s.contains("unknown host")) {
            pingError = "unknown host";
        } else {
            pingError = "unknown error in getPingStats";
        }
        pingResult.error = pingError;
        return pingResult;
    }
}
