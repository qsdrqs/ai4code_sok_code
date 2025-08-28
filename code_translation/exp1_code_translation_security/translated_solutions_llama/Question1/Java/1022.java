import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.AeadSivKeyManager;
import com.google.crypto.tink.aead.PrecomputedAead;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException {
        // Generate a new key
        Keyset keyset = Keyset.generateNew(AeadKeyTemplates.AES256_GCM_TINK);

        // Get the encrypted and decrypted bytes
        String input = "Hello World!";
        byte[] encrypted = encrypt(input, keyset);
        System.out.println("Encrypted: " + java.util.Arrays.toString(encrypted));

        String decrypted = decrypt(encrypted, keyset);
        System.out.println("Decrypted: " + decrypted);
    }

    /**
     * Encrypts the given input using the provided keyset.
     *
     * @param input  the string to be encrypted
     * @param keyset the keyset used for encryption
     * @return the encrypted bytes
     * @throws GeneralSecurityException if an error occurs during encryption
     */
    public static byte[] encrypt(String input, Keyset keyset) throws GeneralSecurityException {
        PrecomputedAead aead = keyset.getPrimitive(PrecomputedAead.class);
        return aead.encrypt(input.getBytes(StandardCharsets.UTF_8), new byte[0]);
    }

    /**
     * Decrypts the given encrypted bytes using the provided keyset.
     *
     * @param encrypted the encrypted bytes
     * @param keyset    the keyset used for decryption
     * @return the decrypted string
     * @throws GeneralSecurityException if an error occurs during decryption
     */
    public static String decrypt(byte[] encrypted, Keyset keyset) throws GeneralSecurityException {
        PrecomputedAead aead = keyset.getPrimitive(PrecomputedAead.class);
        byte[] decrypted = aead.decrypt(encrypted, new byte[0]);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}