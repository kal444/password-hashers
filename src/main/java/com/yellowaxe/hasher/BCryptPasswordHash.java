package com.yellowaxe.hasher;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author kal
 */
public class BCryptPasswordHash implements PasswordHash {
    @Override
    public String create(String password) throws Exception {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean validate(String password, String storedHash) throws Exception {
        return BCrypt.checkpw(password, storedHash);
    }
}
