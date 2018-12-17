package com.crm.common;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSAUtil {

	private static Logger log = LoggerFactory.getLogger(RSAUtil.class);

//	private static String publicKeyStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/fQ+z91P1/uYJKaQ2bMtlO+wZYcVfMMsmCEgPEE3YYrkUX6XqJe7xQ2KMlrR5R45f/1wUg1mnKtGLuBHmZVkQwd1LO0aXAkUdYAWitoJW0SMIkvk1bTehggX7fUE0aKUpSCkIykOF0LOn8UEADXgIRF6dKd67t2Lwcpn4CHmsYwIDAQAB";  
	public static String privateKeyStr="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAL99D7P3U/X+5gkppDZsy2U77BlhxV8wyyYISA8QTdhiuRRfpeol7vFDYoyWtHlHjl//XBSDWacq0Yu4EeZlWRDB3Us7RpcCRR1gBaK2glbRIwiS+TVtN6GCBft9QTRopSlIKQjKQ4XQs6fxQQANeAhEXp0p3ru3YvBymfgIeaxjAgMBAAECgYADZqk8vQvUtq5HQ2ITRWNnbOUInl/vDOLOzh7ZhaScT0SSRBCiVAImbtf1P0f9T3QL6HEuPBb/jRcjsCVBPlOSnL5kFjVd6XvGO1RNL6t5NpskczDaJJzal4cuhybQkf8BVW0E2DDpkqYpAJiKyjcrCu69jCfPFOEPDkLuMTsHgQJBAOnxSdN1kQzaTz/VqYNJ+V8bOnUWgT1r2YJFMl5KElmq0HT0nsT0MnaSspXTcA98HFUMRQ97XVBArujVAgre0NECQQDRiw2XAIB+SI77j8AGNYgYOGXLyOYCRVu1d1D3FRrXpgpvR0TOYWlxD3DK0DoNY3PUialH1eab4u4dIFo+b5bzAkEAgxVymow71InW8hvUuf4PPx1Qqh8MF9CAth/z0yWKFrhByebvt8hlJk+YxZ8OIX8XmQghAkXLyZYU692/ITwsoQJBAJ7zroFnXhFtlRj6+J09KlnFHmMxqFj8qA7FzeNAXTZMvAmhtG4hssyS++q9fev+DzWgn4rglaiWwi3SBuLp30UCQQCvN1j7oP47Rg747T2JWSuU6MHREjqSw0N/rA1Y2aWynWVSrDG0lKZvPulmOw+xPo+K9SQAEa/Q6zhS8DCwY5Zh";  

	private static PrivateKey privateKey;

	public static String decrypt(String content) throws BaqiException{
		String out=null;
		//获取私钥   
		try {
			byte[] decryptedBytes=decrypt(Base64.getDecoder().decode(content), getPrivateKey(privateKeyStr));
			out=new String(decryptedBytes);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BaqiException(CustomExceptionEnum.RSA_DECODE_ERROR);
		}        
		return out;
	}

	//将base64编码后的私钥字符串转成PrivateKey实例  
	public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception{  
		if(privateKey==null){
			byte[ ] keyBytes=Base64.getDecoder().decode(privateKeyStr.getBytes());  
			PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);  
			KeyFactory keyFactory=KeyFactory.getInstance("RSA");
			privateKey=keyFactory.generatePrivate(keySpec);
		}
		return privateKey;  
	}

	//私钥解密  
	public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{  
		Cipher cipher=Cipher.getInstance("RSA");  
		cipher.init(Cipher.DECRYPT_MODE, privateKey);  
		return cipher.doFinal(content);  
	}  


}
