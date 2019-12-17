package com.johnny.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服務器端，基于UDP的用戶登錄
 * @author Jian
 *
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(3339);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("-----服务端已启动-----");
        //循环监听
        while(true){
            socket.receive(packet);
            UDPServerThread thread = new UDPServerThread(data,socket, packet);
            thread.start();
        }

        //4.關閉資源
        //socket.close();
    }
}