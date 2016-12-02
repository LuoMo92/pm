package com.luomo.pm.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by LuoMo on 2016-12-02.
 */
public class TripleDESUtil {

    /**
     * 生成秘钥
     * @return
     * @throws Exception
     */
    public static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 3DES加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key,"DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return  cipher.doFinal(data);
    }

    /**
     * 3DES解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key,"DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return  cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String data = "jikexueyuan";
        byte[] key = initKey();
        byte[] encrypt = encrypt(data.getBytes(),key);
        System.out.println(new Hex().encodeHex(encrypt));
        byte[] decrypt = decrypt(encrypt,key);
        System.out.println(new String(decrypt));
    }
}
