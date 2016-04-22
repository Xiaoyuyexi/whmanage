/**
 * ������oracle10g��oracle11g�м��ܰ�ΪDBMS_CRYPTO��3DES���ܲ���
 * ORACLE����ģʽΪ��ECB+3DES+PKCs5
 * JAVA����ģʽΪ��DESede/ECB/PKCS7Padding
 * �����Ҫ���������Ի��߻�����ʹ�������²���
 * �������Կ����Ϊ����ĳ��ȣ������ܵ��ַ���Ҳ����Ϊ����ĳ���
 * ORACLE����Կ����Ϊ24λ�����Ҳ��ܺ���&�ַ��������ܵ��ַ�������Ϊ����ĳ���
 * 
 * ����Լ�����ܵ���Կ���ȱ���Ϊ24λ
 * �����ܵ��ַ�������Ϊ���ⳤ��
 */
package com.wlt.webm.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author��Administrator
 * @since��2012-8-29 ����04:28:45
 * @version:
 */
public class TripleDESUtil {

	private final String algorithm = "DESede/ECB/PKCS7Padding";

	private static TripleDESUtil instance;

	public static TripleDESUtil getInstance() {
		if (null == instance) {
			synchronized (TripleDESUtil.class) {
				if (null == instance) {
					Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());// ����PKCS7Padding֧��
					instance = new TripleDESUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * ��ȡ��Կ
	 * 
	 * @param keyString
	 *            �ַ���key
	 * @return
	 */
	private Key getKey(String keyString) {
		Key key = new SecretKeySpec(keyString.getBytes(), algorithm);
		return key;
	}

	/**
	 * ��ȡ��Կ
	 * 
	 * @param keyString
	 *            �ֽ�����key
	 * @return
	 */
	private Key getKey(byte[] keyByte) {
		Key key = new SecretKeySpec(keyByte, algorithm);
		return key;
	}

	/**
	 * 3DES����
	 * 
	 * @param sourceString
	 *            ����ǰ��ԭ�ַ���
	 * @param keyString
	 *            3des���ܵ�key
	 * @return String ����֮���16��������
	 * @throws Exception
	 */
	public String encrypt(String sourceString, String keyString) throws Exception {
		Cipher encryptCipher = Cipher.getInstance(algorithm);
		Key key = getKey(keyString);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		return toHexString(encrypt(sourceString.getBytes(), keyString.getBytes()));
	}

	/**
	 * 3DES����
	 * 
	 * @param sourceByte
	 *            ����ǰ��ԭ�ַ����ֽ�����
	 * @param keyByte
	 *            3des���ܵ�key�ֽ�����
	 * @return byte ����֮��������ֽ�����
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] sourceByte, byte[] keyByte) throws Exception {
		Cipher encryptCipher = Cipher.getInstance(algorithm);
		Key key = getKey(keyByte);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		return encryptCipher.doFinal(sourceByte);
	}

	/**
	 * 3DES����
	 * 
	 * @param sourceByte
	 *            ����ܵ��ֽ�����
	 * @param keyByte
	 *            ���ܵ���Կ�ֽ�����
	 * @return byte ���ܺ���ֽ�����
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] sourceByte, byte[] keyByte) throws Exception {
		Cipher decryptCipher = Cipher.getInstance(algorithm);
		Key key = getKey(keyByte);
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		return decryptCipher.doFinal(sourceByte);
	}

	/**
	 * 3DES����
	 * 
	 * @param sourceByte
	 *            ����ܵ������ַ���
	 * @param keyByte
	 *            ���ܵ���Կ
	 * @return byte ���ܺ���ַ���
	 */
	public String decrypt(String sourceString, String keyString) throws Exception {
		return new String(decrypt(fromHexString(sourceString), keyString.getBytes()));
	}

	/**
	 * �ֽ�����ת16��������
	 * 
	 * @param digestByte
	 * @return
	 */
	private byte[] toHex(byte[] digestByte) {
		byte[] rtChar = new byte[digestByte.length * 2];
		for (int i = 0; i < digestByte.length; i++) {
			byte b1 = (byte) (digestByte[i] >> 4 & 0x0f);
			byte b2 = (byte) (digestByte[i] & 0x0f);
			rtChar[i * 2] = (byte) (b1 < 10 ? b1 + 48 : b1 + 55);
			rtChar[i * 2 + 1] = (byte) (b2 < 10 ? b2 + 48 : b2 + 55);
		}
		return rtChar;
	}

	/**
	 * 
	 * �ֽ�����ת16�����ַ���
	 * 
	 * @param digestByte
	 * @return
	 */
	private String toHexString(byte[] digestByte) {
		return new String(toHex(digestByte));
	}

	private static byte[] fromHex(byte[] sc) {
		byte[] res = new byte[sc.length / 2];
		for (int i = 0; i < sc.length; i++) {
			byte c1 = (byte) (sc[i] - 48 < 17 ? sc[i] - 48 : sc[i] - 55);
			i++;
			byte c2 = (byte) (sc[i] - 48 < 17 ? sc[i] - 48 : sc[i] - 55);
			res[i / 2] = (byte) (c1 * 16 + c2);
		}
		return res;
	}

	private static byte[] fromHexString(String hex) {
		return fromHex(hex.getBytes());
	}

	public static void main(String[] args) throws Exception {
		TripleDESUtil pk7 = TripleDESUtil.getInstance();
		String key = "4585A4C1A7DF496D";
		String string = "88888888";
		String miwenString = pk7.encrypt(string, key);
		System.out.println("����֮������:" + miwenString);

		String mingwenString = pk7.decrypt(miwenString, key);
		System.out.println("����֮������:" + mingwenString);
	}
}