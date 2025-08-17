package com.stealthcopter.networktools;

import com.stealthcopter.networktools.ping.PingResult;
import com.stealthcopter.networktools.subnet.Device;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class SubnetDevices {
    private ArrayList<String> addresses;
    private ArrayList<Device> devicesFound;
    private OnSubnetDeviceFound listener;
    private int noThreads = 100;
    private int timeOutMillis = 2500;
    private boolean cancelled = false;
    private HashMap<String, String> ipMacHashMap = null;

    /* loaded from: classes.dex */
    public interface OnSubnetDeviceFound {
        void onDeviceFound(Device device);

        void onFinished(ArrayList<Device> arrayList);
    }

    private SubnetDevices() {
    }

    public static SubnetDevices fromLocalAddress() {
        InetAddress ipv4 = IPTools.getLocalIPv4Address();
        if (ipv4 != null) {
            return fromIPAddress(ipv4.getHostAddress());
        }
        throw new IllegalAccessError("Could not access local ip address");
    }

    public static SubnetDevices fromIPAddress(InetAddress inetAddress) {
        return fromIPAddress(inetAddress.getHostAddress());
    }

    public static SubnetDevices fromIPAddress(String ipAddress) {
        if (!IPTools.isIPv4Address(ipAddress)) {
            throw new IllegalArgumentException("Invalid IP Address");
        }
        SubnetDevices subnetDevice = new SubnetDevices();
        subnetDevice.addresses = new ArrayList<>();
        subnetDevice.addresses.addAll(ARPInfo.getAllIPAddressesInARPCache());
        String segment = ipAddress.substring(0, ipAddress.lastIndexOf(".") + 1);
        for (int j = 0; j < 255; j++) {
            ArrayList<String> arrayList = subnetDevice.addresses;
            if (!arrayList.contains(segment + j)) {
                ArrayList<String> arrayList2 = subnetDevice.addresses;
                arrayList2.add(segment + j);
            }
        }
        return subnetDevice;
    }

    public static SubnetDevices fromIPList(List<String> ipAddresses) {
        SubnetDevices subnetDevice = new SubnetDevices();
        subnetDevice.addresses = new ArrayList<>();
        subnetDevice.addresses.addAll(ipAddresses);
        return subnetDevice;
    }

    public SubnetDevices setNoThreads(int noThreads) throws IllegalArgumentException {
        if (noThreads < 1) {
            throw new IllegalArgumentException("Cannot have less than 1 thread");
        }
        this.noThreads = noThreads;
        return this;
    }

    public SubnetDevices setTimeOutMillis(int timeOutMillis) throws IllegalArgumentException {
        if (timeOutMillis < 0) {
            throw new IllegalArgumentException("Timeout cannot be less than 0");
        }
        this.timeOutMillis = timeOutMillis;
        return this;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public SubnetDevices findDevices(final OnSubnetDeviceFound listener) {
        this.listener = listener;
        this.cancelled = false;
        this.devicesFound = new ArrayList<>();
        new Thread(new Runnable() { // from class: com.stealthcopter.networktools.SubnetDevices.1
            @Override // java.lang.Runnable
            public void run() {
                SubnetDevices.this.ipMacHashMap = ARPInfo.getAllIPAndMACAddressesInARPCache();
                ExecutorService executor = Executors.newFixedThreadPool(SubnetDevices.this.noThreads);
                Iterator it = SubnetDevices.this.addresses.iterator();
                while (it.hasNext()) {
                    executor.execute(new SubnetDeviceFinderRunnable((String) it.next()));
                }
                executor.shutdown();
                try {
                    executor.awaitTermination(1, TimeUnit.HOURS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SubnetDevices.this.ipMacHashMap = ARPInfo.getAllIPAndMACAddressesInARPCache();
                Iterator it2 = SubnetDevices.this.devicesFound.iterator();
                while (it2.hasNext()) {
                    Device device = (Device) it2.next();
                    if (device.mac == null && SubnetDevices.this.ipMacHashMap.containsKey(device.ip)) {
                        device.mac = (String) SubnetDevices.this.ipMacHashMap.get(device.ip);
                    }
                }
                listener.onFinished(SubnetDevices.this.devicesFound);
            }
        }).start();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void subnetDeviceFound(Device device) {
        this.devicesFound.add(device);
        this.listener.onDeviceFound(device);
    }

    /* loaded from: classes.dex */
    public class SubnetDeviceFinderRunnable implements Runnable {
        private final String address;

        SubnetDeviceFinderRunnable(String address) {
            this.address = address;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!SubnetDevices.this.cancelled) {
                try {
                    InetAddress ia = InetAddress.getByName(this.address);
                    PingResult pingResult = Ping.onAddress(ia).setTimeOutMillis(SubnetDevices.this.timeOutMillis).doPing();
                    if (pingResult.isReachable) {
                        Device device = new Device(ia);
                        if (SubnetDevices.this.ipMacHashMap.containsKey(ia.getHostAddress())) {
                            device.mac = (String) SubnetDevices.this.ipMacHashMap.get(ia.getHostAddress());
                        }
                        device.time = pingResult.timeTaken;
                        SubnetDevices.this.subnetDeviceFound(device);
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
