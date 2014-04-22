package com.yellowaxe.hasher;

/**
 * Easier to use facade around hashing and validate a password.
 * Salt creation is assumed as part of the process and salt is return as part of the hash
 *
 * @author kal
 */
public interface PasswordHash {

    /**
     * Takes a plaintext password and return the hashed result.
     * Very likely this hashed result has a salt component.
     *
     * @param password plaintext password
     *
     * @return hashed result
     *
     * @throws Exception
     */
    String create(String password) throws Exception;

    /**
     * Takes a plaintext password and validate it against the stored hash.
     * The stored hash needs contain the salt in the right format depending on the algorithm used.
     *
     * @param password   plaintext password - unvalidated
     * @param storedHash stored hash value
     *
     * @return true if password can be hashed to the storedHash
     *
     * @throws Exception
     */
    boolean validate(String password, String storedHash) throws Exception;
}
