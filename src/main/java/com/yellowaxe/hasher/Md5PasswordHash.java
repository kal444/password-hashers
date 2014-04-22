package com.yellowaxe.hasher;

import java.security.MessageDigest;

import static com.yellowaxe.hasher.HexSupport.fromHexString;
import static com.yellowaxe.hasher.HexSupport.toHexString;
import static java.lang.String.format;

/**
 * @author kal
 */
public class Md5PasswordHash implements PasswordHash {
    @Override
    public String create(String password) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] salt = SaltSupport.getSha1PseudoRandomSalt();
        md.update(password.getBytes());
        md.update(salt);
        return format("%s:%s", toHexString(salt), toHexString(md.digest()));
    }

    @Override
    public boolean validate(String password, String storedHash) throws Exception {

        String[] parts = storedHash.split(":");
        // assert there are 2 parts
        String salt = parts[0];
        String hash = parts[1];

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        md.update(fromHexString(salt));

        return hash.equals(toHexString(md.digest()));
    }
}
