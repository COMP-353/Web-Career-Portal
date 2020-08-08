package com.comp353.webcareerportal.configuration;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;

public class SSHConnection {

    int lport = 3306;
    String rhost = "gyc353.encs.concordia.ca";
    String host = "login.encs.concordia.ca";
    int rport = 3306;
    String user = "";//SSH USER
    String password = "";// SSH PASSWORD
    String dbuserName = "gyc353_1";
    String dbpassword = "pa55w0rd";
    String url = "jdbc:mysql://localhost:" + lport + "/gyc353_1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    Connection conn = null;
    Session session = null;

    public SSHConnection() {
        try {
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
            System.out.println("Port Forwarded");

            //mysql database connectivity
            conn = DriverManager.getConnection(url, dbuserName, dbpassword);
            System.out.println("Database connection established");
            System.out.println("DONE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    @PreDestroy
    public void closeSSHConnection() {
        if (conn != null && !conn.isClosed()) {
            System.out.println("Closing Database Connection");
            conn.close();
        }
        if (session != null && session.isConnected()) {
            System.out.println("Closing SSH Connection");
            session.disconnect();
        }
    }


}
