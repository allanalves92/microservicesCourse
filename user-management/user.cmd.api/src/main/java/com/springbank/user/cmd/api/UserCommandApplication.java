package com.springbank.user.cmd.api;

import com.springbank.user.core.configuration.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }

}
