	import java.security.Key;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import javax.crypto.Cipher;
	import javax.crypto.spec.*;
	import java.util.Base64;

public class AESTutorlial {

	private static final String ALGORITHM ="AES";
	private byte[] keyValue;
	
	public AESTutorlial(String key) {
		keyValue = key.getBytes();
	}
	public String encrypt (String Data)throws Exception {
		
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(Data.getBytes());
		String encryptedValue = new String(Base64.getEncoder().encode(encValue));
		return encryptedValue;
	}
	public String decrypt (String encryptedData)throws Exception {
		
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);

		byte []decodededValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decodededValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	private Key generateKey() throws Exception{
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}
	public static void main(String[] args) {
		try {
			//as a key you shold provide 16, 32 or 64 Bit String. 
			AESTutorlial aes = new AESTutorlial("mykeyforthisexam");
			String encdata = aes.encrypt("ctok");
			System.out.println("Encrpted data: "+encdata);
			String decdata = aes.decrypt(encdata);
			System.out.println("Decrypted: "+decdata);
		}catch(Exception e) {
			Logger.getLogger(AESTutorlial.class.getName()).log(Level.SEVERE,null,e);
		}

	}

}
