import java.nio.charset.StandardCharsets;

/*  <--  any Fernet implementation that exposes an API similar to the Python
         cryptography.fernet.Fernet class will work here.                    */
import com.github.fernetjava.Fernet;     // adjust to the actual package name

public final class Main {

    /* Encrypt ----------------------------------------------------------------- */
    public static byte[] encrypt(final byte[] input, final String base64Key) {
        Fernet f = new Fernet(base64Key);
        return f.encrypt(input);
    }

    /* Decrypt ----------------------------------------------------------------- */
    public static byte[] decrypt(final byte[] input, final String base64Key) {
        Fernet f = new Fernet(base64Key);
        return f.decrypt(input);
    }

    /* Driver ------------------------------------------------------------------ */
    public static void main(final String[] args) {

        /* generate a fresh, URL-safe, base-64 Fernet key (identical to Python) */
        String key = Fernet.generateKey();

        byte[] plainText = "super secret".getBytes(StandardCharsets.UTF_8);

        byte[] cipherText   = encrypt(plainText, key);
        byte[] decrypted    = decrypt(cipherText, key);

        System.out.println(new String(plainText,  StandardCharsets.UTF_8));
        System.out.println(new String(decrypted,  StandardCharsets.UTF_8));
    }
}