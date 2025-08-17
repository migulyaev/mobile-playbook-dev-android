package com.stealthcopter.networktools.ping;
/* loaded from: classes.dex */
public class PingOptions {
    private int timeToLive = 128;
    private int timeoutMillis = 1000;

    public int getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = Math.max(timeoutMillis, 1000);
    }

    public int getTimeToLive() {
        return this.timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = Math.max(timeToLive, 1);
    }
}
