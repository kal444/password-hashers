package com.yellowaxe.hasher;

import java.math.BigInteger;

/**
 * @author kal
 */
public class HexSupport {
    public static String toHexString(byte[] bytes) {
        final String hex = new BigInteger(1, bytes).toString(16);
        int paddingLength = bytes.length * 2 - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public static byte[] fromHexString(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + 2), 16);
        return bytes;
    }
}
