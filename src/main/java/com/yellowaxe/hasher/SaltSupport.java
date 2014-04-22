package com.yellowaxe.hasher;

import java.security.SecureRandom;

/**
 * @author kal
 */
public class SaltSupport {
    public static byte[] getSha1PseudoRandomSalt() throws Exception {
        SecureRandom randomGenerator = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        randomGenerator.nextBytes(salt);
        return salt;
    }
}
