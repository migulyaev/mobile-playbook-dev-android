package com.stealthcopter.networktools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ARPInfo {
    private ARPInfo() {
    }

    public static String getMACFromIPAddress(String ip) {
        if (ip == null) {
            return null;
        }
        Iterator<String> it = getLinesInARPCache().iterator();
        while (it.hasNext()) {
            String[] splitted = it.next().split(" +");
            if (splitted.length >= 4 && ip.equals(splitted[0])) {
                String mac = splitted[3];
                if (mac.matches("..:..:..:..:..:..")) {
                    return mac;
                }
                return null;
            }
        }
        return null;
    }

    public static String getIPAddressFromMAC(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        if (!macAddress.matches("..:..:..:..:..:..")) {
            throw new IllegalArgumentException("Invalid MAC Address");
        }
        Iterator<String> it = getLinesInARPCache().iterator();
        while (it.hasNext()) {
            String[] splitted = it.next().split(" +");
            if (splitted.length >= 4 && macAddress.equals(splitted[3])) {
                return splitted[0];
            }
        }
        return null;
    }

    public static ArrayList<String> getAllIPAddressesInARPCache() {
        return new ArrayList<>(getAllIPAndMACAddressesInARPCache().keySet());
    }

    public static ArrayList<String> getAllMACAddressesInARPCache() {
        return new ArrayList<>(getAllIPAndMACAddressesInARPCache().values());
    }

    public static HashMap<String, String> getAllIPAndMACAddressesInARPCache() {
        HashMap<String, String> macList = new HashMap<>();
        Iterator<String> it = getLinesInARPCache().iterator();
        while (it.hasNext()) {
            String[] splitted = it.next().split(" +");
            if (splitted.length >= 4 && splitted[3].matches("..:..:..:..:..:..") && !splitted[3].equals("00:00:00:00:00:00")) {
                macList.put(splitted[0], splitted[3]);
            }
        }
        return macList;
    }

    private static ArrayList<String> getLinesInARPCache() {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = null;
        try {
            try {
                try {
                    br = new BufferedReader(new FileReader("/proc/net/arp"));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        lines.add(line);
                    }
                    if (br != null) {
                        br.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (br != null) {
                        br.close();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return lines;
        } catch (Throwable th) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }
}
