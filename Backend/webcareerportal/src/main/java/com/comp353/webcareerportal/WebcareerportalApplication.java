package com.comp353.webcareerportal;

import com.comp353.webcareerportal.configuration.SSHConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class WebcareerportalApplication {

    SSHConnection sshConnection;

    public static void main(String[] args) throws SQLException {

        SSHConnection sshConnection = new SSHConnection();
        SpringApplication.run(WebcareerportalApplication.class, args);

    }


}


