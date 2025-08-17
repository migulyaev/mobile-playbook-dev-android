package com.stealthcopter.networktools;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class IPTools {
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    private IPTools() {
    }

    public static boolean isIPv4Address(String address) {
        return address != null && IPV4_PATTERN.matcher(address).matches();
    }

    public static boolean isIPv6StdAddress(String address) {
        return address != null && IPV6_STD_PATTERN.matcher(address).matches();
    }

    public static boolean isIPv6HexCompressedAddress(String address) {
        return address != null && IPV6_HEX_COMPRESSED_PATTERN.matcher(address).matches();
    }

    public static boolean isIPv6Address(String address) {
        return address != null && (isIPv6StdAddress(address) || isIPv6HexCompressedAddress(address));
    }

    public static InetAddress getLocalIPv4Address() {
        ArrayList<InetAddress> localAddresses = getLocalIPv4Addresses();
        if (localAddresses.size() > 0) {
            return localAddresses.get(0);
        }
        return null;
    }

    public static ArrayList<InetAddress> getLocalIPv4Addresses() {
        ArrayList<InetAddress> foundAddresses = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces.hasMoreElements()) {
                Enumeration<InetAddress> addresses = ifaces.nextElement().getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if ((addr instanceof Inet4Address) && !addr.isLoopbackAddress()) {
                        foundAddresses.add(addr);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return foundAddresses;
    }

    public static boolean isIpAddressLocalhost(InetAddress addr) {
        if (addr == null) {
            return false;
        }
        if (addr.isAnyLocalAddress() || addr.isLoopbackAddress()) {
            return true;
        }
        try {
            if (NetworkInterface.getByInetAddress(addr) != null) {
                return true;
            }
            return false;
        } catch (SocketException e) {
            return false;
        }
    }

    public static boolean isIpAddressLocalNetwork(InetAddress addr) {
        return addr != null && addr.isSiteLocalAddress();
    }
}
