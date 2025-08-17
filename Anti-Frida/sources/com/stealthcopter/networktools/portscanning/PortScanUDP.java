package com.stealthcopter.networktools.portscanning;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
/* loaded from: classes.dex */
public class PortScanUDP {
    private PortScanUDP() {
    }

    public static boolean scanAddress(InetAddress ia, int portNo, int timeoutMillis) {
        try {
            byte[] bytes = new byte[128];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            DatagramSocket ds = new DatagramSocket();
            ds.setSoTimeout(timeoutMillis);
            ds.connect(ia, portNo);
            ds.send(dp);
            ds.isConnected();
            ds.receive(dp);
            ds.close();
            return false;
        } catch (SocketTimeoutException e) {
            return true;
        } catch (Exception e2) {
            return false;
        }
    }
}
