/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AESPASS;

/**
 *
 * @author HP
 */
import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



/**
 *
 * @author HP
 */
public class AESPass {
    private static final String ALGO = "AES";
    private byte[] keyValue;
    
//    Mo key tai class
    public AESPass(String key) {
        keyValue = key.getBytes();
    }
    
//    Hàm mã hóa dữ liệu (encrypData)
    
    public String Encryp (String PassWord) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(cipher.ENCRYPT_MODE, key);
        byte[] encValue = cipher.doFinal(PassWord.getBytes());
        return Base64.getEncoder().encodeToString(encValue);
    }
    
//    Hàm giải mã dữ liệu
    
    public String Decryp (String decrypPass) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(decrypPass)));
    }

    private Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    
}
