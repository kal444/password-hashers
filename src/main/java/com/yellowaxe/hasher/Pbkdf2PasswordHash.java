package com.yellowaxe.hasher;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;

import static com.yellowaxe.hasher.HexSupport.fromHexString;
import static com.yellowaxe.hasher.HexSupport.toHexString;
import static java.lang.String.format;

/**
 * @author kal
 */
public class Pbkdf2PasswordHash implements PasswordHash {

    private int generatorIterations = 1000;
    private int keyLength = 64 * 8;

    @Override
    public String create(String password) throws Exception {

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] salt = SaltSupport.getSha1PseudoRandomSalt();
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, generatorIterations, keyLength);
        byte[] secretKey = secretKeyFactory.generateSecret(keySpec).getEncoded();

        return format("%s:%s:%s", Integer.toString(generatorIterations), toHexString(salt), toHexString(secretKey));
    }

    @Override
    public boolean validate(String password, String storedHash) throws Exception {

        String[] parts = storedHash.split(":");
        // assert there are 3 parts
        int iterations = Integer.parseInt(parts[0]);
        String salt = parts[1];
        String hash = parts[2];

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), fromHexString(salt), iterations, keyLength);
        byte[] secretKey = secretKeyFactory.generateSecret(keySpec).getEncoded();

        return hash.equals(toHexString(secretKey));
    }

    public void setGeneratorIterations(int generatorIterations) {
        this.generatorIterations = generatorIterations;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

}
