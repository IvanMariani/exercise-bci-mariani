package cl.com.bci.mariani.util;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Converter
public class EncryptedStringConverter implements AttributeConverter<String, String> {

    @Value("${security.secret.password.key:1234567890123456}")
    String SECRET_KEY = "1234567890123456"; // 16 bytes AES key
    private static final String ALGO = "AES";

    private Cipher getCipher(int mode) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(mode, key);
        return c;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        try {
            Cipher c = getCipher(Cipher.ENCRYPT_MODE);
            byte[] encrypted = c.doFinal(attribute.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            Cipher c = getCipher(Cipher.DECRYPT_MODE);
            byte[] decrypted = c.doFinal(Base64.getDecoder().decode(dbData));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting", e);
        }
    }
}