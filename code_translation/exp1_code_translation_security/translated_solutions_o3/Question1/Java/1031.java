import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 * The project that you compile this file in will already contain a Fernet
 * implementation (and every other dependency that may be required), so we can
 * use it directly.
 */
import io.github.novacrypto.fernet.Fernet;   //  ← supplied dependency
import io.github.novacrypto.fernet.Key;      //  ← supplied dependency

public class FernetExample {

    /* Re-use the same Fernet instance, just like the Python code did                */
    private static Fernet fernet;

    /**
     * encrypt using a symmetric key
     */
    private static String encrypt(String message) {
        /* In the Python version “encrypt” returns raw bytes. To keep the console
         * output readable we Base64-encode the result before returning it.          */
        byte[] cipherText = fernet.encrypt(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(cipherText);
    }

    /**
     * decrypt using a symmetric key
     */
    private static String decrypt(String encMessage) {
        byte[] cipherText = Base64.getUrlDecoder().decode(encMessage);
        byte[] plainText  = fernet.decrypt(cipherText);
        return new String(plainText, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {

        String message = "the solutions are here somewhere";
        System.out.println("Secret Message: " + message);

        /* ----------------------------------------------------------------------------
         * Generate a brand-new symmetric key and create a Fernet helper with it,
         * exactly the same way the Python code does with `Fernet.generate_key()`.
         * ------------------------------------------------------------------------- */
        Key key = Key.generateKey();
        fernet = new Fernet(key);

        /* ---------- Encrypt / decrypt and show the results ------------------------ */
        String encMessage = encrypt(message);
        System.out.println(encMessage);

        String decMessage = decrypt(encMessage);
        System.out.println("decrypted Message: " + decMessage);
    }
}