package shopping.car.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Strings;

public class EncodeUtils {

	public static String encodePassword(String password) {
		String passwordSecure = null;
		if (!Strings.isNullOrEmpty(password)) {
			try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(password.getBytes());
	            byte[] bytes = md.digest();

	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }

	            passwordSecure = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            // Algorithm invalid
	        }
		}
		return passwordSecure;
	}
}
