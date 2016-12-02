package com.luomo.pm.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by LuoMo on 2016-11-29.
 */
public class Base64Util {

    public static String encryptBase64(String data){
        return new BASE64Encoder().encode(data.getBytes());
    }

    public static String decryptBase64(String data) throws IOException {
        byte[] resultBytes = new BASE64Decoder().decodeBuffer(data);
        return new String(resultBytes);
    }
}
