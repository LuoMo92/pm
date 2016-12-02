package com.luomo.pm.email;

import com.luomo.pm.util.Base64Util;

import java.io.*;
import java.net.Socket;

/**
 * Created by LuoMo on 2016-11-29.
 */
public class SocketEmail {

    public static void main(String[] args) {
        String sender = "18521039732@163.com";
        String receiver = "810754420@qq.com";
        String password = "just987456";

        String userBase64 = Base64Util.encryptBase64(sender.substring(0, sender.indexOf("@")));
        String passwordBase64 = Base64Util.encryptBase64(password);
        System.out.println(userBase64);

        try {
            Socket socket = new Socket("smtp.163.com", 25);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            System.out.println(bufferedReader.readLine());
            printWriter.println("HELO luomo");
            System.out.println(bufferedReader.readLine());
            printWriter.println("auth login");
            System.out.println(bufferedReader.readLine());
            printWriter.println(userBase64);
            System.out.println(bufferedReader.readLine());
            printWriter.println(passwordBase64);
            System.out.println(bufferedReader.readLine());
            printWriter.println("MAIL FROM:<" + sender + ">");
            System.out.println(bufferedReader.readLine());
            printWriter.println("RCPT TO:<" + sender + ">");
            System.out.println(bufferedReader.readLine());
            printWriter.println("DATA");
            System.out.println(bufferedReader.readLine());
            printWriter.println("SUBJECT:你好啊");
            printWriter.println("FROM:" + sender);
            printWriter.println("TO:" + receiver);
            printWriter.println("Content-Type:text/plain;charset=\"utf-8\"");
            printWriter.println();
            printWriter.println("哈哈,还好吗?");
            printWriter.println(".");
            printWriter.println("");
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
