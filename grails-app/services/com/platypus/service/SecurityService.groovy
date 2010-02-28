package com.platypus.service

import sun.misc.BASE64Encoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SecurityService {

    boolean transactional = false

    def sign(def stringToSign, def secret) {
		def stringToSignBase64 = (new BASE64Encoder()).encode(
		    stringToSign.getBytes("UTF-8")).replaceAll("\n","").replaceAll("\r","");
		
		Mac hmac = Mac.getInstance("HmacSHA1");
		hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA1"));
		
		def signature = (new BASE64Encoder()).encode(hmac.doFinal(stringToSignBase64.getBytes("UTF-8"))).replaceAll("\n", "");
		
		return [signed : stringToSignBase64, signature : signature]
    }
}
