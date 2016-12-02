package com.luomo.pm.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LuoMo on 2016-12-02.
 */
public class RSAUtil {

    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 生成秘钥对
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        Map<String,Object> keyMap = new HashMap<String, Object>();
        keyMap.put(PUBLIC_KEY,publicKey);
        keyMap.put(PRIVATE_KEY,privateKey);
        return keyMap;
    }

    /**
     * 从Map中取得公钥
     * @param keyMap
     * @return
     */
    public static RSAPublicKey getPublicKey(Map<String,Object> keyMap){
        RSAPublicKey key = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
        return key;
    }

    /**
     * 从Map中取得私钥
     * @param keyMap
     * @return
     */
    public static RSAPrivateKey getPrivateKey(Map<String,Object> keyMap){
        RSAPrivateKey key = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] bytes = cipher.doFinal(data);
        return bytes;
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] bytes = cipher.doFinal(data);
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        String data = "jikexueyuan";
        Map<String, Object> keyMap = initKey();
        RSAPrivateKey privateKey = getPrivateKey(keyMap);
        RSAPublicKey publicKey = getPublicKey(keyMap);
        byte[] encrypt = encrypt(data.getBytes(), publicKey);
        System.out.println(new Hex().encodeHex(encrypt));
        byte[] decrypt = decrypt(encrypt, privateKey);
        System.out.println(new String(decrypt));
    }
}
