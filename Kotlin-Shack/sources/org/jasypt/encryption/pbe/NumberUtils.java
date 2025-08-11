package org.jasypt.encryption.pbe;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

/* loaded from: classes.dex */
final class NumberUtils {
    static byte[] byteArrayFromInt(int number) {
        byte b0 = (byte) (255 & number);
        byte b1 = (byte) ((number >> 8) & 255);
        byte b2 = (byte) ((number >> 16) & 255);
        byte b3 = (byte) (255 & (number >> 24));
        return new byte[]{b3, b2, b1, b0};
    }

    static int intFromByteArray(byte[] byteArray) {
        if (byteArray == null || byteArray.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array into an int");
        }
        int result = byteArray[0] & 255;
        for (int i = 1; i < byteArray.length; i++) {
            result = (result << 8) | (byteArray[i] & 255);
        }
        return result;
    }

    static byte[] processBigIntegerEncryptedByteArray(byte[] byteArray, int signum) {
        if (byteArray.length > 4) {
            int initialSize = byteArray.length;
            byte[] encryptedMessageExpectedSizeBytes = new byte[4];
            System.arraycopy(byteArray, initialSize - 4, encryptedMessageExpectedSizeBytes, 0, 4);
            byte[] processedByteArray = new byte[initialSize - 4];
            System.arraycopy(byteArray, 0, processedByteArray, 0, initialSize - 4);
            int expectedSize = intFromByteArray(encryptedMessageExpectedSizeBytes);
            if (expectedSize < 0 || expectedSize > maxSafeSizeInBytes()) {
                throw new EncryptionOperationNotPossibleException();
            }
            if (processedByteArray.length != expectedSize) {
                int sizeDifference = expectedSize - processedByteArray.length;
                byte[] paddedProcessedByteArray = new byte[expectedSize];
                for (int i = 0; i < sizeDifference; i++) {
                    paddedProcessedByteArray[i] = signum >= 0 ? (byte) 0 : (byte) -1;
                }
                int i2 = processedByteArray.length;
                System.arraycopy(processedByteArray, 0, paddedProcessedByteArray, sizeDifference, i2);
                return paddedProcessedByteArray;
            }
            return processedByteArray;
        }
        return (byte[]) byteArray.clone();
    }

    private static int maxSafeSizeInBytes() {
        long max = Runtime.getRuntime().maxMemory();
        long free = Runtime.getRuntime().freeMemory();
        long total = Runtime.getRuntime().totalMemory();
        return (int) ((free + (max - total)) / 2);
    }

    private NumberUtils() {
    }
}
