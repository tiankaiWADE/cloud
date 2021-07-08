package com.bazl.dna.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * DES加密算法工具类
 *
 * @author zhaoyong
 *
 */
public class DESUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(DESUtils.class);

	/**
	 * 密钥
	 */
	private static final String KEY_STR = "tuspark";

	/**
	 * 加密类型
	 */
	private static final String ALGORITHM = "AES/GCM/NoPadding";
	private static final Map<String, Key> KEY_MAP = Maps.newHashMap();

	private DESUtils() {

	}

	public static SecretKeySpec getKey(String strKey) {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			generator.init(128, secureRandom);
			SecretKey secretKey = generator.generateKey();
			return new SecretKeySpec(secretKey.getEncoded(), "AES");
		} catch (Exception e) {
			LOGGER.error("初始化密钥出现异常 ", e);
		}
		return null;
	}

	/**
	 * 对str进行DES加密
	 *
	 * @param str 字符串
	 * @return String
	 */
	public static String getEncryptString(String str) {
		try {
			byte[] iv = new byte[12];
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.nextBytes(iv);
			byte[] contentBytes = str.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			GCMParameterSpec params = new GCMParameterSpec(128, iv);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(KEY_STR), params);
			byte[] encryptData = cipher.doFinal(contentBytes);
			assert encryptData.length == contentBytes.length + 16;
			byte[] message = new byte[12 + contentBytes.length + 16];
			System.arraycopy(iv, 0, message, 0, 12);
			System.arraycopy(encryptData, 0, message, 12, encryptData.length);
			return Base64.getEncoder().encodeToString(message);
		} catch (Exception e) {
			LOGGER.error("getEncryptString error:", e);
		}
		return null;
	}

	/**
	 * 对str进行DES解密
	 *
	 * @param str 字符串
	 * @return String
	 */
	public static String getDecryptString(String str) {
		try {
			byte[] content = Base64.getDecoder().decode(str);
			if (content.length < 12 + 16) {
				throw new IllegalArgumentException();
			}
			GCMParameterSpec params = new GCMParameterSpec(128, content, 0, 12);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(KEY_STR), params);
			byte[] decryptData = cipher.doFinal(content, 12, content.length - 12);
			return new String(decryptData, StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOGGER.error("getDecryptString error:", e);
		}
		return null;
	}

	public static Key getSecretKey(String saltKey) {
		Key secretKey;
		if (KEY_MAP.get(saltKey) != null) {
			secretKey = KEY_MAP.get(saltKey);
		} else {
			secretKey = getKey(saltKey);
		}
		return secretKey;
	}

	/**
	 * 对str进行DES加密
	 *
	 * @param str 字符串
	 * @param key 键
	 * @return String
	 */
	public static String getEncryptString(String str, String key) {
		try {
			byte[] iv = new byte[12];
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.nextBytes(iv);
			byte[] contentBytes = str.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			GCMParameterSpec params = new GCMParameterSpec(128, iv);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key), params);
			byte[] encryptData = cipher.doFinal(contentBytes);
			assert encryptData.length == contentBytes.length + 16;
			byte[] message = new byte[12 + contentBytes.length + 16];
			System.arraycopy(iv, 0, message, 0, 12);
			System.arraycopy(encryptData, 0, message, 12, encryptData.length);
			return Base64.getEncoder().encodeToString(message);
		} catch (Exception e) {
			LOGGER.error("getEncryptString error:", e);
		}
		return null;
	}

	/**
	 * 对str进行DES解密
	 *
	 * @param str 字符串
	 * @param key 键
	 * @return String
	 */
	public static String getDecryptString(String str, String key) {
		try {
			byte[] content = Base64.getDecoder().decode(str);
			if (content.length < 12 + 16) {
				throw new IllegalArgumentException();
			}
			GCMParameterSpec params = new GCMParameterSpec(128, content, 0, 12);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key), params);
			byte[] decryptData = cipher.doFinal(content, 12, content.length - 12);
			return new String(decryptData, StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOGGER.error("getDecryptString error:", e);
		}
		return null;
	}

}
