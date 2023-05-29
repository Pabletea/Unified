


package com.example.unified;


import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionUtil {
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS7Padding";
    private static final String AES_IV = "abcdefghijklmnop"; // El vector de inicialización debe tener 16 caracteres

    public static String encrypt(String key, String plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), AES_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());

        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    public static String decrypt(String key, String cipherText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), AES_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());

        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encryptedBytes = Base64.decode(cipherText, Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }
}





