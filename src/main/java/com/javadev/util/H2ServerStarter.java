package com.javadev.util;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2ServerStarter {



        public static void main(String[] args) throws Exception {

            // Create the database if it doesn't exist
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:file:D:/db/test",
                    "sa",
                    ""
            );

            connection.close();

            // Start TCP Server
            Server tcpServer = Server.createTcpServer(
                    "-tcp",
                    "-tcpPort", "9092"

            ).start();

            System.out.println("H2 TCP Server started at: " + tcpServer.getURL());

            // Start Web Console (optional)
            Server webServer = Server.createWebServer(
                    "-web",
                    "-webPort", "8082"
            ).start();

            System.out.println("H2 Web Console at: " + webServer.getURL());

            System.out.println("Press Enter to stop...");
            System.in.read();

            tcpServer.stop();
            webServer.stop();
        }
    }

