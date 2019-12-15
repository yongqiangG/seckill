package com.johnny.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerThread extends Thread {
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] data;

    public UDPServerThread(DatagramSocket socket,DatagramPacket packet,byte[] data){
        this.socket = socket;
        this.packet = packet;
        this.data = data;
    }

    @Override
    public void run() {
        //接受客户端数据
        System.out.println("启动线程:");
        String info = new String(data);
        System.out.println("接收到客户端数据:"+info);
        //响应客户端数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] responseData = new String("hi!").getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseData,responseData.length,address,port);
        try {
            socket.send(responsePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
