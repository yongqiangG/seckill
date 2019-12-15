package com.johnny.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9001);
        System.out.println("服务端已启动");
        while(true){
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
            socket.receive(packet);
            //每次接收到数据开启一个新的线程
            new UDPServerThread(socket,packet,bytes).start();
        }
    }
}
