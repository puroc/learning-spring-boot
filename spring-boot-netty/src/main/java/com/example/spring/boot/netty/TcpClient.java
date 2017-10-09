/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.example.spring.boot.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public final class TcpClient {

    static final String HOST = "127.0.0.1";
    static final int PORT = 8007;
    static final int SIZE = 256;
    static final int NUM = 5;

    public static void main(String[] args) throws Exception {
        final TcpClient tcpClient = new TcpClient();
        for (int i = 0; i < NUM; i++) {
            tcpClient.connectServer();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//
//                    } catch (Throwable e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
        }
    }

    private void connectServer() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                    Bootstrap b = new Bootstrap();
                    b.group(group)
                            .channel(NioSocketChannel.class)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline p = ch.pipeline();
                                    //p.addLast(new LoggingHandler(LogLevel.INFO));
                                    p.addLast(new LineBasedFrameDecoder(1024));
                                    p.addLast(new StringDecoder());
                                    p.addLast(new TcpClientHandler());
                                }
                            });

                    // Start the client.
                    ChannelFuture f = b.connect(HOST, PORT);

                    // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
                } finally {
                    // Shut down the event loop to terminate all threads.
//            group.shutdownGracefully();
                }
            }
        }
        ).start();

    }
}
