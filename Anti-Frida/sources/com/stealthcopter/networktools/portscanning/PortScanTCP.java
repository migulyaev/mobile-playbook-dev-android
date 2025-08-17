package com.stealthcopter.networktools.portscanning;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
/* loaded from: classes.dex */
public class PortScanTCP {
    private PortScanTCP() {
    }

    public static boolean scanAddress(InetAddress ia, int portNo, int timeoutMillis) {
        Socket s = null;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(ia, portNo), timeoutMillis);
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e2) {
            if (s == null) {
                return false;
            }
            try {
                s.close();
                return false;
            } catch (IOException e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }
}
