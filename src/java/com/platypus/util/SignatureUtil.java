package com.platypus.util;

import java.security.MessageDigest;
import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureUtil {
	private static final String MD5_ALGORITHM = "MD5";
	
	public static String generateHMACMD5(String key, String data) throws SignatureException {
		String result;
		
		try {
			Mac mac = Mac.getInstance(MD5_ALGORITHM);
		
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), MD5_ALGORITHM);
		mac.init(signingKey);
		
		byte[] raw = mac.doFinal(data.getBytes());
		
		result = Base64.encodeBytes(raw);
		} catch (Exception e) {
			throw new SignatureException("Could not generate MD5 Signature");
		}
		return result;
	}
	
	public static String hashMD5(String data) {
		byte[] md5bytes;
		try {
			MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
			md.reset();
			md.update(data.getBytes("iso-8859-1"), 0, data.length());
			md5bytes = md.digest();
		} catch (Exception e) {
			throw new RuntimeException("can't get ahold of an md5");
		}
		return convertToHex(md5bytes);
	}
	
	public static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
        	int halfbyte = (data[i] >>> 4) & 0x0F;
        	int two_halfs = 0;
        	do {
	        	if ((0 <= halfbyte) && (halfbyte <= 9))
	                buf.append((char) ('0' + halfbyte));
	            else
	            	buf.append((char) ('a' + (halfbyte - 10)));
	        	halfbyte = data[i] & 0x0F;
        	} while(two_halfs++ < 1);
        }
        return buf.toString();
	}
}
