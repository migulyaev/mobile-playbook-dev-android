package com.stealthcopter.networktools;

import com.stealthcopter.networktools.portscanning.PortScanTCP;
import com.stealthcopter.networktools.portscanning.PortScanUDP;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class PortScan {
    private static final int DEFAULT_THREADS_LOCALHOST = 7;
    private static final int DEFAULT_THREADS_LOCALNETWORK = 50;
    private static final int DEFAULT_THREADS_REMOTE = 50;
    private static final int METHOD_TCP = 0;
    private static final int METHOD_UDP = 1;
    private static final int TIMEOUT_LOCALHOST = 25;
    private static final int TIMEOUT_LOCALNETWORK = 1000;
    private static final int TIMEOUT_REMOTE = 2500;
    private InetAddress address;
    private PortListener portListener;
    private int method = 0;
    private int noThreads = 50;
    private int timeOutMillis = 1000;
    private boolean cancelled = false;
    private ArrayList<Integer> ports = new ArrayList<>();
    private ArrayList<Integer> openPortsFound = new ArrayList<>();

    /* loaded from: classes.dex */
    public interface PortListener {
        void onFinished(ArrayList<Integer> arrayList);

        void onResult(int i, boolean z);
    }

    private PortScan() {
    }

    public static PortScan onAddress(String address) throws UnknownHostException {
        return onAddress(InetAddress.getByName(address));
    }

    public static PortScan onAddress(InetAddress ia) {
        PortScan portScan = new PortScan();
        portScan.setAddress(ia);
        portScan.setDefaultThreadsAndTimeouts();
        return portScan;
    }

    public PortScan setTimeOutMillis(int timeOutMillis) {
        if (timeOutMillis < 0) {
            throw new IllegalArgumentException("Timeout cannot be less than 0");
        }
        this.timeOutMillis = timeOutMillis;
        return this;
    }

    public PortScan setPort(int port) {
        this.ports.clear();
        validatePort(port);
        this.ports.add(Integer.valueOf(port));
        return this;
    }

    public PortScan setPorts(ArrayList<Integer> ports) {
        Iterator<Integer> it = ports.iterator();
        while (it.hasNext()) {
            validatePort(it.next().intValue());
        }
        this.ports = ports;
        return this;
    }

    public PortScan setPorts(String portString) {
        this.ports.clear();
        ArrayList<Integer> ports = new ArrayList<>();
        if (portString == null) {
            throw new IllegalArgumentException("Empty port string not allowed");
        }
        String[] split = portString.substring(portString.indexOf(":") + 1, portString.length()).split(",");
        for (String x : split) {
            if (x.contains("-")) {
                int start = Integer.parseInt(x.split("-")[0]);
                int end = Integer.parseInt(x.split("-")[1]);
                validatePort(start);
                validatePort(end);
                if (end <= start) {
                    throw new IllegalArgumentException("Start port cannot be greater than or equal to the end port");
                }
                for (int j = start; j <= end; j++) {
                    ports.add(Integer.valueOf(j));
                }
            } else {
                int start2 = Integer.parseInt(x);
                validatePort(start2);
                ports.add(Integer.valueOf(start2));
            }
        }
        this.ports = ports;
        return this;
    }

    private void validatePort(int port) {
        if (port < 1) {
            throw new IllegalArgumentException("Start port cannot be less than 1");
        } else if (port > 65535) {
            throw new IllegalArgumentException("Start cannot be greater than 65535");
        }
    }

    public PortScan setPortsPrivileged() {
        this.ports.clear();
        for (int i = 1; i < 1024; i++) {
            this.ports.add(Integer.valueOf(i));
        }
        return this;
    }

    public PortScan setPortsAll() {
        this.ports.clear();
        for (int i = 1; i < 65536; i++) {
            this.ports.add(Integer.valueOf(i));
        }
        return this;
    }

    private void setAddress(InetAddress address) {
        this.address = address;
    }

    private void setDefaultThreadsAndTimeouts() {
        if (IPTools.isIpAddressLocalhost(this.address)) {
            this.timeOutMillis = 25;
            this.noThreads = 7;
        } else if (IPTools.isIpAddressLocalNetwork(this.address)) {
            this.timeOutMillis = 1000;
            this.noThreads = 50;
        } else {
            this.timeOutMillis = TIMEOUT_REMOTE;
            this.noThreads = 50;
        }
    }

    public PortScan setNoThreads(int noThreads) throws IllegalArgumentException {
        if (noThreads < 1) {
            throw new IllegalArgumentException("Cannot have less than 1 thread");
        }
        this.noThreads = noThreads;
        return this;
    }

    private PortScan setMethod(int method) {
        switch (method) {
            case 0:
            case 1:
                this.method = method;
                return this;
            default:
                throw new IllegalArgumentException("Invalid method type " + method);
        }
    }

    public PortScan setMethodUDP() {
        setMethod(1);
        return this;
    }

    public PortScan setMethodTCP() {
        setMethod(0);
        return this;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public ArrayList<Integer> doScan() {
        this.cancelled = false;
        this.openPortsFound.clear();
        ExecutorService executor = Executors.newFixedThreadPool(this.noThreads);
        Iterator<Integer> it = this.ports.iterator();
        while (it.hasNext()) {
            executor.execute(new PortScanRunnable(this.address, it.next().intValue(), this.timeOutMillis, this.method));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(this.openPortsFound);
        return this.openPortsFound;
    }

    public PortScan doScan(final PortListener portListener) {
        this.portListener = portListener;
        this.openPortsFound.clear();
        this.cancelled = false;
        new Thread(new Runnable() { // from class: com.stealthcopter.networktools.PortScan.1
            @Override // java.lang.Runnable
            public void run() {
                ExecutorService executor = Executors.newFixedThreadPool(PortScan.this.noThreads);
                Iterator it = PortScan.this.ports.iterator();
                while (it.hasNext()) {
                    executor.execute(new PortScanRunnable(PortScan.this.address, ((Integer) it.next()).intValue(), PortScan.this.timeOutMillis, PortScan.this.method));
                }
                executor.shutdown();
                try {
                    executor.awaitTermination(1, TimeUnit.HOURS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (portListener != null) {
                    Collections.sort(PortScan.this.openPortsFound);
                    portListener.onFinished(PortScan.this.openPortsFound);
                }
            }
        }).start();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void portScanned(int port, boolean open) {
        if (open) {
            try {
                this.openPortsFound.add(Integer.valueOf(port));
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.portListener != null) {
            this.portListener.onResult(port, open);
        }
    }

    /* loaded from: classes.dex */
    private class PortScanRunnable implements Runnable {
        private final InetAddress address;
        private final int method;
        private final int portNo;
        private final int timeOutMillis;

        PortScanRunnable(InetAddress address, int portNo, int timeOutMillis, int method) {
            this.address = address;
            this.portNo = portNo;
            this.timeOutMillis = timeOutMillis;
            this.method = method;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!PortScan.this.cancelled) {
                switch (this.method) {
                    case 0:
                        PortScan.this.portScanned(this.portNo, PortScanTCP.scanAddress(this.address, this.portNo, this.timeOutMillis));
                        return;
                    case 1:
                        PortScan.this.portScanned(this.portNo, PortScanUDP.scanAddress(this.address, this.portNo, this.timeOutMillis));
                        return;
                    default:
                        throw new IllegalArgumentException("Invalid method");
                }
            }
        }
    }
}
