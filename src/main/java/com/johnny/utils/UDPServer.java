package com.johnny.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务器端类
 */
public class UDPServer {
    //参数:本地监听端口号
    public static void serverStartListen(int port) throws IOException {
        DatagramSocket socket = new DatagramSocket(port);
        byte[] data = new byte[Config.UDP_RECEIVE_LEN];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("-----服务端已启动,等待客户端的数据-----");
        while(true){
            socket.receive(packet);
            UDPServerThread thread = new UDPServerThread(data,socket, packet);
            thread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(3339);
        byte[] data = new byte[Config.UDP_RECEIVE_LEN];
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