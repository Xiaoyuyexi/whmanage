package com.wlt.webm.rights.bean;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import com.commsoft.epay.util.logging.Log;


/**
 * 身份验证器工具类
 * 
 * @author caiSJ
 * 
 */
public class AuthenticatorServer {

	public static final int SECRET_SIZE = 10;

	public static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

	public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

	int window_size = 3; // default 3 - max 17 可偏移的时间

	/**
	 * 设置偏移量
	 * 
	 * @param s
	 */
	public void setWindowSize(int s) {
		if (s >= 1 && s <= 17)
			window_size = s;
	}

	public static String generateSecretKey() {
		SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
			sr.setSeed(Base64.decodeBase64(SEED));
			byte[] buffer = sr.generateSeed(SECRET_SIZE);
			Base32 codec = new Base32();
			byte[] bEncodedKey = codec.encode(buffer);
			String encodedKey = new String(bEncodedKey);
			return encodedKey;
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String getQRBarcodeURL(String user, String host, String secret) {
		String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
		return String.format(format, user, host, secret);
	}

	public boolean check_code(String secret, long code, long timeMsec) {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		long t = (timeMsec / 1000L) / 30L;
		for (int i = -window_size; i <= window_size; ++i) {
			long hash;
			try {
				hash = verify_code(decodedKey, t + i);
				System.out.println("server code：" + hash);
			} catch (Exception e) {
				Log.error("验证出错:"+e.toString());
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			if (hash == code) {
				return true;
			}
		}
		return false;
	}

	private static int verify_code(byte[] key, long t)
			throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);
		int offset = hash[20 - 1] & 0xF;
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= (hash[offset + i] & 0xFF);
		}
		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;
		return (int) truncatedHash;
	}

	/**
	 * 生成验证秘钥 和二维码
	 * @param username
	 * @param userhost
	 * @return str[0] 秘钥 tr[1] 二维码url  ,null标示异常
	 */
	public static String[] genSecret(String username, String userhost) {
		String secret = generateSecretKey();
		String url = getQRBarcodeURL(username, userhost, secret);
		if(null==secret||null==url){
			return null;
		}
		String[] str={secret,url};
		return str;
	}
	
	/**
	 * @param String 客户端code
	 * @param savedSecret  客户端秘钥
	 * @return true 表示通过
	 */
	public static boolean auth(String code,String savedSecret){
		long t = System.currentTimeMillis();
		AuthenticatorServer a=new AuthenticatorServer();
		a.setWindowSize(5);
		boolean r = a.check_code(savedSecret, Long.parseLong(code), t);
		return r;
	}
	
	/**
	 * 获取随机秘钥
	 * @return
	 */
	public static String[] genSecret() {
		String[] str={ getRandomKey(),"https://www.google.com/chart?"};
		return str;
	}
	
	public static String  getRandomKey(){
		String str="";
		for(int i=0;i<10;i++){
		str+=(char) (Math.random ()*26+'A');
		}
		Random rm=new Random();
		str+=rm.nextInt(8);
		for(int i=0;i<5;i++){
			str+=(char) (Math.random ()*26+'A');
			}
		return str;
	}
	
	public static void main(String[] args) {
//		String[] str=genSecret("18682033616","whtech");
//		System.out.println(str[0]);
//		System.out.println(str[1]);
		System.out.println(auth("639573","HWXSJUKKDG6FAQQN"));
//		3ZCNQU3BHQI3G4QW
//		https://www.google.com/chart?chs=200x200&chld=M%7C0&cht=qr&chl=otpauth://totp/18682033916@whtech%3Fsecret%3D3ZCNQU3BHQI3G4QW

		System.out.println(getRandomKey());
		
	}
	
}
