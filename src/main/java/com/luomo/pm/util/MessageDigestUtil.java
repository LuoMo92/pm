package com.luomo.pm.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LuoMo on 2016-11-29.
 */
public class MessageDigestUtil {

    public static String encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        byte[] resultBytes = md5.digest();
        Hex hex = new Hex();
        String resultString = new String(hex.encodeHex(resultBytes));//转成16进制
        return resultString;
    }

    public static String getMD5OfFile(String path) throws Exception {
        FileInputStream fis = new FileInputStream(new File(path));
        DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));
        //流输入
        //将fis读入dis中
        byte[] buffer = new byte[1024];
        int read = dis.read(buffer, 0, 1024);
        while (read != -1) {
            read = dis.read(buffer, 0, 1024);
        }
        MessageDigest md = dis.getMessageDigest();
        byte[] resultBytes = md.digest();
        Hex hex = new Hex();
        String resultString = new String(hex.encodeHex(resultBytes));
        return resultString;
    }

    public static String encryptSHA(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data);
        byte[] resultBytes = sha.digest();
        Hex hex = new Hex();
        String resultString = new String(hex.encodeHex(resultBytes));//转成16进制
        return resultString;
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    public static byte[] initHmacKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
        SecretKey sk = kg.generateKey();
        return sk.getEncoded();
    }

    public static String encryptHmac(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey sk = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(sk);
        byte[] resultBytes = mac.doFinal(data);
        Hex hex = new Hex();
        String resultString = new String(hex.encodeHex(resultBytes));//转成16进制
        return resultString;
    }

    public static void main(String[] args) throws Exception {
        String data = "jikexueyuan";
        String result = MessageDigestUtil.encryptMD5(data.getBytes());
        System.out.println(result);

        String resultString = MessageDigestUtil.getMD5OfFile("mysql-installer-web-community-5.6.22.0.msi");
        System.out.println(resultString);

        String shaResult = MessageDigestUtil.encryptSHA(data.getBytes());
        System.out.println(shaResult);

        byte[] hmacKey = MessageDigestUtil.initHmacKey();
        String hmacResult = MessageDigestUtil.encryptHmac(data.getBytes(),hmacKey);
        System.out.println(hmacResult);
    }
}
