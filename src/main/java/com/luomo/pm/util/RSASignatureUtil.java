package com.luomo.pm.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuMei on 2016-12-16.
 */
public class RSASignatureUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String RSA_PUBLIC_KEY = "RSA_PUBLIC_KEY";
    public static final String RSA_PRIVATE_KEY = "RSA_PRIVATE_KEY";

    public static Map<String,Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey  = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String,Object> keyMap = new HashMap<String,Object>();
        keyMap.put(RSA_PRIVATE_KEY,privateKey);
        keyMap.put(RSA_PUBLIC_KEY,publicKey);
        return keyMap;
    }
}
