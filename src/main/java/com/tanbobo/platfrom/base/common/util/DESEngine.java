package com.tanbobo.platfrom.base.common.util;

import java.security.*;
import java.util.HashMap;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * des加密
 */
public class DESEngine {
    private static HashMap encryptCipherMap = new HashMap();
    private static HashMap decryptCipherMap = new HashMap();
    private static String secretKey = "BNMIUYTRDFUIOL<+_(*&^%$com.ywwl.daichong!@#$%^&*()QWERTYUIOPASDFGHJKLZXCVBNM<>?!QAZ@WSX";


    private static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];

            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }

            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }


    private static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    private static DESEngine single = new DESEngine();

    private DESEngine() {
    }

    public static DESEngine getInstance() {
        return single;
    }

    private static void init(String strKey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());
        if (encryptCipherMap.get(strKey) == null) {
            Cipher encryptCipher = Cipher.getInstance("DES/ECB/NoPadding", "SunJCE");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            encryptCipherMap.put(strKey, encryptCipher);
        }

        if (decryptCipherMap.get(key) == null) {
            Cipher decryptCipher = Cipher.getInstance("DES/ECB/NoPadding", "SunJCE");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            decryptCipherMap.put(strKey, decryptCipher);
        }
    }

    private static byte[] encrypt(byte[] arrB, String key) throws Exception {
        return ((Cipher) encryptCipherMap.get(key)).doFinal(arrB);
    }


    private static byte[] decrypt(byte[] arrB, String key) throws Exception {
        return ((Cipher) decryptCipherMap.get(key)).doFinal(arrB);
    }


    private static Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        Key key = new SecretKeySpec(arrB, "DES");

        return key;
    }

    public static String encrypt(String strIn) throws Exception {
        init(secretKey);
        byte[] inb = strIn.getBytes();
        int length = inb.length;
        int mod = length % 8;
        if (mod != 0) {
            mod = 8 - mod;
        }
        byte[] newin = new byte[length + mod];
        for (int u = 0; u < length; u++) {
            newin[u] = inb[u];
        }
        //给存储明文的字节数组a的不足位补0
        for (int u = length; u < newin.length; u++) {
            newin[u] = 0;
        }


        return byteArr2HexStr(encrypt(newin, secretKey));
    }

    public static String decrypt(String strIn) throws Exception {
        init(secretKey);
        return new String(decrypt(hexStr2ByteArr(strIn), secretKey));
    }

    public static void main(String[] args) {
        String value = "tanbobo123";
        System.out.println("加密前：" + value);
        try {
            String e = encrypt(value);
            System.out.println("加密后：" + e);
            String d = decrypt(e);
            System.out.println("解密后：" + d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
