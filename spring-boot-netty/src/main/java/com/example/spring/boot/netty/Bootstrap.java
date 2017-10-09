package com.example.spring.boot.netty;

/**
 * Created by puroc on 2017/10/7.
 */
public class Bootstrap {


    public static void main(String[] args) throws Exception {
        //client 127.0.0.1:8007 50
        if (args[0].equalsIgnoreCase("client")) {
            TcpClient tcpClient = new TcpClient();
            tcpClient.setAddress(args[1]);
            tcpClient.setConnNum(Integer.parseInt(args[2]));
            tcpClient.connectServer();
        }
        //server 8007
        else if (args[0].equalsIgnoreCase("server")) {
            TcpServer tcpServer = new TcpServer();
            tcpServer.setPort(Integer.parseInt(args[1]));
            tcpServer.bind();
        } else {
            System.out.println("wrong parameter.");
        }
    }
}
