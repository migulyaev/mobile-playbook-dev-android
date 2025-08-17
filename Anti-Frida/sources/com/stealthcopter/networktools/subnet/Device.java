package com.stealthcopter.networktools.subnet;

import java.net.InetAddress;
/* loaded from: classes.dex */
public class Device {
    public String hostname;
    public String ip;
    public String mac;
    public float time = 0.0f;

    public Device(InetAddress ip) {
        this.ip = ip.getHostAddress();
        this.hostname = ip.getCanonicalHostName();
    }

    public String toString() {
        return "Device{ip='" + this.ip + "', hostname='" + this.hostname + "', mac='" + this.mac + "', time=" + this.time + '}';
    }
}
