import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.aead.AeadKey;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class FernetEncryption {

    // Function to generate a key for encryption and decryption
    public static byte[] generateKey() throws GeneralSecurityException {
        AeadKeyTemplate template = AeadKeyTemplates.AES256_GCM;
        AeadKey key = template.generateKey();
        return key.getSerializedKeyset().toByteArray();
    }

    // Function to encrypt msg with key using AES-128-CBC with HMAC
    public static byte[] encrypt(byte[] msg, byte[] key) throws GeneralSecurityException {
        Keyset keyset = Keyset.readNoSecret(key);
        Aead aead = keyset.getPrimitive(Aead.class);
        return aead.encrypt(msg, new byte[0]);
    }

    // Function to decrypt msg with key using AES-128-CBC with HMAC
    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws GeneralSecurityException {
        Keyset keyset = Keyset.readNoSecret(key);
        Aead aead = keyset.getPrimitive(Aead.class);
        return aead.decrypt(ciphertext, new byte[0]);
    }

    public static void main(String[] args) throws GeneralSecurityException {
        // Generate a key
        byte[] key = generateKey();

        // Message to encrypt
        String msg = "Hello, World!";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        // Encrypt the message
        byte[] ciphertext = encrypt(msgBytes, key);
        System.out.println("Encrypted: " + java.util.Base64.getEncoder().encodeToString(ciphertext));

        // Decrypt the ciphertext
        byte[] decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + new String(decrypted, StandardCharsets.UTF_8));
    }
}