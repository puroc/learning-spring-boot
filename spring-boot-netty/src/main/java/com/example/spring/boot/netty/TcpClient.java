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

    private String address;

    private int connNum;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConnNum() {
        return connNum;
    }

    public void setConnNum(int connNum) {
        this.connNum = connNum;
    }

    public void connectServer() {
        for (int i = 0; i < connNum; i++) {
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
                ChannelFuture f = b.connect(address.split(":")[0], Integer.parseInt(address.split(":")[1])).sync();

                // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                // Shut down the event loop to terminate all threads.
//            group.shutdownGracefully();
            }
        }
    }
}
