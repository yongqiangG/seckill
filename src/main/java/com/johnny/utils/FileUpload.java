package com.johnny.utils;

import java.io.*;

public class FileUpload{
    private static final int byteSize = 200*1024;//读取字节数
    private static final String firstLinePrefix = ":020000";//第一行标识
    private static final String endLinePrefix = ":04000005";//结束标识
    private static final int firstAddress_10 = 2048;//第一次循环开始的地址为0800,10进制即为2048;
    private static final int dataCharMaxNum = 32;//数据部分最大字符数32,即16字节;
    private static final String compareString ="FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";//数据部分32个F字符

    public static void main(String[] args) {
        File hFile = new File("C:\\Users\\Administrator\\Desktop\\TK3100.hex");
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            if(hFile==null){
                System.out.println("文件为空");
            }
            inputStream = new FileInputStream(hFile);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //当前字符串
            String lineStr = null;
            //当前行数
            int lineNum = 0;
            //当前行地址位
            int lineAddress = 0;
            //每次循环的首地址,第一次是0800,后面都是0000
            int firstAddress = firstAddress_10;
            //当前行的下标
            int offset = 0;
            //地址置零后,保存前面地址的偏移量
            int offsetAdd = 0;
            byte[] bytes = new byte[byteSize];
            while((lineStr = bufferedReader.readLine())!=null){
                lineNum++;
                //判断是否为第一行
                if (lineStr.startsWith(firstLinePrefix)) {
                    if(lineNum!=1){
                        //不是第一行的话,地址位置零,偏移量16字节
                        firstAddress = 0;
                        offsetAdd = offset+dataCharMaxNum/2;
                    }
                    System.out.println("循环,位置偏移: "+offset+" 开始地址: "+firstAddress);
                    continue;
                }
                lineAddress = Integer.parseInt(lineStr.substring(3,7),16);
                //读取数据部分
                String preData = lineStr.substring(9);
                String data = preData.substring(0,preData.length()-2);
                //如果数据部分小于16字节,用F补满
                if(data.length()<dataCharMaxNum){
                    FileUpload.fillDataStr(data);
                }
                offset = lineAddress-firstAddress+offsetAdd;
                //判断结束
                if(lineStr.startsWith(endLinePrefix)){
                    break;
                }


            }





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //使用F补满数据部分的16字节长度
    private static String fillDataStr(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while(sb.length()<dataCharMaxNum){
            sb.append("F");
        }
        return sb.toString();
    }
}