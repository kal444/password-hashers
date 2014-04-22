package com.yellowaxe.hasher;

import com.lambdaworks.crypto.SCryptUtil;

/**
 * @author kal
 */
public class SCryptPasswordHash implements PasswordHash {
    @Override
    public String create(String password) throws Exception {
        return SCryptUtil.scrypt(password, 16384, 8, 1);
    }

    @Override
    public boolean validate(String password, String storedHash) throws Exception {
        return SCryptUtil.check(password, storedHash);
    }
}
