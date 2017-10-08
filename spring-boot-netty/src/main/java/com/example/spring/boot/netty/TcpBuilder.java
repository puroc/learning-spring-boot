package com.example.spring.boot.netty;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by puroc on 2017/10/7.
 */
public class TcpBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("netty4:tcp://0.0.0.0:5021?textline=true").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println(exchange.getIn().getBody());
            }
        });
    }
}
