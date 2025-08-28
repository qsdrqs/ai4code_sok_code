import java.nio.charset.StandardCharsets;

/*  Whatever Fernet implementation you are using will be supplied on the class-path.
    The class below assumes a constructor  Fernet(byte[] key)
    and the static method  generateKey()  that behaves exactly like Python’s
    cryptography.fernet.Fernet.generate_key().
 */
import your.dep.pkg.Fernet;      // ← adjust to the real package name

public class FernetExample {

    /** Encrypt the supplied clear-text message with the supplied key. */
    public static byte[] encrypt(final byte[] msg, final byte[] key) {
        Fernet f = new Fernet(key);
        return f.encrypt(msg);
    }

    /** Decrypt the supplied cipher-text message with the supplied key. */
    public static byte[] decrypt(final byte[] msg, final byte[] key) {
        Fernet f = new Fernet(key);
        return f.decrypt(msg);
    }

    public static void main(String[] args) {
        // Generate a brand-new key (base-64 encoded, exactly like Python’s Fernet)
        byte[] key = Fernet.generateKey();

        byte[] msg = "a much longer message with punctuation!"
                     .getBytes(StandardCharsets.UTF_8);

        byte[] encrypted = encrypt(msg, key);
        byte[] decrypted = decrypt(encrypted, key);

        // The encrypted token is ASCII safe, so we can print it directly.
        System.out.println(new String(encrypted, StandardCharsets.UTF_8));
        // The decrypted message is UTF-8 text, so convert it back to a String.
        System.out.println(new String(decrypted, StandardCharsets.UTF_8));
    }
}