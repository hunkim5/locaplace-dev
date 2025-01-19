package com.locaplace.api.common.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.locaplace.api.common.enums.ErrorCode;
import com.locaplace.api.common.response.LocaException;

@Component
public class StringCryptoConverter {

    //암호화키(32byte)
    private static final String KEY = "암호화키(32byte)";
    //초기화 벡터(16byte)
    private static final String IV = "초기화 벡터(16byte)";

    public String processData(String mode, String data) {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        return switch (mode) {
            case "ENCRYPT" -> dataEncrypt(data, keySpec, ivParamSpec);
            case "DECRYPT" -> dataDecrypt(data, keySpec, ivParamSpec);
            default -> throw new IllegalArgumentException("Invalid crypto mode");
        };
    }

    public String dataEncrypt(String plainData, SecretKeySpec keySpec, IvParameterSpec ivParamSpec) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new LocaException(ErrorCode.NO_SUCH_ALGORITHM);
        } catch (NoSuchPaddingException e) {
            throw new LocaException(ErrorCode.NO_SUCH_PADDING);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
        } catch (InvalidKeyException e) {
            throw new LocaException(ErrorCode.INVALID_KEY);
        } catch (InvalidAlgorithmParameterException e) {
            throw new LocaException(ErrorCode.INVALID_ALGORITHM_PARAMETER);
        }
        byte[] encrypted;
        try {
            encrypted = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
        } catch (IllegalBlockSizeException e) {
            throw new LocaException(ErrorCode.ILLEGAL_BLOCK_SIZE);
        } catch (BadPaddingException e) {
            throw new LocaException(ErrorCode.BAD_PADDING);
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String dataDecrypt(String encryptedData, SecretKeySpec keySpec, IvParameterSpec ivParamSpec) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new LocaException(ErrorCode.NO_SUCH_ALGORITHM);
        } catch (NoSuchPaddingException e) {
            throw new LocaException(ErrorCode.NO_SUCH_PADDING);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        } catch (InvalidKeyException e) {
            throw new LocaException(ErrorCode.INVALID_KEY);
        } catch (InvalidAlgorithmParameterException e) {
            throw new LocaException(ErrorCode.INVALID_ALGORITHM_PARAMETER);
        }
        byte[] decrypted;
        try {
            decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        } catch (IllegalBlockSizeException e) {
            throw new LocaException(ErrorCode.ILLEGAL_BLOCK_SIZE);
        } catch (BadPaddingException e) {
            throw new LocaException(ErrorCode.BAD_PADDING);
        }
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
