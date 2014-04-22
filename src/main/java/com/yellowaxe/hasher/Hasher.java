package com.yellowaxe.hasher;

/**
 * @author kal
 */
public class Hasher {

    private static final String realPassword = "N0tVeryG00d";
    private static final String fakePassword = "NotVeryG00d";

    public static void main(String[] args) throws Exception {

        exerciseHashAlgorithm(new Md5PasswordHash());
        exerciseHashAlgorithm(new Sha256PasswordHash());
        exerciseHashAlgorithm(new Pbkdf2PasswordHash());
        exerciseHashAlgorithm(new BCryptPasswordHash());
        exerciseHashAlgorithm(new SCryptPasswordHash());
    }

    private static void exerciseHashAlgorithm(PasswordHash passwordHash) throws Exception {

        String hash = passwordHash.create(realPassword);
        System.out.println(hash);
        System.out.println("real password verified? " + passwordHash.validate(realPassword, hash));
        System.out.println("fake password verified? " + passwordHash.validate(fakePassword, hash));
    }
}
