package com.stealthcopter.networktools;

import com.stealthcopter.networktools.ping.PingOptions;
import com.stealthcopter.networktools.ping.PingResult;
import com.stealthcopter.networktools.ping.PingStats;
import com.stealthcopter.networktools.ping.PingTools;
import java.net.InetAddress;
import java.net.UnknownHostException;
/* loaded from: classes.dex */
public class Ping {
    public static final int PING_HYBRID = 2;
    public static final int PING_JAVA = 0;
    public static final int PING_NATIVE = 1;
    private InetAddress address;
    private String addressString = null;
    private final PingOptions pingOptions = new PingOptions();
    private int delayBetweenScansMillis = 0;
    private int times = 1;
    private boolean cancelled = false;

    /* loaded from: classes.dex */
    public interface PingListener {
        void onError(Exception exc);

        void onFinished(PingStats pingStats);

        void onResult(PingResult pingResult);
    }

    private Ping() {
    }

    public static Ping onAddress(String address) {
        Ping ping = new Ping();
        ping.setAddressString(address);
        return ping;
    }

    public static Ping onAddress(InetAddress ia) {
        Ping ping = new Ping();
        ping.setAddress(ia);
        return ping;
    }

    public Ping setTimeOutMillis(int timeOutMillis) {
        if (timeOutMillis < 0) {
            throw new IllegalArgumentException("Times cannot be less than 0");
        }
        this.pingOptions.setTimeoutMillis(timeOutMillis);
        return this;
    }

    public Ping setDelayMillis(int delayBetweenScansMillis) {
        if (delayBetweenScansMillis < 0) {
            throw new IllegalArgumentException("Delay cannot be less than 0");
        }
        this.delayBetweenScansMillis = delayBetweenScansMillis;
        return this;
    }

    public Ping setTimeToLive(int timeToLive) {
        if (timeToLive < 1) {
            throw new IllegalArgumentException("TTL cannot be less than 1");
        }
        this.pingOptions.setTimeToLive(timeToLive);
        return this;
    }

    public Ping setTimes(int noTimes) {
        if (noTimes < 0) {
            throw new IllegalArgumentException("Times cannot be less than 0");
        }
        this.times = noTimes;
        return this;
    }

    private void setAddress(InetAddress address) {
        this.address = address;
    }

    private void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveAddressString() throws UnknownHostException {
        if (this.address == null && this.addressString != null) {
            this.address = InetAddress.getByName(this.addressString);
        }
    }

    public void cancel() {
        this.cancelled = true;
    }

    public PingResult doPing() throws UnknownHostException {
        this.cancelled = false;
        resolveAddressString();
        return PingTools.doPing(this.address, this.pingOptions);
    }

    public Ping doPing(final PingListener pingListener) {
        new Thread(new Runnable() { // from class: com.stealthcopter.networktools.Ping.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Ping.this.resolveAddressString();
                    if (Ping.this.address == null) {
                        pingListener.onError(new NullPointerException("Address is null"));
                        return;
                    }
                    long pingsCompleted = 0;
                    long noLostPackets = 0;
                    float totalPingTime = 0.0f;
                    float minPingTime = -1.0f;
                    float maxPingTime = -1.0f;
                    Ping.this.cancelled = false;
                    int noPings = Ping.this.times;
                    while (true) {
                        if (noPings <= 0 && Ping.this.times != 0) {
                            break;
                        }
                        PingResult pingResult = PingTools.doPing(Ping.this.address, Ping.this.pingOptions);
                        if (pingListener != null) {
                            pingListener.onResult(pingResult);
                        }
                        pingsCompleted++;
                        if (pingResult.hasError()) {
                            noLostPackets++;
                        } else {
                            float timeTaken = pingResult.getTimeTaken();
                            totalPingTime += timeTaken;
                            if (maxPingTime == -1.0f || timeTaken > maxPingTime) {
                                maxPingTime = timeTaken;
                            }
                            if (minPingTime == -1.0f || timeTaken < minPingTime) {
                                minPingTime = timeTaken;
                            }
                        }
                        noPings--;
                        if (Ping.this.cancelled) {
                            break;
                        }
                        try {
                            Thread.sleep((long) Ping.this.delayBetweenScansMillis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (pingListener != null) {
                        pingListener.onFinished(new PingStats(Ping.this.address, pingsCompleted, noLostPackets, totalPingTime, minPingTime, maxPingTime));
                    }
                } catch (UnknownHostException e2) {
                    pingListener.onError(e2);
                }
            }
        }).start();
        return this;
    }
}
