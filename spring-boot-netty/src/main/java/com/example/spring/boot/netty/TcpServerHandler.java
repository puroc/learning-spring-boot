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

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


@ChannelHandler.Sharable
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private Timer timer = new Timer();
    private AtomicInteger connNum = new AtomicInteger();
    private AtomicInteger msgNum = new AtomicInteger();


    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("connNum:" + connNum.get()+",msgNum:"+msgNum.get());
            msgNum.set(0);
        }
    }

    public TcpServerHandler() {
        timer.schedule(new MyTimerTask(), 1000, 1000);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        connNum.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        connNum.decrementAndGet();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        msgNum.incrementAndGet();
//        System.out.println("server receive:" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
