package com.johnny.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 相关线程类
 * @author Jian
 */
public class UDPServerThread extends Thread{
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    byte[] data = null;

    public UDPServerThread(byte[] data,DatagramSocket socket,DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;interrupt();
        this.data = data;
    }
    //线程要执行的方法
    public void run(){
        System.out.println("线程启动");
        String info = new String(data);
        System.out.println("收到设备信息："+info);
        InetAddress address = packet.getAddress();
        int port = packet.getPort();

        String msg = "202054585041524b01000000ffffffff00007010305A453D0652454144590000";
        int len = msg.length()/2;
        byte[] data2 = new byte[len];
        for (int i = 0; i < len; i++) {
            String n = msg.substring(i*2,i*2+2);
            int num = Integer.parseInt(n,16);
            data2[i] = (byte)num;
        }
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
        //3.响应客戶端
        try {
            socket.send(packet2);
            System.out.println("服务器端数据发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{

        }
    }
}