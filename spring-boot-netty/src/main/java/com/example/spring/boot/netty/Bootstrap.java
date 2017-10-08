package com.example.spring.boot.netty;

import org.apache.camel.main.Main;

/**
 * Created by puroc on 2017/10/7.
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new TcpBuilder());
        main.run();
    }
}
