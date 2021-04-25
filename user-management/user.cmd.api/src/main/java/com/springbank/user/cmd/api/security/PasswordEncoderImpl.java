package com.springbank.user.cmd.api.security;

import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder(12);
        var hashedPassword = encoder.encode(password);

        return hashedPassword;
    }
}
