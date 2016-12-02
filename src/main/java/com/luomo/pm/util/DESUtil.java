package com.luomo.pm.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by LuoMo on 2016-12-02.
 */
public class DESUtil {

    /**
     * 生成秘钥
     * @return
     * @throws Exception
     */
    public static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        //加密方式/工作模式/填充方式
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES/ECB/PKCS5Padding");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * DES加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key,"DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] cipherByte = cipher.doFinal(data);
        return cipherByte;
    }

    /**
     * DES解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrype(byte[] data,byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key,"DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        byte[] plainByte = cipher.doFinal(data);
        return plainByte;
    };

    public static void main(String[] args) throws Exception {
        String data = "jikexueyuan";
        byte[] desKey = initKey();
        byte[] result = encrypt(data.getBytes(),desKey);
        Hex hex = new Hex();
        System.out.println(hex.encodeHex(result));

        byte[] desPlain = decrype(result,desKey);
        System.out.println(new String(desPlain));
    }

}
