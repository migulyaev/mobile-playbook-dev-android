package com.stealthcopter.networktools;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/* loaded from: classes.dex */
public class WakeOnLan {
    public static final int DEFAULT_NO_PACKETS = 5;
    public static final int DEFAULT_PORT = 9;
    public static final int DEFAULT_TIMEOUT_MILLIS = 10000;
    private InetAddress inetAddress;
    private String ipStr;
    private String macStr;
    private int port = 9;
    private int timeoutMillis = DEFAULT_TIMEOUT_MILLIS;
    private int noPackets = 5;

    /* loaded from: classes.dex */
    public interface WakeOnLanListener {
        void onError(Exception exc);

        void onSuccess();
    }

    private WakeOnLan() {
    }

    public static WakeOnLan onIp(String ipStr) {
        WakeOnLan wakeOnLan = new WakeOnLan();
        wakeOnLan.ipStr = ipStr;
        return wakeOnLan;
    }

    public static WakeOnLan onAddress(InetAddress inetAddress) {
        WakeOnLan wakeOnLan = new WakeOnLan();
        wakeOnLan.inetAddress = inetAddress;
        return wakeOnLan;
    }

    public WakeOnLan withMACAddress(String macStr) {
        if (macStr == null) {
            throw new NullPointerException("MAC Cannot be null");
        }
        this.macStr = macStr;
        return this;
    }

    public WakeOnLan setPort(int port) {
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        this.port = port;
        return this;
    }

    public WakeOnLan setNoPackets(int noPackets) {
        if (noPackets <= 0) {
            throw new IllegalArgumentException("Invalid number of packets to send " + noPackets);
        }
        this.noPackets = noPackets;
        return this;
    }

    public WakeOnLan setTimeout(int timeoutMillis) {
        if (timeoutMillis <= 0) {
            throw new IllegalArgumentException("Timeout cannot be less than zero");
        }
        this.timeoutMillis = timeoutMillis;
        return this;
    }

    public void wake() throws IOException {
        if (this.ipStr == null && this.inetAddress == null) {
            throw new IllegalArgumentException("You must declare ip address or supply an inetaddress");
        } else if (this.macStr == null) {
            throw new NullPointerException("You did not supply a mac address with withMac(...)");
        } else if (this.ipStr != null) {
            sendWakeOnLan(this.ipStr, this.macStr, this.port, this.timeoutMillis, this.noPackets);
        } else {
            sendWakeOnLan(this.inetAddress, this.macStr, this.port, this.timeoutMillis, this.noPackets);
        }
    }

    public void wake(final WakeOnLanListener wakeOnLanListener) {
        new Thread(new Runnable() { // from class: com.stealthcopter.networktools.WakeOnLan.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    WakeOnLan.this.wake();
                    if (wakeOnLanListener != null) {
                        wakeOnLanListener.onSuccess();
                    }
                } catch (IOException e) {
                    if (wakeOnLanListener != null) {
                        wakeOnLanListener.onError(e);
                    }
                }
            }
        }).start();
    }

    public static void sendWakeOnLan(String ipStr, String macStr) throws IllegalArgumentException, IOException {
        sendWakeOnLan(ipStr, macStr, 9, (int) DEFAULT_TIMEOUT_MILLIS, 5);
    }

    public static void sendWakeOnLan(String ipStr, String macStr, int port, int timeoutMillis, int packets) throws IllegalArgumentException, IOException {
        if (ipStr == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        sendWakeOnLan(InetAddress.getByName(ipStr), macStr, port, timeoutMillis, packets);
    }

    public static void sendWakeOnLan(InetAddress address, String macStr, int port, int timeoutMillis, int packets) throws IllegalArgumentException, IOException {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        } else if (macStr == null) {
            throw new IllegalArgumentException("MAC Address cannot be null");
        } else if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        } else if (packets <= 0) {
            throw new IllegalArgumentException("Invalid number of packets to send " + packets);
        } else {
            byte[] macBytes = MACTools.getMacBytes(macStr);
            byte[] bytes = new byte[(macBytes.length * 16) + 6];
            for (int i = 0; i < 6; i++) {
                bytes[i] = -1;
            }
            for (int i2 = 6; i2 < bytes.length; i2 += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i2, macBytes.length);
            }
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            for (int i3 = 0; i3 < packets; i3++) {
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(timeoutMillis);
                socket.send(packet);
                socket.close();
            }
        }
    }
}
